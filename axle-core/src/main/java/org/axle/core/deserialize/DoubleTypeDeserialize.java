package org.axle.core.deserialize;

import java.lang.reflect.Field;

/**
 * axle-core
 * <description>handle the double type</description>
 * @author Johnny Liu
 * @date 2018-09-30
 */
public class DoubleTypeDeserialize extends AbstractTypeDeserialize {

	public boolean match(Field field) {
		return field.getType().isAssignableFrom(Double.class) 
				|| field.getType().isAssignableFrom(double.class);
	}

	protected Object get(String value) {
		return Double.parseDouble(value);
	}

	protected boolean support(Object value) throws Exception {
		return Double.class.isInstance(value);
	}

}
