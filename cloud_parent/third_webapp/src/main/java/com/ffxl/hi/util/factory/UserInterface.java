package com.ffxl.hi.util.factory;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.ffxl.cloud.model.TSclAns;
import com.ffxl.cloud.model.TSclReport;

/**
 * 用户接口
 * @author jiawei
 *
 */
public interface UserInterface {
    
    
   /**
    * 查询或跟新用户
    * @param appid 授权方appid
    * @param code
    * @param componentAppid 第三方appid
    * @param componentAccessToken 第三方token
    */
    public ModelAndView insertOrUpdate(String appid,String code, String componentAppid,String componentAccessToken,ModelAndView mv,String state );
    
    /**
     * 验证是否已注册视频比赛信息
     * @param openId
     * @param mv
     */
    void getVideoMatchUserInfo(String openId, ModelAndView mv);
    
    /**
     * 验证是否已注册视频比赛信息
     * @param openId
     * @param mv
     */
    void getHealthyUserInfo(String openId, ModelAndView mv);
    /**
     * 验证用户是否注册
     * @param openid
     * @param mv
     */
    public ModelAndView getLogoInfoUser(String openId, ModelAndView mv);
}
