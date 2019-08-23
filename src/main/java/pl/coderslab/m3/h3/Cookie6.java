package pl.coderslab.m3.h3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
/*

W projekcie stwórz servlet Cookie6, dostępny pod adresem /rememberMe. Następnie:

    W servlecie utwórz formularz, zawierający pole name oraz checkbox z labelem Zapamiętaj mnie.
    Formularz powinien przesyłać dane metodą POST na ten sam adres.
    Dopisz obsługę metody POST w servlecie. Servlet powinien wyświetlić komunikat Cześć {imię przesłane w formularzu}. W przypadku zaznaczenia checkboxa, imię użytkownika zapamiętaj w ciasteczku.
    Dodaj funkcjonalność, która w przypadku wejścia na stronę metodą GET sprawdzi czy istnieje odpowiednie ciasteczko. Jeżeli tak, to nie wyświetlaj formularza do logowania tylko wyświetl komunikat Cześć {imię przesłane w formularzu}. Twoje dane zostały wczytane z ciasteczka

 */

@WebServlet("/rememberMe")
public class Cookie6 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        String remember=request.getParameter("remember");
        displayHello(response, name);
        if("on".equals(remember)) {
            response.addCookie(new Cookie("remember", name));
        }
    }

    private void displayHello(HttpServletResponse response, String name) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().append("Cześć "+name+". ");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie [] cookies= request.getCookies();
        var cookieRemember= Arrays.stream(cookies).filter(c->"remember".equals(c.getName()))
                .findFirst();
        if(!cookieRemember.isPresent()) {
            response.setContentType("text/html;charset=UTF-8");
            Writer writer = response.getWriter();
            String html = "<body>\n" +
                    "<form method=\"post\" action=\"rememberMe\">\n" +
                    "    <label for=\"name\">Name:</label>\n" +
                    "    <input type=\"text\" id=\"name\" name=\"name\">\n" +
                    "    <label for=\"remember\">remember</label>\n" +
                    "    <input type=\"checkbox\" id=\"remember\" name=\"remember\">\n" +
                    "    <button type=\"submit\">Submit</button>\n" +
                    "</form>\n" +
                    "\n" +
                    "</body>";
            writer.append(html);
        } else {
            displayHello(response,cookieRemember.get().getValue());
            response.getWriter().append("Twoje dane zostały wczytane z ciasteczka.");
        }

    }
}
