package com.slfinance.shanlinbao.sms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.slfinance.shanlinbao.sms.utils.HttpUtil;
import com.slfinance.shanlinbao.sms.utils.JSONHttpClient;

@Slf4j
public class HXKJClient {
	
	public String sendSms(Map<String,Object> paramsMap) throws Exception {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userid", paramsMap.get("userid")+""));
		params.add(new BasicNameValuePair("account", paramsMap.get("account")+""));
		params.add(new BasicNameValuePair("password", JSONHttpClient.MD5Encode(paramsMap.get("password")+"")));
		params.add(new BasicNameValuePair("mobile", paramsMap.get("mobile")+""));
		params.add(new BasicNameValuePair("content", paramsMap.get("content")+""));
		params.add(new BasicNameValuePair("sendTime", paramsMap.get("sendTime")+""));
		params.add(new BasicNameValuePair("action", "send"));
		params.add(new BasicNameValuePair("extno", paramsMap.get("extno")+""));
		// 提交请求
		String result = HttpUtil.request(paramsMap.get("serverURL")+"", params);
		log.info("发送短信返回数据---->"+result);
		return result;
	}
}
