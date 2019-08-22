package pl.coderslab.m3.h3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.text.MessageFormat;

@WebServlet("/servlet31")
public class Servlet31 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String fromTo = request.getParameter("fromTo");
            String amount = request.getParameter("amount");
            String[] ft = fromTo.split("-");
            RatesDAO ratesDAO = new RatesDAO();
            double exchange = ratesDAO.exchange(ft[0], ft[1], Double.valueOf(amount));
            Writer writer = response.getWriter();
            writer.append(MessageFormat.format("Exchanged :{0} fromTo {1} equals {2}", amount, fromTo, exchange));
        } catch (NumberFormatException| NullPointerException e )  {
            response.sendError(401, "Wrong parameter exception");
        } catch (Exception e) {
            response.sendError(501, "Something wrong with service");
        }
    }
}
