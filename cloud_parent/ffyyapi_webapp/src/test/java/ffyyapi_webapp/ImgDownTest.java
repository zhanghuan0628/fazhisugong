package ffyyapi_webapp;
import java.io.BufferedInputStream;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.net.HttpURLConnection;  
import java.net.URL;  
import java.util.Date;
import java.util.Random;  
  


import sun.misc.BASE64Decoder;  
public class ImgDownTest {
    public static void main(String[] args) throws Exception {  
        String str = "http://ffxl.oss-cn-shanghai.aliyuncs.com/ffyy/wechat/card/ea2d88cd56c94ee8bc2489c4d63761ab.JPG";  
        ImgDownTest dw=new ImgDownTest();     
        Date d=new Date();
        dw.saveToFile(str,"D:\\"+d.getTime()+".png");    
        System.out.println("成功");
    }  
  
    /** 
     * 根据网络地址保存图片 
     * @param destUrl 网络地址 
     * @param filePath 图片存储路径 
     */  
    public void saveToFile(String destUrl,String filePath) {    
        FileOutputStream fos = null;    
        BufferedInputStream bis = null;    
        HttpURLConnection httpUrl = null;    
        URL url = null;    
        int BUFFER_SIZE = 1024;    
        byte[] buf = new byte[BUFFER_SIZE];    
        int size = 0;    
        try {    
            url = new URL(destUrl);    
            httpUrl = (HttpURLConnection) url.openConnection();    
            httpUrl.connect();    
            bis = new BufferedInputStream(httpUrl.getInputStream());    
            fos = new FileOutputStream(filePath);    
            while ((size = bis.read(buf)) != -1) {     
                fos.write(buf, 0, size);    
            }    
            fos.flush();    
        } catch (IOException e) {    
        } catch (ClassCastException e) {    
        } finally {    
            try {    
                fos.close();    
                bis.close();    
                httpUrl.disconnect();    
            } catch (IOException e) {    
            } catch (NullPointerException e) {    
            }    
        }    
    }    
}
