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
import java.util.List;

/*
 W servlecie Servlet352 wyświetl zawartość koszyka oraz sumę cen produktów, pamiętaj iż każdy produkt ma dodaną ilość.
 */
@WebServlet("/servlet352")
public class Servlet352 extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession httpSession = request.getSession();
            List<Product> productList = (List<Product>) httpSession.getAttribute("basket");
            int suma = 0;
            response.setContentType("text/html;charset=UTF-8");
            Writer writer = response.getWriter();
            if(productList != null ) {
                for (Product product : productList) {
                    writer.append("<p>");
                    writer.append(product.toString());
                    writer.append("</p>");

                    suma += product.getPrice() * product.getQuantity();
                }
                writer.append("<p>Suma = " + suma + "</p>");
            } else {
                writer.append("Koszyk pusty");
            }
        } catch (Exception e) {
            response.sendError(501,"Something went wrong");
        }
    }
}
