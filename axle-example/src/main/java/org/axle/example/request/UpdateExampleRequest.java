/**
 * 
 */
package org.axle.example.request;

import org.axle.core.annotation.Parameter;
import org.axle.core.request.Request;

/**
 * axle-example
 * <description></description>
 * @author Johnny Liu
 * @date 2018-10-02
 */
public class UpdateExampleRequest extends Request{
	@Parameter
	private int id;
	@Parameter
	private String username;
	@Parameter
	private String nickname;
	@Parameter
	private int age;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
