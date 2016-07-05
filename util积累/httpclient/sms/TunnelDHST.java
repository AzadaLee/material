/** 
 * @(#)Tunnel.java 1.0.0 2015年6月18日 上午9:15:53  
 *  
 * Copyright © 2015 善林金融.  All rights reserved.  
 */ 

package com.slfinance.shanlinbao.sms;

import java.util.Map;
import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.slfinance.common.sms.model.SmsLogInfo;
import com.slfinance.shanlinbao.sms.utils.JSONHttpClient;

/**   
 * 
 *  
 * @author  HuYaHui
 * @version $Revision:1.0.0, $Date: 2015年6月18日 上午9:15:53 $ 
 */
@Slf4j
@Service("DHST")
public class TunnelDHST implements Tunnel{
	@Autowired
	private SmsServiceNew smsServiceNew;

	public static String account = "dh73091";// 用户名（必填）
	public static String password = "5vWrM28q";// 密码（必填）
	public static String sign = "【善林财富】"; // 短信签名（必填）
	public static String subcode = "85281234"; // 子号码（必填）
	public static String msgid = ""; // 短信id，查询短信状态报告时需要，（可选）
	public static String sendtime = ""; // 定时发送时间（可选）

	public SmsLogInfo send(Map<String,Object> params) throws Exception{
		String mobile=params.get("mobile")+"";
		String content=params.get("content")+"";
		boolean syn=new Boolean(params.get("isSync")+"");
		
		// 是否真实发送短信
		String sendFlag = SmsServiceNew.p.getProperty("sms.send.flag");
        
		SmsLogInfo smsLogInfo = new SmsLogInfo();
		smsLogInfo.setReturnMessage("ok");
		String status="0";
		if (sendFlag.equals("Y")) {
			if(!syn){
				smsServiceNew.getThreadPoolTaskExecutor().submitListenable(new _SmsDHSTThread(mobile, content));
				smsLogInfo.setReturnStatus("SUCCESS");
			}else{
				JSONHttpClient jsonHttpClient = JSONHttpClient.getInstance("wt.3tong.net");
				String sendRes = jsonHttpClient.sendSms(account, password, mobile, content, sign, subcode);
		        log.info("发送短信..."+sendRes);
		        //{"msgid":"9960421f1a6245dab282bfa3c5385f9b","result":"0","desc":"提交成功含有错误号码","failPhones":"113817544311"}
		        //{"msgid":"dce8950b747e45799d456b5e2b500461","result":"0","desc":"提交成功","failPhones":""}
		        String desc=JSONObject.parseObject(sendRes).getString("desc");
		        smsLogInfo.setReturnMessage(desc);
		        if(desc.equals("提交成功")){
		        	status="0";
		        }else{
		        	status="1";	
		        }
			}
		}
		smsLogInfo.setMobile(mobile);
		smsLogInfo.setSendContent(content);
		smsLogInfo.setReturnStatus(status.equals("0")?"SUCCESS":"FAIL");
		smsLogInfo.setRemainPoint("00000");
		smsLogInfo.setTaskId("999999");
		smsLogInfo.setSuccessCounts(1);
		return smsLogInfo;
	}
}

@Slf4j
class _SmsDHSTThread implements Callable<String>{
	private String phone ;//= "13817544311"; // 手机号码（必填,多条以英文逗号隔开）
	private String content;// = "测试内容。";// 短信内容（必填）
	
	public _SmsDHSTThread(String phone,String content){
		this.phone=phone;
		this.content=content;
	}
	
	@Override
	public String call() throws Exception {
		try {
			JSONHttpClient jsonHttpClient = JSONHttpClient.getInstance("wt.3tong.net");
			String sendRes = jsonHttpClient.sendSms(TunnelDHST.account, TunnelDHST.password, phone, content, TunnelDHST.sign, TunnelDHST.subcode);
	        log.info("发送短信..."+sendRes);
			// 提交短信
	        String desc=JSONObject.parseObject(sendRes).getString("desc");
	        if(desc.equals("提交成功")){
	        	desc="0";
	        }else{
	        	desc="1";	
	        }
	        return desc;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			e.printStackTrace();
			return "0";
		}
	}
}