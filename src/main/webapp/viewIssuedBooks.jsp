<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view Issued Books</title>
</head>
<body>
	<table border = 1>
		<tr>
	        <th>Issue ID</th>
	        <th>Member Name</th>
	        <th>Book Name</th>
	        <th>Issue Date</th>
	       	<th>Due date</th>
	        <th>Return Date</th>
	    </tr>
	
	    <%
	        List<Object[]> issuedBooks =
	            (List<Object[]>) request.getAttribute("issuedBooks");
	
	        for (Object[] issue : issuedBooks) {
	    %>
	
	    <tr>
	        <td><%= issue[0] %></td>
	        <td><%= issue[1] %></td>
	        <td><%= issue[2] %></td>
	        <td><%= issue[3] %></td>
	        <td><%= issue[4] %></td>
	        
	        <td><%= issue[5] %></td>
	    </tr>
	
	    <%
	        }
	    %>

	</table>


</body>
</html>