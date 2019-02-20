package com.bcs.service;


import java.util.List;

import com.bcs.model.Book;

public interface BookService {
	
	Book findById(long id);
	
	Book findByName(String name);
	
	void saveBook(Book book);
	
	void updateBook(Book book);
	
	void deleteBookById(long id);

	List<Book> findAllBooks();
	
	void deleteAllBook();
	
	boolean isBookExist(Book book);

	List<Book> findBooks(String limit, String author, String price);
	
}
