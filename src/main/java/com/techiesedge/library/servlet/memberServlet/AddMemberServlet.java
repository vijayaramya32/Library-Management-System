package com.techiesedge.library.servlet.memberServlet;

import java.io.IOException;
import java.time.LocalDate;

import com.techiesedge.library.Entity.MemberManagement;
import com.techiesedge.library.service.MemberService;
import com.techiesedge.library.service.MemberServiceImpl;
import com.techiesedge.library.service.Validations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addMember")
public class AddMemberServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MemberService member;

	public void init() {
		
		member = new MemberServiceImpl();
		
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		try {
		
			String memberName = request.getParameter("memberName");
			
			Validations.validateMemberName(memberName);
			
			String email = request.getParameter("email");
			
			Validations.validateEmail(email);
			
			String phone = request.getParameter("phone");
			
			Validations.validatePhone(phone);
			
			String address = request.getParameter("address");
			
			MemberManagement m = new MemberManagement();
			
			m.setMemberName(memberName);
			
			m.setEmail(email);
			
			m.setPhone(phone);
			
			m.setAddress(address);
			
			m.setMembershipDate(LocalDate.now());
			
			m.setStatus("active");
			
			boolean addMember = member.register(m);
			
			if (addMember) {
			    response.setStatus(HttpServletResponse.SC_OK);
			    response.getWriter().write("Member added successfully");
			} else {
			    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			    response.getWriter().write("Failed to add Member");
			}
			
			/*if(addMember) {
				
				request.setAttribute("message", "member added successfully");
			}
			
			else {
				
				request.setAttribute("message","failed to add member");
	
			}
			
			request.getRequestDispatcher("/Validations.jsp").forward(request, response);*/
		}catch(IllegalArgumentException e) {
			
//			request.setAttribute("message",e.getMessage());
			
//			request.getRequestDispatcher("/Validations.jsp").forward(request, response);
		}
		
	}

}
