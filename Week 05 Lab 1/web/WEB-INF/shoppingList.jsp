<%-- 
    Document   : register
    Created on : Oct 10, 2017, 1:49:52 PM
    Author     : 729347
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Shopping List</h1>

        <p>Hello, ${username}
            <a href=ShoppingList?action=logout>Logout</a></p>


        <h2>List</h2>
        <form action="ShoppingList?action=add" method="POST">
            Add Item: <input type="text" name="grocery">

            <input type="submit" value="Add">
        </form>
        <form action="ShoppingList" method="POST">
            <table>
                <c:forEach items="${itemlist}" var="cart" varStatus="status">
                    <tr>
                        <td>
                            <input type="radio" name="delete" value="${status.index}"> ${cart}
                        </td>
                    </tr>
                </c:forEach>

            </table>
            <input type="hidden" name="action" value="deleteuser">
            <input type="submit" value="Delete User">
        </form>
    </body>
</html>
