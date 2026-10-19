package com.techiesedge.library.servlet.bookServlet;

import java.io.IOException;
import java.util.List;

import com.techiesedge.library.Entity.BookManagement;
import com.techiesedge.library.service.BookService;
import com.techiesedge.library.service.BookServiceImpl;
import com.techiesedge.library.service.Validations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/searchBooks")

public class SearchBookServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BookService book;
	
	public void init() {
		
		book = new BookServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		try {						
			
			String searchBy = request.getParameter("searchBy"); 
			
			String searchValue = request.getParameter("searchValue"); 
			
			String  isbn  = null; 
			
			String title = null; 
			
			String author = null; 
			
			String category = null; 
			
			if ("isbn".equals(searchBy)) { 
				
				isbn = searchValue; 
				

				if(isbn == null || isbn.isBlank()) {
					
					throw new IllegalArgumentException("Isbn shouldn't be empty");
				}
				
				
			} 
			
			else if ("title".equals(searchBy)) { 
				
				title = searchValue; 
				
				Validations.validateBookTitle(title);
				
			} 
			
			else if ("author".equals(searchBy)) { 
				
				author = searchValue; 
				
				Validations.validateAuthor(author);
				
			} 
			
			else if ("category".equals(searchBy)) { 
				
				category = searchValue; 
							
			}
			
			List<BookManagement> books = book.filter(isbn, title, author, category);
			
			request.setAttribute("books", books);
			
			request.getRequestDispatcher("/books/SearchBooks.jsp").forward(request, response);
			
			
		}catch(IllegalArgumentException e) {
			
			request.setAttribute("message",e.getMessage());
			
			request.getRequestDispatcher("Validations.jsp").forward(request,response);
			
		}
		
		
	}
	
	

}
