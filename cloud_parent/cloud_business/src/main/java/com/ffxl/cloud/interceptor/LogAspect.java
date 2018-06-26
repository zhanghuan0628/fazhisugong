package com.ffxl.cloud.interceptor;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;


/**
 * 系统日志AOP 的
 * @author wison
 */
public class LogAspect implements MethodBeforeAdvice  {
	private final Logger log = LoggerFactory.getLogger(LogAspect.class);  
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		log.info("==============AOP,调用方法之前================");    
//		log.info("Thread Name :"+Thread.currentThread().getName());
//		 //调用目标方法之前执行的动作
		log.info(method.toString());    
//		String strArgs[] = method.toString().split(" ");
//		if(strArgs.length >0 ){
//			if("public".equals(strArgs[0]) ){
//				log.info("拦截到的方法修饰符:"+strArgs[0] );  
////				Thread serviceThread = new Thread(new ServiceThread(target.getClass().getName()));
////	            serviceThread.start();
//			}
//		}
	} 
	
	
	 // 一次调用启动一个线程执行
    private class ServiceThread implements Runnable {
        String serviceName;
        public ServiceThread(String serviceName) {
            this.serviceName = serviceName;
        }

        public void run() {
        	log.info("启动新线程:");  
//        	new Sender(serviceName);
//			new Receiver();
        }
    }
}
