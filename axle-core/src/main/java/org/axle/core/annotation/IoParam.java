package org.axle.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.axle.core.request.Request;
import org.axle.core.response.Response;

/**
 * axle-core
 * <description>indicate the request type and response type</description>
 * @author Johnny Liu
 * @date 2018-09-30
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IoParam {
	Class<? extends Request> in() default Request.class;
	Class<? extends Response> out() default Response.class;
}
