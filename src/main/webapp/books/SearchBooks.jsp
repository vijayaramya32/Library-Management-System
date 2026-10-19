<%@page import="com.techiesedge.library.Entity.BookManagement"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Search Books</title>
</head>
<body>

	<h2>Search books</h2>
	
	<form action="${pageContext.request.contextPath}/searchBooks" method="get">
	
		<select name="searchBy">
			<option value="isbn">ISBN</option>
			<option value="title">tile</option>
			<option value="author">author</option>
			<option value = "category">category</option>
		</select>
		
		<%String variable = request.getParameter("searchBy"); %>
	
		<h2>search member by <%= request.getAttribute("variable")  %> </h2>
		
		<label>Enter Value:</label>
	    <input type="text" name="searchValue">

	    
	   	<input type="submit" value="Search">
	   	
	</form>
	    
	
	
         
         <%
         	List<BookManagement> books = (List<BookManagement>) request.getAttribute("books"); 
         
         	for(BookManagement book : books){
         		
			if (variable != null) {  
					
				 	if (books != null && !books.isEmpty()) { 
         
         %>
         <table border = 1>
	
			<tr>
				<th>ID</th>
	            <th>ISBN</th>
	            <th>Title</th>
	            <th>Author</th>
	            <th>Category</th>
	            <th>Quantity</th>
	         </tr>
         
         <tr>
         	<td><%= book.getBookID() %></td>
         	<td><%= book.getISBN() %></td>
         	<td><%= book.getBookTitle() %></td>
         	<td><%= book.getAuthor() %>\</td>
         	<td><%= book.getCategory() %></td>
         	<td><%= book.getAvailableQuantity() %></td>
         </tr>
         
         <%}
				 	
	}
	
	
}%>
         	
         	
         	
	</table>

</body>
</html>