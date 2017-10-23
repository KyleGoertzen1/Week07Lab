/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

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
public class AdminServlet extends HttpServlet {

    int index;
    ArrayList<User> list1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //ArrayList<User> list1;
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (action != null && action.equals("logout")) {
            getServletContext().getRequestDispatcher("/WEB-INF/loginjsp.jsp").forward(request, response);
            return;
        }
        if (action == null) {
            list1 = (ArrayList<User>) session.getAttribute("newUser1");
            if (list1 == null) {
                list1 = new ArrayList<User>();
                String path = getServletContext().getRealPath("/WEB-INF/TheOnlyWorkingDBFile.txt");
                BufferedReader br = new BufferedReader(new FileReader(new File(path)));
                String line;
                String parts[] = new String[100];
                line = br.readLine();
                while (line != null) {
                    parts = line.split(",");
                    User u = new User(parts[0], parts[1], Integer.parseInt(parts[2]));
                    list1.add(u);
                    session.setAttribute("newUser1", list1);
                    line = br.readLine();
                }
                br.close();
            }
        }
        
        //String action = request.getParameter("action");
        if (action != null && action.equals("deleteuser")) {
            if (request.getParameter("delete") != null) {
                index = Integer.parseInt(request.getParameter("delete"));
                if (index >= 0) {
                    list1.remove(index);
                }
                for(int i = 0; i < list1.size(); i++){
                    System.out.println(list1.get(i).toString());
                }
                session.setAttribute("newUser1", list1);
                getServletContext().getRequestDispatcher("/WEB-INF/adminpage.jsp").forward(request, response);
                return;
            }
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/adminpage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //ArrayList<User> list1;
        HttpSession session = request.getSession();
            list1 = (ArrayList<User>) session.getAttribute("newUser1");
            if (list1 == null) {
                list1 = new ArrayList<User>();
            }

        
        String action = request.getParameter("action");
        if (action != null && action.equals("adduser")) {
            String name = request.getParameter("newUsername");
            String pass = request.getParameter("newPassword");
            //String path = getServletContext().getRealPath("/WEB-INF/TheOnlyWorkingDBFile.txt");
            User user = null;
            if (!(name.equals("admin"))) {
                user = new User(name, pass, 0);
            } else {
                user = new User(name, pass, 1);
            }

//        String path = getServletContext().getRealPath("/WEB-INF/TheOnlyWorkingDBFile.txt");
//        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
//        String line;
//        String parts[] = new String[100];
//        line = br.readLine();
//        while (line != null) {
//            parts = line.split(",");
//            User u = new User(parts[0], parts[1], Integer.parseInt(parts[2]));
//            list1.add(u);
//            session.setAttribute("newUser1", list1);
//            line = br.readLine();
//        }
//        br.close();
            list1.add(user);
            
            String path = getServletContext().getRealPath("/WEB-INF/TheOnlyWorkingDBFile.txt");
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));

            pw.println(user.getUsername() + "," + user.getPassword() + "," + user.getAdmin());
            pw.close();
            session.setAttribute("newUser1", list1);

            response.sendRedirect("Admin");
        }
    }
}
