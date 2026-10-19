<%@page import="com.techiesedge.library.Entity.MemberManagement"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search member</title>
</head>
<body>
	
	

	
	
	<form method = "Get" action = "searchMember">
	
		<select name="searchBy">
		    <option value="memberID">Member ID</option>
		    <option value="memberName">Name</option>
		    <option value="email">Email</option>
		    <option value = "phone">phone</option>
		</select>
		
		<%String variable = request.getParameter("searchBy"); %>
	
		<h2>search member by <%= request.getAttribute("variable")  %> </h2>
		
		<label>Enter Value:</label>
	    <input type="text" name="searchValue">
	
	    <br><br>
	
	    <input type="submit" value="Search">
	    
	</form>
	
	
	
		
		
		<%List<MemberManagement> members = (List<MemberManagement>) request.getAttribute("members");
		
			for(MemberManagement member : members){
			
			if (variable != null) {  
					
				 	if (members != null && !members.isEmpty()) { 
			%>
			
		<table>
			
			<tr>
				<th>memberID</th>
				<th>memberName</th>
				<th>email</th>
				<th>phone</th>
				<th>address</th>
				<th>membershipDate</th>
			</tr>
				
			<tr>
				<td><%= member.getMemberID() %></td>
				<td><%= member.getMemberName() %></td>
				<td><%= member.getEmail() %></td>
				<td><%= member.getPhone() %></td>
				<td><%=member.getAddress() %></td>
				<td><%= member.getMembershipDate() %></td>
				
			</tr>
		
				
	</table>
	
	<%} 
				 	
			}
			
		}
		%>

</body>
</html>