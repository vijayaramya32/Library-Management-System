package com.techiesedge.library.servlet.memberServlet;

import java.io.IOException;

import com.techiesedge.library.service.MemberService;
import com.techiesedge.library.service.MemberServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteMember")
public class DeleteMemberServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MemberService member;
	
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
		
		member = new MemberServiceImpl();
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		
		try {
		    setCorsHeaders(response);
		    System.out.println("Query string: " + request.getQueryString());
		    System.out.println("memberID: " + request.getParameter("memberID"));

		
			int id = Integer.parseInt(request.getParameter("memberID"));
		
			
			boolean deleted = member.deleteMember(id);
			
			if(deleted) {
				
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write("member deleted sucessfully");
				
//				request.setAttribute("message", "member deleted succesfully");
			}
			
			else {
				
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().write("member not found");
				
//				request.setAttribute("message", "failed to delete");
	
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
