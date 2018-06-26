package cn.com.ffxl;

import java.util.Properties;

public class AppProperties {
    //融云相关的配置参数
    public static Properties rongyunProperties;

    public static Properties getRongyunProperties() {
        return rongyunProperties;
    }

    public static void setRongyunProperties(Properties rongyunProperties) {
        AppProperties.rongyunProperties = rongyunProperties;
    }
    
    
}
