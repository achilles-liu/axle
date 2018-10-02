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
public class UpdateAgeResponse extends Response{
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
