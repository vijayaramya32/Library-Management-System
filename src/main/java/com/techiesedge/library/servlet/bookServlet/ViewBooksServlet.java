package com.techiesedge.library.servlet.bookServlet;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techiesedge.library.DAO.BookManagementDAO;
import com.techiesedge.library.DAO.BookManagementDAOImpl;
import com.techiesedge.library.Entity.BookManagement;
import com.techiesedge.library.service.BookService;
import com.techiesedge.library.service.BookServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewBooks")

public class ViewBooksServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BookManagementDAO book = new BookManagementDAOImpl();


    private BookService bookCon;

    @Override
    public void init() {
        bookCon = new BookServiceImpl();
    }

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws HibernateException, ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<BookManagement> books = book.viewAllBooks();
		
		String json = mapper.writeValueAsString(books);
		
		response.setContentType("application/json");
		response.getWriter().write(json);

	}

}
