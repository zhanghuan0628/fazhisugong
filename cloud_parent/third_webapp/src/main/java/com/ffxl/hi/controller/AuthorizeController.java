package com.ffxl.hi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.ffxl.cloud.model.SWxOpenAccount;
import com.ffxl.cloud.model.SWxOpenAuthorizerApp;
import com.ffxl.cloud.service.SWxOpenAccountService;
import com.ffxl.cloud.service.SWxOpenAuthorizerAppService;
import com.ffxl.cloud.service.SgVideoMatchUserService;
import com.ffxl.hi.util.ThirdAPI;
import com.ffxl.hi.util.aes.AesException;
import com.ffxl.hi.util.aes.InfoType;
import com.ffxl.hi.util.aes.WXBizMsgCrypt;
import com.ffxl.platform.exception.BusinessException;
import com.ffxl.platform.model.ApiComponentTokenRequest;
import com.ffxl.platform.model.ApiQueryAuthResponse;
import com.ffxl.platform.util.JsonResult;
import com.ffxl.platform.util.Message;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.wechat.util.WeChatConst;
import com.ffxl.platform.wechat.util.WechatJSSDKUtil;

/**
 * 第三方开放平台配置
 * @author jiawei
 *
 */
@Controller
@RequestMapping(value = "/auth")
@Api(value = "/auth")
public class AuthorizeController {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizeController.class);
    @Autowired
    private SWxOpenAccountService swxOpenAccountService;
    @Autowired
    private SWxOpenAuthorizerAppService swxOpenAuthorizerAppService;
    @Autowired
    private SgVideoMatchUserService sgVideoMatchUserService;
	
    /** 
     * 微信全网测试账号 
     */  
    private final static String APPID = "wx570bc396a51b8ff8";//自动化测试的专用测试公众号
    private final static String SMALL_APPID = "wxd101a85aa106f53e";//自动化测试的专用测试小程序
    
    
//    private final static String COMPONENT_APPID = "wxb4764964f9e26714";  //本地
//    private final static String COMPONENT_APPSECRET = "486fca92de17e1515464cae93ec7648c"; //本地
    
