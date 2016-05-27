/** 
 * @(#)ProductBusinessServiceTest.java 1.0.0 2015年4月28日 下午2:33:46  
 *  
 * Copyright © 2015 善林金融.  All rights reserved.  
 */

package com.slfinance.shanlinbao.service;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.slfinance.exception.SLException;
import com.slfinance.shanlinbao.entity.AccountInfoEntity;
import com.slfinance.shanlinbao.entity.CustInfoEntity;
import com.slfinance.shanlinbao.repository.AccountInfoRepository;
import com.slfinance.shanlinbao.repository.CustInfoRepository;
import com.slfinance.shanlinbao.utils.Constant;

/**
 * 
 * 
 * @author caoyi
 * @version $Revision:1.0.0, $Date: 2015年4月28日 下午2:33:46 $
 */
@ContextConfiguration(locations = { "classpath:/application-test.xml",
"classpath:/applicationContext-restclient.xml" })
@ActiveProfiles("dev")
public class CustActivityInfoServiceTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	CustActivityInfoService custActivityInfoService;
	
	@Autowired
	private FlowNumberService numberService;
	
	@Autowired
	private CustInfoRepository custInfoRepository;
	
	@Autowired
	private AccountInfoRepository accountInfoRepository;

	// 测试获取金牌推荐人统计信息
	@Test
	public void testFindCustCommissionInfo() throws SLException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("custId", "1");
		Map<String, Object> map = custActivityInfoService.findCustCommissionInfo(param);
		assertTrue(!map.isEmpty());
	}

	// 测试获取我的佣金记录
	@Test
	public void testFindCustCommissionList() throws SLException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", 1);
		param.put("length", 10);
		param.put("custId", "1");
		param.put("dateBegin", "2015-08-24");
		param.put("dateEnd", "2015-08-25");
		param.put("tradeStatus", "否");
		Map<String, Object> map = custActivityInfoService.findCustCommissionList(param);
		assertTrue(!map.isEmpty());

	}

	// 测试获取我的佣金详情记录
	@Test
	public void testFindCustCommissionDetailList() throws SLException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", 1);
		param.put("length", 10);
		param.put("commissionId", "1");
		Map<String, Object> map = custActivityInfoService.findCustCommissionDetailList(param);
		assertTrue(!map.isEmpty());

	}

	@Test
	public void testReceiveExperience() throws SLException  {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", Constant.ACTIVITY_ID_REGIST_04);
		map.put("activityRecommend", new BigDecimal("100"));
		map.put("custId", "69987597-9db6-42c9-8c27-1f772656c937");
		map.put("tradeNo", numberService.generateTradeNumber());
		map.put("quiltCustId", "");
		map.put("flowDecr", "购买定期宝送体验金");
		//custActivityInfoService.receiveExperience(map);
	}
	
	@Test
	public void testCustActivityRecommend() {
		CustInfoEntity custInfoEntity = custInfoRepository.findOne("b19d3925-8783-4397-9524-b5025fe00043");
		AccountInfoEntity accountInfoEntity = accountInfoRepository.findByCustId("b19d3925-8783-4397-9524-b5025fe00043");
		Map<String, Object> custActivityMap = new HashMap<String, Object>();
		custActivityMap.put("activityId", Constant.ACTIVITY_ID_REGIST_07);
		custActivityMap.put("custInfoEntity", custInfoEntity);
		custActivityMap.put("custAccount", accountInfoEntity);
		custActivityMap.put("tradeNo", numberService.generateTradeNumber());
		custActivityInfoService.custActivityRecommend(custActivityMap);
	}
}
