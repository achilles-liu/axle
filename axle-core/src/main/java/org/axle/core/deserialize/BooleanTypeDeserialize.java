package org.axle.core.deserialize;

import java.lang.reflect.Field;

/**
 * axle-core
 * <description>handle the boolean type</description>
 * @author Johnny Liu
 * @date 2018-09-30
 */
public class BooleanTypeDeserialize extends AbstractTypeDeserialize {

	public boolean match(Field field) {
		return field.getType().isAssignableFrom(Boolean.class) 
				|| field.getType().isAssignableFrom(boolean.class);
	}

	protected Object get(String value) {
		return Boolean.parseBoolean(value);
	}
	
}
