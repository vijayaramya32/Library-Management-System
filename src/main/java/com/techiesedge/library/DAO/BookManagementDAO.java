package com.techiesedge.library.DAO;

import java.util.List;

import com.techiesedge.library.Entity.BookManagement;

public interface BookManagementDAO {
	
	boolean addBook(BookManagement book);
	
	List<BookManagement> viewAllBooks();
	
	List<BookManagement> filter(String ISBN, String title,String author,String category);
	
	boolean updateDetails(int id,String author,String category, double price, int quantity);
	
	boolean deleteBook(int id);

	BookManagement getBookById(long bookId);
	
	BookManagement getBookByISBN(String isbn);
}
