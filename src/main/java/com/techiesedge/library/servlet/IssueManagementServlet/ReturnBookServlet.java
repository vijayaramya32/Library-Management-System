package com.techiesedge.library.servlet.IssueManagementServlet;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techiesedge.library.service.IssueManagementService;
import com.techiesedge.library.service.IssueManagementServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/returnBook")
public class ReturnBookServlet extends HttpServlet{

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
			
		    setCorsHeaders(response);

			
			response.setHeader("Access-Control-Allow-Origin", "*");	
			
			
			/*
			 * long issueID = Long.parseLong(request.getParameter("issueID"));
			 * 
			 * long memberID = Long.parseLong(request.getParameter("memberID"));
			 * 
			 * boolean returned = issue.returnBook(memberID,issueID);
			 * 
			 * if(returned) {
			 * 
			 * response.setStatus(HttpServletResponse.SC_OK);
			 * 
			 * response.getWriter().write("Book returned successfully");
			 * 
			 * // request.setAttribute("message", "book returned successfully"); }
			 * 
			 * else {
			 * 
			 * response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			 * response.getWriter().write("Failed to issued book");
			 * 
			 * 
			 * // request.setAttribute("message","error while returning"); }
			 */
//			request.getRequestDispatcher("/Validations.jsp").forward(request, response);
			
			
			
			 ObjectMapper mapper = new ObjectMapper();
			 JsonNode data = mapper.readTree(request.getReader());

			 long memberID = data.get("memberID").asLong();

			 List<Long> issueIDs =  mapper.convertValue(
					    data.get("issueIDs"),
					    new TypeReference<List<Long>>() {}
					);			

//			 boolean returned  = issue.returnBook(issueIDs);
			 
			 boolean returned = issue.returnBook(memberID, issueIDs);

			 
			 if(returned) {

				 response.setStatus(HttpServletResponse.SC_OK);
				 response.getWriter().write("Books returned successfully");
			 }
			 
			 else {
				 
				 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				 response.getWriter().write("Failed to Returned book");
				
			 }
			
		}catch(IllegalArgumentException e) {
			
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
			response.getWriter().write("Bad Request: " + e.getMessage()); 
			/*
			 * request.setAttribute("message", e.getMessage());
			 * 
			 * request.getRequestDispatcher("/validations.jsp").forward(request, response);
			 */
		}catch (Exception e) { 
			e.printStackTrace(); 
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); 
			response.getWriter().write("Internal Server Error: " + e.getMessage()); 
		}
	}

}
