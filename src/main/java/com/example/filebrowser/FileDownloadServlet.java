package com.example.filebrowser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/download")
public class FileDownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filePath = request.getParameter("path");
        if (filePath == null || filePath.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        File file = new File(filePath);
        if (!file.exists() || file.isDirectory()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + file.getName() + "\"");

        try (InputStream in = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }
}