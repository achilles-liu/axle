package org.axle.example.model;

import org.axle.core.annotation.Parameter;

public class Author {
	@Parameter
	private String alias;
	@Parameter
	private int age;
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
