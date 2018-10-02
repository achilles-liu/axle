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
public class DeleteExampleResponse extends Response {
	private int id;
	private String nickname;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
