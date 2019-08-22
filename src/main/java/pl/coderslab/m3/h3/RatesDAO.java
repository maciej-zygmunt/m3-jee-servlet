package pl.coderslab.m3.h3;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RatesDAO {
    private Map<String, Double> rates= new HashMap<>();

    public RatesDAO() {
        getRates();
    }

    private void  getRates() {

        String ratesApi="http://api.nbp.pl/api/exchangerates/tables/a?format=json";
        DocumentContext documentContext= null;
        try {
            documentContext = JsonPath.parse(new URL(ratesApi));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = documentContext.read("$[0]['rates'][*]");
        JsonPath codeJsonPath=JsonPath.compile("code");
        JsonPath midJsonPath=JsonPath.compile("mid");
        for (Object element : jsonArray) {
            String symbol=codeJsonPath.read(element);
            Double rate=midJsonPath.read(element);
            rates.put(symbol,rate);
        }
        rates.put("PLN",1.0);

    }
    public  double exchange(String from,String to, double amount) {
        double fromRate=rates.get(from);
        double toRate=rates.get(to);
        double inPln=amount*fromRate;
        return inPln/toRate;
    }
}
