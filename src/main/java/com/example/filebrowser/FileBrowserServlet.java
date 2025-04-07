package com.example.filebrowser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

import java.util.Date;


public class FileBrowserServlet extends HttpServlet {

    private static final String BASE_PATH = System.getProperty("user.home");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathParam = request.getParameter("path");
        String currentPath = (pathParam != null && !pathParam.isEmpty()) ? pathParam : BASE_PATH;

        File currentDir = new File(currentPath);

        if (!currentDir.exists() || !currentDir.isDirectory()) {
            currentDir = new File(BASE_PATH);
            currentPath = BASE_PATH;
        }

        File[] files = currentDir.listFiles();

        request.setAttribute("currentPath", currentDir.getAbsolutePath());
        request.setAttribute("files", files);
        request.setAttribute("generatedTime", new Date());
        request.setAttribute("parentPath", currentDir.getParent());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}