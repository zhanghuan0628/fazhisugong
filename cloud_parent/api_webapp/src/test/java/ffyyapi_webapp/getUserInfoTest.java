package ffyyapi_webapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;

import com.ffxl.platform.wechat.model.WechatUser;
import com.ffxl.platform.wechat.token.AccessToken;

public class getUserInfoTest {

  public static void main(String[] args) {

    String openid = "oi8KuwVJJLED4M-1DWl4KNnwHnXo";
    AccessToken token = new AccessToken();
    token.setAccess_token("5_nWpyKTTj6sGFSOfpdkoety72M8XubQ_PO99w8QaREsuxmRfXYhjMK239xdy0X69L4qyakSEL0cn9YWCaNCxyXsTCcBuXaDy_vHlF9fS7pBjnq3cStlqTmTHhQ9IEFViACARSI");
    JSONObject jsonObject = getSnsapiUserInfo(openid, token, "zh_CN");
    WechatUser chatUser = (WechatUser) JSONObject.toBean(jsonObject, WechatUser.class);
    String unionid = chatUser.getUnionid();
    
    JSONObject jo =getUserInfo(openid, token, "zh_CN");
    WechatUser chatUser2 = (WechatUser) JSONObject.toBean(jsonObject, WechatUser.class);
  }

  
  
  public static JSONObject getSnsapiUserInfo(String openid, AccessToken accessToken, String lang) {
    String apiUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken.getAccess_token() + "&openid=" + openid + "&lang=" + lang;
    JSONObject json = null;
    try {
      URL getUrl = new URL(apiUrl);
      HttpsURLConnection http = (HttpsURLConnection) getUrl.openConnection();
      http.setRequestMethod("GET");
      http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      http.setDoOutput(true);
      http.setDoInput(true);
      http.connect();
      InputStream is = http.getInputStream();
      int size = is.available();
      byte[] b = new byte[size];
      is.read(b);
      String message = new String(b, "UTF-8");
      json = JSONObject.fromObject(message);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return json;
  }
  
  
  public static JSONObject getUserInfo(String openid, AccessToken accessToken, String lang) {
    String apiUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken.getAccess_token() + "&openid=" + openid + "&lang="
        + lang;
    JSONObject json = null;
    try {
      URL getUrl = new URL(apiUrl);
      HttpsURLConnection http = (HttpsURLConnection) getUrl.openConnection();
      http.setRequestMethod("GET");
      http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      http.setDoOutput(true);
      http.setDoInput(true);
      http.connect();
      InputStream is = http.getInputStream();
      int size = is.available();
      byte[] b = new byte[size];
      is.read(b);
      String message = new String(b, "UTF-8");
      json = JSONObject.fromObject(message);
      if (json.containsKey("errcode")) {
        
      } else {
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return json;
  }
  
  
}
