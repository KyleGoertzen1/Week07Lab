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
import UserDB.UserDB;
/**
 *
 * @author 729347
 */
public class UserServlet extends HttpServlet {

    ArrayList<User> ulist;
    String uName;
    PrintWriter pw;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        ulist = (ArrayList<User>) session.getAttribute("newUser1");
        if(ulist == null) ulist = new ArrayList<User>(); 
        
        String action = request.getParameter("action");
        if (action != null && action.equals("updatePassword")) {
            System.out.println(action);
            String theChange = request.getParameter("newPassword");
            String path = getServletContext().getRealPath("/WEB-INF/TheOnlyWorkingDBFile.txt");

            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String line;
            String parts[] = new String[100];
            String reWriteFile[] = new String[100];
            line = br.readLine();
            while (line != null) {
                parts = line.split(",");
                for (int i = 0; i < ulist.size(); i++) {
                    uName = (String) session.getAttribute("uname");
                    if (uName.equals(parts[0])) {
                        parts[1] = theChange;
                    }
                }
                User u = new User(parts[0], parts[1], Integer.parseInt(parts[2]));
                ulist.add(u);
                line = br.readLine();
            }
            UserDB udb = new UserDB();
            udb.updateFile(ulist, path);
            br.close();
            getServletContext().getRequestDispatcher("/WEB-INF/userpage.jsp").forward(request, response);
            return;
        }

        System.out.println("action" + action);
        if (action != null && action.equals("logout")) {
            getServletContext().getRequestDispatcher("/WEB-INF/loginjsp.jsp").forward(request, response);
            return;
        }

        getServletContext().getRequestDispatcher("/WEB-INF/userpage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
