package com.ffxl.admin.controller.modular;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;

import com.ffxl.admin.core.util.UploadImgUtil;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;

import cn.com.ffxl.OSSUtil;

@Controller
@RequestMapping(value = "/common")
public class CommonController {
	//图片存储
		//图片存储
		public static final String IMG_FFYY_WECHAT_SERVICE_PATH = "FaZhiSuSong/fzsg/"; 
		public static final String IMG_DEFAULT_PATH = "default"; //图片默认存储目录
		public static final String IMG_REQUEST_PATH = "catalog"; //图片请求存储目录关键字
	/**
     * 图片上传
     * @return
     * @throws IOException 
     */
    @RequestMapping(value="/img_upload")
    @ResponseBody
    public void imgUpload(HttpServletRequest request,HttpServletResponse response) throws IOException{
        String reqMethod = request.getMethod().toLowerCase();
        
        
        response.setContentType("textml;charset=UTF-8");
        String requestPath = (String) request.getParameter(IMG_REQUEST_PATH);
        if(StringUtil.isEmpty(requestPath)){
            requestPath = IMG_DEFAULT_PATH;
        }
        String imgUrl = null;// 头像路径
        try {
        Map<String, MultipartFile> files = ((AbstractMultipartHttpServletRequest) request).getFileMap();
        if (files.isEmpty()) {
            // 没有上传文件
            response.getWriter().write("error|未选择图片");
            return;
        }
        String  servicePath= IMG_FFYY_WECHAT_SERVICE_PATH;
       
        // 默认上传一个文件, 所以该循环只会跑一次
        for (String key : files.keySet()) {
            MultipartFile file = files.get(key);
            /**获取文件的后缀**/    
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toUpperCase(); 
            String filename = servicePath +requestPath+"/"+ UUIDUtil.getUUID()+ "."+suffix;
            OSSUtil.upload(filename, file.getBytes());
            String path = OSSUtil.getDownloadUrl();
            imgUrl = path+ "/" + filename;
            response.getWriter().write(imgUrl);
            break;
          }
        } catch (IOException e) {
            e.printStackTrace();
            response.getWriter().write("error|服务器异常");
            return;
        }
    }
    
    @RequestMapping(value = "/uploadToOss")
    @ResponseBody
    public JsonResult uploadToOss(String urls) {
      String[] urlss = urls.split(",");
      List<String> list = new ArrayList<String>();
      for (String url : urlss) {
        String res = UploadImgUtil.getInputStreamByGetForInfos(url);
        list.add(res);
      }
      return new JsonResult(Message.M2000,list);
    }
}
