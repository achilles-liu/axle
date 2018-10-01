/**
 * 
 */
package org.axle.core.formatter;

import org.axle.core.ApiReceipt;

/**
 * axle-core
 * <description></description>
 * @author Johnny Liu
 * @date 2018-10-01
 */
@FunctionalInterface
public interface Formatter {
	/**
	 * format the <code>ApiReceipt</code> to string.
	 * @param apiReceipt - the incoming object
	 * @return
	 */
	public String format(ApiReceipt apiReceipt);
}
