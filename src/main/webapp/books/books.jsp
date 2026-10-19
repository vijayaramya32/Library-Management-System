
<%@ page import="java.util.List" %>
<%@ page import="com.techiesedge.library.Entity.BookManagement" %>

<!DOCTYPE html>
<html>
<head>
    <title>View All Books</title>
</head>

<body>

    <h2>All Books</h2>

    <table border="1">

        <tr>
            <th>ID</th>
            <th>ISBN</th>
            <th>Title</th>
            <th>Author</th>
            <th>Category</th>
            <th>Quantity</th>
            <th>status</th>
        </tr>

        <%
            List<BookManagement> books =
                (List<BookManagement>) request.getAttribute("books");
        

            for (BookManagement book : books) {
        %>

        <tr>
            <td><%= book.getBookID() %></td>
            <td><%= book.getISBN() %></td>
            <td><%= book.getBookTitle() %></td>
            <td><%= book.getAuthor() %></td>
            <td><%= book.getCategory() %></td>
            <td><%= book.getAvailableQuantity() %></td>
            <td><%= book.getStatus() %></td>
            
        </tr>

        <%
            }
        %>

    </table>

</body>
</html>

