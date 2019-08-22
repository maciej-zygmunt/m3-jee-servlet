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
W projekcie stwórz servlet Servlet32, oraz stronę HTML servlet32.html, w której zawarty jest formularz, zawierający jedno pole liczbowe. Po przesłaniu formularza Servlet przelicza wpisaną do formularza wartość binarną liczby na wartość dziesiętną.
Servlet musi wykonać następujące kroki:

    Sprawdzić czy przekazany ciąg zawiera tylko 0 i 1, jeśli nie, wyświetlić stosowny komunikat
    Podzielić ciąg na pojedyncze znaki
    Idąc od końca ciągu mnożyć kolejne liczby przez kolejne potęgi liczby 2
        Ostatnia liczba x 2^0
        Przedostatnia liczba x 2^1
        itd.
        Po dokonaniu obliczeń należy zsumować wszystkie wyniki potęgowania.
    Po dokonaniu obliczenia wyświetl na stronie komunikat np. 1001 to liczba 9.


 */
@WebServlet("/servlet32")
public class Servlet32 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String binInput=request.getParameter("binInput");
        Writer writer= response.getWriter();
        if (!binInput.matches("[01]+")) {
            writer.append(MessageFormat.format("Wrong input format input ={0}",binInput));
        } else {
            int decOutput=0;
            int mult=1;
            for (int i = binInput.length()-1; i >=0; i--) {
                decOutput+=(binInput.charAt(i)-'0')*mult;
                mult*=2;
            }
            writer.append(MessageFormat.format("Binary is: {0} Decimal number is: {1}",binInput,decOutput));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
