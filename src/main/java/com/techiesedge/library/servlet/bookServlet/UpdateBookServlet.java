package com.techiesedge.library.servlet.bookServlet;

import java.io.IOException;

import com.techiesedge.library.service.BookService;
import com.techiesedge.library.service.BookServiceImpl;
import com.techiesedge.library.service.Validations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/updateBook")

public class UpdateBookServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BookService book;
	
	private void setCorsHeaders(HttpServletResponse response) {
	    response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type");
	}
	
	@Override
	protected void doOptions(HttpServletRequest request,
	                         HttpServletResponse response) {
	    setCorsHeaders(response);
	    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
	public void init() {
		
		book = new BookServiceImpl();
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		
		try {
			
		    setCorsHeaders(response);

		
			 int id = Integer.parseInt(request.getParameter("bookID"));
			 
			 String author = request.getParameter("author");
			 
			 Validations.validateAuthor(author);

			 String category = request.getParameter("category");
			 
			 double price = Double.parseDouble(request.getParameter("price"));
			 
			 int quantity = Integer.parseInt(request.getParameter("availableQuantity"));
			 
	         Validations.validateBookQuantity(quantity);
			 /*String update = request.getParameter("update");
			 
			 String updateValue = request.getParameter("updateValue");
			 
			 String title = null;
			 
			 String author = null;
			 
			 String category = null;
			 
			 String priceInput = null;
			 
			 String quantityInput = null;

			 if("bookTitle".equals(update)) {
				 
				 title = updateValue;
				 
				 Validations.validateBookTitle(title);
				 
			 }
			 
			 if("author".equals(update)) {
				 
				author = updateValue;
					 
				Validations.validateAuthor(author);
				 
			 }
			 
			 if("category".equals(update)) {
		
				 category = updateValue; 
				 
			 }
			 
			 double price = 0;
			 int quantity = -1;
			 
			 try {
				 
				 if("price".equals(update)) {
					 
					 priceInput = updateValue;
					 
					 if (priceInput != null && !priceInput.isBlank()) {
				          price = Double.parseDouble(priceInput);
				      }
		
					 
				 }
				 
				 if("availableQuantity".equals(update)) {
					 
					 quantityInput = updateValue;
					 
					 if (quantityInput != null && !quantityInput.isBlank()) {
				           quantity = Integer.parseInt(quantityInput);
				           
				           Validations.validateBookQuantity(quantity);
				      }
		
					 
					 
				 }*/

				boolean updated = book.updateDetails(id, author, category, price, quantity);
				
				/*if (updated) {
			    	
			        request.setAttribute("message", "book updated succesfully");
			        
			    } else {
			    	
			        request.setAttribute("message", "Failed to update book.");

			    }
			    
			     request.getRequestDispatcher("/Validations.jsp").forward(request, response);*/

				if (updated) {
					    response.setStatus(HttpServletResponse.SC_OK);
					    response.getWriter().write("book updated successfully");
					} else {
					    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					    response.getWriter().write("book not found");
					}
						
	
			    
			     
			    } catch (NumberFormatException e) {
	
			    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		            response.getWriter().write("Invalid price/quantity/bookID");
	
			 	}catch (Exception e) {
		            e.printStackTrace();
		            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		            response.getWriter().write("Failed to update book");
		        }

			 
			
		 
		 
		 
		
	}
	
	

}
