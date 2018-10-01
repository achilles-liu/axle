package org.axle.core.deserialize;

import java.lang.reflect.Field;
import java.util.Map;

import org.axle.core.util.ReflectUtil;

/**
 * axle-core
 * <description>handle the object type</description>
 * @author Johnny Liu
 * @date 2018-09-30
 */
public class ObjectTypeDeserialize extends AbstractTypeDeserialize {
	
	public boolean match(Field field) {
		return field.getType().isAssignableFrom(Object.class);
	}

	public void deserialize(Object target, Field field, Object value) throws Exception {
		if(value instanceof Map) {
			Field[] subFields = field.getType().getDeclaredFields();
			Object subTarget = ReflectUtil.create(field.getType());
			@SuppressWarnings("unchecked")
			Map<String,Object> subParamMap = (Map<String, Object>) value;
			for(Field subField : subFields) {
				super.deserialize(subTarget, subField, subParamMap.get(subField.getName()));
			}
			field.setAccessible(true);
			field.set(target, subTarget);
		}
	}

	protected Object get(String value) {
		/**ignore*/
		return null;
	}

}
