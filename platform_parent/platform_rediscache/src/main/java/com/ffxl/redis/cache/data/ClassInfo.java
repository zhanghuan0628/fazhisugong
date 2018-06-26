package com.ffxl.redis.cache.data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import com.ffxl.redis.cache.annotation.Description;

/**
 * 类信息
 */
public class ClassInfo {
    private String beanName;
    private Class<?> interfaceCls;
    private Object proxyObject;
    private String category;
    private String description;
    private int sort;
    private Map<String, MethodInfo> methods;

    protected ClassInfo() {
        super();
        this.methods = new HashMap<String, MethodInfo>();
    }

    public ClassInfo(String beanName, Class<?> interfaceCls, Object proxyObject) {
        this();

        this.beanName = beanName;
        this.interfaceCls = interfaceCls;
        this.proxyObject = proxyObject;

        this.loadMethods();
        this.loadDescription();
    }

    protected void loadMethods() {
        for (Method method : this.interfaceCls.getDeclaredMethods()) {
            MethodInfo methodInfo = new MethodInfo(method);
            this.methods.put(method.getName(), methodInfo);
        }
    }

    protected void loadDescription() {
        Description description = interfaceCls.getDeclaredAnnotation(Description.class);
        if (description != null) {
            this.category = interfaceCls.getName();
            this.description = description.value();
            this.sort = description.sort();
        } else {
            this.category = interfaceCls.getName();
            this.description = null;
            this.sort = this.category.hashCode();
        }
    }

    public InvocationHandler getInvocationHandler() {
        return Proxy.getInvocationHandler(this.proxyObject);
    }

    public String getBeanName() {
        return beanName;
    }

    public Class<?> getInterfaceCls() {
        return interfaceCls;
    }

    public Object getProxyObject() {
        return proxyObject;
    }

    public String getCategory() {
        return category;
    }

    public int getSort() {
        return sort;
    }

    public Map<String, MethodInfo> getMethods() {
        return methods;
    }
}