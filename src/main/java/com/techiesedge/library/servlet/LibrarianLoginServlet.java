package com.techiesedge.library.servlet;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/librarianLogin")
public class LibrarianLoginServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String userName = "librarian";
    private static final String PASSWORD = "Librarian@123";

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
    	
    	response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		
		response.setHeader("Access-Control-Allow-Credentials", "true");

        String user_name = request.getParameter("userName");
        String password = request.getParameter("password");

        if (userName.equals(user_name) && PASSWORD.equals(password)) {

            HttpSession session = request.getSession(true);

            session.setAttribute("librarian", true);
            session.setAttribute("librarianuserName", user_name);

            response.getWriter().write(
                    "{\"success\":true,\"message\":\"Login successful\"}"
                );

        } else {

        	response.getWriter().write(
                    "{\"success\":false,\"message\":\"Invalid username or password\"}"
                );
        }
    }
}
