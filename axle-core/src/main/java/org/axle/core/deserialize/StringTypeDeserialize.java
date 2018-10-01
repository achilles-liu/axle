package org.axle.core.deserialize;

import java.lang.reflect.Field;

/**
 * axle-core
 * <description>handle the String type</description>
 * @author Johnny Liu
 * @date 2018-09-30
 */
public class StringTypeDeserialize extends AbstractTypeDeserialize {

	public boolean match(Field field) {
		return field.getType().isAssignableFrom(String.class);
	}

	protected Object get(String value) {
		return value;
	}

}
