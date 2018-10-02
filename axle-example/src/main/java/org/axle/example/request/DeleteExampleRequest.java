/**
 * 
 */
package org.axle.example.request;

import org.axle.core.request.Request;

/**
 * axle-example
 * <description></description>
 * @author Johnny Liu
 * @date 2018-10-02
 */
public class DeleteExampleRequest extends Request {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
