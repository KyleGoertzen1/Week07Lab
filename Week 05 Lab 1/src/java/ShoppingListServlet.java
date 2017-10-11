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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);  
        HttpSession session = request.getSession();
        //User newUser = (User) session.getAttribute("itemlist");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String action = request.getParameter("action");
                HttpSession session = request.getSession();
            HttpSession addNewItem = request.getSession();
            itemArray = (ArrayList<String>) addNewItem.getAttribute("itemlist");
            if(itemArray == null){
                itemArray = new ArrayList<String>();
            }
        
            if(action != null && action.equals("Register")){
                String username = request.getParameter("uname");
                session.setAttribute("username", username);
            }
            
            
            if(action != null && action.equals("logout")){
                session.invalidate();
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
               }
            
            if(action != null && action.equals("add")){
                String newItem = request.getParameter("grocery");
                itemArray.add(newItem);
                addNewItem.setAttribute("itemlist", itemArray);
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
            
            if((session.getAttribute("username") != null) || (session.getAttribute("username") != "")){
               //response.sendRedirect();
               getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
               return;
//               String list = request.getParameter("grocery");
//               session.setAttribute("grocery", list);
            }
            
            
    }
}
