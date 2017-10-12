<%-- 
    Document   : adminpage
    Created on : Oct 11, 2017, 6:31:02 PM
    Author     : 729347
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Admin Page</h1>
            <a href="Admin?action=refresh">Refresh</a>
            <a href="Admin?action=logout">Logout</a>
            
            <h2>Add user</h2>
            
            <form action="Admin" method="POST">
            New username: <input type="text" name="newUsername" value=""><br>
            Password: <input type="text" name="newPassword" value=""><br>
            <input type="hidden" name="action" value="adduser">
            <input type="submit" value="Add User">
            </form>
            <h2>List of Users</h2>
            
            <form action="Admin" method=POST">
                <table>
                    <tr>
                      <th>Username</th>
                      <th>Password</th>
                      <th>Delete</th>
                    </tr>
                        <c:forEach items="${addUser}" var="i" varStatus="status">
                            ${i}
                        </c:forEach>
                </table>
                <input type="hidden" name="action" value="deleteuser">
                <input type="submit" value="Delete User">
            </form>
        
    </body>
</html>
