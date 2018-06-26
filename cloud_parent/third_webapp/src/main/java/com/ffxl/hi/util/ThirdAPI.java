package com.ffxl.hi.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.ffxl.OSSUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ffxl.cloud.model.SWxOpenAccount;
import com.ffxl.cloud.model.SWxOpenAuthorizerApp;
import com.ffxl.cloud.model.tool.HttpRequestUtil;
import com.ffxl.cloud.model.tool.WeiXinQRCode;
import com.ffxl.cloud.service.SWxOpenAccountService;
import com.ffxl.cloud.service.SWxOpenAuthorizerAppService;
import com.ffxl.cloud.util.wxmsg.ApplicationContextUtils;
import com.ffxl.platform.app.util.wechat.HttpConnectUtil;
import com.ffxl.platform.exception.BusinessException;
import com.ffxl.platform.model.ApiAuthorizerInfoRequest;
import com.ffxl.platform.model.ApiComponentTokenRequest;
import com.ffxl.platform.model.ApiComponentTokenResponse;
import com.ffxl.platform.model.ApiCreatePreauthcodeRequest;
import com.ffxl.platform.model.ApiQueryAuthRequest;
import com.ffxl.platform.util.Const;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;
import com.ffxl.platform.wechat.ticket.Ticket;
import com.ffxl.platform.wechat.token.WebAccessToken;
import com.ffxl.platform.wechat.util.Sha1Util;
import com.ffxl.platform.wechat.util.WeChatConst;

public class ThirdAPI {
    private static final Logger logger = LoggerFactory.getLogger(ThirdAPI.class);

    // 获取第三方平台component_access_token
    public static final String COMPONENT_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";

    // 获取预授权码pre_auth_code
    public static final String CREATE_PREAUTHCODE = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=";

    // 使用授权码换取公众号或小程序的接口调用凭据和授权信息
    public static final String QUERY_AUTH = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=";

    // 该API用于获取授权方的基本信息，包括头像、昵称、帐号类型、认证类型、微信号、原始ID和二维码图片URL。
    public static final String API_GET_AUTHORIZER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=";

    // 获取（刷新）授权公众号或小程序的接口调用凭据（令牌）
    public static final String API_AUTHORIZER_TOKEN = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?";

    // 代第三方网页授权，获取用户的openId和access_token
    public static final String THIRD_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/component/access_token?";

    // 通过网页授权access_token获取用户基本信息（需授权作用域为snsapi_userinfo）
    public static final String USER_INFO = "https://api.weixin.qq.com/sns/userinfo?";

    // 客服消息
    public static final String CUSTOM_SEND = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

    // 二维码ticket
    public static final String QR_SCENE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
    
    // 通过ticket换取二维码
    public static final String SHOWQRCODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
    
    public static final String QR_CODE_PATH = "qr_code"; //二维码保存目录
    

