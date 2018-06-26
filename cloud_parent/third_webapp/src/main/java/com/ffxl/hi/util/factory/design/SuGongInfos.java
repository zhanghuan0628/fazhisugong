package com.ffxl.hi.util.factory.design;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.ffxl.qrcode.QRImgUtil;

import com.alibaba.fastjson.JSONObject;
import com.ffxl.cloud.model.SWxOpenAuthorizerApp;
import com.ffxl.cloud.model.SgHealthyUser;
import com.ffxl.cloud.model.SgLogoLogo;
import com.ffxl.cloud.model.SgLogoUser;
import com.ffxl.cloud.model.SgVideoMatchUser;
import com.ffxl.cloud.service.SWxOpenAuthorizerAppService;
import com.ffxl.cloud.service.SgHealthyUserService;
import com.ffxl.cloud.service.SgLogoUserService;
import com.ffxl.cloud.service.SgVideoMatchUserService;
import com.ffxl.cloud.util.wxmsg.ApplicationContextUtils;
import com.ffxl.hi.util.ThirdAPI;
import com.ffxl.hi.util.factory.UserInterface;
import com.ffxl.platform.exception.BusinessException;
import com.ffxl.platform.util.JsonResult;
import com.ffxl.platform.util.Message;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;
import com.ffxl.platform.wechat.model.WechatUser;
import com.ffxl.platform.wechat.token.WebAccessToken;

/**
 * 苏供用户信息
 * @author jiawei
 *
 */
public class SuGongInfos implements UserInterface {
	private static final Logger logger = LoggerFactory.getLogger(SuGongInfos.class);

	@Override
	public ModelAndView insertOrUpdate(String appid,String code, String componentAppid,String componentAccessToken,ModelAndView mv,String state) {
		WebAccessToken webAccessToken  = ThirdAPI.oauth2Authorize(appid, code, componentAppid, componentAccessToken);
        if (webAccessToken != null && webAccessToken.getOpenid() != null) {
            String openId = webAccessToken.getOpenid();
        	String accessToken = webAccessToken.getAccess_token(); //网页授权token
            JSONObject jsonObject =ThirdAPI.getUserInfo(openId,accessToken ,"zh_CN");
            if (null != jsonObject) {
                try {
                    WechatUser chatUser = (WechatUser)JSONObject.toJavaObject(jsonObject,WechatUser.class);
                    if (chatUser != null) {
                        //微信授权成功 保存用户信息
                        SgVideoMatchUser user = new SgVideoMatchUser();
                        user.setOpenId(chatUser.getOpenid());
                        SgVideoMatchUserService sgVideoMatchUserService = ApplicationContextUtils.getBean(SgVideoMatchUserService.class);
                        SgVideoMatchUser checkModel = sgVideoMatchUserService.queryByModel(user);
                        if (checkModel != null) {
                            mv.addObject("user", checkModel);
                            mv.setViewName(state);
                            return mv;
                        } else {
                            user.setId(UUIDUtil.getUUID());
                            user.setNickName(chatUser.getNickname());
//                        值为1时是男性，值为2时是女性，值为0时是未知
                            if (chatUser.getSex() == 1) {
                                user.setSex("男");
                            } else if (chatUser.getSex() == 2) {
                                user.setSex("女");
                            } else {
                                user.setSex("未知");
                            } 
                            user.setWechatHeadUrl(chatUser.getHeadimgurl());
                            user.setCreateDate(new Date());
                            //为用户生成二维码
//                            SWxOpenAuthorizerAppService swxOpenAuthorizerAppService = ApplicationContextUtils.getBean(SWxOpenAuthorizerAppService.class);
//                            SWxOpenAuthorizerApp app =new SWxOpenAuthorizerApp();
//                            app.setAuthorizerAppid(appid);
//                            SWxOpenAuthorizerApp model = swxOpenAuthorizerAppService.queryByModel(app);
//                            if(model ==null){
//                                throw new BusinessException("请先引导授权公众号管理员授权给第三方权限");
//                            }
//                            //检验token 是否可用,不可用时使用token刷新
//                            ThirdAPI.checkAuthorizerAccessToken(model, componentAccessToken);
//                            QRImgUtil util = new QRImgUtil();     
//                            String qrcodeUrl = ThirdAPI.getQrcode(user.getOpenId(), model.getAuthorizerAccessToken(), "qrcode", null, "sugong");
//                            String qrcodeUrl = util.getQRImg("https://www.feifanxinli.com");
//                            logger.info("用户二维码："+qrcodeUrl);
                            int result = sgVideoMatchUserService.insertSelective(user);
                            if (result > 0) {
                                mv.addObject("user", user);
                                mv.setViewName(state);
                            } else {
                                mv.addObject("msg", "用户信息记录失败");
                                mv.setViewName("common/error.jsp");
                            }
                        }
                    }
                    logger.info("用户信息"+jsonObject);
                    } catch (Exception e) {
                        Integer errorCode = jsonObject.getInteger("errcode");
                        String errorMsg = jsonObject.getString("errmsg");
                        logger.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
                        throw new BusinessException(errorMsg);
                    }
                }else{
                	mv.addObject("msg", "用户信息记录失败");
                	mv.setViewName("common/error.jsp");
            }
        } else {
        	mv.addObject("msg", "用户授权失败");
        	mv.setViewName("common/error.jsp");
        }
        return mv;
	}

    @Override
    public void getVideoMatchUserInfo(String openId, ModelAndView mv) {
        SgVideoMatchUserService sgVideoMatchUserService = ApplicationContextUtils.getBean(SgVideoMatchUserService.class);
        SgVideoMatchUser u = new SgVideoMatchUser();
        u.setOpenId(openId);
        u = sgVideoMatchUserService.queryByModel(u);
        if (u != null) {
            mv.addObject("user", u);
        } else {
            u = new SgVideoMatchUser();
            u.setOpenId(openId);
            u.setId(UUIDUtil.getUUID());
            u.setCreateDate(new Date());
            sgVideoMatchUserService.insertSelective(u);
            mv.addObject("user", u);
            //mv.addObject("extUset", "0");//未创建用户信息
        }
    }

    @Override
    public void getHealthyUserInfo(String openId, ModelAndView mv) {
        SgHealthyUserService sgHealthyUserService = ApplicationContextUtils.getBean(SgHealthyUserService.class);
        SgHealthyUser u = new SgHealthyUser();
        u.setOpenId(openId);
        u = sgHealthyUserService.queryByModel(u);
        if (u != null) {
            mv.addObject("extUset", "1");//已创建用户信息
        } else {
            mv.addObject("extUset", "0");//未创建用户信息
        }
    }

    @Override
    public ModelAndView getLogoInfoUser(String openId, ModelAndView mv) {
        SgLogoUserService SgLogoUserService = ApplicationContextUtils.getBean(SgLogoUserService.class);
        SgLogoUser u = new SgLogoUser();
        u.setOpenId(openId);
        u = SgLogoUserService.queryByModel(u);
        if (u != null) {
            mv.addObject("extUset", "1");//已创建用户信息
        } else {
            u = new SgLogoUser();
            u.setOpenId(openId);
            u.setId(UUIDUtil.getUUID());
            u.setCreateDate(new Date());
            SgLogoUserService.insertSelective(u);
            mv.addObject("user", u);
        }
        return mv;
        
    }
}
