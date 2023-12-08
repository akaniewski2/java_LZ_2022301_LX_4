package pl.arkani.LZ_2022301_LX.Examples;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.apache.commons.io.IOUtils;
import pl.arkani.LZ_2022301_LX.model.Purchase;
import pl.arkani.LZ_2022301_LX.model.PurchaseCategory;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class SimpleJson2 {


    public static void main(String[] args) throws IOException, MalformedURLException {
      //  URL url = new URL("http://example.com/json");

//        URL url = new URL("http://localhost:8080/api/getcategories");
//        String json = IOUtils.toString(url, "UTF-8");

        String url = "https://arkani.alwaysdata.net/api/getcategories";
        //
        // String url = "localhost:8080/api/purchase/getcategories";
        String url2 = IOUtils.toString(URI.create(url), "UTF-8");

        JSONObject jsonObj = new JSONObject(new JSONTokener(url2));
        System.out.println(jsonObj);
        JSONArray categories = jsonObj.getJSONArray("kategorie");
        String result="";

        PurchaseCategory purchaseCategory = new PurchaseCategory();

        for (int i = 0; i < categories.length() ; i++) {
            JSONObject category = categories.getJSONObject(i);

                    purchaseCategory.setId( category.getLong("id"));
                    purchaseCategory.setName( category.getString("kategoria"));
                    purchaseCategory.setOrderr( category.getInt("kolejnosc"));

                    result =
                            "id: " + category.getLong("id") + "\n" +
                            "kategoria: " +  category.getString("kategoria") + "\n" +
                            "kolejnosc: " +  category.getInt("kolejnosc") + "\n";


            System.out.println(purchaseCategory);
        }


        System.out.println(result);

    }
}
