/**
 * 
 */
package org.axle.core.security;

import javax.servlet.http.HttpServletRequest;

/**
 * axle-core
 * <description>determine whether the incoming request should be accessed.
 * it is recommended that inject the authentication information into the request header.
 * For example,a HTTP request may be sent with "WWW-Authorition" header.</description>
 * @author Johnny Liu
 * @date 2018-10-02
 */
public interface Authorition{
	default boolean auth(HttpServletRequest request) { return false; }
}
