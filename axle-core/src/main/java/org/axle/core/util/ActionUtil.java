/**
 * 
 */
package org.axle.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * axle-core
 * <description></description>
 * @author Johnny Liu
 * @date 2018-10-01
 */
public class ActionUtil {
	
	public static Map<String,Object> extractFromRequest(HttpServletRequest request) throws IOException{
		Map<String,Object> rawParamMap = new HashMap<>();
		if(RequestMethod.GET.name().equals(request.getMethod())) {
			// XXX 抽取参数
			Enumeration<String> enu = request.getParameterNames();
			while(enu.hasMoreElements()) {
				String name = enu.nextElement();
				if(!rawParamMap.containsKey(name))
					rawParamMap.put(name, request.getParameter(name));
			}
		}else {
			// XXX 抽取body中的参数
			String str;
			BufferedReader br = request.getReader();
			StringBuilder rawStr = new StringBuilder();
			while((str = br.readLine()) != null) rawStr.append(str);
			if(rawStr.length() > 0) {
				// XXX {"name":"kitty","age":20}
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, Object.class);
				Map<String,Object> rawBody = objectMapper.readValue(rawStr.toString(), javaType);
				rawParamMap.putAll(rawBody);
			}
		}
		return rawParamMap;
	}
	
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
