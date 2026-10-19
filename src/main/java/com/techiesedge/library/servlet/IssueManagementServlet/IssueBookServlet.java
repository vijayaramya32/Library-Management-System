package com.techiesedge.library.servlet.IssueManagementServlet;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techiesedge.library.Entity.IssueManagement;
import com.techiesedge.library.service.IssueManagementService;
import com.techiesedge.library.service.IssueManagementServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/issueBook")
public class IssueBookServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IssueManagementService issue;
	
	public void init() {
		
		issue = new IssueManagementServiceImpl();
	}
	
	
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
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		
		try {
		
			/*
			 * long memberId = Long.parseLong(request.getParameter("memberID"));
			 * 
			 * long bookId = Long.parseLong(request.getParameter("bookID"));
			 */
			
			 setCorsHeaders(response);
			 ObjectMapper mapper = new ObjectMapper();
			 JsonNode data = mapper.readTree(request.getReader());

			 long memberID = data.get("memberID").asLong();

			 List<Long> bookIDs =  mapper.convertValue(
					    data.get("bookIDs"),
					    new TypeReference<List<Long>>() {}
					);	
						
			
			boolean issued = issue.issueBook(bookIDs,memberID);
			
			if (issued) {
			    response.setStatus(HttpServletResponse.SC_OK);
			    response.getWriter().write("Book issued successfully");
			} else {
			    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			    response.getWriter().write("Failed to issued book");
			}
			
			
			/*if(issued) {
				
				request.setAttribute("message", "book issued succesfully");
				
			}
			
			else {
				
				request.setAttribute("message","fail to book issue");
			}
			
			request.getRequestDispatcher("/Validations.jsp").forward(request, response);*/
			
		}catch (IllegalArgumentException e) { 
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
			response.getWriter().write("Bad Request: " + e.getMessage()); 
		} catch (Exception e) { 
			e.printStackTrace(); 
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); 
			response.getWriter().write("Internal Server Error: " + e.getMessage()); 
		}
		
		/*catch(IllegalArgumentException e) {
			
			request.setAttribute("message", e.getMessage());
			
			request.getRequestDispatcher("/Validations.jsp").forward(request, response);
			
		}*/
		
		
	}
	
	
	
	

}
