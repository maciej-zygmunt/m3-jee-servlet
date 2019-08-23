package pl.coderslab.m3.h3;

import pl.coderslab.model.Country;

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
W projekcie stwórz servlet Sess08. Następnie:

    Przygotuj sobie tablicę z nazwami krajów sąsiadujących z Polską i ich stolicami.
    Po uruchomieniu servletu na serwerze, wyświetl formularz z zapytaniem o stolicę sąsiadującego z Polską państwa. W postaci: Podaj stolicę dla państwa: Niemcy
    Po przesłaniu odpowiedzi, system zweryfikuje poprawność odpowiedzi i wyświetli odpowiedni komunikat oraz ponownie wyświetli pytanie o kolejne państwo.
    Po wyświetleniu wszystkich pytań wyświetli się wynik naszego quizu z informacją o liczbie poprawnych odpowiedzi: Poprawnych odpowiedzi: 4.

 */
@WebServlet("/sess08")
public class Sess08 extends HttpServlet {
    public static final String CORRECT_ANSWERS = "correctAnswers";
    public static final String POINTER = "pointer";
    public static final String CAPITAL = "capital";
    private Country[] countries= {
            new Country("Niemcy","Berlin"),
//            new Country("Czechy","Praga"),
//            new Country("Słowacja","Bratysława"),
//            new Country("Ukraina","Kijów"),
//            new Country("Białoruś","Mińsk"),
            new Country("Litwa","Wilno")};
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Writer writer=response.getWriter();
        HttpSession session= request.getSession();
        int pointer=(Integer)session.getAttribute(POINTER);
        int correctAnswers=(Integer)session.getAttribute(CORRECT_ANSWERS);
        String answer=request.getParameter(CAPITAL);
        if(countries[pointer].getCapital().equals(answer)) {
            writer.append(MessageFormat.format("<p>Odpowiedź prawidłowa, stilicą {0} jest {1}</p>",
                    countries[pointer].getName(), answer));
            correctAnswers++;
        }
        pointer++;
        if(pointer>=countries.length) {
            writer.append(MessageFormat.format("<p>Gra skończona prawidłowych odpowiedzi {0} na {1} pytań</p>",
                    correctAnswers,countries.length));
            pointer=0;
            correctAnswers=0;
        }
        session.setAttribute(POINTER,pointer);
        session.setAttribute(CORRECT_ANSWERS,correctAnswers);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession();
        if(session.isNew()) {
            session.setAttribute(POINTER,0);
            session.setAttribute(CORRECT_ANSWERS,0);
        }
        int pointer=(Integer)session.getAttribute("pointer");
        String country=countries[pointer].getName();
        String form="<form action=\"sess08\" method=\"post\">\n" +
                MessageFormat.format("<input type=\"text\" name=\"{0}\">\n",CAPITAL)+
                "    <button type=\"submit\">submit</button>\n" +
                "</form>";
        Writer writer = response.getWriter();
        writer.append("<body>");
        writer.append(MessageFormat.format("<p> Podaj stolice dla kraju {0}</p>" ,country));
        writer.append(form);
        writer.append("</body>");
    }
}
