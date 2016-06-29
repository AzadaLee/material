package com.slfinance.shanlinbao.sms.utils;

import lombok.Data;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.slfinance.exception.SLException;
import com.slfinance.shanlinbao.sms.Tunnel;

@Component
@Data
public class SmsServiceUtils {
	private String defaultTunnelName = "MDJC";
	
	private String defaultValidateTunnelName = "MDJC";
	/**
	 * 获取营销短信服务商
	 * @param tunnelName
	 * @return
	 */
	public Tunnel getTunnel(String tunnelName) throws SLException{
		if(StringUtils.isEmpty(tunnelName)){
			return ApplicationContextUtils.getBean(defaultTunnelName,Tunnel.class);
		} 
		return ApplicationContextUtils.getBean(tunnelName.toUpperCase(), Tunnel.class);
	}
	
	
	/**
	 * 获取验证码短信服务商
	 * @param tunnelName
	 * @return
	 */
	public Tunnel getValidateTunnel(String tunnelName){
		if(StringUtils.isEmpty(tunnelName)){
			return ApplicationContextUtils.getBean(defaultValidateTunnelName,Tunnel.class);
		} 
		return ApplicationContextUtils.getBean(tunnelName.toUpperCase(), Tunnel.class);
	}
}
