package org.axle.core.deserialize;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.axle.core.util.ScanUtil;
import org.axle.core.util.ReflectUtil;
import org.springframework.beans.factory.InitializingBean;

/**
 * axle-core
 * <description><code>TypeDeserialize</code> chain.all <code>TypeDeserialize</code> 
 * will be loaded with launching the poject</description>
 * @author Johnny Liu
 * @date 2018-09-30
 */
public class TypeDeserializeChain implements InitializingBean{
	private static final String DEFAULT_INJECT = "inject";
	private List<Object> targets = new ArrayList<>();
	
	/**
	 * determine if the input type is an object type.
	 * @param type
	 * @return
	 */
	private boolean isObject(Class<?> type) {
		return ObjectTypeDeserialize.class.isAssignableFrom(type);
	}
	
	private boolean support(Class<?> type) {
		return TypeDeserialize.class.isAssignableFrom(type) 
				&& !type.isInterface() && !Modifier.isAbstract(type.getModifiers());
	}
	
	/**
	 * load the sub-class of <code>TypeDeserialize</code> in current directory.
	 * @throws Exception
	 */
	private void scan() throws Exception {
		ScanUtil.doScan((typeStr) -> {
			String tmpPath = typeStr.substring(0, typeStr.length()-6);
			Class<?> type = null;
			try {
				String tmp = tmpPath.replace('\\', '.');
				type = ReflectUtil.getType(tmp);
			} catch (Exception e) {/**ignore*/}
			if(type == null) type = ReflectUtil.loadClass(tmpPath);
			if(type == null) throw new Exception("can not load type deserialize");
			if(!isObject(type) && support(type)) {
				targets.add(ReflectUtil.create(type));
			}
		});
		targets.add(ReflectUtil.create(ObjectTypeDeserialize.class));
	}
	
	/**
	 * assemble the <code>TypeDeserialize</code>
	 * @throws Exception
	 */
	private void sort() throws Exception{
		int i = targets.size()-1;
		do {
			Object tmp = targets.get(i);
			Object pre = targets.get(i-1);
			ReflectUtil.inject(DEFAULT_INJECT,AbstractTypeDeserialize.class, pre, tmp);
			i--;
		}while(i > 0);
		ReflectUtil.inject(DEFAULT_INJECT, AbstractTypeDeserialize.class, targets.get(targets.size()-1), targets.get(0));
	}
	
	/**
	 * expose the <code>TypeDeserialize</code> chain.
	 * @return
	 * @throws Exception
	 */
	public TypeDeserialize chain() throws Exception{
		return (TypeDeserialize) targets.get(0);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		scan();
		sort();
	}
}
