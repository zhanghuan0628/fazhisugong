package com.ffxl.admin.core.log;


import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.ffxl.admin.core.util.ApplicationContextUtils;

/**
 * 被修改的bean临时存放的地方
 * @author jiawei
 * 2018年6月27日
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class LogObjectHolder implements Serializable{

    private Object object = null;

    public void set(Object obj) {
        this.object = obj;
    }

    public Object get() {
        return object;
    }

    public static LogObjectHolder me(){
        LogObjectHolder bean = ApplicationContextUtils.getBean(LogObjectHolder.class);
        return bean;
    }
}
