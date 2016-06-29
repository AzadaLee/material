package com.slfinance.shanlinbao.sms.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author taoxm
 * @version
 */
@Component
@Lazy(false)
public class ApplicationContextUtils implements ApplicationContextAware{

	private static ApplicationContext ctx;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		System.out.println("---------------上下文初始化开始-------------------");
		ctx = applicationContext;
		System.out.println("---------------上下文初始化结束-------------------");
	}

	public static Object getBean(String name){
		return ctx.getBean(name);
	}
	
	public static <T>T getBean(Class<T> clazz){
		return ctx.getBean(clazz);
	}
	
	public static <T> T getBean(String beanName, Class<T> clazz){
		return ctx.getBean(beanName, clazz);
	}
}
