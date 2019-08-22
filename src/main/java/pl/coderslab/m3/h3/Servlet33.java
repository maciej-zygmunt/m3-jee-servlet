package pl.coderslab.m3.h3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.Map;

/*
W projekcie stwórz servlet Servlet33. Ma on implementować następujące funkcjonalności:

    Po wejściu na stronę metodą GET (czyli w metodzie doGet), wygeneruj formularz z 5 polami tekstowymi input.
    Formularz ma przesyłać dane z użyciem POST do adresu z servletu.
    Po otrzymaniu danych z formularza, zapisz pobrane dane w sesji.
    Do metody doGet dopisz funkcjonalność sprawdzającą czy w sesji istnieją dane z poprzedniego punktu.
    Jeżeli dane istnieją wypełnij nimi pola input formularza.

 */
@WebServlet("/servlet33")
public class Servlet33 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String[]> params = request.getParameterMap();
        HttpSession httpSession=request.getSession();
        for (String paramName : params.keySet()) {
            String paramValue=request.getParameter(paramName);
            httpSession.setAttribute(paramName,paramValue);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String [] pola={"text1","text2","text3","text4","text5"};
        HttpSession httpSession=request.getSession();
        Writer writer = response.getWriter();

        response.setContentType("text/html;charset=UTF-8");
        writer.append("<html>");
        writer.append("<body>");
        writer.append("<form action=\"servlet33\" method=\"post\">");
        for (int i = 0; i < pola.length; i++) {

            if(!httpSession.isNew()) {
                String value=(String)httpSession.getAttribute(pola[i]);
                writer.append(MessageFormat.format("<p><label>{0}: <input type=\"text\" name=\"{1}\" value=\"{2}\"></label></p>",
                        pola[i].toUpperCase(), pola[i], value));
            } else {
                writer.append(MessageFormat.format("<p><label>{0}: <input type=\"text\" name=\"{1}\" ></label></p>",
                        pola[i].toUpperCase(), pola[i]));
            }
        }
        writer.append("<p><button type=\"submit\">Submit</button></p>");
        writer.append("</form>");
        writer.append("</body>");
        writer.append("</html>");

    }
}
