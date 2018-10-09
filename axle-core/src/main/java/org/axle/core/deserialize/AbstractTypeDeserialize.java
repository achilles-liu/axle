package org.axle.core.deserialize;

import java.lang.reflect.Field;

import org.axle.core.annotation.Parameter;
import org.axle.core.util.R;

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
 			// check parameter type
 			check(field,value);
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
 	private void check(Field field,Object value) throws Exception{
 		if(!field.isAnnotationPresent(Parameter.class)) throw new Exception(String.format(R.param.WITHOUT_ANNOTATION, field.getName()));
		Parameter param = field.getAnnotation(Parameter.class);
		if(param.required() && value == null) throw new Exception(String.format(R.param.NOT_NULL, field.getName()));
		if(!param.fromUri() && !support(value))
			throw new Exception(String.format(R.param.INCORRENT_TYPE, field.getName()));
		if(!"".equals(param.regular()) && !String.valueOf(value).matches(param.regular()))
			throw new Exception(String.format(R.param.INCORRENT_VALUE, field.getName()));
 	}
	protected boolean support(Object value) throws Exception{ return true; }
}
