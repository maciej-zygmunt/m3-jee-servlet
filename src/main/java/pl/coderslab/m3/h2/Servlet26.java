package pl.coderslab.m3.h2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/servlet26")
public class Servlet26 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Map<String,String> headers=getHeaders((request));
        response.getWriter().append(MessageFormat.format("<h1>{0}</h1>","Headers"));
        response.getWriter().append("<table border=\"1\">");
        for (Map.Entry<String,String> entry : headers.entrySet()) {
            String key=entry.getKey();
            String value=entry.getValue();
            String row= MessageFormat.format("<tr><td>{0}</td><td>{1}</td></tr>",key,value);
            response.getWriter().append(row);
        }
        response.getWriter().append("</table>");

      
    }
    private Map<String,String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
}
        
