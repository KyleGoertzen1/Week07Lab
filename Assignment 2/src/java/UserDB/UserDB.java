/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserDB;

import business.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * t
 *
 * @author 729347
 */
public class UserDB implements Serializable {

    PrintWriter pw;
    BufferedReader br;
    int isAdmin = 1;
    int isUser = 0;

    public void updateFile(ArrayList<User> ulist, String path) throws FileNotFoundException, IOException {

        pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));

        for (User u : ulist) {
            if (u.getUsername().equals("admin")) {
                u.setAdmin(1);
                pw.println(u.getUsername() + "," + u.getPassword() + "," + u.getAdmin());
            } else {
                u.setAdmin(0);
                pw.println(u.getUsername() + "," + u.getPassword() + "," + u.getAdmin());
            }
        }

        pw.close();

    }
}

//    String path = getServletContext().getRealPath("/WEB-INF/NONCORRUPTDB.txt");
//    System.out.println(path);
//    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
//
//    pw.println(user.getUsername() + "," + user.getPassword() + "," + user.getAdmin());
//    pw.close();
//    response.sendRedirect("Admin");
//        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
//                String newItem = request.getParameter("grocery");
//                itemArray.add(newItem);
//                session.setAttribute("itemlist", itemArray);
//                response.sendRedirect("ShoppingList");

