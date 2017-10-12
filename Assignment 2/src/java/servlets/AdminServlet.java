/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 729347
 */
public class AdminServlet extends HttpServlet {
    ArrayList<String> userList;
    ArrayList<String> passCodeList;
    int index;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userLogin");
         if(action != null && action.equals("logout")){
            session.invalidate();
            response.sendRedirect("login");
            return;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        userList = (ArrayList<String>) session.getAttribute("newUsername");
        if(userList == null){
            userList = new ArrayList<String>();
        }
        
        passCodeList = (ArrayList<String>) session.getAttribute("newPassword");
        if(passCodeList == null){
            passCodeList = new ArrayList<String>();
        }
        
        String action = request.getParameter("action");
        if(action != null && action.equals("adduser")){
            String name = request.getParameter("newUsername");
            String pass = request.getParameter("newPassword");
            userList.add(name);
            passCodeList.add(pass);
//            session.setAttribute("addUser", userList);
//            session.setAttribute("addPass", passCodeList);
            response.sendRedirect("Admin");
//                String newItem = request.getParameter("grocery");
//                itemArray.add(newItem);
//                session.setAttribute("itemlist", itemArray);
//                response.sendRedirect("ShoppingList");
        }
    }
}
