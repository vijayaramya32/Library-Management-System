package com.techiesedge.library.servlet.bookServlet;

import java.io.IOException;

import com.techiesedge.library.Entity.BookManagement;
import com.techiesedge.library.service.BookService;
import com.techiesedge.library.service.BookServiceImpl;
import com.techiesedge.library.service.Validations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BookService bookSer;
	
	public void init() {
		
		bookSer = new BookServiceImpl();
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		
		response.setHeader("Access-Control-Allow-Origin","*");
		
//		try {
			
			 double price;
			
			long quantity;
			
			int publishedYear;
		
			String isbn = request.getParameter("isbn");
			
			System.out.println("ISBN received: " + isbn);

			
			Validations.validateIsbn(isbn);
			
			String bookTitle = request.getParameter("bookTitle");
			
			Validations.validateBookTitle(bookTitle);
			
			String author = request.getParameter("author");
			
			Validations.validateAuthor(author);
			
			String category = request.getParameter("category");
			
			try { 
				
				price = Double.parseDouble(request.getParameter("price")); 
				
				quantity = Long.parseLong(request.getParameter("availableQuantity")); 
				
				Validations.validateBookQuantity(quantity);
				
				publishedYear = Integer.parseInt(request.getParameter("publishedYear"));
				
			}catch (NumberFormatException e) {
				
				
				/*
				 * request.setAttribute("message",
				 * "Price, quantity and published year should be in number format.");
				 * 
				 * request.getRequestDispatcher("/books/addBook.jsp") .forward(request,
				 * response);
				 */
				  
				  return; 
			}
				 
		
			
			BookManagement book = new BookManagement(); 
			
			book.setISBN(isbn); 
			
			book.setBookTitle(bookTitle); 
			
			book.setAuthor(author); 
			
			book.setCategory(category); 
			
			book.setPrice(price); 
			
			book.setAvailableQuantity(quantity); 
			
			book.setPublishedYear(publishedYear);
			
			book.setStatus("active");
					
			boolean saved = bookSer.addBook(book);
			
			if (saved) {
			    response.setStatus(HttpServletResponse.SC_OK);
			    response.getWriter().write("Book added successfully");
			} else {
			    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			    response.getWriter().write("Failed to add book");
			}
			
			
			
		/*	if (saved) { 
				
				request.setAttribute("message","Book added successfully!" );
  
			}
							
			else {
				
				request.setAttribute("message","Failed to add book" );
						
			}
			
			request.getRequestDispatcher("/Validations.jsp")
	           .forward(request, response);*/
			
		/*}catch(IllegalArgumentException e) {
			
			request.setAttribute("message", e.getMessage());
			
			request.getRequestDispatcher("/Validations.jsp")
	           .forward(request, response);
		}*/

	
	}
		
}
