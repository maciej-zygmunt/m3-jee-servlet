package pl.coderslab.m3.h3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Optional;

/*
W projekcie stwórz servlet Servlet34. Celem zadania jest wyświetlanie ilości wizyt na stronie. W servlecie:

    Sprawdź czy użytkownik posiada zapisane ciasteczko o nazwie visits:
        jeśli nie, wyświetl komunikat: Witaj pierwszy raz na naszej stronie oraz dodaj ciasteczko o nazwie visits
        zapisując mu wartość 1 i czas ważności 1 rok.
        jeśli ciasteczko jest zapisane, pobierz jego aktualną wartość i wypisz na stronie komunikat Witaj,
        odwiedziłeś nas już X razy. Zwiększ też wartość ciasteczka o 1.

 */
@WebServlet("/servlet34")
public class Servlet34 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cookie[] cookies = request.getCookies();
            Optional<Cookie> first = Arrays.stream(cookies).filter(c -> "visits".equals(c.getName()))
                    .findFirst();
            if (! first.isPresent() ) {
                Cookie cookie = new Cookie("visits", "1");
                cookie.setMaxAge(365 * 24 * 60 * 60);
                response.addCookie(cookie);
            } else {
                Cookie cookie =  first.get();
                int v = Integer.parseInt(cookie.getValue());
                cookie.setValue(String.valueOf(++v));
                response.addCookie(cookie);
                response.setContentType("text/html;charset=UTF-8");
                Writer writer = response.getWriter();
                writer.append("<html>");
                writer.append("<body>");
                writer.append(
                        MessageFormat.format("<p>Jesteś już {0} raz na tej stronie, dziękujemy</p>", v));
                writer.append("</body>");
                writer.append("</html>");
            }
        } catch (Exception e) {
            response.sendError(500, "Some problem");
        }
    }
}
