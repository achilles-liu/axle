package org.axle.core.deserialize;

import java.lang.reflect.Field;

/**
 * axle-core
 * <description>handle the Integer type and int type</description>
 * @author Johnny Liu
 * @date 2018-09-30
 */
public class IntegerTypeDeserialize extends AbstractTypeDeserialize {
	
	public boolean match(Field field) {
		return field.getType().isAssignableFrom(Integer.class) 
				|| field.getType().isAssignableFrom(int.class);
	}

	protected Object get(String value) {
		return Integer.parseInt(value);
	}

	protected boolean support(Object value) throws Exception {
		return Integer.class.isInstance(value);
	}

}
