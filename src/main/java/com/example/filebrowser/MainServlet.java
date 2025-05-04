package com.example.filebrowser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // получаем сессию
        HttpSession session = req.getSession();
        // получаем объект username
        String username = (String) session.getAttribute("username");

        if (username == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        Path userHome = Paths.get("C:/FileBrowserApp/" + username);

        String currentPath = req.getParameter("path");


        Path requastedPath = null;

        if (currentPath == null || currentPath.isEmpty()) {
            requastedPath = userHome;
        } else {
            try {
                requastedPath = Paths.get(currentPath).toRealPath();
                if (!requastedPath.startsWith(userHome)) {
                    requastedPath = userHome;
                }
            } catch (IOException e) {
                requastedPath = userHome;
            }
        }

        File directory = requastedPath.toFile();
        if (!directory.exists()) {
            directory.mkdir();
        }

        FileEntities entities = new FileEntities(requastedPath != null ? requastedPath.toString() : userHome.toString());



        if (entities.parentPath() != null && entities.parentPath().startsWith(userHome.toString())) {
            req.setAttribute("parentPath", entities.parentPath());
        } else {
            req.setAttribute("parentPath", null);
        }

        req.setAttribute("files", entities.getFiles());
        req.setAttribute("currentPath", requastedPath.toString());
        req.setAttribute("Time", new Date());

        req.getRequestDispatcher("mypage.jsp").forward(req, resp);
    }

}