    /**
     * 获取平台接口调用凭证，凭证有效期2小时
     * 
     * @param apiComponentToken
     *            第三方开发平台的token
     * @return
     */
    public static String getAccessToken(ApiComponentTokenRequest apiComponentToken) {
        SWxOpenAccountService swxOpenAccountService = ApplicationContextUtils.getBean(SWxOpenAccountService.class);
        // 查询平台信息
        SWxOpenAccount openAccount = swxOpenAccountService.queryByAppId(apiComponentToken.getComponent_appid());
        if (openAccount != null) {
            if (openAccount.getTokenTime() == null) {
                // 组装json字符串
                String apiComponentTokenJson = JSONObject.toJSONString(apiComponentToken);
                String result = HttpConnectUtil.httpsRequest(COMPONENT_ACCESS_TOKEN_URL, WeChatConst.REQUEST_METHOD_POST, apiComponentTokenJson);
                logger.info(result);
                if (result.contains("component_access_token")) {
                    JSONObject js = JSONObject.parseObject(result);
                    ApiComponentTokenResponse response = JSONObject.toJavaObject(js, ApiComponentTokenResponse.class);
                    String component_access_token = response.getComponent_access_token();
                    // 保存entity
                    openAccount.setComponentAccessToken(component_access_token);
                    openAccount.setTokenTime(new Date());
                    swxOpenAccountService.saveOrUpdate(openAccount);
                    return component_access_token;
                } else {
                    throw new BusinessException("获取component_access_token失败!！");
                }
            }
            // 验证凭证是否可用
            Date tokenDate = openAccount.getTokenTime();
            Calendar calDate = Calendar.getInstance();
            calDate.setTime(tokenDate);
            long HMillis = (60 * 60 * 2 * 1000 - 5 * 60 * 1000); // 115分钟
            Date resultDate = new Date(calDate.getTimeInMillis() + HMillis);
            Date currentDate = new Date();
            // //过期，需要重新刷新获取access_token
            if (currentDate.compareTo(resultDate) > 0) {
                // 组装json字符串
                String apiComponentTokenJson = JSONObject.toJSONString(apiComponentToken);
                String result = HttpConnectUtil.httpsRequest(COMPONENT_ACCESS_TOKEN_URL, WeChatConst.REQUEST_METHOD_POST, apiComponentTokenJson);
                logger.info(result);
                if (result.contains("component_access_token")) {
                    JSON js = JSONObject.parseObject(result);
                    ApiComponentTokenResponse response = JSONObject.toJavaObject(js, ApiComponentTokenResponse.class);
                    String component_access_token = response.getComponent_access_token();
                    // 保存entity
                    openAccount.setComponentAccessToken(component_access_token);
                    openAccount.setTokenTime(new Date());
                    swxOpenAccountService.saveOrUpdate(openAccount);
                    return component_access_token;
                } else {
                    logger.info("获取component_access_token失败！");
                    throw new BusinessException("获取component_access_token失败！");
                }
            } else {
                return openAccount.getComponentAccessToken();
            }
        } else {
            // 组装json字符串
            String apiComponentTokenJson = JSONObject.toJSONString(apiComponentToken);
            String result = HttpConnectUtil.httpsRequest(COMPONENT_ACCESS_TOKEN_URL, WeChatConst.REQUEST_METHOD_POST, apiComponentTokenJson);
            logger.info(result);
            if (result.contains("component_access_token")) {
                JSONObject js = JSONObject.parseObject(result);
                ApiComponentTokenResponse response = JSONObject.toJavaObject(js, ApiComponentTokenResponse.class);
                String component_access_token = response.getComponent_access_token();
                // 保存entity
                openAccount = new SWxOpenAccount();
                openAccount.setComponentAccessToken(component_access_token);
                openAccount.setTokenTime(new Date());
                swxOpenAccountService.saveOrUpdate(openAccount);
                return component_access_token;
            } else {
                throw new BusinessException("获取component_access_token失败!！");
            }
        }
    }

