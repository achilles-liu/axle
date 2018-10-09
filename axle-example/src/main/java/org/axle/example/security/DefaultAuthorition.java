/**
 * 
 */
package org.axle.example.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.axle.core.security.Authorition;
import org.axle.core.util.ContextUtil;
import org.axle.example.cache.ExampleCache;
import org.axle.example.model.Example;

/**
 * axle-example
 * <description></description>
 * @author Johnny Liu
 * @date 2018-10-02
 */
public class DefaultAuthorition implements Authorition{
	
	/**
	 * XXX it is a simple example.the complex arithmetic should be used here.
	 */
	public boolean auth(HttpServletRequest request) {
		String header = request.getHeader("WWW-Authorition");
		if(header == null) return false;
		System.out.println(header);
		byte[] headerBytes = Base64.decodeBase64(header.getBytes());
		String headerStr = new String(headerBytes);
		String[] userInfo = headerStr.split("\\|");
		byte[] pwdBytes = Base64.decodeBase64(userInfo[1].getBytes());
		String pwdStr = new String(pwdBytes);
		System.out.println("User -> "+userInfo[0]+",Password -> "+pwdStr);
		//XXX compare with the user information of database.
		Example example = new Example();
		example.setUsername(userInfo[0]);
		example.setNickname(userInfo[0]);
		ExampleCache cache = ContextUtil.getBean(ExampleCache.class);
		return cache.checkAuth(example);
	}
	
}
