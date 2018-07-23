package cn.com.ffxl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

public class OSSUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(OSSUtil.class);

    /**
     * 访问的阿里云路径
     */
    private static String ossEndpoint ="oss-cn-shanghai.aliyuncs.com";

    /**
     * 阿里云oss使用的accessId
     */
//    private static String ossAccessId="LTAIgrOfu9fWCy9T";  //
    private static String ossAccessId="LTAICG7rs8rsGNj4";  //公司账号
    

    /**
     * 阿里云oss使用的ossAccessKey
     */
//    private static String ossAccessKey="Yq08FhNRQNQk5lThu6wNSAMP8QwhLW";
    private static String ossAccessKey="FDtacJMEQXKRwIPgK3WKYR2Cyv8xKm";

    /**
     * bucket名称
     */
//    private static String defaultBucket="ffyy";
    private static String defaultBucket="sugong";

    /**
     * 允许打开的最大HTTP连接数。默认为50
     */
    private int ossMaxConnection=50;

    /**
     * 可重试的请求失败后最大的重试次数。默认为3次
     */
    private int ossMaxErrorRetry=3;

    /**
     * 建立连接的超时时间（单位：毫秒）。默认为50000毫秒
     */
    private int ossConnectionTimeout=50000;

    /**
     * 通过打开的连接传输数据的超时时间（单位：毫秒）。默认为50000毫秒
     */
    private int ossSocketTimeout=50000;

    private static ClientConfiguration conf;
    
    public ObjectMetadata getObjectMetadata(String key) {
        return getOSSClient().getObjectMetadata(defaultBucket, key);
    }

    private static OSSClient getOSSClient() {
        return new OSSClient(ossEndpoint, ossAccessId, ossAccessKey, conf);
    }

    private static String uploadFile(String bucketName, String key, ObjectMetadata objectMeta, InputStream input) {
        OSSClient client = getOSSClient();
        PutObjectResult result = client.putObject(bucketName, key, input, objectMeta);
        client.shutdown();
        return result.getETag();
    }

    /**
     * 上传流文件
     * @param key
     * @param is
     * @return
     */
    public String upload(String key, InputStream is) {
        try {
            byte[] bytes = IOUtils.toByteArray(is);

            return this.upload(key, bytes);

        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return "";
        }
    }
    
    

    /**
     * 上传字节文件
     * @param key
     * @param bytes
     * @return
     */
    public static String upload(String key, byte[] bytes) {

        // 必须在这里做转换,不然无法成功上传
        InputStream input = new ByteArrayInputStream(bytes);

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(bytes.length);
        objectMeta.setContentType("image/jpeg");

        return uploadFile(defaultBucket, key, objectMeta, input);
    }
    
    
    public String upload(String key, byte[] bytes,String contentType) {

        // 必须在这里做转换,不然无法成功上传
        InputStream input = new ByteArrayInputStream(bytes);

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(bytes.length);
        if(contentType!=null){
            objectMeta.setContentType(contentType);
        }

        return uploadFile(defaultBucket, key, objectMeta, input);
    }

   
    public String upload(String bucketName, String key, String filename)
            throws FileNotFoundException {

        File file = new File(filename);

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(file.length());
        objectMeta.setContentType("image/jpeg");

        InputStream input = new FileInputStream(file);

        return uploadFile(bucketName, key, objectMeta, input);
    }
    
    public String upload(String key, String filename)
            throws FileNotFoundException {

        File file = new File(filename);

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(file.length());
        objectMeta.setContentType("image/jpeg");

        InputStream input = new FileInputStream(file);

        return uploadFile(defaultBucket, key, objectMeta, input);
    }

    
    public void download(String key, String filename) {
        GetObjectRequest getObjectRequest = new GetObjectRequest(defaultBucket, key);
        File file = new File(filename);
        getOSSClient().getObject(getObjectRequest, file);
    }

    
    public String upload(String key, InputStream is, String contentType) {
        byte[] bytes = new byte[] {};

        try {
            bytes = IOUtils.toByteArray(is);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return "";
        }

        // 必须在这里做转换,不然无法成功上传
        InputStream input = new ByteArrayInputStream(bytes);

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(bytes.length);
        objectMeta.setContentType(contentType);

        return uploadFile(defaultBucket, key, objectMeta, input);
    }

    /**
     * 获取下载url地址
     * 
     */
    
    public static String getDownloadUrl() {
        return "http://"+defaultBucket +"."+ossEndpoint ;
    }
    
    /**
     * 获取CDN下载url地址
     * 
     */
    
    public static String getCDNDownloadUrl() {
        return "http://img.feifanxinli.com" ;
    }
    
    /**  
     * 根据key删除OSS服务器上的文件  
     * @param key Bucket下的文件的路径名+文件名 
     */    
       public static void deleteFile(String key){    
         OSSClient client = getOSSClient();
         client.deleteObject(defaultBucket, key);   
         client.shutdown();
         LOGGER.info("删除" + defaultBucket + "下的文件"+ key + "成功");  
       } 
}
