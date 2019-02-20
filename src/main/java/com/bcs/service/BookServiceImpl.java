package com.bcs.service;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bcs.model.Book;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Service("bookService")
public class BookServiceImpl implements BookService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Book> books;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Book findById(long id) {
		Book books = new Book();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("select book_id ,book_name,book_author,book_price from book where book_id=?",new Object[] {id});
        if(!rows.isEmpty()){
	        for (Map<String, Object> row : rows) 
	        {
	        	books.setId((Integer)(row.get("book_id")));
	        	books.setBookName(String.valueOf(row.get("book_name")));
	        	books.setAuthor(String.valueOf(row.get("book_author")));
	        	books.setPrice(String.valueOf((row.get("book_price"))));
	         }
	        return books;
        }
		return null;
	}
	
	public Book findByName(String name) {		
		Book books = new Book();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("select book_id ,book_name,book_author,book_price from book where book_name=?",new Object[] {name});
        if(!rows.isEmpty()){
	        for (Map<String, Object> row : rows) 
	        {
	        	if(String.valueOf(row.get("book_name")).equalsIgnoreCase(name)){
	        		books.setId((Integer)(row.get("book_id")));
	        		books.setBookName(String.valueOf(row.get("book_name")));
	        		books.setAuthor(String.valueOf(row.get("book_author")));
	        		books.setPrice(String.valueOf((row.get("book_price"))));
	        	}
	         }
	        return books;
        }
		return null;
	}
	
	public void saveBook(Book book) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO book ("
					+ "book_name, "
					+ "book_author, "
					+ "book_price"
					+ ") "
	         		+ "VALUES (?,?,?)";	

	      jdbcTemplate.update(
	              connection -> {
	                  PreparedStatement ps = connection.prepareStatement(sql, new String[]{"req_rid"});
	                  ps.setString(1, book.getBookName());
	                  ps.setString(2, book.getAuthor());
	                  ps.setString(3, book.getPrice());
	                  return ps;
	              }, keyHolder);

	      Number key = keyHolder.getKey();
        //return (int) key.longValue();
	}

	public void updateBook(Book book) {
		
		  int rows = jdbcTemplate.update("update book set book_name = ?,"
		  		+ " book_author = ?, "
		  		+ "book_price = ? "
		  		+ "where book_id = ? "
		  		,new Object[] {book.getBookName(),
		  				book.getAuthor(),
		  				book.getPrice(),
		  				book.getId()},
		  				new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER});
	}

	public void deleteBookById(long id) {
		jdbcTemplate.update("DELETE FROM BOOK where book_id=?",id);
	}

	public boolean isBookExist(Book book) {
		return findByName(book.getBookName())!=null;
	}
	
	public void deleteAllBook(){
		jdbcTemplate.update("DELETE FROM BOOK");
	}

	public List<Book> findAllBooks(){
		List<Book> books = new ArrayList<Book>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("select book_id ,book_name,book_author,book_price from book");
        if(!rows.isEmpty()){
        for (Map<String, Object> row : rows) 
        {
        	Book book = new Book();
        	book.setId((Integer)(row.get("book_id")));
			book.setBookName(String.valueOf(row.get("book_name")));
			book.setAuthor(String.valueOf(row.get("book_author")));
			book.setPrice(String.valueOf((row.get("book_price"))));
			books.add(book);
         }
        }
		return books;
	}
	public List<Book> findBooks(String limit, String author, String price)
	{
		String sql ="select book_id ,book_name,book_author,book_price from book ";
		    if(author !=null && price !=null){
				sql += " where book_author='"+author+"' and book_price ='"+price+"'";
			}else{
				if(author !=null){
				    sql +=  " where book_author='"+author+"'";
				}
				if(price !=null){
				    sql += " where book_price ='"+price+"'";
				}
			}
			if(limit != null){
				sql += " LIMIT "+limit;
			}
			
		List<Book> books = new ArrayList<Book>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        if(!rows.isEmpty()){
        for (Map<String, Object> row : rows) 
        {
        	Book book = new Book();
        	book.setId((Integer)(row.get("book_id")));
			book.setBookName(String.valueOf(row.get("book_name")));
			book.setAuthor(String.valueOf(row.get("book_author")));
			book.setPrice(String.valueOf((row.get("book_price"))));
			books.add(book);
         }
        }
		return books;
	}
}
