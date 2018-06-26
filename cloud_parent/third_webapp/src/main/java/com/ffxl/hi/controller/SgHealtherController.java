package com.ffxl.hi.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.cloud.model.SgHealthyAnswerRecord;
import com.ffxl.cloud.model.SgHealthyClock;
import com.ffxl.cloud.model.SgHealthyPraise;
import com.ffxl.cloud.model.SgHealthyQuestion;
import com.ffxl.cloud.model.SgHealthyUser;
import com.ffxl.cloud.service.SgHealthyAnswerRecordService;
import com.ffxl.cloud.service.SgHealthyClockService;
import com.ffxl.cloud.service.SgHealthyPraiseService;
import com.ffxl.cloud.service.SgHealthyQuestionService;
import com.ffxl.cloud.service.SgHealthyUserService;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.util.DateUtil;
import com.ffxl.platform.util.JsonResult;
import com.ffxl.platform.util.Message;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;

@Controller
@RequestMapping(value = "/SgHealtherController")
public class SgHealtherController {
    @Autowired
    private SgHealthyUserService sgHealthyUserService;
    
    @Autowired
    private SgHealthyClockService sgHealthyClockService;
    
    @Autowired
    private SgHealthyAnswerRecordService sgHealthyAnswerRecordService;
    
    @Autowired
    private SgHealthyPraiseService sgHealthyPraiseService;
    
