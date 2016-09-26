package com.sean.kim.service;

//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * Created by seankim on 2016-09-25.
 */
@Service
public class ScheduleService {
    public String[] executeGet(String url, String courseCode, String sectionCode) throws Exception {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();

            HttpGet method = new HttpGet(uri);
            HttpPost postMethod = new HttpPost(uri);
            HttpDelete deleteMethod = new HttpDelete(uri);
            method.addHeader("Accept", "application/json");
            method.addHeader("Content-Type", "application/json");

            // Parse and check response
            HttpResponse response = httpClient.execute(method);
            ResponseHandler<String> handler = new BasicResponseHandler();
            String body = handler.handleResponse(response);
            body = body.replace("\n","");

            JSONObject jsonObj = new JSONObject(body);

//            URL url = new URL(stringUrl);
//            HttpURLConnection request = (HttpURLConnection) url.openConnection();
//            request.connect();

            // Convert to a JSON object to print data
//            JsonParser jp = new JsonParser(); //from gson
//            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
//            JsonObject jsonObj = root.getAsJsonObject(); //May be an array, may be an object.
//            zipcode = rootobj.get("zip_code").getAsString(); //just grab the zipcode


            JSONArray jsonArray = (JSONArray) jsonObj.getJSONArray("data");
//            JsonArray jsonArray = jsonObj.getAsJsonArray("data");


            return returnExamDetails(jsonArray, courseCode, sectionCode);

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {
            // Close the connection
            try {
                httpClient.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    private String[] returnExamDetails(final JSONArray standings, String courseCode, String sectionCode) {

        String[] info = new String[6];
        for (Object standing : standings) {
            JSONObject jsonStanding = (JSONObject) standing;
            if(jsonStanding.get("course").toString().equals(courseCode))
            {
                //for loop here
                if(jsonStanding.getJSONArray("sections").get(0).toString().equals(sectionCode))
                {
                    info[0] = jsonStanding.getJSONObject("sections").get("day").toString();
                    info[1] = jsonStanding.getJSONObject("sections").get("date").toString();
                    info[2] = jsonStanding.getJSONObject("sections").get("start_time").toString();
                    info[3] = jsonStanding.getJSONObject("sections").get("end_time").toString();
                    info[4] = jsonStanding.getJSONObject("sections").get("location").toString();
                    info[5] = jsonStanding.getJSONObject("sections").get("notes").toString();
                    return info;
                }

            }




//            standingDao.saveStanding(standingPopulator.populateStandingFromJson(jsonStanding));
        }
        return null;
    }
}
