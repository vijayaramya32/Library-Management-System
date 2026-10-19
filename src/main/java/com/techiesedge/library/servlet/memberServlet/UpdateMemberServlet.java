package com.techiesedge.library.servlet.memberServlet;

import java.io.IOException;

import com.techiesedge.library.service.MemberService;
import com.techiesedge.library.service.MemberServiceImpl;
import com.techiesedge.library.service.Validations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateMember")
public class UpdateMemberServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MemberService member;
	
	public void init() {
		
		member = new MemberServiceImpl();
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
		
		
			int id = Integer.parseInt(request.getParameter("memberID"));
			
			/*
			 * String memberName = request.getParameter("memberName");
			 * 
			 * Validations.validateMemberName(memberName);
			 * 
			 * String email = request.getParameter("email");
			 * 
			 * Validations.validateEmail(email);
			 */
			
			String phone = request.getParameter("phone");
            String address = request.getParameter("address");
			
//			String update = request.getParameter("update");
//			
//			String updateValue = request.getParameter("updateValue");
//			
//			String phone = null;
//			
//			String address = null;
//			
//			if("phone".equals(update)) {
//				
//				phone = updateValue;
//				
//				Validations.validatePhone(phone);
//				
//				
//			}
//			
//			if("address".equals(update)) {
//			
//				address = updateValue;
//				
//				Validations.validateAddress(address);
//				
//			}
//			
			Boolean updated = member.updateMembers(id, phone, address);
			if (updated) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Member updated successfully");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Member not found");
            }

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid member ID");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to update member");
        }
    
//			
////			Boolean updated = member.updateMembers(id, memberName, email, phone, address);

//			
//			if(updated) {
//				
//				request.setAttribute("message","member updated successfully");
//			}
//			
//			else {
//				
//				request.setAttribute("message","fail to update members");
//			}
//			
//			request.getRequestDispatcher("/Validations.jsp").forward(request, response);
//
//			
//		}catch(IllegalArgumentException e) {
//			
//			request.setAttribute("message", e.getMessage());
//			
//			request.getRequestDispatcher("/Validations.jsp").forward(request, response);
//		}
	}

}
