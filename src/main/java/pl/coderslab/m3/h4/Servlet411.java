package pl.coderslab.m3.h4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
W projekcie stwórz servlet Servlet411 oraz Servlet412. Następnie:

    Na pierwszej stronie (Servlet411) stwórz formularz z elementem select oraz opcjami wyboru zgodnymi z kluczami poniższej mapy:

    Map<String, String> lang = new  HashMap<>();
    lang.put("en", "Hello");
    lang.put("pl", "Cześć");
    lang.put("de", "Ehre");
    lang.put("es", "Hola");
    lang.put("fr", "Bienvenue");

    Strona ma przesyłać dane za pomocą POST do drugiej strony (Servlet412), która ma ustawić ciasteczko language na wartość wybraną przez użytkownika.
    Po ponownym wejściu na pierwszą powinna być wyświetlana informacja powitalna w wybranym przez użytkownika języku.
    Gdy ciasteczko nie istnieje, wiadomość powitalna powinna być wyświetlana w języku polskim.

Hint: Odpowiednie dane przekaż i przetwórz w pliku widoku.
 */
@WebServlet("/servlet411")
public class Servlet411 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> langMap = new HashMap<>();
        langMap.put("en", "Hello");
        langMap.put("pl", "Cześć");
        langMap.put("de", "Ehre");
        langMap.put("es", "Hola");
        langMap.put("fr", "Bienvenue");
        Cookie [] cookies = request.getCookies();
        var lang= Arrays.stream(cookies).filter(c->"language".equals(c.getName()))
                .findFirst();
        String greeting;
        if(lang.isPresent()) {
            greeting=langMap.get(lang.get().getValue());
        } else {
            greeting=langMap.get("pl");
        }
        request.setAttribute("lang",langMap.keySet());
        request.setAttribute("greeting",greeting);
        getServletContext().getRequestDispatcher("/mvc/servlet411.jsp")
                .forward(request,response);
    }
}
