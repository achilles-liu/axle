/**
 * 
 */
package org.axle.core.security;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import org.springframework.beans.factory.FactoryBean;

/**
 * axle-core
 * <description></description>
 * @author Johnny Liu
 * @date 2018-10-02
 */
public class AuthoritionFactoryBean implements FactoryBean<Authorition>{

	public Authorition getObject() throws Exception {
		List<Authorition> list =new ArrayList<>();
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		ServiceLoader<Authorition> auth = ServiceLoader.load( Authorition.class, classloader );
		Iterator<Authorition> authIter = auth.iterator();
		while(authIter.hasNext())  list.add(authIter.next());
		return !list.isEmpty() ? list.get(0) : null;
	}

	public Class<?> getObjectType() {
		return Authorition.class;
	}

}
