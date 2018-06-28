package com.ffxl.admin.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring的ApplicationContext的持有者,可以用静态方法的方式获取spring容器中的bean
 * 
 * @author jiawei
 * 2018年6月28日
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {
	private static ApplicationContext appCtx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.appCtx = applicationContext;
	}

	public static <T> T getBean(Class<T> beanClass) throws BeansException {
		return appCtx.getBean(beanClass);
	}

	public static <T> T getBean(String name, Class<T> beanClass) throws BeansException {
		return appCtx.getBean(name, beanClass);
	}

	public static boolean containsBean(String name) {
		return appCtx.containsBean(name);
	}
}
