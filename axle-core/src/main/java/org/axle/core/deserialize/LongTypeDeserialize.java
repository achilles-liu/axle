/**
 * 
 */
package org.axle.core.deserialize;

import java.lang.reflect.Field;

/**
 * axle-core
 * <description></description>
 * @author Johnny Liu
 * @date 2018-11-08
 */
public class LongTypeDeserialize extends AbstractTypeDeserialize{

	public boolean match(Field field) {
		return field.getType().isAssignableFrom(Long.class) 
				|| field.getType().isAssignableFrom(long.class);
	}

	protected Object get(String value) {
		return Long.parseLong(value);
	}
	
	protected boolean support(Object value) throws Exception {
		return Long.class.isInstance(value);
	}

}
