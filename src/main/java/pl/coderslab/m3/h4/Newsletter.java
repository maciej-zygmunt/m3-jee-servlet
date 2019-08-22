package pl.coderslab.m3.h4;

import pl.coderslab.Config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

@WebServlet("/newsletter")
public class Newsletter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        String email=request.getParameter("email");

        try(Connection connection= Config.getConnection(getServletContext())) {
            String query="INSERT INTO newsleter (name,email) " +
                    "VALUES (?,?)";
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setString(2,email);
            statement.executeUpdate();
            Cookie subscribedCookie=new Cookie("subscribed", "true");
            subscribedCookie.setMaxAge(24*60*60);
            response.addCookie(subscribedCookie);
        }catch (SQLException e) {
            response.sendError(501," Database problem");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie [] cookies = request.getCookies();
        var subscribed= Arrays.stream(cookies).filter(c->"subscribed".equals(c.getName()))
                .findFirst();
        if(subscribed.isPresent()) {
            request.setAttribute("subscribed",true);
        } else {
            request.setAttribute("subscribed", false);

        }
        getServletContext().getRequestDispatcher("/mvc/newsletter.jsp")
                .forward(request,response);
    }
}
