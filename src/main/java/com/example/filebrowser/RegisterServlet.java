package com.example.filebrowser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        if(UsersInfo.getUser(username) != null){
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
        else {
            User newUser = new User(username, password, email);
            UsersInfo.addUser(newUser);

            req.setAttribute("username", username);
            resp.sendRedirect( req.getContextPath()+"/file-explorer");
        }
    }

}
