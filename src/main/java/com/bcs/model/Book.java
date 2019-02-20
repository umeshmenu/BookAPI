package com.bcs.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
	
public class Book {

	private long id;
	@NotNull
	private String bookName;
	@NotNull
	// @Pattern(regexp="[a-zA-Z]{1,50}$", message="author name should be alphabetical and less than or equal to 50 characters")
	private String author;	
	@NotNull
	private String price;
	

	public Book(){
		id=0;
	}
	
	public Book(long id, String bookName, String author, String price){
		this.id = id;
		this.bookName = bookName;
		this.author = author;
		this.price = price;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", author=" + author
				+ ", price=" + price + "]";
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


}
