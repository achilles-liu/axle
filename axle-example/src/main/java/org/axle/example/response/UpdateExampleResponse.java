/**
 * 
 */
package org.axle.example.response;

import org.axle.core.response.Response;

/**
 * axle-example
 * <description></description>
 * @author Johnny Liu
 * @date 2018-10-02
 */
public class UpdateExampleResponse extends Response{
	private int id;
	private String username;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
