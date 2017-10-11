<%-- 
    Document   : shoppingList
    Created on : Oct 10, 2017, 1:49:59 PM
    Author     : 729347
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <h1>Shopping List</h1>
            <form action="ShoppingList" method="POST">
        Username: <input type="text" name="uname" value="${username}">
        <input type="hidden" name="action" value="Register">
        <input type="submit" value="Register">
            </form>
    </body>
</html>
