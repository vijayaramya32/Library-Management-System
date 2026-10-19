package com.techiesedge.library.service;

import java.util.List;

import com.techiesedge.library.Entity.BookManagement;

public interface BookService {
	
	boolean addBook(BookManagement book1);
	
	List<BookManagement> viewAllBooks();
	
	List<BookManagement> filter(String ISBN, String title,String author,String category);
	
//	boolean updateDetails(int id,String title,String author,String category, double price, int quantity);
	boolean updateDetails(int id,String author,String category, double price, int quantity);

	
	boolean deleteBook(int id);
	
	BookManagement getBookByISBN(String isbn);

}
