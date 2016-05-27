/** 
 * @(#)Idempotent.java 1.0.0 2015年10月19日 下午2:32:41  
 *  
 * Copyright © 2015 善林金融.  All rights reserved.  
 */ 

package com.slfinance.shanlinbao.annotation;


/**   
 * 
 *  
 * @author  wangjf
 * @version $Revision:1.0.0, $Date: 2015年10月19日 下午2:32:41 $ 
 */
public interface BeanSelfAware {
	 public void setSelf(Object proxyBean);
}