package com.sean.kim.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;

/**
 * Created by seankim on 2016-09-25.
 */
@Service
public class ScheduleService {
    public String[] executeGet(String url) throws Exception {

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



            return null;

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
}
