package com.mbtroads;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;


public class HttpClient {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public void close() throws IOException {
        httpClient.close();
    }

    public String Get_id(String content) {

        String id;
        id = substringBefore(content, ",\"serviceDefinition");
        id = substringAfter(id, ":");
        return id;

    }

    public HttpResponse ServiceAvailable()  {


        HttpResponse response = null;

        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("http://localhost:8443/serviceregistry/echo");
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            response = httpClient.execute(request);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;


    }

    public HttpResponse sendGet_All()  {


        HttpResponse response = null;

        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("http://localhost:8443/serviceregistry/mgmt?direction=ASC&sort_field=id");
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            response = httpClient.execute(request);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;


    }

    public HttpResponse sendGet(String id)  {


        HttpResponse response = null;

        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("http://localhost:8443/serviceregistry/mgmt/"+id);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            response = httpClient.execute(request);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;


    }

    public HttpResponse sendPost(String payload)  {
            HttpResponse response = null;

        try {

            StringEntity entity = new StringEntity(payload);
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost("http://localhost:8443/serviceregistry/mgmt");
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            request.setEntity(entity);

            response = httpClient.execute(request);

        } catch (Exception e) {
            e.printStackTrace();
        }

            return response;

    }

}
