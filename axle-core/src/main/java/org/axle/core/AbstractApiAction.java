package org.axle.core;

import java.lang.reflect.Field;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.axle.core.annotation.ApiAction;
import org.axle.core.annotation.IoParam;
import org.axle.core.deserialize.TypeDeserialize;
import org.axle.core.deserialize.TypeDeserializeChain;
import org.axle.core.formatter.Formatter;
import org.axle.core.request.Request;
import org.axle.core.response.Response;
import org.axle.core.util.ActionUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.AntPathMatcher;

/**
 * axle-core
 * <description>@param <K>
 * <description>@param <V></description>
 * @author Johnny Liu
 * @date 2018-09-30
 * @param <K>
 * @param <V>
 */
public abstract class AbstractApiAction<K extends Request,V extends Response> implements ApplicationContextAware{
	protected ApplicationContext applicationContext;
	AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	protected Class<?> findActualClass(String actualUrl,Map<String,Object> paramMap){
		String[] beanNames = this.applicationContext.getBeanNamesForAnnotation(ApiAction.class);
		Class<?> actualType = null;
		for(String beanName : beanNames) {
			Class<?> beanType = this.applicationContext.getType(beanName);
			ApiAction apiAction = beanType.getAnnotation(ApiAction.class);
			String url = apiAction.url();
			// XXX extract the incoming parameter in request uri.
			if(antPathMatcher.match(url, actualUrl.substring(4))){ 
				Map<String, String> urlVar = antPathMatcher.extractUriTemplateVariables(url, actualUrl.substring(4));
				paramMap.putAll(urlVar);
				actualType = beanType;
				break;
			}
			// XXX without the uri parameter.
			if(url.equals(actualUrl)) {
				actualType = beanType;
				break;
			}
		}
		return actualType;
	}
	
	@SuppressWarnings("unchecked")
	protected K extractParam(HttpServletRequest request) throws Exception {
		TypeDeserialize deser = this.applicationContext.getBean(TypeDeserializeChain.class).chain();
		String path = request.getServletPath();
		Map<String,Object> rawParamMap = ActionUtil.extractFromRequest(request);
		Class<?> beanType = findActualClass(path,rawParamMap);
		if(beanType == null) throw new RuntimeException("unknown request uri");
		ApiAction apiAction = beanType.getAnnotation(ApiAction.class);
		IoParam ioParam = apiAction.io();
		Class<?> inParamType = ioParam.in();
		// XXX should it retrieve the fields of ancestor class here?
		Field[] fileds = inParamType.getDeclaredFields();
		Object target = inParamType.newInstance();
		// XXX convert to the actual type.
		// TODO How to valid the field? How to use <code>Parameter</code> annotation?
		for(Field field : fileds) {
			deser.deserialize(target, field, rawParamMap.get(field.getName()));
		}
		return (K) target;
	}
	
	protected String execute(HttpServletRequest request, HttpServletResponse response){
		ApiReceipt.Builder apiRecBuilder = new ApiReceipt.Builder().code(-1).message("success");
		try {
			K param = extractParam(request);
			param.setUuid(ActionUtil.uuid());
			V val = command(param);
			if(val != null) val.setUuid(param.getUuid());
			apiRecBuilder.code(0);
			apiRecBuilder.data(val);
		} catch (Exception e) {
			apiRecBuilder.message(e.getMessage());
		}
		Formatter formatter = this.applicationContext.getBean(Formatter.class);
		return formatter.format(apiRecBuilder.builder());
	};
	protected abstract V command(K request);

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if(this.applicationContext == null) this.applicationContext = applicationContext;
	}
}
