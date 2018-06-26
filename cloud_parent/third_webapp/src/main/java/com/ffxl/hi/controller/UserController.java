package com.ffxl.hi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.ffxl.qrcode.QRImgUtil;

import com.ffxl.cloud.model.SWxOpenAccount;
import com.ffxl.cloud.model.SWxOpenAuthorizerApp;
import com.ffxl.cloud.model.SgHealthyUser;
import com.ffxl.cloud.model.SgLogoUser;
import com.ffxl.cloud.model.SgVideoMatchVideo;
import com.ffxl.cloud.model.TSclCrowdModule;
import com.ffxl.cloud.service.SWxOpenAccountService;
import com.ffxl.cloud.service.SWxOpenAuthorizerAppService;
import com.ffxl.cloud.service.SgHealthyUserService;
import com.ffxl.cloud.service.SgLogoUserService;
import com.ffxl.cloud.service.SgVideoMatchUserService;
import com.ffxl.hi.util.ThirdAPI;
import com.ffxl.hi.util.factory.SuGongFactory;
import com.ffxl.hi.util.factory.UserInterface;
import com.ffxl.hi.util.factory.UserProviderInterface;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.exception.BusinessException;
import com.ffxl.platform.util.JsonResult;
import com.ffxl.platform.util.Message;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;
import com.ffxl.platform.wechat.token.WebAccessToken;
import com.ffxl.platform.wechat.util.WeChatConst;

/**
 * 用户微信授权、获取头像昵称接口
 * @author jiawei
 *
 */
