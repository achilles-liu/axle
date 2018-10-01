package org.axle.core.deserialize;

import java.lang.reflect.Field;

/**
 * axle-core
 * <description>inject the incoming <code>TypeDeserialize</code> 
 * and implement parameter type deserialization.
 * it can be extended by inheriting the abstract class.</description>
 * @author Johnny Liu
 * @date 2018-09-30
 */
public abstract class AbstractTypeDeserialize implements TypeDeserialize {
	
	protected TypeDeserialize deser;

	public TypeDeserialize inject(TypeDeserialize deser) {
		this.deser = deser;
		return this;
	}

 	public void deserialize(Object target, Field field, Object value) throws Exception {
 		if(match(field)) {
 			field.setAccessible(true);
 			field.set(target, get(String.valueOf(value)));
 		}else {
 			if(this.deser != null)
 				this.deser.deserialize(target, field, value);
 		}
	}
 	
 	/**
 	 * cast to the corresponding type of field.
 	 * @param value
 	 * @return
 	 */
 	protected abstract Object get(String value);
	
}
