package org.axle.example.model;

import org.axle.core.annotation.Parameter;

public class Book {
	@Parameter
	private String name;
	@Parameter
	private double price;
	@Parameter
	private Author author;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
}
