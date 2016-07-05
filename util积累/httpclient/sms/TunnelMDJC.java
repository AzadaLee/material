/** 
 * @(#)Tunnel.java 1.0.0 2015年6月18日 上午9:15:53  
 *  
 * Copyright © 2015 善林金融.  All rights reserved.  
 */ 

package com.slfinance.shanlinbao.sms;

import java.util.Map;
import java.util.concurrent.Callable;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slfinance.common.sms.model.SmsLogInfo;

/**   
 * 
 *  
 * @author  HuYaHui
 * @version $Revision:1.0.0, $Date: 2015年6月18日 上午9:15:53 $ 
 */
@Service("MDJC")
public class TunnelMDJC implements Tunnel{

	@Autowired
	private SmsServiceNew smsServiceNew;
	
	public SmsLogInfo send(Map<String,Object> params) throws Exception{
		String mobile=params.get("mobile")+"";
		String content=params.get("content")+"";
		String sendTime=params.get("sendTime")+"";
		String extno=params.get("extno")!=null?params.get("extno").toString():"";
		boolean syn=new Boolean(params.get("isSync")+"");
		String result = "";
		// 是否真实发送短信
		String sendFlag = SmsServiceNew.p.getProperty("sms.send.flag");
		// 服务器地址
		String serverUrl = SmsServiceNew.p.getProperty("sms.serverUrl");
		// 企业id
		String userid = SmsServiceNew.p.getProperty("sms.userid");
		// 用户帐号
		String account = SmsServiceNew.p.getProperty("sms.account");
		// 用户密码
		String password = SmsServiceNew.p.getProperty("sms.password");

		SmsLogInfo smsLogInfo = new SmsLogInfo();
		smsLogInfo.setMobile(mobile);
		smsLogInfo.setSendContent(content);
		
		smsLogInfo.setExtno(extno);
		if (sendFlag.equals("Y")) {
			// 初始化短信对象
			MDJCClient c = new MDJCClient(userid, account, password, serverUrl);
			if(!syn){
				smsServiceNew.getThreadPoolTaskExecutor().submitListenable(new _SmsThread(mobile, content, sendTime, extno, c));
				result="异步发送短信！";
				smsLogInfo.setReturnStatus("SUCCESS");
				return smsLogInfo;
			}else{
				// 提交短信
				result = c.sendSms(mobile, content, sendTime, extno);
			}
		} else {
			result = "<?xml version='1.0' encoding='utf-8' ?><returnsms><returnstatus>Success</returnstatus><message>ok</message><remainpoint>00000</remainpoint><taskID>999999</taskID> <successCounts>1</successCounts></returnsms>";
		}
		Document doc = null;
		doc = DocumentHelper.parseText(result);
		Element root = doc.getRootElement();
		// 返回状态值：成功返回Success 失败返回：Faild
		Element returnStatusElement = root.element("returnstatus");
		String returnStatus = returnStatusElement.getTextTrim();
		// 返回信息
		Element messageElement = root.element("message");
		String message = messageElement.getTextTrim();
		// 返回余额
		Element remainPointElement = root.element("remainpoint");
		String remainPoint = remainPointElement.getTextTrim();
		// 本次任务的序列ID
		Element taskIdElement = root.element("taskID");
		String taskId = taskIdElement.getTextTrim();
		// 成功短信数
		Element successCountsElement = root.element("successCounts");
		String successCounts = successCountsElement.getTextTrim();

		smsLogInfo.setReturnStatus(returnStatus.toUpperCase());
		smsLogInfo.setReturnMessage(message);
		smsLogInfo.setRemainPoint(remainPoint);
		smsLogInfo.setTaskId(taskId);
		smsLogInfo.setSuccessCounts(Integer.parseInt(successCounts));
		return smsLogInfo;
	}
}


class _SmsThread implements Callable<String>{
	private MDJCClient c;
	// 服务器地址
	private String mobile;
	// 企业id
	private String content;
	private String sendTime;
	private String extno;
	public _SmsThread(String mobile,String content,String sendTime,String extno,MDJCClient c){
		this.mobile=mobile;
		this.content=content;
		this.sendTime=sendTime;
		this.extno=extno;
		this.c=c;
	}
	
	@Override
	public String call() throws Exception {
		try {
			// 提交短信
			return c.sendSms(mobile, content, sendTime, extno);
		} catch (Exception e) {
			return null;
		}
	}
}