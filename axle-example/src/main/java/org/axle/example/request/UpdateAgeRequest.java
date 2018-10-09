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
public class UpdateAgeRequest extends Request{
	@Parameter(fromUri=true,required=true,regular="^[0-9]*[1-9][0-9]*$")
	private int id;
	@Parameter(required=true)
	private int age;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
