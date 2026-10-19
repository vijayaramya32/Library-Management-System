package com.techiesedge.library.servlet.bookServlet;

import java.io.IOException;

import com.techiesedge.library.service.BookService;
import com.techiesedge.library.service.BookServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteBook")

public class DeleteBookServlet extends HttpServlet{

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
			
			boolean deleteBook = book.deleteBook(id);
			
			
			if(deleteBook) {
				
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write("book deleted succesfully");

//				request.setAttribute("message", "delete successfully");
			}
			
			else {
				
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().write("book not found");

				
//				request.setAttribute("message","delete failed");
	
			}
			
//			request.getRequestDispatcher("/Validations.jsp").forward(request, response);
			
		}catch (NumberFormatException e) {
			
	    	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Bad Request: " + e.getMessage());

	 	}catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Internal Server Error: " + e.getMessage());
        }
	}

}