    /**
     * 获取预授权码
     * 
     * @param componentAppid
     *            第三方开发平台的appId
     * @param componentAccessToken
     *            第三方开发平台的token
     * @return
     */
    public static String getPreAuthCode(String componentAppid, String componentAccessToken) {
        SWxOpenAccountService swxOpenAccountService = ApplicationContextUtils.getBean(SWxOpenAccountService.class);
        // 查询平台信息
        SWxOpenAccount openAccount = swxOpenAccountService.queryByAppId(componentAppid);
        if (openAccount != null) {
            if (openAccount.getCodeTime() == null) {
                ApiCreatePreauthcodeRequest preauthCodeRequest = new ApiCreatePreauthcodeRequest();
                preauthCodeRequest.setComponent_appid(componentAppid);
                String preauthCodeRequestJson = JSONObject.toJSONString(preauthCodeRequest);
                String result = HttpConnectUtil.httpsRequest(
                        CREATE_PREAUTHCODE + componentAccessToken,
                        WeChatConst.REQUEST_METHOD_POST,
                        preauthCodeRequestJson);
                logger.info(result);
                if (result.contains("pre_auth_code")) {
                    JSONObject js = JSONObject.parseObject(result);
                    String pre_auth_code = js.getString("pre_auth_code");
                    // 保存entity
                    openAccount.setPreAuthCode(pre_auth_code);
                    openAccount.setCodeTime(new Date());
                    swxOpenAccountService.saveOrUpdate(openAccount);
                    return pre_auth_code;
                } else {
                    throw new BusinessException("出错了");
                }
            } else {
                // 验证预授权码是否可用
                Date codeDate = openAccount.getCodeTime();
                Calendar calDate = Calendar.getInstance();
                calDate.setTime(codeDate);
                long HMillis = (10 * 60 * 1000 - 60 * 1000); // 9分钟
                Date resultDate = new Date(calDate.getTimeInMillis() + HMillis);
                Date currentDate = new Date();
                // 过期，需要重新刷新获取预授权码
                if (currentDate.compareTo(resultDate) > 0) {
                    ApiCreatePreauthcodeRequest preauthCodeRequest = new ApiCreatePreauthcodeRequest();
                    preauthCodeRequest.setComponent_appid(componentAppid);
                    String preauthCodeRequestJson = JSONObject.toJSONString(preauthCodeRequest);
                    String result = HttpConnectUtil.httpsRequest(
                            CREATE_PREAUTHCODE + componentAccessToken,
                            WeChatConst.REQUEST_METHOD_POST,
                            preauthCodeRequestJson);
                    logger.info(result);
                    if (result.contains("pre_auth_code")) {
                        JSONObject js = JSONObject.parseObject(result);
                        String pre_auth_code = js.getString("pre_auth_code");
                        // 保存entity
                        openAccount.setPreAuthCode(pre_auth_code);
                        openAccount.setCodeTime(new Date());
                        swxOpenAccountService.saveOrUpdate(openAccount);
                        return pre_auth_code;
                    } else {
                        throw new BusinessException("不包含pre_auth_code");
                    }
                } else {
                    return openAccount.getPreAuthCode();
                }
            }
        } else {
            throw new BusinessException("获取预授权码时，未查询到ticket等第三方账号信息");
        }

    }

    /**
     * 使用授权码换取公众号或小程序的接口调用凭据和授权信息 在获取授权码前已经验证了accesstoken并且处理了，所以此时componentAccessToken 一定有效 TODO 获取到的授权方接口调用凭证会过期，微信提供刷新方法，后续用倒时需要实现
     * 
     * @param componentAppid
     *            第三方开发平台的appId
     * @param auth_code
     *            微信引导授权方管理员扫码后跳转所带参数
     * @return
     */
    public static String getAuthorizationInfo(String componentAppid, String auth_code) {
        SWxOpenAccountService swxOpenAccountService = ApplicationContextUtils.getBean(SWxOpenAccountService.class);
        // 查询平台信息
        SWxOpenAccount openAccount = swxOpenAccountService.queryByAppId(componentAppid);
        ApiQueryAuthRequest queryAuthRequest = new ApiQueryAuthRequest();
        queryAuthRequest.setComponent_appid(componentAppid);
        queryAuthRequest.setAuthorization_code(auth_code);
        String queryAuthRequestJson = JSONObject.toJSONString(queryAuthRequest);
        String result = HttpConnectUtil.httpsRequest(
                QUERY_AUTH + openAccount.getComponentAccessToken(),
                WeChatConst.REQUEST_METHOD_POST,
                queryAuthRequestJson);
        logger.info(result);
        return result;
    }

