/**
 * 
 */
package org.axle.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * axle-core
 * <description></description>
 * @author Johnny Liu
 * @date 2018-10-02
 */
@Component
public class ContextUtil implements ApplicationContextAware{
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ContextUtil.applicationContext = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    } 
	
    public static <T> T getBean(Class<T> clazz) throws BeansException {  
        return applicationContext.getBean(clazz);  
    }
}
