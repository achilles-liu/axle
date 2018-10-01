package org.axle.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * axle-core
 * <description>handle the request and build the response</description>
 * @author Johnny Liu
 * @date 2018-09-30
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@ResponseBody
public @interface ApiAction {
	@AliasFor(annotation = Controller.class)
	String value() default "";
	String url() default "";
	RequestMethod method() default RequestMethod.GET;
	IoParam io();
}
