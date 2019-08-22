package pl.coderslab.m3.h4;

import pl.coderslab.Config;
import pl.coderslab.model.Guest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/guest-book")
public class GuestBook extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(Connection connection= Config.getConnection(getServletContext())){
            String name=request.getParameter("name");
            String description=request.getParameter("description");
            PreparedStatement statement=connection.prepareStatement("INSERT INTO " +
                    "`guest-book`(name, description) VALUES (?,?)");
            statement.setString(1,name);
            statement.setString(2,description);
            statement.executeUpdate();
        } catch (SQLException e) {
            response.sendError(501,"Database problem");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(Connection connection= Config.getConnection(getServletContext())) {
            List<Guest> guests= new ArrayList<>();
            Statement statement=connection.createStatement();
            ResultSet rs= statement.executeQuery("SELECT name, description FROM  `guest-book`" +
                    " ORDER BY name");
            while (rs.next()) {
                String name=rs.getString(1);
                String description=rs.getString(2);
                Guest guest = new Guest(name,description);
                guests.add(guest);
            }
            request.setAttribute("guests",guests);
            getServletContext().getRequestDispatcher("/mvc/guest-book.jsp")
                    .forward(request,response);

        } catch (SQLException e) {
            response.sendError(501,"Database problem");
        }

    }
}
