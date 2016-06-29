package com.slfinance.shanlinbao.sms;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.slfinance.common.sms.model.SmsLogInfo;
import com.slfinance.exception.SLException;
import com.slfinance.shanlinbao.service.ParamService;
import com.slfinance.shanlinbao.sms.utils.SmsServiceUtils;
import com.slfinance.shanlinbao.utils.Constant;
import com.slfinance.shanlinbao.utils.DateUtils;
import com.slfinance.util.BeanMapConvertUtil;
import com.slfinance.vo.ResultVo;

/**
 * @author taoxm
 *
 */
@Component
public class SmsServiceNew{
	@Autowired 
	private ParamService paramService;
	@Autowired
	private SmsServiceUtils smsServiceUtils;
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	public static Properties p = new Properties();
	static{
		try {
			p.load(SmsServiceNew.class.getResourceAsStream("/sms.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ThreadPoolTaskExecutor getThreadPoolTaskExecutor(){
		return this.threadPoolTaskExecutor;
	}
	
	/**
	 * 发送短信
	 * @param params
	 * @return
	 * @throws SLException 
	 */
	public ResultVo sendMessage(Map<String, Object> params) throws SLException{
		if(!ResultVo.isSuccess(preSendMessage(params))){
			return preSendMessage(params);
		}
		String messageType = (String) params.get("messageType");
		String tunnelName = (String) params.get("tunnel");
		String sendTime = (String) params.get("sendTime");
		params.put("content", params.get("smsContent")+"");
		Tunnel tunnel = null;
		try {
			if(messageType.equals(Constant.SMS_TYPE_SMSMARKETING)){
				tunnel = smsServiceUtils.getTunnel(tunnelName);
			}else{
				tunnelName = paramService.findRechargeQuota(Constant.PARAM_SMS_TYPE,Constant.PARAM_SMS_VALIDATION_NAME);
				tunnel = smsServiceUtils.getValidateTunnel(tunnelName);
				params.put("tunnel", tunnelName);//之前的短信没有渠道概念，验证类短信强制加发送渠道
			}
		} catch (Exception e1) {
			throw new SLException("短信供应商："+tunnelName+"未找到，请核对！");
		}
		SmsLogInfo smsLogInfo = null;
		try {
			smsLogInfo = tunnel.send(params);
			Date currentDate = new Date();
			if (sendTime != null && !sendTime.equals("") ) {
				smsLogInfo.setSendTime(DateUtils.parseDate(sendTime, "yyyy-MM-dd HH:mm:ss"));
			} else {
				smsLogInfo.setSendTime(currentDate);
			}
			return analysisSmsLogInfo(smsLogInfo);
		} catch (Exception e) {
			return new ResultVo(false,"短信发送失败！");
		}
	}
	
	/**
	 * 校验非空参数
	 * @param params
	 * @return
	 */
	private ResultVo preSendMessage(Map<String, Object> params){
		if(StringUtils.isEmpty(params.get("mobile"))){
			return new ResultVo(false,"moblie参数不能为空！");
		}
		if(StringUtils.isEmpty(params.get("smsContent"))){
			return new ResultVo(false,"smsContent参数不能为空！");
		}
//		if(StringUtils.isEmpty(params.get("sendTime"))){
//			return new ResultVo(false,"sendTime参数不能为空！");
//		}
//		if(!StringUtils.isEmpty(params.get("tunnelName")) && params.get("tunnelName").toString().equalsIgnoreCase("YTX")){
//			//TODO taoxiuma templateId取什么值
//			params.put("templateId", "");
//		}
		return new ResultVo(true,"参数校验通过！");
	}
	
	
	private ResultVo analysisSmsLogInfo(SmsLogInfo smsLogInfo) throws Exception{
		if(smsLogInfo.getReturnStatus().equals("SUCCESS")){
			return new ResultVo(true,"调用成功",BeanMapConvertUtil.beanToMap(smsLogInfo));
		}else{
			return new ResultVo(false,"调用成功,发送失败",BeanMapConvertUtil.beanToMap(smsLogInfo));
		}
	}
}