//    private final static String COMPONENT_APPID = "wxf664be7a0807e7a7";  
//    private final static String COMPONENT_APPSECRET = "62b8bed5d542923a367dd03656052ec8";  //测试环境
//    private final static String COMPONENT_ENCODINGAESKEY = "2f3d123dfe32wesr43eto9ikju8yhfu8ie3wsu87enc";  
//    private final static String COMPONENT_TOKEN = "ffxl2580";  
    private final static String COMPONENT_APPID = WeChatConst.COMPONENT_APPID;  
    private final static String COMPONENT_APPSECRET = WeChatConst.COMPONENT_APPSECRET;
    private final static String COMPONENT_ENCODINGAESKEY = WeChatConst.COMPONENT_ENCODINGAESKEY;  
    private final static String COMPONENT_TOKEN = WeChatConst.COMPONENT_TOKEN;  
    
       
    
    /** 
     * 一键授权功能,主动引入用户进入授权页
     * @param request 
     * @param response 
     * @throws IOException 
     * @throws AesException 
     * @throws DocumentException 
     */  
    @RequestMapping(value = "/goAuthor")  
    public ModelAndView goAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException, AesException, DocumentException {  
        String baseUrl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath(); 
        ModelAndView mv =new ModelAndView();
    	mv.setViewName("home.jsp");
    	ApiComponentTokenRequest apiComponentToken = new ApiComponentTokenRequest();  
        apiComponentToken.setComponent_appid(COMPONENT_APPID);  
        apiComponentToken.setComponent_appsecret(COMPONENT_APPSECRET);  
        //授权事件接收会每隔10分钟检验一下ticket的有效性，从而保证了此处的ticket是长期有效的
        SWxOpenAccount  entity = getWeixinOpenAccount(COMPONENT_APPID);
        if(entity ==null){
            throw new BusinessException("请先等待第三方平台账号自动初始化");
        }
        apiComponentToken.setComponent_verify_ticket(entity.getTicket());  
        try {  
        	//获取第三方平台的接口调用凭证，有效期2小时
            String component_access_token = ThirdAPI.getAccessToken(apiComponentToken);  
            //获取预授权码，有效期10分钟
            String preAuthCode = ThirdAPI.getPreAuthCode(COMPONENT_APPID, component_access_token);  
            String url = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid="+COMPONENT_APPID+"&pre_auth_code="
            +preAuthCode+"&redirect_uri="+baseUrl+"/auth/authorCallback";  
            mv.addObject("url", url);
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return mv;
    }  
      
    /**
     * 引导授权回调
     * @param request
     * @param response
     * @throws IOException
     * @throws AesException
     * @throws DocumentException
     */
    @RequestMapping(value = "/authorCallback")  
    public void authorCallback(HttpServletRequest request, HttpServletResponse response) throws IOException, AesException, DocumentException {  
        String auth_code = request.getParameter("auth_code");  
        String expires_in = request.getParameter("expires_in");  
        // 授权信息        
        String result = ThirdAPI.getAuthorizationInfo(COMPONENT_APPID,auth_code);
    	if(result.contains("authorization_info")){
  			JSONObject js = JSONObject.parseObject(result);
  			String authorization_info = js.getString("authorization_info");
  			JSONObject infoJosn = JSONObject.parseObject(authorization_info);
  			ApiQueryAuthResponse authResponse = JSONObject.toJavaObject(infoJosn, ApiQueryAuthResponse.class);
  			 //插入已授权企业
  	        SWxOpenAuthorizerApp sWxOpenAuthorizerApp = new SWxOpenAuthorizerApp();
  	        sWxOpenAuthorizerApp.setAuthorizerAppid(authResponse.getAuthorizer_appid()); //授权appid
  	        sWxOpenAuthorizerApp.setComponentAppid(COMPONENT_APPID);//开放平台
  	        SWxOpenAuthorizerApp model = swxOpenAuthorizerAppService.queryByModel(sWxOpenAuthorizerApp);
  	        if(model ==null){
  	          SWxOpenAccount account =swxOpenAccountService.queryByAppId(COMPONENT_APPID);
  	          if(account ==null){
  	            throw new BusinessException("开放平台数据未初始化");
  	          }
  	            // 获取第三方信息
  	            JSONObject  jsonObject =ThirdAPI.getAuthorizerInfo(account.getComponentAccessToken(), account.getComponentAppid(), authResponse.getAuthorizer_appid());
    	        String authorizer = jsonObject.getString("authorizer_info");
    	        JSONObject authorizerJson = JSONObject.parseObject(authorizer);
    	        String nickName = authorizerJson.getString("nick_name");
    	        String headImg = authorizerJson.getString("head_img");
    	        String serviceTypeInfo = authorizerJson.getString("service_type_info");
    	        String verifyTypeInfo = authorizerJson.getString("verify_type_info");
    	        String userName = authorizerJson.getString("user_name");
    	        String principalName = authorizerJson.getString("principal_name");
    	        String alias = authorizerJson.getString("alias");
    	        String businessInfo = authorizerJson.getString("business_info");
    	        String qrcodeUrl = authorizerJson.getString("qrcode_url");
  	            model = new SWxOpenAuthorizerApp();
  	        	model.setComponentAppid(account.getComponentAppid());
  	            model.setAuthorizerAppid(authResponse.getAuthorizer_appid());
  	            model.setNickName(nickName);
                model.setHeadImg(headImg);
                model.setServiceTypeInfo(serviceTypeInfo);
                model.setVerifyTypeInfo(verifyTypeInfo);
                model.setUserName(userName);
                model.setAlias(alias);
                model.setBusinessInfo(businessInfo);
  	            model.setPrincipalName(principalName);
  	            model.setQrcodeUrl(qrcodeUrl);
  	            model.setAuthorizerAccessToken(authResponse.getAuthorizer_access_token());//授权方的接口调用凭证
  	            model.setExpiresIn(authResponse.getExpires_in());
  	            model.setAuthorizerRefreshToken(authResponse.getAuthorizer_refresh_token());
  	            model.setFuncInfo(authResponse.getFunc_info().toString());
  	        }else{
  	        	model.setAuthorizerAccessToken(authResponse.getAuthorizer_access_token());//授权方的接口调用凭证
  	        	model.setAuthorizerRefreshToken(authResponse.getAuthorizer_refresh_token());
  	        	model.setFuncInfo(authResponse.getFunc_info().toString());
  	        	model.setExpiresIn(authResponse.getExpires_in());
  	        	model.setUpdateDate(new Date()); //更新token时间，避免重复刷新
  	        }
  	      swxOpenAuthorizerAppService.insertOrUpdate(model);
    	}
    }  
    
    /**
     * 代公众号使用jssdk参数
     * 
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/jssdkconfig/{appid}")
    @ResponseBody
    @ApiOperation(value = "公众号回调地址", httpMethod = "GET", hidden=true)
    public JsonResult backUrl(@PathVariable String appid,String requestUrl ,HttpServletResponse response){ 
        if(StringUtil.isEmpty(appid,requestUrl)){
            return new JsonResult(Message.M4003);
        }
        response.setHeader("Access-Control-Allow-Origin", "http://feifanxinli.com");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        response.setContentType("textml;charset=UTF-8");
        //根据appid查询第三方信息
        SWxOpenAccount account = getWeixinOpenAccount(COMPONENT_APPID);
        if(account ==null){
            return new JsonResult(Message.M5000, "请先等待第三方平台账号自动初始化",null);
        }
        //查询授权方信息
        SWxOpenAuthorizerApp model =new SWxOpenAuthorizerApp();
        model.setAuthorizerAppid(appid);
        model.setComponentAppid(COMPONENT_APPID);
        SWxOpenAuthorizerApp app = swxOpenAuthorizerAppService.queryByModel(model);
        if(app ==null){
            return new JsonResult(Message.M5000, "请先引导授权公众号管理员授权给第三方权限",null);
        }
        ThirdAPI.checkAuthorizerAccessToken(app, account.getComponentAccessToken());
        String authorizerAccessToken = app.getAuthorizerAccessToken();
        String ticket = app.getAuthorizerTicket();
        Map<String, Object> objMap =ThirdAPI.getWxConfig(requestUrl, appid, authorizerAccessToken, ticket);
        return new JsonResult(true, objMap);
    }
    
    
     /** 
     * 授权事件接收 
     * 第三方开发平台每间隔10分钟调用一次
     * @param request 
     * @param response 
     * @throws IOException 
     * @throws AesException 
     * @throws DocumentException 
     */  
    @RequestMapping(value = "/event/authorize")  
    public void acceptAuthorizeEvent(HttpServletRequest request, HttpServletResponse response) throws IOException, AesException, DocumentException {  
         logger.info("微信第三方平台---------微信推送Ticket消息10分钟一次-----------");  
         processAuthorizeEvent(request);  
         output(response, "success"); // 输出响应的内容。  
    }  
      
    
    
    /**
     * 授权公众号的回调地址
     * 处理消息等用户操作时，请务必使用appid进行匹配
     * @param appid
     * @param request
     * @param response
     * @throws IOException
     * @throws AesException
     * @throws DocumentException
     */
    @RequestMapping(value = "{appid}/callback")  
    public void acceptMessageAndEvent(@PathVariable String appid,HttpServletRequest request, HttpServletResponse response) throws IOException, AesException, DocumentException {  
        String msgSignature = request.getParameter("msg_signature");  
        logger.info("第三方平台全网发布-------------{appid}/callback-----------验证开始。。。。msg_signature="+msgSignature);  
        if (StringUtil.isEmpty(msgSignature))  
            return;// 微信推送给第三方开放平台的消息一定是加过密的，无消息加密无法解密消息  
        StringBuilder sb = new StringBuilder();  
        BufferedReader in = request.getReader();  
        String line;  
        while ((line = in.readLine()) != null) {  
            sb.append(line);  
        }  
        in.close();  
   
        String xml = sb.toString();  
        Document doc = DocumentHelper.parseText(xml);  
        Element rootElt = doc.getRootElement();  
        String toUserName = rootElt.elementText("ToUserName"); 
        //微信全网测试账号  
        if (StringUtils.equalsIgnoreCase(APPID, appid) || StringUtils.equalsIgnoreCase(SMALL_APPID, appid)) {  
        logger.info("全网发布接入检测消息反馈开始---------------appid="+ appid +"------------------------toUserName="+toUserName);  
           checkWeixinAllNetworkCheck(appid,request,response,xml);  
        }  
//         TODO 建议使用缓存
//        SWxOpenAuthorizerApp app = getAuthorizerAccount(toUserName);
//        if(app !=null){
//            String appId = app.getAuthorizerAppid();
//            //消息处理
//            if (StringUtils.equalsIgnoreCase(appId, appid)) {  
//            logger.info("全网发布接入检测消息反馈开始---------------appid="+ appid +"------------------------toUserName="+toUserName);  
//               checkWeixinAllNetworkCheck(appid,request,response,xml);  
//            }  
//        }

    }  
      
    /** 
     * 第三方开放平台处理授权事件的推送 
     *  
     * @param request 
     * @throws IOException 
     * @throws AesException 
     * @throws DocumentException 
     */  
    public void processAuthorizeEvent(HttpServletRequest request) throws IOException, DocumentException, AesException {  
        String nonce = request.getParameter("nonce");  
        String timestamp = request.getParameter("timestamp");  
        String signature = request.getParameter("signature");  
        String msgSignature = request.getParameter("msg_signature");  
   
        if (StringUtil.isEmpty(msgSignature))
            return; // 微信推送给第三方开放平台的消息一定是加过密的，无消息加密无法解密消息  
        boolean isValid = checkSignature(COMPONENT_TOKEN, signature, timestamp, nonce);  
        if (isValid) {  
            StringBuilder sb = new StringBuilder();  
            BufferedReader in = request.getReader();  
            String line;  
            while ((line = in.readLine()) != null) {  
                sb.append(line);  
            }  
            String xml = sb.toString();  
            logger.info("第三方平台全网发布-----------------------原始 Xml="+xml);  
            String encodingAesKey = COMPONENT_ENCODINGAESKEY;// 第三方平台组件加密密钥  
            String appId = getAuthorizerAppidFromXml(xml);// 此时加密的xml数据中ToUserName是非加密的，解析xml获取即可  
            logger.info("第三方平台全网发布-------------appid----------getAuthorizerAppidFromXml(xml)-----------appId="+appId);  
            WXBizMsgCrypt pc = new WXBizMsgCrypt(COMPONENT_TOKEN, encodingAesKey, COMPONENT_APPID);  
            String type ="component_verify_ticket";
            xml = pc.decryptMsg(type,msgSignature, timestamp, nonce, xml);  
            logger.info("第三方平台全网发布-----------------------解密后 Xml="+xml);  
            processAuthorizationEvent(xml);  
        }  
    }  
      
    /** 
     * 授权公众号对应授权到第三方事件，接收到微信的请求，包括ticket推送和授权消息通知
     * @param xml 
     */  
    void processAuthorizationEvent(String xml){  
        Document doc;  
        try {  
            doc = DocumentHelper.parseText(xml);  
            Element rootElt = doc.getRootElement(); 
            //消息类型  
            String infoType = rootElt.elementText("InfoType");
            switch (InfoType.valueOf(infoType)) {  
            //授权成功，可以获得授权码；授权码也可以在流程图⑤中获得，所以可以忽略  
            case authorized:  
                break;  
            //取消授权，可以删除本地保存的已授权公众号  
            case unauthorized:  
                String appId = rootElt.elementText("AuthorizerAppid");  
               //TODO 删除本地授权的公众号  
                break;  
            //更新授权，可以更新授权方令牌authorizer_access_token，刷新令牌authorizer_refresh_token，权限集列表等  
            case updateauthorized:  
            //TODO 更新令牌等  
                break;  
            //推送ticket，妥善保存ticket，用于获取component_access_token  
            case component_verify_ticket:  
                String ticket = rootElt.elementText("ComponentVerifyTicket");  
               //存储ticket  
                logger.info("8、推送component_verify_ticket协议-----------ticket = "+ticket);  
                SWxOpenAccount  entity = getWeixinOpenAccount(COMPONENT_APPID);  
                entity = entity==null?new SWxOpenAccount():entity;  
                entity.setTicket(ticket);  
                entity.setComponentAppid(COMPONENT_APPID);  
                entity.setTicketTime(new Date());  
                swxOpenAccountService.saveOrUpdate(entity);  
               //获取第三方平台的接口调用凭证，有效期2小时
                ApiComponentTokenRequest apiComponentToken = new ApiComponentTokenRequest();  
                apiComponentToken.setComponent_appid(COMPONENT_APPID);  
                apiComponentToken.setComponent_appsecret(COMPONENT_APPSECRET);  
                //授权事件接收会每隔10分钟检验一下ticket的有效性，从而保证了此处的ticket是长期有效的
                apiComponentToken.setComponent_verify_ticket(ticket);
                //验证token有效性(2小时)
                String component_access_token = ThirdAPI.getAccessToken(apiComponentToken);  
                //验证预授权码有效性(10分钟)
                ThirdAPI.getPreAuthCode(COMPONENT_APPID, component_access_token);
                break;  
            default:  
                break;  
        }
        } catch (DocumentException e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 业务工具--获取开放平台账号信息 
     * @param appid 
     * @return 
     */  
    SWxOpenAccount getWeixinOpenAccount(String appid){  
        SWxOpenAccount entity = new SWxOpenAccount();
        entity.setComponentAppid(appid);
        SWxOpenAccount account = swxOpenAccountService.queryByModel(entity);
        return account;  
    }  
    
    /** 
     * 业务工具--获取授权方账号信息 
     * @param userName 原始id 
     * @return 
     */  
    SWxOpenAuthorizerApp getAuthorizerAccount(String userName){  
    	SWxOpenAuthorizerApp entity = new SWxOpenAuthorizerApp();
        entity.setUserName(userName);
        SWxOpenAuthorizerApp app = swxOpenAuthorizerAppService.queryByModel(entity);
        return app;  
    }  
      
    /** 
     * 微信工具--获取授权的Appid 
     * @param xml 
     * @return 
     */  
    String getAuthorizerAppidFromXml(String xml) {  
        Document doc;  
        try {  
            doc = DocumentHelper.parseText(xml);  
            Element rootElt = doc.getRootElement();  
            String AppId = rootElt.elementText("AppId");  
            return AppId;  
        } catch (DocumentException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return null;  
    }  
     
    /**
     * 全网验证
     * @param appid
     * @param request
     * @param response
     * @param xml
     * @throws DocumentException
     * @throws IOException
     * @throws AesException
     */
    public void checkWeixinAllNetworkCheck(String appid,HttpServletRequest request, HttpServletResponse response,String xml) throws DocumentException, IOException, AesException{  
        String nonce = request.getParameter("nonce");  
        String timestamp = request.getParameter("timestamp");  
        String msgSignature = request.getParameter("msg_signature");  

        WXBizMsgCrypt pc = new WXBizMsgCrypt(COMPONENT_TOKEN, COMPONENT_ENCODINGAESKEY, COMPONENT_APPID);  
        xml = pc.decryptMsg("user_message",msgSignature, timestamp, nonce, xml);  
   
        Document doc = DocumentHelper.parseText(xml);  
        Element rootElt = doc.getRootElement();  
        String msgType = rootElt.elementText("MsgType");  
        String toUserName = rootElt.elementText("ToUserName");  
        String fromUserName = rootElt.elementText("FromUserName");  
   
        logger.info("---全网发布接入检测--step.1-----------msgType="+msgType+"-----------------toUserName="+toUserName+"-----------------fromUserName="+fromUserName);  
        logger.info("---全网发布接入检测--step.2-----------xml="+xml);  
        if("event".equals(msgType)){  
             logger.info("---全网发布接入检测--step.3-----------事件消息--------");  
             String event = rootElt.elementText("Event");  
             replyEventMessage(request,response,event,toUserName,fromUserName,appid);  
        }else if("text".equals(msgType)){  
        	 logger.info("---全网发布接入检测--step.3-----------文本消息--------");  
             String content = rootElt.elementText("Content");  
             processTextMessage(request,response,content,toUserName,fromUserName,appid);  
        }  
    }  
      
    /**
     * 事件消息
     * @param request
     * @param response
     * @param event
     * @param toUserName
     * @param fromUserName
     * @param appid
     * @throws DocumentException
     * @throws IOException
     */
    public void replyEventMessage(HttpServletRequest request, HttpServletResponse response, String event, String toUserName, String fromUserName,String appid) throws DocumentException, IOException {  
        String content = event + "from_callback";  
        logger.info("---全网发布接入检测------step.4-------事件回复消息  content="+content + "   toUserName="+toUserName+"   fromUserName="+fromUserName);  
        replyTextMessage(request,response,content,toUserName,fromUserName);  
    }  
   
    /**
     * 文本消息
     * @param request
     * @param response
     * @param content
     * @param toUserName
     * @param fromUserName
     * @param appid
     * @throws IOException
     * @throws DocumentException
     */
    public void processTextMessage(HttpServletRequest request, HttpServletResponse response,String content,String toUserName, String fromUserName,String appid) throws IOException, DocumentException{  
        if("TESTCOMPONENT_MSG_TYPE_TEXT".equals(content)){  
            String returnContent = content+"_callback";  
            replyTextMessage(request,response,returnContent,toUserName,fromUserName);  
        }else if(StringUtils.startsWithIgnoreCase(content, "QUERY_AUTH_CODE")){  
            output(response, "");  
            //接下来客服API再回复一次消息  
            replyApiTextMessage(request,response,content.split(":")[1],fromUserName,appid);  
        }  
    }  
   
    /**
     * 客服消息
     * @param request
     * @param response
     * @param content
     * @param fromUserName
     * @param appid
     * @throws DocumentException
     * @throws IOException
     */
    public void replyApiTextMessage(HttpServletRequest request, HttpServletResponse response, String content, String fromUserName,String appid) throws DocumentException, IOException {  
        
        // 得到微信授权成功的消息后，应该立刻进行处理！！相关信息只会在首次授权的时候推送过来  
        System.out.println("------step.1----使用客服消息接口回复粉丝----逻辑开始-------------------------");  
        try {  
            ApiComponentTokenRequest apiComponentToken = new ApiComponentTokenRequest();  
            apiComponentToken.setComponent_appid(COMPONENT_APPID);  
            apiComponentToken.setComponent_appsecret(COMPONENT_APPSECRET);  
            SWxOpenAccount  entity = getWeixinOpenAccount(COMPONENT_APPID);//第三方平台平台
            apiComponentToken.setComponent_verify_ticket(entity.getTicket());  
            String component_access_token =entity.getComponentAccessToken();
            // 获取第三方信息
            SWxOpenAuthorizerApp sWxOpenAuthorizerApp = new SWxOpenAuthorizerApp();
            sWxOpenAuthorizerApp.setComponentAppid(COMPONENT_APPID);//开放平台
            sWxOpenAuthorizerApp.setAuthorizerAppid(appid);//授权方
            SWxOpenAuthorizerApp app = swxOpenAuthorizerAppService.queryByModel(sWxOpenAuthorizerApp);
            if(app !=null){
            	String authorizer_access_token = app.getAuthorizerAccessToken();
            	System.out.println("------step.2----使用客服消息接口回复粉丝------- component_access_token = "+component_access_token + "---------authorizer_access_token = "+authorizer_access_token);  
      			Map<String,Object> obj = new HashMap<String,Object>();  
                Map<String,Object> msgMap = new HashMap<String,Object>();  
                String msg = content + "_from_api";  
                msgMap.put("content", msg);  
                obj.put("touser", fromUserName);  
                obj.put("msgtype", "text");  
                obj.put("text", msgMap);  
                JSONObject json = ThirdAPI.sendMessage(obj, authorizer_access_token);  
                if(json.containsKey("errcode")){
                    int errcode = json.getIntValue("errcode");
                    logger.error("换取网页授权access_token失败，错误码："+errcode);
                    if(errcode==42001){
                    	//刷新并更新第三方code
                    	JSONObject authorizerJson = ThirdAPI.getAuthorizerToken(component_access_token, COMPONENT_APPID, appid, app.getAuthorizerRefreshToken());
                    	String authorizerAccessToken = authorizerJson.getString("authorizer_access_token");
            	        Integer expiresIn = authorizerJson.getInteger("expires_in");
            	        String authorizerRefreshToken = authorizerJson.getString("authorizer_refresh_token");
            	        app.setAuthorizerAccessToken(authorizerAccessToken);
            	        app.setAuthorizerRefreshToken(authorizerRefreshToken);
            	        app.setExpiresIn(expiresIn);
            	        swxOpenAuthorizerAppService.updateByPrimaryKeySelective(app);
            	        System.out.println("------step.3----使用客服消息接口回复粉丝-------------- 获取authorizerAccessToken = "+authorizerAccessToken);
                        ThirdAPI.sendMessage(obj, authorizerAccessToken);  
                    }
                }
            }   
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
    }     
      
    /** 
     * 验证是否过期 
     * @param accessTokenExpires 
     * @return 
     */  
    boolean isExpired(long accessTokenExpires){  
        return false;  
    }  
      
    /** 
     * 回复微信服务器"文本消息" 
     * @param request 
     * @param response 
     * @param content 
     * @param toUserName 
     * @param fromUserName 
     * @throws DocumentException 
     * @throws IOException 
     */  
    public void replyTextMessage(HttpServletRequest request, HttpServletResponse response, String content, String toUserName, String fromUserName) throws DocumentException, IOException {  
        Long createTime = Calendar.getInstance().getTimeInMillis() / 1000;  
        StringBuffer sb = new StringBuffer();  
        sb.append("<xml>");  
        sb.append("<ToUserName><![CDATA["+fromUserName+"]]></ToUserName>");  
        sb.append("<FromUserName><![CDATA["+toUserName+"]]></FromUserName>");  
        sb.append("<CreateTime>"+createTime+"</CreateTime>");  
        sb.append("<MsgType><![CDATA[text]]></MsgType>");  
        sb.append("<Content><![CDATA["+content+"]]></Content>");  
        sb.append("</xml>");  
        String replyMsg = sb.toString();  
          
        String returnvaleue = "";  
        try {  
            WXBizMsgCrypt pc = new WXBizMsgCrypt(COMPONENT_TOKEN, COMPONENT_ENCODINGAESKEY, COMPONENT_APPID);  
            returnvaleue = pc.encryptMsg(replyMsg, createTime.toString(), "easemob");  
//            System.out.println("------------------加密后的返回内容 returnvaleue： "+returnvaleue);  
        } catch (AesException e) {  
            e.printStackTrace();  
        }  
        output(response, returnvaleue);  
    }  
      
      
    public static void main(String[] args) {  
         Long createTime = Calendar.getInstance().getTimeInMillis() / 1000;  
         String replyMsg = "LOCATIONfrom_callback";  
           
         String returnvaleue = "";  
         try {  
             WXBizMsgCrypt pc = new WXBizMsgCrypt(COMPONENT_TOKEN, COMPONENT_ENCODINGAESKEY, COMPONENT_APPID);  
             returnvaleue = pc.encryptMsg(replyMsg, createTime.toString(), "easemob");  
             System.out.println(returnvaleue);  
         } catch (AesException e) {  
             e.printStackTrace();  
         }  
    }  
    /** 
     * 工具类：回复微信服务器"文本消息" 
     * @param response 
     * @param returnvaleue 
     */  
    public void output(HttpServletResponse response,String returnvaleue){  
        try {  
            PrintWriter pw = response.getWriter();  
            pw.write(returnvaleue);  
            pw.flush();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 判断是否加密 
     * @param token 
     * @param signature 
     * @param timestamp 
     * @param nonce 
     * @return 
     */  
    public static boolean checkSignature(String token,String signature,String timestamp,String nonce){  
        System.out.println("###token:"+token+";signature:"+signature+";timestamp:"+timestamp+"nonce:"+nonce);  
           boolean flag = false;  
           if(signature!=null && !signature.equals("") && timestamp!=null && !timestamp.equals("") && nonce!=null && !nonce.equals("")){  
              String sha1 = "";  
              String[] ss = new String[] { token, timestamp, nonce };   
              Arrays.sort(ss);    
              for (String s : ss) {    
               sha1 += s;    
              }    
       
              sha1 = AddSHA1.SHA1(sha1);    
       
              if (sha1.equals(signature)){  
               flag = true;  
              }  
           }  
           return flag;  
       }  
}  
  
  
class AddSHA1 {  
    public static String SHA1(String inStr) {  
        MessageDigest md = null;  
        String outStr = null;  
        try {  
            md = MessageDigest.getInstance("SHA-1");     //选择SHA-1，也可以选择MD5  
            byte[] digest = md.digest(inStr.getBytes());       //返回的是byet[]，要转化为String存储比较方便  
            outStr = bytetoString(digest);  
        }  
        catch (NoSuchAlgorithmException nsae) {  
            nsae.printStackTrace();  
        }  
        return outStr;  
    }  
      
      
    public static String bytetoString(byte[] digest) {  
        String str = "";  
        String tempStr = "";  
          
        for (int i = 0; i < digest.length; i++) {  
            tempStr = (Integer.toHexString(digest[i] & 0xff));  
            if (tempStr.length() == 1) {  
                str = str + "0" + tempStr;  
            }  
            else {  
                str = str + tempStr;  
            }  
        }  
        return str.toLowerCase();  
    }  
}