    /**
     * 获取授权方的帐号基本信息
     * 
     * @param componentAccessToken
     *            第三方开发平台的token
     * @param componentAppid
     *            第三方开发平台的appId
     * @param authorizerAppid
     *            授权方appId
     * @return
     */
    public static JSONObject getAuthorizerInfo(String componentAccessToken, String componentAppid, String authorizerAppid) {
        String url = API_GET_AUTHORIZER_INFO_URL + componentAccessToken;
        ApiAuthorizerInfoRequest apiAuthorizerInfoRequest = new ApiAuthorizerInfoRequest();
        apiAuthorizerInfoRequest.setComponent_appid(componentAppid);
        apiAuthorizerInfoRequest.setAuthorizer_appid(authorizerAppid);
        String infoRequestJson = JSONObject.toJSONString(apiAuthorizerInfoRequest);
        String result = HttpConnectUtil.httpsRequest(url, WeChatConst.REQUEST_METHOD_POST, infoRequestJson);
        logger.info(result);
        JSONObject json = JSONObject.parseObject(result);
        if (json.containsKey("errcode")) {
            int errcode = json.getIntValue("errcode");
            logger.error("换取网页授权access_token失败，错误码：" + errcode);
            return null;
        } else {
            return json;
        }
    }

    /**
     * 获取（刷新）授权公众号或小程序的接口调用凭据（令牌）
     * 
     * @param componentAccessToken
     *            第三方开发平台的token
     * @param componentAppid
     *            第三方开发平台的appId
     * @param authorizerAppid
     *            授权方appId
     * @return
     */
    public static JSONObject getAuthorizerToken(String componentAccessToken, String componentAppid, String authorizerAppid,
            String authorizerRefreshToken) {
        String url = API_AUTHORIZER_TOKEN + "component_access_token=" + componentAccessToken;
        ApiAuthorizerInfoRequest apiAuthorizerInfoRequest = new ApiAuthorizerInfoRequest();
        apiAuthorizerInfoRequest.setComponent_appid(componentAppid);
        apiAuthorizerInfoRequest.setAuthorizer_appid(authorizerAppid);
        apiAuthorizerInfoRequest.setAuthorizer_refresh_token(authorizerRefreshToken);
        String infoRequestJson = JSONObject.toJSONString(apiAuthorizerInfoRequest);
        String result = HttpConnectUtil.httpsRequest(url, WeChatConst.REQUEST_METHOD_POST, infoRequestJson);
        logger.info(result);
        JSONObject json = JSONObject.parseObject(result);
        if (json.containsKey("errcode")) {
            int errcode = json.getIntValue("errcode");
            logger.error("换取网页授权access_token失败，错误码：" + errcode);
            return null;
        } else {
            return json;
        }
    }

    /**
     * 代第三方通过code换取access_token 服务重启后，在微信每10分钟调用 ticket推送前，会有一定几率导致component_access_token失效，此时 重新获取component_access_token并且更新，让用户重新尝试打开页面即可
     * 
     * @param appid
     *            授权方appId
     * @param code
     *            code值，微信回调中的参数
     * @param component_appid
     *            第三方开发平台的appId
     * @param component_access_token
     *            第三方开发平台的token
     * @return
     */
    public static WebAccessToken oauth2Authorize(String appid, String code, String component_appid, String component_access_token) {
        // SWxOpenAccount openAccount = swxOpenAccountService.queryByAppId(componentAppid);
        String url = THIRD_ACCESS_TOKEN + "appid=" + appid + "&code=" + code + "&grant_type=authorization_code&component_appid=" + component_appid
                + "&component_access_token=" + component_access_token;
        String result = HttpConnectUtil.httpsRequest(url, WeChatConst.REQUEST_METHOD_POST, null);
        logger.info(result);
        JSONObject json = JSONObject.parseObject(result);
        if (json.containsKey("errcode")) {
            int errcode = json.getIntValue("errcode");
            if (errcode == 42001) {
                // 等待微信自动刷新accesstoken
                return null;
            }
            logger.error("换取网页授权access_token失败，错误码：" + errcode);
            return null;
        } else {
            WebAccessToken token = new WebAccessToken();
            token.setAccess_token(json.getString("access_token"));
            token.setExpires_in(new Integer(json.getString("expires_in")));
            token.setOpenid(json.getString("openid"));
            token.setRefresh_token(json.getString("refresh_token"));
            token.setScope(json.getString("scope"));
            return token;
        }
    }

