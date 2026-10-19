package com.techiesedge.library.servlet.memberServlet;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techiesedge.library.Entity.MemberManagement;
import com.techiesedge.library.service.MemberService;
import com.techiesedge.library.service.MemberServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@WebServlet("/viewMembers")
public class ViewAllMembersServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MemberService member;
	
	public void init() {
		
		member = new MemberServiceImpl();
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.registerModule(new JavaTimeModule());
		
		List<MemberManagement> members = member.viewAllMembers();
		
		String json = mapper.writeValueAsString(members);
		
		response.setContentType("application/json");
		
		response.getWriter().write(json);
	}
	
	

}
