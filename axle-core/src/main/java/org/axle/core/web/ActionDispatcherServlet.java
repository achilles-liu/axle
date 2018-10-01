package org.axle.core.web;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.axle.core.annotation.ApiAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * axle-core
 * <description>register <code>ApiAction</code> to web context of spring.</description>
 * @author Johnny Liu
 * @date 2018-09-30
 */
public class ActionDispatcherServlet extends DispatcherServlet{
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_METHOD_SIGNATURE = "execute";
	private static final String DEFAULT_URL_SUFFIX = "/api";

	public ActionDispatcherServlet(WebApplicationContext applicationContext) {
		super(applicationContext);
	}
	
	protected boolean isApiAction(Class<?> beanType) {
		return beanType.isAnnotationPresent(ApiAction.class);
	}

	protected void initStrategies(ApplicationContext context) {
		super.initStrategies(context);
		List<HandlerMapping> handlerMappings = getHandlerMappings();
		for(int i = 0;i < handlerMappings.size();i++) {
			if(handlerMappings.get(i) instanceof RequestMappingHandlerMapping) {
				RequestMappingHandlerMapping requestMapping = (RequestMappingHandlerMapping) handlerMappings.get(i);
				String[] beanNames = context.getBeanNamesForType(Object.class);
				for(String beanName : beanNames) {
					Class<?> beanType = context.getType(beanName);
					if(beanType != null && isApiAction(beanType)) {
						try {
							ApiAction apiAction = beanType.getAnnotation(ApiAction.class);
							String actionUrl = apiAction.url();
							Method invokeMethod = beanType.getSuperclass().getDeclaredMethod(DEFAULT_METHOD_SIGNATURE, new Class<?>[] {HttpServletRequest.class,HttpServletResponse.class});
							requestMapping.registerMapping(RequestMappingInfo.paths(DEFAULT_URL_SUFFIX+actionUrl).methods(apiAction.method()).build(), beanName, invokeMethod);
						} catch (Exception e) {
							e.printStackTrace();
						} 
					}
				}
			}
		}
	}

	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doDispatch(request, response);
	}
}