    /**
     * 通过网页授权access_token获取用户基本信息
     * 
     * @param openId
     *            用户openId
     * @param accessToken
     *            网页授权token,区别于授权方的的调用凭证对应的token
     * @param string
     *            语言
     * @return
     */
    public static JSONObject getUserInfo(String openId, String accessToken, String lang) {
        String url = USER_INFO + "access_token=" + accessToken + "&openid=" + openId + "&lang=" + lang;
        String result = HttpConnectUtil.httpsRequest(url, WeChatConst.REQUEST_METHOD_GET, null);
        logger.info(result);
        JSONObject json = JSONObject.parseObject(result);
        if (json.containsKey("errcode")) {
            int errcode = json.getIntValue("errcode");
            logger.error("换取网页授权access_token失败，错误码：" + errcode);
            return null;
        } else {
            return json;
        }
    }

    /**
     * 客服消息
     * 
     * @param obj
     *            消息xml
     * @param authorizer_access_token
     *            授权方的的调用凭证
     */
    public static JSONObject sendMessage(Map<String, Object> obj, String authorizer_access_token) {
        String result = HttpConnectUtil.httpsRequest(CUSTOM_SEND + authorizer_access_token, "POST", obj.toString());
        logger.debug("回复消息结果：" + result);
        JSONObject json = JSONObject.parseObject(result);
        if (json.containsKey("errcode")) {
            int errcode = json.getIntValue("errcode");
            logger.error("换取网页授权access_token失败，错误码：" + errcode);
            return json;
        } else {
            return json;
        }
    }

    /**
     * 创建临时ticket
     * 
     * @param authorizerAccessToken
     * @param expire_seconds
     * @return
     */
    public static WeiXinQRCode qrScene(String authorizerAccessToken, int expireSeconds, String sceneStr) {
        // 构造post参数
        Map<String, String> sceneMap = new HashMap<String, String>();
        sceneMap.put("scene_str", sceneStr);
        Map<String, Map<String, String>> actionInfoMap = new HashMap<String, Map<String, String>>();
        actionInfoMap.put("scene", sceneMap);

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("expire_seconds", expireSeconds);
        paramsMap.put("action_name", "QR_STR_SCENE"); //临时
        paramsMap.put("action_info", actionInfoMap);
        String outputStr = JSONObject.toJSONString(paramsMap);
        String result = HttpConnectUtil.httpsRequest(QR_SCENE + authorizerAccessToken, "POST", outputStr);
        logger.info("获取临时ticket结果：" + result);
        JSONObject json = JSONObject.parseObject(result);
        if (json.containsKey("errcode")) {
            int errcode = json.getIntValue("errcode");
            logger.error("换取临时二维码ticket失败，错误码：" + errcode);
            return null;
        }else{
            
            WeiXinQRCode qrCode = JSONObject.toJavaObject(json, WeiXinQRCode.class);
            return qrCode;
        }
    }

