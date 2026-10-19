package com.techiesedge.library.servlet.IssueManagementServlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.techiesedge.library.service.IssueManagementService;
import com.techiesedge.library.service.IssueManagementServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewAllBooks")
public class ViewIssueServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IssueManagementService issue;
	
	public void init() {
		
		issue = new IssueManagementServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");	
		
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.registerModule(new JavaTimeModule());
		
		List<Object[]> issuedBooks = issue.viewIssuedBooks();
		
		String json = mapper.writeValueAsString(issuedBooks);
		
		response.setContentType("application/json");
		
		response.getWriter().write(json);


	    
	}
	
	

}
