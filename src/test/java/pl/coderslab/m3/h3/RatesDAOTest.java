package pl.coderslab.m3.h3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RatesDAOTest {
    @Test
    public void testRatesDAO() {
        RatesDAO ratesDAO= new RatesDAO();

        double amountPln=100;
        double usd=ratesDAO.exchange("PLN","USD",amountPln);
        double amountAfter=ratesDAO.exchange("USD","PLN",usd);
        assertEquals(amountPln,amountAfter,0.1);
    }
}
