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
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userLogin");
        
        if(user != null){
            response.sendRedirect("Admin");
            return;
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User();
        
        String username = request.getParameter("uname");
        String password = request.getParameter("pword");
        
        // validation
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty()) {
            // set error message            
            // repopulate the JSP values with first name and last name
            //request.setAttribute("user", user);
            request.setAttribute("error", true);
            request.setAttribute("errorMessage", "Both values are required!");
            
            // forward the request back to index.jsp
            //getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            // stop other execution of code
            response.sendRedirect("login");
            return;
        }
        
        if(!username.equals("admin") || !password.equals("password")){
            response.sendRedirect("login");
            return;
        }
        
        if (username.equalsIgnoreCase("admin") && password.equals("password")) {
            HttpSession session = request.getSession();
            user = new User(username, password);
            user.setUsername(username);
            session.setAttribute("userLogin", user);
            user.setPassword(password);
            // set the attributes for the JSP
            request.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/adminpage.jsp").forward(request, response);
            
            return;
        }
        
//        if (!userName.equals("adam") || !userName.equals("betty") &&
//                !passWord.equals("password")) {
//            request.setAttribute("errorMessage", "Invalid username or password!");
//            
//            user.setFirstName(userName);
//            user.setPassWord(passWord);
//            
//            // set the attributes for the JSP
//            request.setAttribute("user", user);
//
//            getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").
//                    forward(request, response);
//            
//            return;
//        }
        
        
        
        
//        if(!username.equals("") && !password.equals("") && username != null && password != null && username.equals("admin") && password.equals("password")){
//            getServletContext().getRequestDispatcher("/WEB-INF/adminpage.jsp").forward(request, response);
//            return;
//        }
        //getServletContext().getRequestDispatcher("/WEB-INF/adminpage.jsp").forward(request, response);
    }
}
