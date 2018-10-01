package org.axle.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.axle.core.deserialize.TypeDeserialize;

public class ReflectUtil {
	
	public static Class<?> getType(String typeStr) throws Exception{
		try {
			return Class.forName(typeStr);
		} catch (ClassNotFoundException e) {
			throw new Exception(e);
		}
	}
	
	public static Class<?> loadClass(String typeStr){
		try {
			return Thread.currentThread().getContextClassLoader().loadClass(typeStr);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object create(Class<?> type){
		try {
			return type.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void inject(String signature,Class<?> targetClass,Object target,Object...params) {
		try {
			Method mn = targetClass.getDeclaredMethod(signature, 
					new Class<?>[] {TypeDeserialize.class});
			mn.invoke(target, params);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
