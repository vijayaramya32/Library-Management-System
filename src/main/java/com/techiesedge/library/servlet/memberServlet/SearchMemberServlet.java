package com.techiesedge.library.servlet.memberServlet;

import java.io.IOException;
import java.util.List;

import com.techiesedge.library.Entity.MemberManagement;
import com.techiesedge.library.service.MemberService;
import com.techiesedge.library.service.MemberServiceImpl;
import com.techiesedge.library.service.Validations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/searchMember")
public class SearchMemberServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MemberService member;
	
	public void init() {
		
		member = new MemberServiceImpl();
		
	}
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		try {
		
			String searchBy = request.getParameter("searchBy"); 
			
			String searchValue = request.getParameter("searchValue"); 
			
			int memberID = 0; 
			
			String name = null; 
			
			String email = null; 
			
			String phone = null; 
			
			if ("memberID".equals(searchBy)) { 
				
				memberID = Integer.parseInt(searchValue); 
				
			} 
			
			else if ("memberName".equals(searchBy)) { 
				
				name = searchValue; 
				
				Validations.validateMemberName(name);
				
			} 
			
			else if ("email".equals(searchBy)) { 
				
				email = searchValue; 
				
				Validations.validateEmail(email);
				
			} 
			
			else if ("phone".equals(searchBy)) { 
				
				phone = searchValue; 
				
				Validations.validatePhone(phone);
				
			}
			
			List<MemberManagement> members = member.searchMember(memberID, name, email, phone);
			
			request.setAttribute("members", members);
			
			request.getRequestDispatcher("/members/SearchMember.jsp").forward(request, response);
		}catch(IllegalArgumentException e) {
			
			request.setAttribute("message",e.getMessage());
			
			request.getRequestDispatcher("/Validations.jsp").forward(request, response);
		}
		
	}
		

}
