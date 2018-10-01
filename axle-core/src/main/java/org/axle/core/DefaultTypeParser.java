package org.axle.core;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * axle-core
 * <description></description>
 * @author Johnny Liu
 * @date 2018-09-30
 * @see org.axle.core.deserialize.TypeDeserializeChain
 */
public class DefaultTypeParser implements TypeParser {

	@Override
	public void parse(Object target, Field field, Map<String, Object> rawParamMap) throws Exception{
		// FIXME 解析属性
		String fval = field.getName();
		field.setAccessible(true);
		Class<?> fieldType = field.getType();
		String typeName = fieldType.getSimpleName();
		if("int".equals(typeName) || "Integer".equals(typeName)) {
			field.set(target, Integer.parseInt(String.valueOf(rawParamMap.get(fval))));
		}else {
			field.set(target, fieldType.cast(rawParamMap.get(fval)));
		}
	}

}
