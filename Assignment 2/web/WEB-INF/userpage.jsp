<%-- 
    Document   : userpage.jsp
    Created on : Oct 17, 2017, 3:44:31 PM
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
        <h1>Your Account</h1>
        Hello, ${uname}
        <input type="hidden" name="getName" value="${uname}">
        <a href="uServ?action=logout">Logout</a>
        
        <form action="uServ?action=updatePassword" method="GET">
        Change password: <input type="text" name="newPassword" value="">
        <input type="hidden" name="action" value="updatePassword">
        <input type="submit" value="Change Password">
        </form>
    </body>
</html>
