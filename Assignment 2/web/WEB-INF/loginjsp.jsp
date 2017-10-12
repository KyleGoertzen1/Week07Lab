<%-- 
    Document   : login
    Created on : Oct 11, 2017, 6:22:19 PM
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
        <h1>Manage Users Login</h1>
    </body>
        <form action="login" method="POST">
            Username: <input type="text" name="uname" value="${username}"><br>
            Password: <input type="text" name="pword" value="${password}"><br>
            <input type="hidden" name="action" value="Login">
            <input type="submit" value="Login">
        </form>
        <c:if test="${error}">
            ${errorMessage}
        </c:if>
</html>
