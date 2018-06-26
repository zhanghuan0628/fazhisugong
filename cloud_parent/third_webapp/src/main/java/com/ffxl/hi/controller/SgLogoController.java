package com.ffxl.hi.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.cloud.model.SgLogoLogo;
import com.ffxl.cloud.model.SgLogoUserExample;
import com.ffxl.cloud.model.SgLogoVote;
import com.ffxl.cloud.service.SgLogoLogoService;
import com.ffxl.cloud.service.SgLogoUserService;
import com.ffxl.cloud.service.SgLogoVoteService;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.util.JsonResult;
import com.ffxl.platform.util.Message;
import com.ffxl.platform.util.StringUtil;

@Controller
@RequestMapping(value = "/SgLogorController")
public class SgLogoController {
    @Autowired
    private SgLogoLogoService sgLogoLogoService;
    
    @Autowired
    private SgLogoVoteService sgLogoVoteService;
    
    @Autowired
    private SgLogoUserService sgLogoUserService;
    /**
     * 查询所有logo信息
     * @return
     */
    @RequestMapping(value = "/selectAllLogoInfo")
    @ResponseBody
    public JsonResult selectAllLogoInfo(SgLogoLogo model, Page page){
        if(StringUtil.isEmpty(model.getOpenId())){
            return new JsonResult(Message.M4003);
        }
        if (!StringUtil.isEmpty(model.getSerachCode())) {
            String serachCode = model.getSerachCode();
            if (HasDigit(serachCode) && isDigit2(serachCode) == false) {
                String s = splitNotNumber(serachCode);
                if(s.equals("号")){
                    String code = getNumbers(serachCode);
                    model.setSerachMunber(code);
                    model.setSerachCode("");
                }
            }
        }
        List<SgLogoLogo> selectLogoList = sgLogoLogoService.selectLogoByPage(model, page);
        page.setDataList(selectLogoList);
        return new JsonResult("2000", page);
        
    }
    /**
     * 判断一个字符串是否含有数字
     * @param content
     * @return
     */
    public boolean HasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }
    /**
     * 判断一个字符串是否都为数字  
     * @param strNum
     * @return
     */
    public static boolean isDigit2(String strNum) {  
        Pattern pattern = Pattern.compile("[0-9]{1,}");  
        Matcher matcher = pattern.matcher((CharSequence) strNum);  
        return matcher.matches();  
    }
    /**
     * 截取数字  【读取字符串中第一个连续的字符串，不包含后面不连续的数字】
     * @param content
     * @return
     */
    public static String getNumbers(String content) {  
        Pattern pattern = Pattern.compile("\\d+");  
        Matcher matcher = pattern.matcher(content);  
        while (matcher.find()) {  
           return matcher.group(0);  
        }  
        return "";  
    }
    /**
     * 截取非数字  
     * @param content
     * @return
     */
    public static String splitNotNumber(String content) {  
        Pattern pattern = Pattern.compile("\\D+");  
        Matcher matcher = pattern.matcher(content);  
        while (matcher.find()) {  
            return matcher.group(0);  
        }  
        return "";  
    }
    /**
     * 点赞插入
     * @param model
     * @return
     */
    @RequestMapping(value = "/insertLogoVote")
    @ResponseBody
    public JsonResult insertLogoVote(SgLogoVote model){
        
        if(StringUtil.isEmpty(model.getOpenId())||StringUtil.isEmpty(model.getLogoId())){
            return new JsonResult(Message.M4003);
        }
        //验证用户是否真实有效
        SgLogoUserExample example = new SgLogoUserExample();
        com.ffxl.cloud.model.base.BaseSgLogoUserExample.Criteria c= example.createCriteria();
        c.andOpenIdEqualTo(model.getOpenId());
        int count = sgLogoUserService.countByExample(example);
        if(count == 0){
            //验证账号，防止非法刷票
            return new JsonResult(Message.M3001);
        }
        JsonResult js = new JsonResult();
        int i = sgLogoVoteService.insertLogoVote(model);
        if(i > 0){
            return new JsonResult(Message.M2000);
        }
        if(i == -1){
            js.setCode("5005");
            js.setMessage("点赞已达上限");
            return js;//点赞已达上限
        }
        if(i == -2){
            js.setCode("1");
            js.setMessage("已经点赞");
            return js;//已经点赞
        }
        if(i == -3){
            js.setCode("6000");
            js.setMessage("活动时间已结束");
            return js;//活动时间已结束
        }
        return js;
        
    }
    /**
     * 查询logo详情
     * @param model
     * @return
     */
    @RequestMapping(value = "/selectLogoDetail")
    @ResponseBody
    public JsonResult selectLogoDetail(SgLogoLogo model){
        if(StringUtil.isEmpty(model.getOpenId())||StringUtil.isEmpty(model.getId())){
            return new JsonResult(Message.M4003);
        }else{
          //验证用户是否真实有效
            SgLogoUserExample example = new SgLogoUserExample();
            com.ffxl.cloud.model.base.BaseSgLogoUserExample.Criteria c= example.createCriteria();
            c.andOpenIdEqualTo(model.getOpenId());
            int count = sgLogoUserService.countByExample(example);
            if(count == 0){
                //验证账号，防止非法刷票
                return new JsonResult(Message.M3001);
            }
            SgLogoLogo sl = sgLogoLogoService.selectLogoDetail(model);
            if(sl != null ){
                return new JsonResult(Message.M2000,sl);
            }else{
                return new JsonResult(Message.M5000);
            }
            
        }
    }
}
