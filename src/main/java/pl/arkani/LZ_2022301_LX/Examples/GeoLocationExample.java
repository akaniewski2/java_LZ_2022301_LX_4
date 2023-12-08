package pl.arkani.LZ_2022301_LX.Examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pl.arkani.LZ_2022301_LX.model.gelocationJsonObjects.Location;
import pl.arkani.LZ_2022301_LX.model.gelocationJsonObjects.LocationList;
import pl.arkani.LZ_2022301_LX.webclient.weather.Converters;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class GeoLocationExample {

    private static final String YOUR_URL  ="http://api.openweathermap.org/geo/1.0/direct?q=london&limit=5&appid=a66b540bcde954f8ef6423bf15c6ee5c";

    public static void main(String[] args) throws Exception {
        String jsonTmp = "[\n" +
                "    {\n" +
                "        \"name\": \"Pruszcz Gdański\",\n" +
                "        \"local_names\": {\n" +
                "            \"de\": \"Praust\",\n" +
                "            \"pl\": \"Pruszcz Gdański\"\n" +
                "        },\n" +
                "        \"lat\": 54.25764755,\n" +
                "        \"lon\": 18.650104044555775,\n" +
                "        \"country\": \"PL\",\n" +
                "        \"state\": \"Pomeranian Voivodeship\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"Pruszcz\",\n" +
                "        \"local_names\": {\n" +
                "            \"pl\": \"Pruszcz\",\n" +
                "            \"ru\": \"Прущ\",\n" +
                "            \"uk\": \"Прущ\"\n" +
                "        },\n" +
                "        \"lat\": 53.3294095,\n" +
                "        \"lon\": 18.1993931,\n" +
                "        \"country\": \"PL\",\n" +
                "        \"state\": \"Kuyavian-Pomeranian Voivodeship\"\n" +
                "    }\n" +
                "]";



//
        URL url1 = new URL(YOUR_URL );
        HttpURLConnection request1 = (HttpURLConnection) url1.openConnection();
        request1.setRequestMethod("GET");
        request1.connect();

        String responseBody = Converters.convertStreamToString(request1.getInputStream());
//        JSONArray jsonArray = new JSONArray(responseBody);
//        for (int i = 0; i < jsonArray.length(); i++) {
//            String email = jsonArray.getJSONObject(i).getString("Email");
//            System.out.println(email);

//            //... Similarly you can parse all the objects..
//        }

        jsonTmp=responseBody;

        JSONObject jobject=new JSONObject();
        try {
            JSONArray jarray = new JSONArray(jsonTmp);
            jobject = jarray.getJSONObject(0);
          //  String id =  jobject .getString("Id");

            System.out.println(jobject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        ObjectMapper objectMapper = new ObjectMapper();
//       // List<Location>  locationList = objectMapper.readValue(json, LocationList.class);
//        //LocationList locationList = objectMapper.readValue(json, LocationList.class);
//        //LocationList locationList = objectMapper.readValue(jobject.toString(), LocationList.class);
//        Location locationList= objectMapper.readValue(jobject.toString(), Location.class);
//
//        // Access the location objects
//        List<Location> locations = locationList.getLocations();
//        for (Location location : locations) {
//            System.out.println("Name: " + location.getName());
//            System.out.println("Local Names: " + location.getLocal_names());
//            System.out.println("Latitude: " + location.getLat());
//            System.out.println("Longitude: " + location.getLon());
//            System.out.println("Country: " + location.getCountry());
//            System.out.println("State: " + location.getState());
//            System.out.println();
//        }

        System.out.println("---------------------------------------------------------------------------------------");

        ObjectMapper objectMapper = new ObjectMapper();
        Location[] locations = objectMapper.readValue(jsonTmp, Location[].class);

        LocationList locationList = new LocationList();
        locationList.setLocations(locations);

        System.out.println("-----------------");
        System.out.println(locationList        );
        System.out.println("-----------------");
        // Access the location objects
        for (Location location : locationList.getLocations()) {

            System.out.println("Name: " + location.getName());
            System.out.println("Local Names: " + location.getLocal_names());
            System.out.println("Latitude: " + location.getLat());
            System.out.println("Longitude: " + location.getLon());
            System.out.println("Country: " + location.getCountry());
            System.out.println("State: " + location.getState());
            System.out.println();
        }

       // System.out.println(location.toString());
    }
}