    /**
     * 创建永久二维码(字符串)
     * 
     * @param authorizerAccessToken
     * @param sceneStr
     *            场景str
     * @return
     */
    public static WeiXinQRCode qrLimitScene(String authorizerAccessToken, String sceneStr) {
     // 构造post参数
        Map<String, String> sceneMap = new HashMap<String, String>();
        sceneMap.put("scene_str", sceneStr);
        Map<String, Map<String, String>> actionInfoMap = new HashMap<String, Map<String, String>>();
        actionInfoMap.put("scene", sceneMap);

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("action_name", "QR_LIMIT_STR_SCENE"); //永久
        paramsMap.put("action_info", actionInfoMap);

        String result = HttpConnectUtil.httpsRequest(QR_SCENE + authorizerAccessToken, "POST", paramsMap.toString());
        logger.info("获取永久ticket结果：" + result);
        JSONObject json = JSONObject.parseObject(result);
        if (json.containsKey("errcode")) {
            int errcode = json.getIntValue("errcode");
            logger.error("换取临时二维码ticket失败，错误码：" + errcode);
            return null;
        }else{
            
            WeiXinQRCode qrCode = JSONObject.toJavaObject(json, WeiXinQRCode.class);
            return qrCode;
        }
    }

    /**
     * 获取二维码ticket后，通过ticket换取二维码图片
     * 
     * @param id
     * @param authorizerAccessToken 授权方token
     * @param qrcodeType 二维码类型  qrcode：临时二维码  limit_qrcode：永久二维码
     * @param appType sugong
     * 
     * @return
     */
    public static String getQrcode(String id, String authorizerAccessToken,String qrcodeType,Integer expireSeconds,String appType) throws Exception { 
        //临时 
        WeiXinQRCode weiXinQRCode = null;
        if(qrcodeType.equals("qrcode")){
            if(expireSeconds ==null){
                expireSeconds = 2592000;//默认30天
            }
            weiXinQRCode = qrScene(authorizerAccessToken, expireSeconds, id);
        }else if(qrcodeType.equals("limit_qrcode")){
            weiXinQRCode = qrLimitScene(authorizerAccessToken, id);
        }
        if(weiXinQRCode ==null){
            throw new BusinessException("获取ticket失败，请联系管理员");
        }
        String ticket= weiXinQRCode.getTicket();
        String path = ThirdAPI.class.getResource("/").getPath().replace("/C:", "C:");
        path = path.replace("classes", "wechat");
        Date d = new Date();
        String imgName = d.getTime() + ".jpg";
        path = path + imgName;
        logger.info("二维码临时存放目录:" + path);
        
        TreeMap<String, String> params = new TreeMap<String, String>();
        try {
            params.put("ticket", HttpRequestUtil.urlEncode(ticket, HttpRequestUtil.DEFAULT_CHARSET));
        } catch (Exception e) {
            throw new BusinessException("ticket 异常，请联系开发人员处理");
        }
        // 上传图片到项目
        HttpRequestUtil.downMeaterMetod(params, HttpRequestUtil.GET_METHOD, SHOWQRCODE, path);
        
        //项目图片上传OSS
        String requestPath = appType+"/"+QR_CODE_PATH;

        String servicePath = Const.IMG_FFYY_WECHAT_SERVICE_PATH; //
        String filename = servicePath + requestPath + "/" + UUIDUtil.getUUID() + ".jpg";
        OSSUtil os =new OSSUtil();
        os.upload(filename,  path);
        String ossUrl = os.getDownloadUrl();
        filename = ossUrl + "/" + filename;
        File productFile =new File(path);
        productFile.delete();// 删除项目生成文件
        return filename;
    }
    
