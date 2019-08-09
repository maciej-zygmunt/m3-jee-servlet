package pl.coderslab.m3.h2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet241")
public class Servlet241 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        for (int i = 0; i < 7; i++) {
            String base="id="+i;
            String servlet="servlet242";
            //<a href="servlet242?id=0">id=0</a>
            String href="<p><a href=\""+servlet+"?"+base+"\">"+base+"</a></p>";
            response.getWriter().append(href);
        }
    }
}
