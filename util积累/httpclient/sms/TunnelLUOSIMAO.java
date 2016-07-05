/** 
 * @(#)Tunnel.java 1.0.0 2015年6月18日 上午9:15:53  
 *  
 * Copyright © 2015 善林金融.  All rights reserved.  
 */ 

package com.slfinance.shanlinbao.sms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slfinance.common.sms.model.SmsLogInfo;
import com.slfinance.shanlinbao.sms.utils.HttpUtil;
import com.slfinance.util.Json;

/**   
 * 
 *  
 * @author  HuYaHui
 * @version $Revision:1.0.0, $Date: 2015年6月18日 上午9:15:53 $ 
 */
@Slf4j
@Service("LUOSIMAO")
public class TunnelLUOSIMAO implements Tunnel{
	@Autowired
	private SmsServiceNew smsServiceNew;

	public SmsLogInfo send(Map<String,Object> params) throws Exception{
		String mobile=params.get("mobile")+"";
		String content=params.get("content")+"【善林金融】";
		boolean syn=new Boolean(params.get("isSync")+"");

		String sms_mdjc_api= SmsServiceNew.p.getProperty("sms.mdjc.api");
		String sms_mdjc_url = SmsServiceNew.p.getProperty("sms.mdjc.url");
		// 是否真实发送短信
		String sendFlag = SmsServiceNew.p.getProperty("sms.send.flag");
        
		SmsLogInfo smsLogInfo = new SmsLogInfo();
		String status="0";
		if (sendFlag.equals("Y")) {
			if(!syn){
				smsServiceNew.getThreadPoolTaskExecutor().submitListenable(new _SmsLUOSIMAOThread(mobile, content,sms_mdjc_api,sms_mdjc_url));
				smsLogInfo.setReturnStatus("SUCCESS");
			}else{
				List<NameValuePair> paramsNV=new ArrayList<NameValuePair>();
				paramsNV.add(new BasicNameValuePair("mobile", mobile));
				paramsNV.add(new BasicNameValuePair("message", content));
		        String textEntity = HttpUtil.request(sms_mdjc_url, paramsNV, "api", sms_mdjc_api);
		        log.info("发送短信..."+textEntity);
		        Map<String,Object> map=Json.ObjectMapper.readValue(textEntity, Map.class);
				// 提交短信h123123
				status=map.get("error")+"";
			}
		}
        //System.out.print(textEntity);
//        System.out.print(status);
        
		smsLogInfo.setMobile(mobile);
		smsLogInfo.setSendContent(content);
		smsLogInfo.setReturnStatus(status.equals("0")?"SUCCESS":"FAIL");
		smsLogInfo.setReturnMessage("ok");
		smsLogInfo.setRemainPoint("00000");
		smsLogInfo.setTaskId("999999");
		smsLogInfo.setSuccessCounts(1);
		return smsLogInfo;
	}
	
}

@Slf4j
class _SmsLUOSIMAOThread implements Callable<String>{
	// 服务器地址
	private String mobile;
	// 企业id
	private String content;

	private String sms_mdjc_api= "";
	private String sms_mdjc_url = "";
	
	public _SmsLUOSIMAOThread(String mobile,String content,
			String _sms_mdjc_api,String _sms_mdjc_url){
		this.mobile=mobile;
		this.content=content;
		this.sms_mdjc_api=_sms_mdjc_api;
		this.sms_mdjc_url=_sms_mdjc_url;
	}
	
	@Override
	public String call() throws Exception {
		try {
			List<NameValuePair> paramsNV=new ArrayList<NameValuePair>();
			paramsNV.add(new BasicNameValuePair("mobile", mobile));
			paramsNV.add(new BasicNameValuePair("message", content));
	        String textEntity = HttpUtil.request(sms_mdjc_url, paramsNV, "api", sms_mdjc_api);
	        log.info("发送短信..."+textEntity);
	        Map<String,Object> map=Json.ObjectMapper.readValue(textEntity, Map.class);
			// 提交短信
			return map.get("error")+"";
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return "0";
		}
	}
}