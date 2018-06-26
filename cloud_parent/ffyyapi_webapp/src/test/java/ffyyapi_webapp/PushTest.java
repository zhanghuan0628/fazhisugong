package ffyyapi_webapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ffxl.app.controller.active.v2p02.WechatPayActiveOrderV202Controller;
import com.ffxl.cloud.model.BActive;
import com.ffxl.cloud.model.BActiveOrder;
import com.ffxl.cloud.model.BActiveProgrammePrice;
import com.ffxl.cloud.model.BNotice;
import com.ffxl.cloud.model.BUser;
import com.ffxl.cloud.service.BActiveOrderService;
import com.ffxl.cloud.service.BActiveProgrammePriceService;
import com.ffxl.cloud.service.BActiveService;
import com.ffxl.cloud.service.BNoticeService;
import com.ffxl.cloud.service.BUserService;
import com.ffxl.cloud.service.STimetaskService;
import com.ffxl.cloud.util.wxmsg.ApplicationContextUtils;
import com.ffxl.platform.util.StringUtil;

import cn.com.ffxl.push.AndroidNotification;
import cn.com.ffxl.push.AppType;
import cn.com.ffxl.push.PushClient;
import cn.com.ffxl.push.PushMessage;
import cn.com.ffxl.push.android.AndroidBroadcast;
import cn.com.ffxl.push.android.AndroidCustomizedcast;
import cn.com.ffxl.push.android.AndroidFilecast;
import cn.com.ffxl.push.android.AndroidGroupcast;
import cn.com.ffxl.push.android.AndroidPushMessageModel;
import cn.com.ffxl.push.android.AndroidUnicast;
import cn.com.ffxl.push.ios.IOSBroadcast;
import cn.com.ffxl.push.ios.IOSCustomizedcast;
import cn.com.ffxl.push.ios.IOSFilecast;
import cn.com.ffxl.push.ios.IOSGroupcast;
import cn.com.ffxl.push.ios.IOSPushMessageModel;
import cn.com.ffxl.push.ios.IOSUnicast;
import cn.com.ffxl.rong.server.RongCloudMethodUtil;
import cn.com.ffxl.rong.server.RongCloudUtil;

