package com.slfinance.shanlinbao.sms;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.slfinance.common.sms.model.SmsLogInfo;
import com.slfinance.shanlinbao.utils.DateUtils;

@Slf4j
@Component("HXKJ")
public class TunnelHXKJ  implements Tunnel{
	@Autowired
	private SmsServiceNew smsServiceNew;
	
	public static String serverURL= "http://sh2.ipyy.com/smsJson.aspx";
	public static String account = "slb888";// 用户名（必填）
	public static String password = "shanlin123";// 密码（必填）
	public static String sign = "【善林宝】"; // 短信签名
	public static String action = "send";//发送任务命令（固定值）
	public static String extno = ""; // 子号码（必填）
	public static String userid = "1";//企业ID(不验证)
	
	
	@Override
	public SmsLogInfo send(Map<String, Object> params) throws Exception {
		final Map<String, Object> requestMap = new HashMap<String, Object>();
		String mobile = params.get("mobile")+"";
		String content = params.get("smsContent")+sign;
		if(params.get("sendTime") == null){
			requestMap.put("sendTime", "");
		}else if(params.get("sendTime") instanceof Date){
			requestMap.put("sendTime", DateUtils.formatDate((Date)params.get("sendTime"), "yyyy-MM-dd HH:mm:ss"));
		}else{
			requestMap.put("sendTime", params.get("sendTime")+"");
		}
		requestMap.put("serverURL", serverURL);
		requestMap.put("userid", userid);
		requestMap.put("account", account);
		requestMap.put("password", password);
		
		requestMap.put("sign", sign);
		requestMap.put("action", action);
		requestMap.put("extno", extno);
		requestMap.put("mobile", mobile);
		requestMap.put("content", content);
		// 是否真实发送短信
		String sendFlag = SmsServiceNew.p.getProperty("sms.send.flag");
		SmsLogInfo smsLogInfo = new SmsLogInfo();
		smsLogInfo.setMobile(mobile);
		smsLogInfo.setSendContent(content);
		String result = "";
		if (sendFlag.equals("Y")) {
			final HXKJClient client = new HXKJClient();
			if(params.get("isSync") != null || new Boolean(params.get("isSync")+"")){
				new Callable<String>() {
					@Override
					public String call() throws Exception {
						try {
							return client.sendSms(requestMap);
						} catch (Exception e) {
							return null;
						}
					}
				};
			}
			log.info("发送参数{}",requestMap.toString());
			result = client.sendSms(requestMap);
			log.info("返回参数{}",result);
		}else {
			result = "{\"returnstatus\":\"Success\",\"message\":\"操作成功\",\"remainpoint\":\"-4\",\"taskID\":\"1504080852350206\",\"successCounts\":\"1\"}";
		}
		
		JSONObject resultObj = JSON.parseObject(result);
		String returnstatus = resultObj.getString("returnstatus");
		smsLogInfo.setReturnStatus(returnstatus.equals("Success")?"SUCCESS":"FAIL");
		String returnMessage = resultObj.getString("message");
		smsLogInfo.setReturnMessage(returnMessage);
		String remainpoint = resultObj.getString("remainpoint");
		smsLogInfo.setRemainPoint(remainpoint);
		String taskId = resultObj.getString("taskId");
		smsLogInfo.setTaskId(taskId);
		int successCounts = Integer.parseInt(resultObj.getString("successCounts"));
		smsLogInfo.setSuccessCounts(successCounts);
		return smsLogInfo;
	}
}
