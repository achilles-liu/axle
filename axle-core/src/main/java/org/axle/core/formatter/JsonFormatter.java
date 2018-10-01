/**
 * 
 */
package org.axle.core.formatter;

import org.axle.core.ApiReceipt;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * axle-core
 * <description></description>
 * @author Johnny Liu
 * @date 2018-10-01
 */
public class JsonFormatter implements Formatter {
	private static final String JSON_TEMPLATE = "{\"code\":%s,\"message\":\"%s\"}";
	private ObjectMapper objectMapper = new ObjectMapper();
	/* (non-Javadoc)
	 * @see org.axle.core.formatter.Formatter#format(org.axle.core.ApiReceipt)
	 */
	@Override
	public String format(ApiReceipt apiReceipt) {
		try {
			return this.objectMapper.writeValueAsString(apiReceipt);
		} catch (Exception e) {
			apiReceipt.setMessage(e.getMessage());
		}
		return String.format(JSON_TEMPLATE, apiReceipt.getCode(),apiReceipt.getMessage());
	}

}
