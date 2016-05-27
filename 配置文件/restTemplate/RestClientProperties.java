/** 
 * @(#)RestClient.java 1.0.0 2014年6月19日 下午2:25:50  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.slfinance.shanlinbao.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import lombok.Data;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;


/**
 * RectClient Properties
 * 
 * @author 孟山
 * @version $Revision:1.0.0, $Date: 2014年6月19日 下午2:25:50 $
 */
@Configuration
@Data
public class RestClientProperties  {
	private static final String PROPERTIES_RESOURCE_LOCATION = "restClient.properties";
	
	private static final Log logger = LogFactory.getLog(RestClientProperties.class);
	
	private static final Properties localProperties = new Properties();
	static {
		//File Resource
		try {
			URL url = ResourceUtils.getURL("config/" + PROPERTIES_RESOURCE_LOCATION);
			if(url != null) {
				InputStream is = url.openStream();
				try {
					logger.info("Found 'restClient.properties' file in local config file");
					localProperties.load(is);
				}
				finally {
					is.close();
				}
			}
		} catch (IOException ex) {
			try {
				URL url = ResourceUtils.getURL(PROPERTIES_RESOURCE_LOCATION);
				if(url != null) {
					InputStream is = url.openStream();
					try {
						logger.info("Found 'restClient.properties' file in local file");
						localProperties.load(is);
					}
					finally {
						is.close();
					}
				}
			}catch (IOException ex2) {
				try {
					//ClassPath Resource
					ClassLoader cl = RestClientProperties.class.getClassLoader();
					URL url = (cl != null ? cl.getResource(PROPERTIES_RESOURCE_LOCATION) :
							ClassLoader.getSystemResource(PROPERTIES_RESOURCE_LOCATION));
					if (url != null) {
						InputStream is = url.openStream();
						try {
							logger.info("Found 'restClient.properties' file in local classpath");
							localProperties.load(is);
						}
						finally {
							is.close();
						}
					}
				}
				catch (IOException ex3) {
					if (logger.isInfoEnabled()) {
						logger.info("Could not load 'spring.properties' file from local classpath: " + ex3);
					}
				}	
			}
		}
	}
	
	private FoundtionClient foundtionClient = new FoundtionClient();
	
	private TppClient tppClient = new TppClient();
	
	@Data
	public static class FoundtionClient {
		String appSource = localProperties.getProperty("foundation.appSource" , "web");
		int readTimeout = Integer.parseInt(localProperties.getProperty("foundation.readTimeout" , "60000"));
		int connectTimeout = Integer.parseInt(localProperties.getProperty("foundation.connectTimeout" , "60000"));;
		String  md5Seeds = localProperties.getProperty("foundation.md5Seeds" ,"er4uydfjdkf6");
		String user = localProperties.getProperty("foundation.user" ,"test");
		String pwd = localProperties.getProperty("foundation.pwd" ,"test");
		String servicePrefix = localProperties.getProperty("foundation.servicePrefix" ,"http://localhost:8090/");
	}
	
	@Data
	public static class TppClient {
		int readTimeout = Integer.parseInt(localProperties.getProperty("tpp.readTimeout" , "60000"));
		int connectTimeout = Integer.parseInt(localProperties.getProperty("tpp.connectTimeout" , "60000"));;
		String servicePrefix = localProperties.getProperty("tpp.servicePrefix" ,"http://localhost:8090/");
	}

}
