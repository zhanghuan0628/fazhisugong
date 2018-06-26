package com.ffxl.hi.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.cloud.model.SgVideoMatchVideo;
import com.ffxl.cloud.model.SgVideoMatchVote;
import com.ffxl.cloud.service.SgVideoMatchUserService;
import com.ffxl.cloud.service.SgVideoMatchVideoService;
import com.ffxl.cloud.service.SgVideoMatchVoteService;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.util.JsonResult;
import com.ffxl.platform.util.Message;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;

/**
 * 
 * @author ys
 *
 */
@Controller
@RequestMapping(value = "/video_match")
//@Api(value = "/auth", description = "用户微信授权、分享等api")
public class VideoMacthController {
	private static final Logger logger = LoggerFactory.getLogger(VideoMacthController.class);
	
    @Autowired
    private SgVideoMatchUserService sgVideoMatchUserService;
    
    @Autowired
    private SgVideoMatchVideoService sgVideoMatchVideoService;
    
    @Autowired
    private SgVideoMatchVoteService sgVideoMatchVoteService;
    
//    private void ky (HttpServletResponse response) {
////        response.setHeader("Access-Control-Allow-Origin", "http://192.168.0.126:8080");
////        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
////        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
////        response.setContentType("textml;charset=UTF-8");
//        
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
//        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
//
//    }
    /**
     * video列表
     * @param 
     * @return
     * @author ys
     */
    @RequestMapping(value="/list")
    @ResponseBody
    public JsonResult create(SgVideoMatchVideo model, Page page, HttpServletResponse response){
        if (!StringUtil.isEmpty(model.getSerachCode())) {
            if (isStr2Num(model.getSerachCode())) {
                if (model.getSerachCode().length() == 1) {
                    model.setSerachMunber("00" + model.getSerachCode());
                    model.setSerachCode("");
                }
                if (model.getSerachCode().length() == 2) {
                    model.setSerachMunber("0" + model.getSerachCode());
                    model.setSerachCode("");
                }
            }
        }
        model.setOrderBySroce("ballot");//排序方式 ballot：投票多寡排序  num作品编号排序
        int userCount = sgVideoMatchUserService.getUserCount();
        JsonResult js = new JsonResult();
        List<SgVideoMatchVideo> list = sgVideoMatchVideoService.selectMatchList(model, page);
        js.setModule(userCount + "");
        js.setData(list);
        return js;//new JsonResult(Message.M2000, list);
    }
    
    
    /**
     * 详情
     * @param id
     * @param openId
     * @return
     * @author ys
     */
    @RequestMapping(value="/detail")
    @ResponseBody
    public JsonResult detail(SgVideoMatchVideo model, HttpServletResponse response){
//        if (StringUtil.isEmpty(model.getId())) {
//            return new JsonResult(Message.M4003);
//        }
        model.setOrderBySroce("ballot");//排序方式 ballot：投票多寡排序  num作品编号排序
        SgVideoMatchVideo video = sgVideoMatchVideoService.selectMatchList(model, null).get(0);
        return new JsonResult(Message.M2000, video);
    }
    
    /** 
    * 查看一个字符串是否可以转换为数字 
    * @param str 字符串 
    * @return true 可以; false 不可以 
    */  
    public static boolean isStr2Num(String str) {   
        try {  
            Integer.parseInt(str);  
            return true;  
        } catch (NumberFormatException e) {  
            return false;  
        }  
    }  

    /**
     * 投票
     * @param id
     * @param openId
     * @return
     * @author ys
     */
    @RequestMapping(value="/ballot")
    @ResponseBody
    public JsonResult ballot(String openId, String videoId, HttpServletResponse response){
        if (StringUtil.isEmpty(openId, videoId)) {
            return new JsonResult(Message.M4003);
        }
        //验证用户是否真实有效
        int count = sgVideoMatchUserService.countByOpenId(openId);
        if(count == 0){
            //验证账号，防止非法刷票
            return new JsonResult(Message.M3001);
        }
        //查看今天已投多少票  限10
        int result = sgVideoMatchVoteService.selectCountByOpenId(openId);
        if (result >= 10) {
            return new JsonResult(Message.M3016);//点赞已达上限
        }
        //记录投票，且回写用户票数
        int ret = sgVideoMatchVoteService.doVote(openId,videoId);
        if(ret == -1)
            return new JsonResult(Message.M3069);//此作品已点赞
        if(ret >0)
            return new JsonResult(Message.M2000, (10 - (result + 1)));
        else
            return new JsonResult(Message.M5000);
    }
}
