<%-- 
    Document   : register
    Created on : Oct 10, 2017, 1:49:52 PM
    Author     : 729347
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Shopping List</h1>
        <div>
            <form action="login" method="POST">
        Username: <input type="text" name="uname" value="${username}">
        <input type="submit" value="Register">
            </form>
        </div>
    </body>
</html>
