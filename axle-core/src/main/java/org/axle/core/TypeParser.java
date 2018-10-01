package org.axle.core;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * axle-core
 * <description>FIXME handle request paremeter type</description>
 * @author Johnny Liu
 * @date 2018-09-30
 */
public interface TypeParser {
	void parse(Object target,Field field,Map<String,Object> rawParamMap) throws Exception;
}