public class PushTest {
  private static final Logger logger = LoggerFactory.getLogger(PushTest.class);
	public static void main(String[] args) {
//		PushMessage demo = new PushMessage(AppType.ffxl_ios_app);
//		IOSPushMessageModel model = new IOSPushMessageModel();
//		model.setAlert("封装测试");
//		model.setDeviceToken("2fe72ecbe08f53bd78e10f751dc899d251bc8baf98a54ab89402cf2fd0a2efa4");
//	    try {
//        demo.sendIOSUnicast(model);
//      } catch (Exception e) {
//        e.printStackTrace();
//      }	
	  
	  sendMessage("95cf058c483646549585dd74d7ba9127");
	  
	  
//	  
//	  PushMessage demo = new PushMessage(AppType.fxf_ios_app);
//      IOSPushMessageModel model = new IOSPushMessageModel();
//      model.setAlert("封装测试");
//      model.setDeviceToken("83b57ec6c23db04a0072455358c95f843c052bf92f29910a48222244abef3e4c");
//      try {
//      demo.sendIOSUnicast(model);
//    } catch (Exception e) {
//      e.printStackTrace();
//    } 
	  
	  
    
//   PushMessage demo = new PushMessage(AppType.ffxl_android_app);
//     AndroidPushMessageModel model = new AndroidPushMessageModel();
//     model.setTitle("封装测试");
//     model.setTicker("封装测试ticker");
//     model.setText("封装测试text");
//     model.setDeviceToken("AmVQwJE5uxbEXe4Fy53rNQR0lDgxZX2AXR5Q6dRo_oqb");
//     try {
//     demo.sendAndroidUnicast(model);
//   } catch (Exception e) {
//     e.printStackTrace();
//   } 
	  
//	  
//	   PushMessage demo = new PushMessage(AppType.fxf_android_app);
//	     AndroidPushMessageModel model = new AndroidPushMessageModel();
//	     model.setTitle("封装测试");
//	     model.setTicker("封装测试ticker");
//	     model.setText("封装测试text");
//	     model.setDeviceToken("AsDhsSh_YUcgtXhJvOZLaSgJshH4tGZA69aRjcAxUXVE");
//	     try {
//	     demo.sendAndroidUnicast(model);
//	   } catch (Exception e) {
//	     e.printStackTrace();
//	   } 
	  
	    
	}
	
	
	public static void sendMessage(String orderId) {
	   // 这里从数据库中获取任务信息数据
	  BActiveOrderService bActiveOrderService = (BActiveOrderService) ApplicationContextUtils.getBean(BActiveOrderService.class);
	  BActiveService bActiveService = (BActiveService) ApplicationContextUtils.getBean(BActiveService.class);
	  BActiveProgrammePriceService bActiveProgrammePriceService = (BActiveProgrammePriceService) ApplicationContextUtils.getBean(BActiveProgrammePriceService.class);
	  BNoticeService bNoticeService = (BNoticeService) ApplicationContextUtils.getBean(BNoticeService.class);
	  BUserService buserService = (BUserService) ApplicationContextUtils.getBean(BUserService.class);
	  
	    logger.info("短信通知开始=============================================================");
	    // 获取手机号
	    String content = "";//短信内容
	    String pushContent =  "";//手机推送内容
	    BActiveOrder order = bActiveOrderService.selectByPrimaryKey(orderId);
	    BActive active = new BActive();
	    if (order != null) {
	      active = bActiveService.selectByPrimaryKey(order.getActiveId());
	      BActiveProgrammePrice price = bActiveProgrammePriceService.selectByPrimaryKey(order.getProgrammePriceId());
	      if (price != null) {
	        if (price.getSurplusEnrollCount() != null && price.getSurplusEnrollCount() != 0) {
	          price.setSurplusEnrollCount(price.getSurplusEnrollCount() - order.getQuantity());
	          bActiveProgrammePriceService.updateByPrimaryKeySelective(price);
	        }
	      }
	    }
	    //生成通知
	    if (active != null) {
	      BNotice bNotice = new BNotice();
	      bNotice.setSourceId(order.getId());
	      bNotice.setUserId(order.getUserId());
	      bNotice.setSourceType(BNotice.BNOTICE_ACTIVE_TYPE);
	      bNotice.setSourceType(BNotice.BNOTICE_ACTIVE_TYPE);
	      bNotice.setNoticeCotent("您的《" + active.getActiveName() + "》报名订单已经成功！");
	      bNoticeService.createModel(bNotice);
	    }
	 // {p1}
	    Map<String, String> puMap = new HashMap<String, String>();
	    if (order.getEnrollType().equals(BActive.ACTIVE_ENROLL_TYPE_NORMAL)
	        || order.getEnrollType().equals(BActive.ACTIVE_ENROLL_TYPE_GRATIS)) {
	      /**常价、免费*/
	      content = "您已成功报名《" + active.getActiveName() + "》，电子消费码为" + order.getIdentifyingCode() + ",请妥善保管。客服电话:400-080-9291";
	      pushContent = "您已成功报名《" + active.getActiveName() + "》";
	    } else if (order.getEnrollType().equals(BActive.ACTIVE_ENROLL_TYPE_CROWDFUNDING)) {
	      /**众筹*/
	      SimpleDateFormat f =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	      BActiveProgrammePrice price = bActiveProgrammePriceService.selectByPrimaryKey(order.getProgrammePriceId());  
	      System.out.println("########当前时间:" + f.format(price.getEnrollEndTime()));  
	      Calendar c = Calendar.getInstance();  
	      c.setTime(price.getEnrollEndTime());  
	      c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天  
	      Date tomorrow = c.getTime();  
	      System.out.println("########时间+1天:" + f.format(tomorrow));
	      content = "您众筹的《" + active.getActiveName() + "》已报名成功，众筹结果将于" + f.format(tomorrow) + "揭晓。客服电话:400-080-9291";
	      pushContent = "您已成功报名《" + active.getActiveName() + "》";
	    } else if (order.getEnrollType().equals(BActive.ACTIVE_ENROLL_TYPE_PRESELL)) {
	      /**预售*/
	      SimpleDateFormat f =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	      BActiveProgrammePrice price = bActiveProgrammePriceService.selectByPrimaryKey(order.getProgrammePriceId());  
	      System.out.println("########当前时间:" + f.format(price.getEnrollEndTime()));  
	      Calendar c = Calendar.getInstance();  
	      c.setTime(price.getEnrollEndTime());  
	      c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天  
	      Date tomorrow = c.getTime();  
	      System.out.println("########时间+1天:" + f.format(tomorrow));
	      content = "您众筹的《" + active.getActiveName() + "》已报名成功，预售结果将于" + f.format(tomorrow) + "揭晓。客服电话:400-080-9291";
	      pushContent = "您已成功报名《" + active.getActiveName() + "》";
	    } else if (order.getEnrollType().equals(BActive.ACTIVE_ENROLL_TYPE_WITH_FRIEND)) {
	      /**约伴*/
//	      content = "您已成功约伴《" + active.getActiveName() + "》，请带上您的小伙伴，根据您的电子票进场参加";
	    }
	    if (StringUtil.isEmpty(content)) {
	      return;
	    }
	    puMap.put("p1", content);
	    RongCloudMethodUtil userUtil = new RongCloudMethodUtil(RongCloudUtil.APP_FFXL);// 来访者根据订单的不同来使用不同模板
	    RongCloudUtil userRongUtil = new RongCloudUtil(RongCloudUtil.APP_FFXL);
	    String resut2 = userUtil.sendNotify(order.getCellphone(), userRongUtil.TEMP_MESSAGE_COMMON, puMap);// 通用模版
	    JSONObject obj2 = JSONObject.fromObject(resut2);
	    if (obj2.containsKey("code")) {
	      Integer code2 = (Integer) obj2.get("code");
	      if (code2 == 200) {
	        String sessionId = (String) obj2.get("sessionId");
	        logger.info(">>>>>>>>>>>>>>>>通知用户" + "userid" + "成功,短信唯一标识:" + sessionId);
	      } else {
	        logger.info(">>>>>>>>>>>>>>>>错误码：code:" + code2);
	      }
	    }
	    logger.info("短信通知结束=============================================================");
	    //手机推送
	    BUser u = buserService.selectByPrimaryKey(order.getUserId());
	    if (u != null) {
	      if (!StringUtil.isEmpty(u.getPhoneType())) {
	        if (u.getPhoneType().equals("ffxl_ios_app")) {
	          PushMessage demo = null;
	          demo = new PushMessage(AppType.ffxl_ios_app);//model.getPhoneType()
	          IOSPushMessageModel message = new IOSPushMessageModel();
	          message.setAlert(active.getActiveName());
	          message.setSound("");//Ticker("active test title");
	          message.setContent_available(pushContent);
	          message.setDeviceToken(u.getDeviceToken());
	          Map<String, String> map = new HashMap<String, String>();
	          map.put("type", "active_order");
	          map.put("id", order.getId());
	          message.setNotificationExtra(map);
	          try {
	            demo.sendIOSUnicast(message);
	          } catch (Exception e) {
	            e.printStackTrace();
	          }
	        } else if (u.getPhoneType().equals("ffxl_android_app")) {
	          PushMessage demo = null;
	          demo = new PushMessage(AppType.ffxl_android_app);//model.getPhoneType()
	          AndroidPushMessageModel message = new AndroidPushMessageModel();
	          message.setTitle(active.getActiveName());
	          message.setTicker(active.getActiveName());//Ticker("active test title");
	          message.setText(pushContent);
	          message.setDeviceToken(u.getDeviceToken());
	          Map<String, String> map = new HashMap<String, String>();
	          map.put("type", "active_order");
	          map.put("id", order.getId());
	          message.setNotificationExtra(map);
	          try {
	            demo.sendAndroidUnicast(message);
	          } catch (Exception e) {
	            e.printStackTrace();
	          }
	        }
	      }
	    }
	  }
}
