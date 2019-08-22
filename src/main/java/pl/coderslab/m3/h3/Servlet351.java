package pl.coderslab.m3.h3;

import pl.coderslab.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/*

W projekcie stwórz servlet Servlet351 oraz Servlet352. Celem zadania jest przechowywanie koszyka zakupowego.

    Stwórz formularz z polem tekstowym (nazwa) oraz dwoma numerycznymi (ilość i cena) służącymi do dodawania produktu do koszyka. Formularz powinien być przesłany na tą samą stronę metodą POST.
    Po przesłaniu danych metodą POST przesłany produkt wraz z ceną i ilością dodaj do sesji pod kluczem basket. Pamiętaj, iż klucz basket w sesji musi przechowywać więcej niż 1 produkt (użyj tablicy).
     Po dodaniu elementu do koszyka wyświetl komunikat Produkt dodany i formularz służący do dodanie następnego produktu (taki sam jak w punkcie 1).
    Na stronie formularza dodaj odnośnik do strony wyświetlającej zawartość koszyka.


 */
@WebServlet("/servlet351")
public class Servlet351 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession=request.getSession();
        List<Product> productList =  (List<Product>)httpSession.getAttribute("basket");
        if (productList == null) {
            productList=new ArrayList<>();
        }
        Product product = new Product(
                request.getParameter("nazwa"),
                Integer.parseInt(request.getParameter("ilosc")),
                Integer.parseInt(request.getParameter("cena"))
        );
        productList.add(product);
        httpSession.setAttribute("basket",productList);
        response.setContentType("text/html;charset=UTF-8");
        Writer writer = response.getWriter();
        writer.append("Dodano: "+ product.toString());
        inputForm(writer);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Writer writer = response.getWriter();
        inputForm(writer);
    }
    private void inputForm(Writer writer) throws IOException {
        writer.append("<html>");
        writer.append("<body>");
        writer.append("<form action=\"servlet351\" method=\"post\">");
        writer.append(MessageFormat.format("<p><label>{0}: <input type=\"{1}\" name=\"{2}\"></label></p>","Nazwa","text","nazwa"));
        writer.append(MessageFormat.format("<p><label>{0}: <input type=\"{1}\" name=\"{2}\"></label></p>","Ilość","number","ilosc"));
        writer.append(MessageFormat.format("<p><label>{0}: <input type=\"{1}\" name=\"{2}\"></label></p>","Cena","number","cena"));
        writer.append(MessageFormat.format("<p><button type=\"submit\">{0}</button></p>","Dodaj"));
        writer.append("</form>");
        writer.append("</body>");
        writer.append("</html>");
    }
}
