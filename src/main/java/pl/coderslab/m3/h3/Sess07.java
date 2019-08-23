package pl.coderslab.m3.h3;

import com.mysql.cj.api.Session;
import pl.coderslab.model.Calculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Random;

/*
W projekcie stwórz servlet Sess07. Następnie:

    Wylosuj w nim 2 liczby całkowite z przedziału 20-1000.
    Wyświetl 3 pola do wpisania wyników działań na wylosowanych liczbach:
        dodawania,
        odejmowania,
        mnożenia.
    Po wysłaniu formularza wygeneruj stronę, która sprawdzi wpisane przez nas wyniki i przedstawi je w postaci:

20	+	3	=	23	Correct
20	-	3	=	17	Correct
20	*	3	=	89	Wrong

Hint: w sesji zapamiętaj liczby, a nie wyniki - dzięki temu będziesz trzymać jedną zmienną mniej.
 */
@WebServlet("/sess07")
public class Sess07 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            int a = (Integer) session.getAttribute("a");
            int b = (Integer) session.getAttribute("b");
            String[] guessArr = request.getParameterValues("guess");
            Calculator[] operations = {
                    new Calculator('+'),
                    new Calculator('-'),
                    new Calculator('*'),
            };
            response.setContentType("text/html;charset=UTF-8");
            Writer writer = response.getWriter();
            String startOfPage = "<body>\n" +
                    "<table border=\"1\">\n";
            String endOfPage = "</table>\n" +
                    "</body>\n";
            writer.append(startOfPage);
            writer.append("<thead><td>a</td><td>op</td><td>b</td><td></td><td>result</td><td>guess</td><td>message</td></thead>");
            for (int i = 0; i < guessArr.length; i++) {
                writer.append("<tr>");
                writer.append(MessageFormat.format("<td>{0}</td>", a));
                writer.append(MessageFormat.format("<td>{0}</td>", operations[i]));
                writer.append(MessageFormat.format("<td>{0}</td>", b));
                writer.append(MessageFormat.format("<td>{0}</td>", "="));
                int res = operations[i].calculate(a, b);
                writer.append(MessageFormat.format("<td>{0}</td>", res));
                writer.append(MessageFormat.format("<td>{0}</td>", guessArr[i]));
                String message = (res == Integer.parseInt(guessArr[i]) ? "Correct" : "wrong");
                writer.append(MessageFormat.format("<td>{0}</td>", message));
                writer.append("</tr>");
            }
            writer.append(endOfPage);
        }catch (NumberFormatException| ArrayIndexOutOfBoundsException e) {
            response.sendError(402, " Wrong parameters");
        } catch (Exception e) {
            response.sendError(502,"Server error");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Random random = new Random();
            Integer a = random.nextInt(1000 - 20) + 20;
            Integer b = random.nextInt(1000 - 20) + 20;
            HttpSession session = request.getSession();
            session.setAttribute("a", a);
            session.setAttribute("b", b);
            String html = "<body>\n" +
                    "<form method=\"post\" action=\"sess07\">\n" +
                    "    <p>\n" +
                    "        <label for=\"r1\">r1</label>\n" +
                    "        <input type=\"number\" id=\"r1\" name=\"guess\">\n" +
                    "    </p>\n" +
                    "    <p>\n" +
                    "        <label for=\"r2\">r2</label>\n" +
                    "        <input type=\"number\" id=\"r2\" name=\"guess\">\n" +
                    "    </p>\n" +
                    "    <p>\n" +
                    "        <label for=\"r3\">r3</label>\n" +
                    "        <input type=\"number\" id=\"r3\" name=\"guess\">\n" +
                    "    </p>\n" +
                    "    <p>\n" +
                    "        <button type=\"submit\">Submit</button>\n" +
                    "    </p>\n" +
                    "</form>\n" +
                    "</body>";
            response.getWriter().append(html);
        }catch (Exception e) {
            response.sendError(502,"Server error");
        }
    }
}
