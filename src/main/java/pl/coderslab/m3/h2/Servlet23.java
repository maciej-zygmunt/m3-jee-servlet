package pl.coderslab.m3.h2;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

@WebServlet("/servlet23")
public class Servlet23 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String oopFilePath= IOUtils.resourceToURL("oop.txt",  getClass().getClassLoader()).getPath();
        Scanner scanner = new Scanner(new File(oopFilePath));
        response.getWriter().append("<h1>OOP languages list</h1>");
        while(scanner.hasNext()) {
            String name=scanner.nextLine();
            response.getWriter().append("<p>" + name + "</p>");
        }
    }
}

