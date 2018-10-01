package org.axle.example.request;

import org.axle.core.annotation.Parameter;
import org.axle.core.request.Request;
import org.axle.example.model.Book;

public class AddExampleRequest extends Request{
	@Parameter
	private String username;
	@Parameter
	private String nickname;
	@Parameter
	private int age;
	@Parameter
	private Book book;
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
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
}