    @Autowired
    private SgHealthyQuestionService sgHealthyQuestionService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat fmt = new SimpleDateFormat(DateUtil.STANDARD_DATE_FORMAT_STR);
        CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }
    /**
     * 判断用户是否注册过
     * @param openId
     * @return
     */
    @RequestMapping(value = "/selectLoginUser")
    @ResponseBody
    public JsonResult selectLoginUser(String openId){
        if(StringUtil.isEmpty(openId)){
            return new JsonResult(Message.M4003);
        }
        SgHealthyUser model = sgHealthyUserService.selectLoginUser(openId);
        int personNum = sgHealthyUserService.countNums();
        if(model != null){
            return new JsonResult("1",personNum);//已注册
        }
        return new JsonResult("0",personNum);//未注册;
        
    }
    /**
     * 个人信息(包括用户一共募集了多少水，用户一共签了几天到,姓名，排名)
     * @return
     */
    @RequestMapping(value = "/selectAllWater")
    @ResponseBody
    public JsonResult selectAllWater(String openId,String beOpenId){
        if(StringUtil.isEmpty(openId)&&StringUtil.isEmpty(beOpenId)){
            return new JsonResult(Message.M4003);
        }else{
            SgHealthyUser model = sgHealthyUserService.queryById(openId,beOpenId);
            SgHealthyUser user = new SgHealthyUser();
            if(!StringUtil.isEmpty(beOpenId)){         
                user = sgHealthyUserService.selectUserRank(beOpenId);
            }else{          
                user = sgHealthyUserService.selectUserRank(openId);
            } 
            String rowNo = "";
            if(user != null){
                rowNo = user.getRowNo();
            }else{
                return new JsonResult(Message.M4008,model); 
            }
            if(model != null){
                String pid = model.getPid();
                int pariseCount = model.getPariseCount();
                if(pariseCount >= 10){
                    model.setIsOverNum("1");//1代表已助力 超过次数  null代表没有超过次数
                }
                if(!StringUtil.isEmpty(pid)){
                    model.setIsPrise("1");//1代表已助力 null 代表没助力
                }
                int clockCount = model.getClockCount();
                model.setRowNo(rowNo);
                model.setClickDay(clockCount+"");
            }else{
                return new JsonResult(Message.M4008,model); 
            }
            return new JsonResult(Message.M2000,model);
        }
        
    }
    /**
     * 判断用户今天是否签到过
     * @param openId
     * @return
     */
    @RequestMapping(value = "/selectClockDate")
    @ResponseBody
    public JsonResult selectClockDate(String openId){
        if(StringUtil.isEmpty(openId)){
            return new JsonResult(Message.M4003);
        }
        boolean isOpenId = sgHealthyClockService.selectClockDate(openId);
        if (isOpenId) {
            return new JsonResult("1");//是否签到 1已签到
        } else {
            return new JsonResult("0");//是否签到 0未签到
        }       
    }
    /**
     * 用户签到（签到之后水量相应增加）
     * @param model
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/insertClickDay")
    @ResponseBody
    public JsonResult insertClickDay(SgHealthyClock model) throws ParseException{
        if(StringUtil.isEmpty(model.getOpenId())){
            return new JsonResult(Message.M4003);
        }
        boolean isOpenId = sgHealthyClockService.selectClockDate(model.getOpenId().toString());//判断用户今天是否签到过
        if(isOpenId){
            List<SgHealthyClock> list = sgHealthyClockService.selectAllClockDateByOpenId(model);//通过用户id查询到此用户的签到信息
            for(SgHealthyClock sc:list){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String timer = sdf.format(sc.getClockDate());
                sc.setcDate(timer);
            }
            JsonResult jsonResult = new JsonResult();
            jsonResult.setCode(Message.M2000);
            jsonResult.setData(list);
            if (isOpenId) {
                jsonResult.setModule("1");//是否签到 1已签到
            } else {
                jsonResult.setModule("0");//是否签到 0未签到
            }
            return jsonResult;
        }else{
            model.setId(UUIDUtil.getUUID());
            int i = sgHealthyClockService.insertClickDay(model);//新增用户签到信息
            List<SgHealthyClock> list = sgHealthyClockService.selectAllClockDateByOpenId(model);//通过用户id查询到此用户的签到信息
            for(SgHealthyClock sc:list){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String timer = sdf.format(sc.getClockDate());
                sc.setcDate(timer);
            }
            if(i > 0){
                return new JsonResult(Message.M2000,list);
            }
            return new JsonResult(Message.M5000,list);
        }  
    }
    /**
     * 查询用户已回答的问题
     * @param openId
     * @return
     */
    @RequestMapping(value = "/selectAnswerQuestion")
    @ResponseBody
    public JsonResult selectAnswerQuestion(String openId){
        if(StringUtil.isEmpty(openId)){
            return new JsonResult(Message.M4003);
        }
        List<SgHealthyAnswerRecord> list = sgHealthyAnswerRecordService.selectAnswerQuestion(openId);        
        return new JsonResult(Message.M2000,list);        
    }
    /**
     * 回答问题或提示
     * @param Map
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value = "/selectQuestionAndTips")
    @ResponseBody
    public JsonResult selectQuestionAndTips(String openId){
        if(StringUtil.isEmpty(openId)){
            return new JsonResult(Message.M4003);
        }else{
            List<SgHealthyQuestion> list = sgHealthyAnswerRecordService.selectQuestionAndTips(openId);
            if(list != null && list.size()>0){
                return new JsonResult(Message.M2000,list);
            }
            return new JsonResult(Message.M5000,"今日题目没有五道");
        }          
    }
    /**
     * 随机查询提示
     * @return
     */
    @RequestMapping(value = "/selectRandomTips")
    @ResponseBody
    public JsonResult selectRandomTips(){
        SgHealthyQuestion tips = sgHealthyQuestionService.selectRandomTips().get(0);
        return new JsonResult(Message.M2000,tips);
        
    }
    /**
     *回答的问题健康值并增加10
     * @param sgHealthyAnswerRecord
     * @return
     */
    @RequestMapping(value = "/updateAnswerQuestion")
    @ResponseBody
    public JsonResult updateAnswerQuestion(String water,String openId){
        water = "10";
        if(StringUtil.isEmpty(openId)){
            return new JsonResult(Message.M4003);
        }else{
            int i = sgHealthyUserService.updateUserWaterMl(water, openId);
            if(i > 0){
                return new JsonResult(Message.M2000);
            }
            return new JsonResult(Message.M5000); 
        }  
    }
    /**
     * 喝水时用户水量增加
     * @param water
     * @param openId
     * @return
     */
    @RequestMapping(value = "/updateUserWaterMl")
    @ResponseBody
    public JsonResult updateUserWaterMl(String water,String openId,String questionNum,String sort){
        if(StringUtil.isEmpty(openId)){
            return new JsonResult(Message.M4003);
        }else{                       
            List<SgHealthyAnswerRecord> list = sgHealthyAnswerRecordService.selectUserAnswerInfo(openId,sort);
            if(list.size() > 0 && list != null){
                return new JsonResult(Message.M5000,"重复录入");
            }
            SgHealthyAnswerRecord sgHealthyAnswerRecord = new SgHealthyAnswerRecord();
            sgHealthyAnswerRecord.setId(UUIDUtil.getUUID());
            sgHealthyAnswerRecord.setOpenId(openId);
            sgHealthyAnswerRecord.setQuestionNum(Integer.parseInt(questionNum));
            sgHealthyAnswerRecord.setAnswerDate(new Date());
            sgHealthyAnswerRecord.setSort(sort);
            sgHealthyAnswerRecordService.insertAnswerQuestion(sgHealthyAnswerRecord);
            int i = sgHealthyUserService.updateUserWaterMl(water, openId);
            if(i > 0){
                return new JsonResult(Message.M2000);
            }
            return new JsonResult(Message.M5000,"水量更新失败");
        }
    }
    /**
     * 用户个人排行榜
     */
    @RequestMapping(value = "/selectUserRank")
    @ResponseBody
    public JsonResult selectUserRank(String openId){
        if(StringUtil.isEmpty(openId)){
            return new JsonResult(Message.M4003);
        }
        SgHealthyUser model = sgHealthyUserService.selectUserRank(openId);
        return new JsonResult(Message.M2000,model);
        
    }
    /**
     * 所有用户排行榜并带搜索条件
     * @param model
     * @param page
     * @param session
     * @return
     */
    @RequestMapping(value = "/selectAllUserRank")
    @ResponseBody
    public JsonResult getOrders(SgHealthyUser model, Page page) {
        List<SgHealthyUser> selectMatchList = sgHealthyUserService.selectByPage(model, page);
        page.setDataList(selectMatchList);
        return new JsonResult("2000", page);
    }
    /**
     * 助力
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value = "/selectPraiseUser")
    @ResponseBody
    public JsonResult selectPraiseUser(String openId,String beOpenId) throws ParseException{
        if(StringUtil.isEmpty(openId) && StringUtil.isEmpty(beOpenId)){
            return new JsonResult(Message.M4003);
        }
        return sgHealthyPraiseService.insertPraiseUser(openId,beOpenId);       
    }
}
