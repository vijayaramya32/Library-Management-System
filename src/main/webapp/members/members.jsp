<%@page import="com.techiesedge.library.Entity.MemberManagement"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view all members</title>
</head>
<body>
	<h2>view Members</h2>
	<table border = "1">
				
		<tr>
			<th>MemberID</th>
			<th>MemberName</th>
			<th>email</th>
			<th>phone</th>
			<th>address</th>
			<th>RegistrationDate</th>
			<th>status</th>
		</tr>
		
		
			<% List<MemberManagement> members = (List<MemberManagement>) request.getAttribute("members");
			
				
				
				for(MemberManagement member : members){ %>
			<tr>
				
				<td><%= member.getMemberID() %></td>
				<td><%= member.getMemberName() %></td>
				<td><%= member.getEmail() %></td>
				<td><%= member.getPhone() %></td>
				<td><%= member.getAddress() %></td>
				<td><%= member.getMembershipDate()%></td> 
				<td><%= member.getStatus()%></td>
				
			</tr>
			
				<%} %>
			
	
			
			
			
			
	
	</table>

</body>
</html>