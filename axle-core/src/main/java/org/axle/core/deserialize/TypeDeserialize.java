package org.axle.core.deserialize;

import java.lang.reflect.Field;

/**
 * axle-core
 * <description>this interface is intended to convert parameter type</description>
 * @author Johnny Liu
 * @date 2018-09-30
 */
public interface TypeDeserialize {
	/**
	 * inject the next <code>TypeDeserialize</code>
	 * @param deser - next <code>TypeDeserialize</code>
	 * @return current <code>TypeDeserialize</code>
	 */
	TypeDeserialize inject(TypeDeserialize deser);
	/**
	 * whether the field matches the specified type.
	 * @param field - actual filed
	 * @return
	 * 		  true  - match the specified type
	 * 		  false - don't match the specified type
	 */
	boolean match(Field field);
	/**
	 * set the actual type to the target instance.
	 * @param target - target instance
	 * @param field  - target field
	 * @param value  - actual value collection
	 * @throws Exception
	 */
	void deserialize(Object target,Field field,Object value) throws Exception;
}
