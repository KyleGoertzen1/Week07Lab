/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 729347
 */
public class ShoppingListServlet extends HttpServlet {

    ArrayList<String> itemArray;
    int index;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String action = request.getParameter("action");

        if (action == null) {
            if (username != null) {

                itemArray = (ArrayList<String>) session.getAttribute("itemlist");
                if (itemArray == null) {
                    itemArray = new ArrayList<String>();
                }
                System.out.println(username);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }
        } else if (action != null && action.equals("logout")) {
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }
        //User newUser = (User) session.getAttribute("itemlist");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        //HttpSession addNewItem = request.getSession();
        itemArray = (ArrayList<String>) session.getAttribute("itemlist");
        if (itemArray == null) {
            itemArray = new ArrayList<String>();
        }

        if (action != null && action.equals("deleteuser")) {
            if (request.getParameter("delete") != null) {
                index = Integer.parseInt(request.getParameter("delete"));
                if (index >= 0) {
                    itemArray.remove(index);
                }
            }

            session.setAttribute("itemlist", itemArray);
            response.sendRedirect("ShoppingList");
        }

        if (action != null && action.equals("Register")) {
            String username = request.getParameter("uname");
            session.setAttribute("username", username);
            response.sendRedirect("ShoppingList");
        }

        if (action != null && action.equals("add")) {
            String newItem = request.getParameter("grocery");
            itemArray.add(newItem);
            session.setAttribute("itemlist", itemArray);
            response.sendRedirect("ShoppingList");
        }

//            if((session.getAttribute("username") != null) || (session.getAttribute("username") != "")){
//               //response.sendRedirect();
//               getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
//               return;
////               String list = request.getParameter("grocery");
////               session.setAttribute("grocery", list);
//            }
    }
}
