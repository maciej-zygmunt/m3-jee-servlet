package pl.coderslab.m3.h3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.text.MessageFormat;

/*
W projekcie stwórz servlet Servlet36, oraz stronę HTML servlet36.html, w której zawarty jest formularz przesyłający
(metodą GET) 4 parametry liczbowe o nazwie num. Po przejściu do servletu oblicz ich średnią, sumę oraz iloczyn i zwróć wynik w przeglądarce:
 */
@WebServlet("/servlet36")
public class Servlet36 extends HttpServlet {
    final static String LI="<li>{0}</li>";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Writer writer = response.getWriter();
            String[] nums = request.getParameterValues("num");
            int sum = 0;
            int product = 1;
            double average = 0.0;
            writer.append(MessageFormat.format("<p>{0}</p>", "Liczby:"));
            writer.append("<ul>");
            for (String num : nums) {
                int n = Integer.parseInt(num);
                writer.append(MessageFormat.format(LI, n));
                sum += n;
                product *= n;
            }
            writer.append("</ul>");
            average = ((double) sum) / nums.length;
            writer.append(MessageFormat.format("<p>{0}</p>", "Suma:"));
            writer.append("<ul>");
            writer.append(MessageFormat.format(LI, sum));
            writer.append("</ul>");
            writer.append(MessageFormat.format("<p>{0}</p>", "Średnia:"));
            writer.append("<ul>");
            writer.append(MessageFormat.format(LI, average));
            writer.append("</ul>");
            writer.append(MessageFormat.format("<p>{0}</p>", "Iloraz:"));
            writer.append("<ul>");
            writer.append(MessageFormat.format(LI, product));
            writer.append("</ul>");
        }catch (NumberFormatException e) {
            response.sendError(401,"Błąd parsowania liczb");
        } catch (Exception e) {
            response.sendError(501, "Something goew wrong");
        }

    }
}
