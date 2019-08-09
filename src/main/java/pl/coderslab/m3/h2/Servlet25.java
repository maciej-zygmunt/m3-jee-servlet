package pl.coderslab.m3.h2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Random;

@WebServlet("/servlet25")
public class Servlet25 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        int [] rnd = new int[10];
        Random random= new Random();
        for (int i = 0; i < rnd.length; i++) {
            rnd[i]=random.nextInt(100)+1;
        }
        response.getWriter().append(MessageFormat.format("<h1>{0}</h1>","Random"));
        printResults(response, rnd);
        Arrays.sort(rnd);
        response.getWriter().append(MessageFormat.format("<h1>{0}</h1>","Sorted"));
        printResults(response, rnd);
    }

    private void printResults(HttpServletResponse response, int[] rnd) throws IOException {
        response.getWriter().append("<table border=\"1\">");
        for (int i = 0; i < rnd.length; i++) {
            String row= MessageFormat.format("<tr><td>{0}</td><td>{1}</td></tr>",i,rnd[i]);
            response.getWriter().append(row);
        }
        response.getWriter().append("</table>");
    }

}
