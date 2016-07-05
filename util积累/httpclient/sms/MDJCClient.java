/** 
 * @(#)SmsClient.java 1.0.0 2014年10月8日 下午6:31:56  
 *  
 * Copyright © 2014 善林金融.  All rights reserved.  
 */

package com.slfinance.shanlinbao.sms;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.slfinance.common.utils.DesUtil;
import com.slfinance.shanlinbao.sms.utils.HttpUtil;

/**
 * 
 * 
 * @author caoyi
 * @version $Revision:1.0.0, $Date: 2014年10月8日 下午6:31:56 $
 */
@Slf4j
public class MDJCClient {

	// 企业ID
	private String userid;

	// 用户帐号，由系统管理员
	private String account;

	// 用户账号对应的密码
	private String password;

	// 服务器地址
	private String serverUrl;

	public MDJCClient(String userid, String account, String password, String serverUrl) throws Exception {
		DesUtil desUtil = new DesUtil("caoyi");
		this.userid = userid;
		this.account = account;
		this.password = desUtil.decrypt(password);
		this.serverUrl = serverUrl;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return password;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public String getUserid() {
		return userid;
	}

	/**
	 * 查询余额与发送量
	 * 
	 * @return 请求返回值
	 * @throws Exception
	 */
	public String getBalance() throws Exception {

		// 参数赋值
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userid", userid));
		params.add(new BasicNameValuePair("account", account));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("action", "overage"));

		// 提交请求
		String result = HttpUtil.request(serverUrl, params);
		return result;
	}

	/**
	 * 非法关键词检查
	 * 
	 * @param content
	 *            待检查内容
	 * @return 返回结果
	 * @throws Exception
	 */
	public String checkContent(String content) throws Exception {

		// 参数赋值
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userid", userid));
		params.add(new BasicNameValuePair("account", account));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("action", "checkkeyword"));
		params.add(new BasicNameValuePair("content", content));

		// 提交请求
		String result = HttpUtil.request(serverUrl, params);
		return result;
	}

	/**
	 * 获取返回报告数据
	 * 
	 * @return 返回报告数据
	 * @throws Exception
	 */
	public String getReport() throws Exception {

		// String url = "http://118.244.214.125:8888/statusApi.aspx";
		String url = "http://sdk8.mdjc.net.cn:8888/statusApi.aspx";
		// 参数赋值
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userid", userid));
		params.add(new BasicNameValuePair("account", account));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("action", "query"));

		// 提交请求
		String result = HttpUtil.request(url, params);
		return result;
	}

	/**
	 * 获取上行数据
	 * 
	 * @return 获取上行数据
	 * @throws Exception
	 */
	public String getMo() throws Exception {

		// String url = "http://118.244.214.125:8888/callApi.aspx";
		String url = "http://sdk8.mdjc.net.cn:8888/callApi.aspx";
		// 参数赋值
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userid", userid));
		params.add(new BasicNameValuePair("account", account));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("action", "query"));

		// 提交请求
		String result = HttpUtil.request(url, params);
		return result;
	}

	/**
	 * 发送短信（utf-8格式内容）
	 * 
	 * @param mobile
	 *            手机号，多个使用半角逗号分隔
	 * @param content
	 *            内容
	 * @param sendTime
	 *            定时时间，格式2010-10-24 09:08:10，小于当前时间或为空表示立即发送
	 * @param extno
	 *            扩展码
	 * @return 发送返回值
	 * @throws Exception
	 *             抛出异常
	 */
	public String sendSms(String mobile, String content, String sendTime, String extno) throws Exception {

		// 参数赋值
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userid", userid));
		params.add(new BasicNameValuePair("account", account));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("mobile", mobile));
		params.add(new BasicNameValuePair("content", content));
		params.add(new BasicNameValuePair("sendTime", sendTime));
		params.add(new BasicNameValuePair("action", "send"));
		params.add(new BasicNameValuePair("extno", extno));

		// 提交请求
		String result = HttpUtil.request(serverUrl, params);
		log.info("发送短信返回数据---->"+result);
		return result;
	}

}
