/** 
 * @(#)SmsHttpUtil.java 1.0.0 2014年10月8日 下午3:16:39  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.slfinance.shanlinbao.sms.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 
 * 
 * @author caoyi
 * @version $Revision:1.0.0, $Date: 2014年10月8日 下午3:16:39 $
 */
public class HttpUtil {
	// 使用示例
	public static void main(String[] args) throws Exception {
		// 服务器地址
		String url = "http://sdk8.mdjc.net.cn:8888/sms.aspx";

		// 参数赋值
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userid", "xxx"));
		params.add(new BasicNameValuePair("account", "xxx"));
		params.add(new BasicNameValuePair("password", "xxx"));
		params.add(new BasicNameValuePair("mobile", "xxx"));
		params.add(new BasicNameValuePair("content", "你好测试UTF-8"));
		params.add(new BasicNameValuePair("sendTime", ""));
		params.add(new BasicNameValuePair("action", "send"));
		params.add(new BasicNameValuePair("extno", ""));

		// 提交请求
		String result = HttpUtil.request(url, params);
		System.out.println("提交结果：" + result);
	}

	/**
	 * @param url
	 * @param params
	 * @param authUser
	 * @param authPwd
	 * @return
	 * @throws Exception 
	 */
	public static String request(String url, List<NameValuePair> params,String authUser,String authPwd) throws Exception{
		CloseableHttpClient client = null;
		HttpPost httpPost = new HttpPost(url);
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
			httpPost.setEntity(entity);
			byte[] byte64=org.apache.commons.codec.binary.Base64.encodeBase64((authUser+":"+authPwd).getBytes());
			String aut="Basic "+new String(byte64);
			httpPost.setHeader("Authorization", aut);
			client = HttpClients.custom().build();
			HttpResponse response = client.execute(httpPost);
			String str=EntityUtils.toString(response.getEntity(),"UTF-8");
			return str;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			httpPost.releaseConnection();
			httpPost.abort();
			client.close();
		}
		return "";
	}
	/**
	 * 使用apache httpclient 进行post请求
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            参数
	 * @return 请求返回值
	 * @throws Exception
	 *             抛出异常
	 */
	public static String request(String url, List<NameValuePair> params) throws Exception {

		// 结果
		String result = null;

		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(url);
		UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(params, "UTF-8");
		httppost.setEntity(uefEntity);

		// 设置请求与数据处理的超时时间
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(20000).build();
		httppost.setConfig(requestConfig);

		// 提交请求
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			result = EntityUtils.toString(entity, "UTF-8");
		} else {
			httppost.releaseConnection();
			throw new Exception();
		}
		httppost.releaseConnection();
		return result;
	}
}
