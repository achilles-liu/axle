/**
 * 
 */
package org.axle.core.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * axle-core
 * <description></description>
 * @author Johnny Liu
 * @date 2018-10-02
 */
public class AuthInterceptor implements HandlerInterceptor {
	private @Autowired Authorition authorition;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(this.authorition == null || this.authorition.auth(request)) return true;
		response.setStatus(403);
		return false;
	}

}
