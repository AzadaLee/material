/** 
 * @(#)JSONHttpClient.java 1.0.0 2016年3月14日 下午3:38:08  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */ 

package com.slfinance.shanlinbao.sms.utils;

import java.security.MessageDigest;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class JSONHttpClient {
	private static final Log LOG = LogFactory.getLog(JSONHttpClient.class);
	private int conTimeOut = 60000;
	private int readTimeOut = 60000;
	private static String url = "";
	private static JSONHttpClient instance = new JSONHttpClient();

	public static JSONHttpClient getInstance(String host) {
		if (host != null) {
			if (host.indexOf("http://") != -1) {
				url = host;
			} else {
				url = "http://" + host;
			}
		}
		return instance;

	}

	private JSONHttpClient() {

	}

	/**
	 * 发送短信
	 * 
	 * @param account
	 *            帐号（必填）
	 * @param password
	 *            密码 （必填）
	 * @param msgid
	 *            msgId（可选,长度最长32位）
	 * @param phones
	 *            手机号码（必填）
	 * @param content
	 *            发送内容（长度最长1000）
	 * @param sign
	 *            短信签名（必填）
	 * @param subcode
	 *            子号码（必填,大汉提供子号码+（用户扩展码,可选））
	 * @param sendtime
	 *            定时发送时间（可选,格式:yyyyMMddHHmmss）
	 */
	public String sendSms(String account, String password, String phones, String content, String sign, String subcode, String msgid, String sendtime) {
		String sendurl = url + "/json/sms/Submit";
		JSONObject param = new JSONObject();
		param.put("account", account);
		param.put("password", MD5Encode(password));
		param.put("msgid", msgid);
		param.put("phones", phones);
		param.put("content", content);
		param.put("sign", sign);
		param.put("subcode", subcode);
		param.put("sendtime", sendtime);
		String requestData = param.toString();
		LOG.debug("sendSms请求数据：" + requestData);
		String resp = doPost(sendurl, requestData);
		LOG.debug("sendSms响应数据:" + resp);
		return resp;

	}

	public String sendSms(String account, String password, String phones, String content, String sign, String subcode, String msgid) {
		return sendSms(account, password, phones, content, sign, subcode, msgid, "");
	}

	public String sendSms(String account, String password, String phones, String content, String sign, String subcode) {
		return sendSms(account, password, phones, content, sign, subcode, "", "");
	}

	/**
	 * 华信科技
	 * @param params
	 * @return
	 */
	public String sendHXKJSms(Map<String,Object> params){
		JSONObject param = new JSONObject();
		String sendurl = params.get("serverURL")+"/smsJson.aspx";
		param.put("userid", params.get("userid"));
		param.put("account", params.get("account"));
		param.put("password", MD5Encode(params.get("password")+""));
		param.put("mobile",  params.get("mobile"));
		param.put("content", params.get("content"));
		param.put("sendTime", params.get("sendTime"));
		param.put("action", params.get("action"));
		param.put("extno", params.get("extno"));
		String requestData = param.toString();
		LOG.info("华信科技sendSms请求数据：" + requestData);
		String resp = doPost(sendurl, requestData);
		LOG.debug("华信科技sendSms响应数据:" + resp);
		return resp;
	}
	/**
	 * 获取状态报告
	 * 
	 * @param account
	 *            帐号（必填）
	 * @param password
	 *            密码(必填)
	 * @param msgid
	 *            msgid(可选)
	 * @param phone
	 *            手机号码（可选）
	 */
	public String getReport(String account, String password, String msgid, String phone) {
		String getReportUrl = url + "/json/sms/Report";
		JSONObject param = new JSONObject();
		param.put("account", account);
		param.put("password", MD5Encode(password));
		param.put("msgid", msgid);
		param.put("phone", phone);
		String requestData = param.toString();
		LOG.debug("getReport请求数据：" + requestData);
		String resp = doPost(getReportUrl, requestData);
		LOG.debug("getReport响应数据:" + resp);
		return resp;
	}

	public String getReport(String account, String password) {
		return getReport(account, password, "", "");
	}

	/**
	 * 获取余额
	 * 
	 * @param account
	 *            帐号（必填）
	 * @param password
	 *            密码(必填)
	 */
	public String getBalance(String account, String password) {
		String getBalanceUrl = url + "/json/sms/Balance";
		JSONObject param = new JSONObject();
		param.put("account", account);
		param.put("password", MD5Encode(password));
		String requestData = param.toString();
		LOG.debug("getBalance请求数据：" + requestData);
		String resp = doPost(getBalanceUrl, requestData);
		LOG.debug("getBalance响应数据:" + resp);
		return resp;
	}

	/**
	 * 获取上行短信
	 * 
	 * @param account
	 *            帐号（必填）
	 * @param password
	 *            密码(必填)
	 */
	public String getSms(String account, String password) {
		String getSmsUrl = url + "/json/sms/Deliver";
		JSONObject param = new JSONObject();
		param.put("account", account);
		param.put("password", MD5Encode(password));
		String requestData = param.toString();
		LOG.debug("getSms请求数据：" + requestData);
		String resp = doPost(getSmsUrl, requestData);
		LOG.debug("getSms响应数据:" + resp);
		return resp;
	}

	/**
	 * 检测敏感词
	 * 
	 * @param account
	 *            帐号（必填）
	 * @param password
	 *            密码(必填)
	 * @param content
	 *            短信内容（必填,长度最长1000）
	 */
	public String checkContent(String account, String password, String content) {
		String getSmsUrl = url + "/json/sms/KeywordCheck";
		JSONObject param = new JSONObject();
		param.put("account", account);
		param.put("password", MD5Encode(password));
		param.put("content", content);
		String requestData = param.toString();
		LOG.debug("checkContent请求数据：" + requestData);
		String resp = doPost(getSmsUrl, requestData);
		LOG.debug("checkContent响应数据:" + resp);
		return resp;
	}

	/**
	 * 检测黑名单
	 * 
	 * @param account
	 *            帐号（必填）
	 * @param password
	 *            密码(必填)
	 * @param phones
	 *            手机号码（必填,多个以英文逗号隔开，最多500个）
	 */
	public String checkBlankList(String account, String password, String phones) {
		String getSmsUrl = url + "/json/sms/BlackListCheck";
		JSONObject param = new JSONObject();
		param.put("account", account);
		param.put("password", MD5Encode(password));
		param.put("phones", phones);
		String requestData = param.toString();
		LOG.debug("checkBlankList请求数据：" + requestData);
		String resp = doPost(getSmsUrl, requestData);
		LOG.debug("checkBlankList响应数据:" + resp);
		return resp;
	}

	/**
	 * HTTP POST 请求
	 * 
	 * @param url
	 *            请求地址
	 * @param data
	 *            提交数据
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String doPost(String url, String data) {
		String respStr = null;
		HttpPost postMethod = null;
		try {
			HttpClient httpclient = HttpClientBuilder.create().build();
			postMethod = new HttpPost(url);
			postMethod.getParams().setParameter("http.protocol.content-charset", "utf-8");
			byte[] byteData = data.getBytes("utf-8");
			ByteArrayEntity requestEntity=new ByteArrayEntity(byteData);
			postMethod.setEntity(requestEntity);
			// 设置请求与数据处理的超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(20000).build();
			postMethod.setConfig(requestConfig);

			// 提交请求
			HttpResponse response = httpclient.execute(postMethod);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				respStr = EntityUtils.toString(entity, "UTF-8");
			} else {
				postMethod.releaseConnection();
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (postMethod != null){
				postMethod.releaseConnection();
			}
		}
		return respStr;
	}

	public static String MD5Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	private static final String byte2hexString(byte[] bytes) {
		StringBuffer bf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
				bf.append("0");
			}
			bf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return bf.toString();
	}

	public int getConTimeOut() {
		return conTimeOut;
	}

	public void setConTimeOut(int conTimeOut) {
		this.conTimeOut = conTimeOut;
	}

	public int getReadTimeOut() {
		return readTimeOut;
	}

	public void setReadTimeOut(int readTimeOut) {
		this.readTimeOut = readTimeOut;
	}

}
