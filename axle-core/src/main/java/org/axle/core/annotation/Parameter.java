package org.axle.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * axle-core
 * <description>TODO prepare for validating filed</description>
 * @author Johnny Liu
 * @date 2018-09-30
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Parameter {
	/**
	 * indicate whether the parameter is from uri,
	 * For /api/{id},id is path variable.
	 * @return
	 */
	boolean fromUri() default false;
	boolean required() default false;
	String regular() default "";
}
