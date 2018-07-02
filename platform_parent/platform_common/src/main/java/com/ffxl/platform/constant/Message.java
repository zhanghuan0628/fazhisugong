package com.ffxl.platform.constant;



import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ffxl.platform.util.HttpHeader;


/**
 * 消息统一管理类
 * @author jiawei
 * 2018年6月22日
 */
public class Message {
    private static final Logger LOG = LoggerFactory.getLogger(Message.class);

    /**
     * 平台接口返回错误码分类： 操作成功:2000： 程序正常的处理了客户端的请求； 
     * 业务类错误:3xxx： 程序执行中断，存在不符合业务规则的错误，如:账号不存在，密码不正确，账号重复等；
     * 程序类错误:4xxx： 如:参数为空，参数格式不正确；
     * 服务执行错误:5xxx：程序执行非正常结束，主线业务处理失败，如数据库连接失败，图片处理失败，图片上传失败等错误；
     * 正常的中英文字符翻译(文案信息):6xxx
     * 
     * 消息文案文件：message_zh.properties、message_en.properties
     */
    /**
     * 操作成功
     */
    public static final String M2000 = "2000";
    
    
    //-----------------------正常的中英文字符翻译(文案信息):6xxx-------------开始
    /**
     * 不能停用超级管理员
     */
    public static final String M6000 = "6000";
    /**
     * 不能删除超级管理员
     */
    public static final String M6001 = "6001";
    /**
     * 登录账号已存在
     */
    public static final String M6002 = "6002";
    //-----------------------正常的中英文字符翻译(文案信息):6xxx-------------结束

    //-----------------------服务执行错误:5xxx-------------开始
    /**
     * 处理失败
     */
    public static final String M5000 = "5000";
    /**
     * 系统繁忙
     */
    public static final String M5001 = "5001";
    /**
     * 文件未找到！
     */
    public static final String M5002 = "5002";
    /**
     * 上传异常
     */
    public static final String M5003 = "5003";
    /**
     * 发送失败
     */
    public static final String M5004 = "5004";
    /**
     * 访问失败
     */
    public static final String M5005 = "5005";
    /**
     * 删除失败
     */
    public static final String M5006 = "5006";
    /**
     * 修改失败
     */
    public static final String M5007 = "5007";
    /**
     * 添加失败
     */
    public static final String M5009 = "5009";
    /**
     * 数据库操作错误
     */
    public static final String M5010 = "5010";
    /**
     * 短信发送失败
     */
    public static final String M5011 = "5011";
    /**
     * 手机号验证码缓存失效，请重新获取验证码
     */
    public static final String M5012 = "5012";
    /**
     * 短信验证失败
     */
    public static final String M5013 = "5013";
    /**
     * 微信支付参数配置错误
     */
    public static final String M5014 = "5014";
    /**
     * AliPay参数配置错误
     */
    public static final String M5015 = "5015";
    /**
     * 服务器异常
     */
    public static final String M5016 = "5016";
    //-----------------------服务执行错误:5xxx-------------结束
    

    //-----------------------程序类错误:4xxx-------------开始
    /**
     * 未知类型
     */
    public static final String M4000 = "4000";
    /**
     * {0}参数不正确
     */
    public static final String M4001 = "4001";
    /**
     * {0}参数不能为空
     */
    public static final String M4002 = "4002";
    /**
     * 
     * 参数不能为空
     */
    public static final String M4003 = "4003";
    /**
     * 参数不正确
     */
    public static final String M4004 = "4004";
    /**
     * 参数格式不正确
     */
    public static final String M4005 = "4005";
    /**
     * {0}参数类型错误
     */
    public static final String M4006 = "4006";
    /**
     * 请求头参数不完整
     */
    public static final String M4007 = "4007";
    /**
     * 数据不存在
     */
    public static final String M4008 = "4008";
    public static final String M4009 = "4009";
    //-----------------------程序类错误:4xxx-------------结束
 
    //-----------------------业务类错误:3xxx-------------开始
    /**
     * 账号不存在!
     */
    public static final String M3001 = "3001";
    /**
     * 密码错误
     */
    public static final String M3002 = "3002";
    /**
     * app_id无效
     */
    public static final String M3003 = "3003"; 
    /**
     * 手机号格式错误
     */
    public static final String M3004 = "3004";
    /**
     * 图片数据不完整，请换一张
     */
    public static final String M3005 = "3005";
    /**
     * 权限异常
     */
    public static final String M3006 = "3006";
    public static final String M3007 = "3007";
    public static final String M3008 = "3008";
    public static final String M3009 = "3009";
    public static final String M3010 = "3010";
    public static final String M3011 = "3011";
    public static final String M3012 = "3012";
    public static final String M3013 = "3013";
    public static final String M3014 = "3014";
    public static final String M3015 = "3015";
    //-----------------------业务类错误:3xxx-------------结束
    
    private static Properties PROP_ZH = new Properties();
    private static Properties PROP_EN = new Properties();

    static {
        loads();
    }

    /**
     * 加载消息初始化信息
     */
    synchronized static public void loads() {
        LOG.info("载入消息信息   开始");
        InputStream is1 = Message.class.getResourceAsStream("/message_zh.properties");
        try {
            PROP_ZH.load(is1);
            is1.close();
        } catch (Exception e) {
            // System.err.println("不能读取属性文件. ");
            LOG.error("不能读取属性文件.");
        }

        InputStream is2 = Message.class.getResourceAsStream("/message_en.properties");
        try {
            PROP_EN.load(is2);
            is2.close();
        } catch (Exception e) {
            // System.err.println("不能读取属性文件. ");
            LOG.error("不能读取属性文件.");
        }
        LOG.info("载入消息信息   结束");
    }

    /**
     * 根据消息code获取消息文案信息
     * 
     * @param code
     *            消息code
     * @return 消息文案
     */
    public static String getMessage(String code) {
        Thread current = Thread.currentThread();
        LOG.info("$$$$$$$$$$$$$$$$ getMessage Thread id=" + current.getId() + "  name=" + current.getName());

        boolean isEnglish = false;
        HttpHeader header = HttpHeader.get();
        if (header != null) {
            LOG.info("$$$$$$$$$$$$$$$$ getMessage header=" + header.toString());
            isEnglish = header.isLanguage(HttpHeader.LANGUAGE_EN);
        }
        LOG.info("$$$$$$$$$$$$$$$$ getMessage isEnglish=" + isEnglish);
        String msg = "";
        if (isEnglish) {
            // 英文
            msg = String.valueOf(PROP_EN.get(code));
        } else {
            // 中文
            msg = String.valueOf(PROP_ZH.get(code));
        }
        LOG.info("$$$$$$$$$$$$$$$$ getMessage code值："+code+"；message内容 : " + msg);
        return msg;
        
    }

    /**
     * 可传递参数，并根据消息code获取消息文案信息
     * 
     * @param code
     *            消息code
     * @param args
     *            消息参数
     * @return 消息文案
     */
    public static String getMessage(String code, Object... args) {
        String msg = MessageFormat.format(getMessage(code), args);
        return msg;
    }
}
