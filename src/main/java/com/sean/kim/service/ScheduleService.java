package com.sean.kim.service;

//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;

import com.sean.kim.dao.ExamDetailsDao;
import com.sean.kim.model.ExamDetails;
import com.sean.kim.model.impl.ExamDetailsImpl;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;

/**
 * Created by seankim on 2016-09-25.
 */
@Service
public class ScheduleService {

    @Autowired
    ExamDetailsDao examDetailsDao;


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
                for(int i = 0; i < jsonStanding.getJSONArray("sections").length() ; i++) {
                    if (i==Integer.parseInt(sectionCode)-1) {
                        info[0] = jsonStanding.getJSONArray("sections").getJSONObject(i).get("day").toString();
                        info[1] = jsonStanding.getJSONArray("sections").getJSONObject(i).get("date").toString();
                        info[2] = jsonStanding.getJSONArray("sections").getJSONObject(i).get("start_time").toString();
                        info[3] = jsonStanding.getJSONArray("sections").getJSONObject(i).get("end_time").toString();
                        info[4] = jsonStanding.getJSONArray("sections").getJSONObject(i).get("location").toString();
                        info[5] = jsonStanding.getJSONArray("sections").getJSONObject(i).get("notes").toString();
                        return info;
                    }
                }
                return null;
            }
        }

        return null;
    }

    @Transactional
    public void saveExamDetails(String[] details)
    {
        ExamDetails examDetails = new ExamDetailsImpl();
        examDetails.setDay( details[0]);
        examDetails.setDate( details[1]);
        examDetails.setStartTime( details[2]);
        examDetails.setEndTime( details[3]);
        examDetails.setLocation( details[4]);
        examDetails.setNote( details[5]);


        examDetailsDao.saveOrUpdate(examDetails);

    }

}
