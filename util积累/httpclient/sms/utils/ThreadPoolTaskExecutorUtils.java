package com.slfinance.shanlinbao.sms.utils;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
@Component("threadPoolTaskExecutor")
public class ThreadPoolTaskExecutorUtils extends ThreadPoolTaskExecutor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 884024532015928490L;
	public ThreadPoolTaskExecutorUtils(){
		super.setCorePoolSize(5);
		super.setMaxPoolSize(100);
		super.setQueueCapacity(60000);
		super.setKeepAliveSeconds(300);
		super.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
	}
	
}
