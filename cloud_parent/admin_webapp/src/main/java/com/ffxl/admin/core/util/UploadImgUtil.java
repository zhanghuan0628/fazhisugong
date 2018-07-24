package com.ffxl.admin.core.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;

import cn.com.ffxl.OSSUtil;

public class UploadImgUtil {
	//图片存储
	//图片存储
	public static final String IMG_FFYY_WECHAT_SERVICE_PATH = "FaZhiSuSong/fzsg/"; 
	public static final String IMG_DEFAULT_PATH = "default"; //图片默认存储目录
	public static final String IMG_REQUEST_PATH = "catalog"; //图片请求存储目录关键字
  public static String getInputStreamByGet(String url) {
    try {
      HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
      conn.setReadTimeout(5000);
      conn.setConnectTimeout(5000);
      conn.setRequestMethod("GET");
      if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
        InputStream inputStream = conn.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int ch;
        while ((ch = inputStream.read()) != -1) {
          bos.write(ch);
        }
        byte[] resizedBytes = bos.toByteArray();
        String requestPath = "fzsg/shareimg";
        if (StringUtil.isEmpty(requestPath)) {
          requestPath = IMG_DEFAULT_PATH;
        }
        String servicePath = IMG_FFYY_WECHAT_SERVICE_PATH;
        String uuid = UUIDUtil.getUUID();
        String filename = servicePath + requestPath + "/" + uuid + ".jpg";
        OSSUtil.upload(filename, resizedBytes);
        return "http://sugong.oss-cn-shanghai.aliyuncs.com/" + filename;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  
  public static String getInputStreamByGetForInfos(String url) {
    try {
      HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
      conn.setReadTimeout(5000);
      conn.setConnectTimeout(5000);
      conn.setRequestMethod("GET");
      if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
        InputStream inputStream = conn.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int ch;
        while ((ch = inputStream.read()) != -1) {
          bos.write(ch);
        }
        byte[] resizedBytes = bos.toByteArray();
        String requestPath = "fzsg/images";
        if (StringUtil.isEmpty(requestPath)) {
          requestPath = IMG_DEFAULT_PATH;
        }
        String uuid = UUIDUtil.getUUID();
        String filename = requestPath + "/" + uuid + ".jpg";
        OSSUtil.upload(filename, resizedBytes);
        return "http://sugong.oss-cn-shanghai.aliyuncs.com/" + filename;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}