    /**
     * 验证token的有效性，过期刷新
     * @param app
     * @return
     */
    public static void checkAuthorizerAccessToken(SWxOpenAuthorizerApp app,String componentAccessToken){
        // 验证凭证是否可用
        Date tokenDate = app.getUpdateDate();
        Calendar calDate = Calendar.getInstance();
        calDate.setTime(tokenDate);
        long HMillis = (60 * 60 * 2 * 1000 - 5 * 60 * 1000); // 115分钟
        Date resultDate = new Date(calDate.getTimeInMillis() + HMillis);
        Date currentDate = new Date();
        //过期，需要重新刷新获取access_token
        if (currentDate.compareTo(resultDate) > 0 || StringUtil.isEmpty(app.getAuthorizerTicket())) {
            //刷新并更新第三方code
            JSONObject authorizerJson = ThirdAPI.getAuthorizerToken(componentAccessToken, app.getComponentAppid(), app.getAuthorizerAppid(), app.getAuthorizerRefreshToken());
            String authorizerAccessToken = authorizerJson.getString("authorizer_access_token");
            //
            Ticket ticket = getTicket(authorizerAccessToken);
            if(ticket !=null){
                app.setAuthorizerTicket(ticket.getTicket());
            }
            Integer expiresIn = authorizerJson.getInteger("expires_in");
            String authorizerRefreshToken = authorizerJson.getString("authorizer_refresh_token");
            app.setAuthorizerAccessToken(authorizerAccessToken);
            app.setAuthorizerRefreshToken(authorizerRefreshToken);
            app.setExpiresIn(expiresIn);
            app.setUpdateDate(new Date());
            SWxOpenAuthorizerAppService swxOpenAuthorizerAppService = ApplicationContextUtils.getBean(SWxOpenAuthorizerAppService.class);
            int ret = swxOpenAuthorizerAppService.updateByPrimaryKey(app);
            if(ret >0){
                logger.info("token 过去后刷新成功-------------- 获取authorizerAccessToken = "+authorizerAccessToken);  
            }else{
                throw new BusinessException("刷新token后保存信息失败");
            }
        }
    }
    
    /**
     * 获取ticket
     * @param authorizerAccessToken
     * @return
     */
    public static Ticket getTicket(String authorizerAccessToken){
        // 访问微信服务器
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ authorizerAccessToken + "&type=jsapi";  
        String result = HttpConnectUtil.httpsRequest(url,WeChatConst.REQUEST_METHOD_GET, null);
        JSONObject json = JSONObject.parseObject(result);
        if (json != null&&json.containsKey("ticket")) {  
            Ticket ticket = new Ticket();
            String jsapi_ticket = json.getString("ticket");  
            ticket.setTicket(jsapi_ticket);
            ticket.setExpires_in(new Integer(json.getString("expires_in")));
            return ticket;
        }else{
            return null;
        } 
        
    }
    
    /**
     * 获取jssdk参数
     * @param requesUrl
     * @return
     */
    public static Map<String, Object> getWxConfig(String requestUrl,String authorizerAppid,String authorizerAccessToken,String jsapi_ticket) {
        Map<String, Object> ret = new HashMap<String, Object>();  
        String timestamp = Long.toString(System.currentTimeMillis() / 1000); // 必填，生成签名的时间戳  
        String nonceStr = RandomStringUtils.random(8, "123456789"); // 8位随机数,必填，生成签名的随机串  
        if(StringUtil.isEmpty(jsapi_ticket)){
            logger.error("+++++未获取到jssdk调用凭证+++++++");
        }
        String signature = ""; 
        // 注意这里参数名必须全部小写，且必须有序  
        String sign = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr+ "&timestamp=" + timestamp + "&url=" + requestUrl;  
        try {  
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");  
            crypt.reset();
            crypt.update(sign.getBytes("UTF-8"));  
            signature = byteToHex(crypt.digest());  
            String shaSign=Sha1Util.getSha1(sign);
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        ret.put("appId", authorizerAppid);  
        ret.put("timestamp", timestamp);  
        ret.put("nonceStr", nonceStr);  
        ret.put("signature", signature);  
        return ret; 
    }
    
    /** 
     * 方法名：byteToHex</br> 
     * 详述：字符串加密辅助方法 </br> 
     * 开发人员：jiawei  </br> 
     * 创建时间：2016-1-5  </br> 
     * @param hash 
     * @return 说明返回值含义 
     * @throws 说明发生此异常的条件 
      */  
     private static String byteToHex(final byte[] hash) {  
         Formatter formatter = new Formatter();  
         for (byte b : hash) {  
             formatter.format("%02x", b);  
         }  
         String result = formatter.toString();  
         formatter.close();  
         return result;  
   
     }  
}
