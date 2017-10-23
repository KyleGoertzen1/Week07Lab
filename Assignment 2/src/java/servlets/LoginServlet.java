/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import UserDB.UserDB;
import business.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

    String uName;
    ArrayList<User> reWrite;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userLogin");

        if (action == null) {
            if (user != null) {
                getServletContext().getRequestDispatcher("/WEB-INF/adminpage.jsp").forward(request, response);
                return;
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/loginjsp.jsp").forward(request, response);
            }
        }

        if (action != null && action.equals("logout")) {
            getServletContext().getRequestDispatcher("/WEB-INF/loginjsp.jsp").forward(request, response);
            return;
        }

        
        getServletContext().getRequestDispatcher("/WEB-INF/userpage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String username = request.getParameter("uname");
        String password = request.getParameter("pword");

        String path = getServletContext().getRealPath("/WEB-INF/TheOnlyWorkingDBFile.txt");
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        String line;
        String parts[] = new String[100];
        line = br.readLine();
        while (line != null) {
            parts = line.split(",");

            line = br.readLine();
            uName = parts[0];
            int isAdmin = 0;
            if (username.equalsIgnoreCase(parts[0]) && password.equals(parts[1]) && isAdmin == Integer.parseInt(parts[2])) {
                User newUser = new User();
                session.setAttribute("uname", uName);
                response.sendRedirect("uServ");
                return;
            }
        }
        br.close();

        User user = new User(username, password, 1);
        if (username == null || username.isEmpty()
                || password == null || password.isEmpty()) {
            request.setAttribute("error", true);
            request.setAttribute("errorMessage", "Both values are required!");
            response.sendRedirect("login");
            return;
        }

        if (!username.equals("admin") || !password.equals("password")) {
            response.sendRedirect("login");
            return;
        }

        String path1 = getServletContext().getRealPath("/WEB-INF/TheOnlyWorkingDBFile.txt");
        BufferedReader br1 = new BufferedReader(new FileReader(new File(path1)));
        String line1;
        String parts1[] = new String[100];
        line1 = br1.readLine();
        int isAdmin = 1;
        while (line1 != null) {
            parts1 = line1.split(",");

            line1 = br1.readLine();
            if (parts1[0].equalsIgnoreCase("admin") && parts1[1].equalsIgnoreCase("password") && isAdmin == Integer.parseInt(parts1[2])) {
                user = new User(username, password, 0);
                user.setUsername(username);
                session.setAttribute("userLogin", user);
                user.setPassword(password);
                request.setAttribute("user", user);
                response.sendRedirect("Admin");
                return;
            }
        }
        br1.close();
    }
}