@Controller
@RequestMapping(value = "/user")
@Api(value = "/user", description = "用户微信授权、获取头像昵称接口")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private SWxOpenAccountService swxOpenAccountService;
    @Autowired
    private SWxOpenAuthorizerAppService swxOpenAuthorizerAppService;
    @Autowired
    private SgVideoMatchUserService sgVideoMatchUserService;
    @Autowired
    private SgHealthyUserService sgHealthyUserService;
    @Autowired
    private SgLogoUserService sgLogoUserService;
	
    /** 
     * 微信全网测试账号 
     */  
    private final static String COMPONENT_APPID = WeChatConst.COMPONENT_APPID;   
       
     
    protected static final HashSet<String> APP_TYPE_KEYS = new HashSet<String>(Arrays.asList(new String[]{
			"sgVideo","sgHealthy","sgLogo"}));
    private final static String APP_TYPE_SGVIDEO = "sgVideo";  //视频比赛
    private final static String APP_TYPE_SGHEALTHY = "sgHealthy";  //视频比赛
    private final static String APP_TYPE_SGLOGO = "sgLogo";  //logo征集
    
    /**
     * 获取授权标识
     * @param request
     * @param response
     * @author jiawei
     */
     @RequestMapping(value="/{scope}/{appid}")
     @ResponseBody
     @ApiOperation(value = "获取授权标识", httpMethod = "GET",notes = "微信授权，获取用户信息")
     public JsonResult getScope(@PathVariable String scope,@PathVariable String appid){
         if(StringUtil.isEmpty(appid)){
        	 throw new BusinessException(Message.M4003);
         }
         if(!scope.equals(WeChatConst.SCOPE_USERINFO) && !scope.equals(WeChatConst.SCOPE)){
        	 throw new BusinessException(Message.M4004);
         }
         if(!StringUtil.isEmpty(appid,scope)){
             Map<String,String> resultMap = new HashMap<String,String>();
             resultMap.put("appId", appid);
             resultMap.put("scope", scope);
             resultMap.put("componentAppId", COMPONENT_APPID);
             return new JsonResult(true,resultMap);
         }else{
             return new JsonResult(false);
         }
     }
    
    /**
     * 微信重定向方法
     * @param request
     * @return
     */
    @RequestMapping(value = "/redirect/{appid}/{appType}")
    @ResponseBody
    @ApiOperation(value = "微信授权，获取用户信息", httpMethod = "GET", hidden=true, response = TSclCrowdModule.class, notes = "微信授权，获取用户信息")
    public ModelAndView redirectDetail(@PathVariable String appid,@PathVariable String appType,HttpServletRequest request,HttpServletResponse response) {
    	 if(StringUtil.isEmpty(appid,appType)){
        	 throw new BusinessException(Message.M4003);
         }
    	 if (!APP_TYPE_KEYS.contains(appType)) {
    		 throw new BusinessException(Message.M4004);
 		}
    	//验证授权方信息
    	 SWxOpenAuthorizerApp entity = new SWxOpenAuthorizerApp();
         entity.setAuthorizerAppid(appid);
         SWxOpenAuthorizerApp app = swxOpenAuthorizerAppService.queryByModel(entity);
         if(app == null){
        	 throw new BusinessException("请先引导授权公众号管理员授权给第三方权限");
         }
    	 
    	ModelAndView mv = new ModelAndView();
        String code = request.getParameter("code");
        //state为跳转路径，微信不识别&符号，顾前端如有参数，请使用@代替，在此处做转换
        // 页面路径必须在jsp目录下，后缀名可自定义
        String state = request.getParameter("state");
        logger.debug("------------------------state:----" + state);
		state = state.replaceAll("@", "&");
		logger.debug("------------------------state:----" + state);
        // 用户授权
		//根据
		SWxOpenAccount account = swxOpenAccountService.queryByAppId(COMPONENT_APPID);
		if(account ==null){
			throw new BusinessException("请等待第三方开放平台数据初始化，最长10分钟");
		}
		if(APP_TYPE_SGVIDEO.equals(appType)){
			UserProviderInterface provider = new SuGongFactory();  
			UserInterface sender = provider.produce();  
            mv = sender.insertOrUpdate(appid, code, COMPONENT_APPID, account.getComponentAccessToken(), mv, state);
		}
		return mv;
    }
    
    
    /**
     * 获取openid方法
     * @param request
     * @return
     */
    @RequestMapping(value = "/get_openid/{appid}/{appType}")
    @ResponseBody
    @ApiOperation(value = "微信授权，获取用户信息", httpMethod = "GET", hidden=true, response = TSclCrowdModule.class, notes = "微信授权，获取用户信息")
    public ModelAndView getOpenid(@PathVariable String appid, @PathVariable String appType, HttpServletRequest request,HttpServletResponse response) {
         if(StringUtil.isEmpty(appid)){
             throw new BusinessException(Message.M4003);
         }
         if (!APP_TYPE_KEYS.contains(appType)) {
             throw new BusinessException(Message.M4004);
        }
        //验证授权方信息
         SWxOpenAuthorizerApp entity = new SWxOpenAuthorizerApp();
         entity.setAuthorizerAppid(appid);
         SWxOpenAuthorizerApp app = swxOpenAuthorizerAppService.queryByModel(entity);
         if(app == null){
             throw new BusinessException("请先引导授权公众号管理员授权给第三方权限");
         }
         
        ModelAndView mv = new ModelAndView();
        String code = request.getParameter("code");
        //state为跳转路径，微信不识别&符号，顾前端如有参数，请使用@代替，在此处做转换
        // 页面路径必须在jsp目录下，后缀名可自定义
        String state = request.getParameter("state");
        logger.debug("------------------------appid:----" + appid);
        logger.debug("------------------------state:----" + state);
        if (!StringUtil.isEmpty(state)) {
            state = state.replaceAll("@", "&");
        }
        logger.debug("------------------------state:----" + state);
        // 用户授权
        //根据
        SWxOpenAccount account = swxOpenAccountService.queryByAppId(COMPONENT_APPID);
        if(account ==null){
            throw new BusinessException("请等待第三方开放平台数据初始化，最长10分钟");
        }
        WebAccessToken webAccessToken  = ThirdAPI.oauth2Authorize(appid, code, COMPONENT_APPID, account.getComponentAccessToken());
        if(webAccessToken ==null){
            throw new BusinessException("打开页面超时，请重新打开");
        }
        UserProviderInterface provider = new SuGongFactory();  
        UserInterface sender = provider.produce();  
        if (appType.equals(APP_TYPE_SGVIDEO)) {
            //苏供视频比赛用户
            sender.getVideoMatchUserInfo(webAccessToken.getOpenid(), mv);
        } else if (appType.equals(APP_TYPE_SGHEALTHY)) {
            //苏供21天春季膳食健康
            sender.getHealthyUserInfo(webAccessToken.getOpenid(), mv);
        } else if (appType.equals(APP_TYPE_SGLOGO)){
            mv = sender.getLogoInfoUser(webAccessToken.getOpenid(), mv);
        }
        mv.setViewName(state);
        mv.addObject("openId", webAccessToken.getOpenid());
        mv.addObject("oauth", true);
        return mv;
    }
    
    /**
     * 21天健康活动 用户信息保存
     * @param 
     * @return
     * @author ys
     */
    @RequestMapping(value="/sava_healthy_user")
    @ResponseBody
    public JsonResult create(SgHealthyUser model){
        if (StringUtil.isEmpty(model.getOpenId())) {
            return new JsonResult(Message.M4003);
        }
        SgHealthyUser u = sgHealthyUserService.queryByModel(model);
        if (u != null) {
            sgHealthyUserService.updateByPrimaryKeySelective(model);
        } else {
            /**需添加用户二维码**/
            model.setId(UUIDUtil.getUUID());
            String qrcodeUrl = new QRImgUtil().getQRImg(model.getQrUrl());
            model.setQrImg(qrcodeUrl);
            sgHealthyUserService.insertSelective(model);
        }
        return new JsonResult(Message.M2000, model);
    }
}
