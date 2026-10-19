package com.techiesedge.library.service;

import java.util.List;

import com.techiesedge.library.DAO.BookManagementDAO;
import com.techiesedge.library.DAO.BookManagementDAOImpl;
import com.techiesedge.library.Entity.BookManagement;

public class BookServiceImpl implements BookService{
	
	BookManagementDAO book = new BookManagementDAOImpl();
		
	public boolean addBook(BookManagement book1) {
		
		
		return book.addBook(book1);
	}
	
	public List<BookManagement> viewAllBooks() {
		
		
		return book.viewAllBooks();
	}
	
	public List<BookManagement> filter(String ISBN, String title,String author,String category){
		
		return book.filter(ISBN, title, author, category);
	}
	
//	public boolean updateDetails(int id,String title,String author,String category, double price, int quantity) {
	
	public boolean updateDetails(int id,String author,String category, double price, int quantity) {

		
		return book.updateDetails(id, author, category, price, quantity);
	}
	
	public boolean deleteBook(int id) {
	
		return book.deleteBook(id);
		
	}

	@Override
	public BookManagement getBookByISBN(String isbn) {
	
		return book.getBookByISBN(isbn);
	}

}
