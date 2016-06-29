/** 
 * @(#)TunnelInterface.java 1.0.0 2015年6月18日 上午9:24:42  
 *  
 * Copyright © 2015 善林金融.  All rights reserved.  
 */ 

package com.slfinance.shanlinbao.sms;

import java.util.Map;

import com.slfinance.common.sms.model.SmsLogInfo;

/**   
 * 
 *  
 * @author  HuYaHui
 * @version $Revision:1.0.0, $Date: 2015年6月18日 上午9:24:42 $ 
 */
public interface VoiceTunnel extends Tunnel {

	public SmsLogInfo sendVoice(Map<String, Object> params) throws Exception;
}
