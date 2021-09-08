package com.mbtroads;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
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


public class HttpClient implements ISystemProperties {

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

    public String Get_id_p(String content, String type) {

        String id;
        String After_seprator = "";
        String Before_seperator = "";
        if (type.contains("provider"))
        {
            After_seprator = ",\"provider\":{\"id\":";
            Before_seperator = ",\"systemName";

        }
        if (type.contains("serviceDefinition"))
        {
            After_seprator = ",\"serviceDefinition\":{\"id\":";
            Before_seperator = ",\"serviceDefinition\":";

        }
        if (type.contains("interface"))
        {
            After_seprator = ",\"interfaces\":[{\"id\":";
            Before_seperator = ",\"interfaceName";

        }
        if (type.contains("servicename"))
        {
            After_seprator = "\"systemName\":\"";
            Before_seperator = "\",\"address";

        }
        if (type.contains("storeid"))
        {
            After_seprator = "data\":[{\"id\":";
            Before_seperator = ",\"serviceDefinition\":{\"id";

        }
        id = substringAfter(content, After_seprator);
        id = substringBefore(id, Before_seperator);
        return id;

    }

    public String Get_name(String content) {

        String name;
        name = substringBefore(content, "\",\"address");
        name = substringAfter(name, "systemName\":\"");
        return name;

    }

    public HttpResponse ServiceAvailable(String serviceName)  {


        HttpResponse response = null;
        HttpGet request= null;
            try {
                CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                if (OS.contains("Windows") || OS.contains("Mac")) {
                    if(serviceName.contains("serviceregistery"))
                    {
                        request = new HttpGet("http://localhost:8443/serviceregistry/echo");
                    }
                    if(serviceName.contains("authontication"))
                    {
                        request = new HttpGet("http://localhost:8445/authorization/echo");
                    }
                    if(serviceName.contains("orchestration"))
                    {
                        request = new HttpGet("http://localhost:8441/orchestrator/echo");
                    }
                }
                else {
                    if (serviceName.contains("serviceregistery")) {
                        request = new HttpGet("http://128.130.39.42:8443/serviceregistry/echo");
                    }
                    if(serviceName.contains("authontication"))
                    {
                        request = new HttpGet("http://128.130.39.42:8445/authorization/echo");
                    }
                    if(serviceName.contains("orchestration"))
                    {
                        request = new HttpGet("http://128.130.39.42:8441/orchestrator/echo");
                    }
                }
                request.setHeader("Accept", "application/json");
                request.setHeader("Content-type", "application/json");
                response = httpClient.execute(request);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;


    }

    public HttpResponse sendGet_All(String type)  {


        HttpResponse response = null;
        HttpGet request = null;
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();

            if (type.contains("serviceregistry")) {
                if (OS.contains("Windows") || OS.contains("Mac")) {
                    request = new HttpGet("http://localhost:8443/serviceregistry/mgmt?direction=ASC&sort_field=id");
                } else {
                    request = new HttpGet("http://128.130.39.42:8443/serviceregistry/mgmt?direction=ASC&sort_field=id");
                }
            }
            if (type.contains("storepriority")) {
                if (OS.contains("Windows") || OS.contains("Mac")) {
                    request = new HttpGet("http://localhost:8441/orchestrator/mgmt/store/all_top_priority?direction=ASC&sort_field=id");
                } else {
                    request = new HttpGet("http://128.130.39.42:8441/orchestrator/mgmt/store/all_top_priority?direction=ASC&sort_field=id");
                }
            }

            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            response = httpClient.execute(request);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;


    }

    public HttpResponse sendGet(String id, String type)  {


        HttpResponse response = null;
        HttpGet request= null;

        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();

            if (type.contains("serviceregistry")) {
                if (OS.contains("Windows") || OS.contains("Mac")) {
                    request = new HttpGet("http://localhost:8443/serviceregistry/mgmt/" + id);
                } else {
                    request = new HttpGet("http://128.130.39.42:8443/serviceregistry/mgmt/" + id);
                }
            }
            if (type.contains("store")) {
                if (OS.contains("Windows") || OS.contains("Mac")) {
                    request = new HttpGet("http://localhost:8441/orchestrator/mgmt/store/" + id);
                } else {
                    request = new HttpGet("http://128.130.39.42:8441/orchestrator/mgmt/store/" + id);
                }
            }
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            response = httpClient.execute(request);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;


    }

    public HttpResponse sendPost_Query(String payload,String type)  {
            HttpResponse response = null;

        HttpPost request= null;
        try {

            StringEntity entity = new StringEntity(payload);
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            if (type.contains("query")) {
                if (OS.contains("Windows") || OS.contains("Mac")) {
                    request = new HttpPost("http://localhost:8443/serviceregistry/query");
                } else {
                    request = new HttpPost("http://128.130.39.42:8443/serviceregistry/query");

                }
            }
            if (type.contains("mgmt")) {
                if (OS.contains("Windows") || OS.contains("Mac")) {
                    request = new HttpPost("http://localhost:8443/serviceregistry/mgmt");
                } else {
                    request = new HttpPost("http://128.130.39.42:8443/serviceregistry/mgmt");

                }
            }
            if (type.contains("requesrauth")) {
                    if (OS.contains("Windows") || OS.contains("Mac")) {
                        request = new HttpPost("http://localhost:8445/authorization/intracloud/check");
                    } else {
                        request = new HttpPost("http://128.130.39.42:8445/authorization/intracloud/check");

                    }
                }
            if (type.contains("createstore")) {
                if (OS.contains("Windows") || OS.contains("Mac")) {
                    request = new HttpPost("http://localhost:8441/orchestrator/mgmt/store");
                } else {
                    request = new HttpPost("http://128.130.39.42:8441/orchestrator/mgmt/store");

                }
            }



            request.setHeader("Content-type", "application/json");
            request.setEntity(entity);


            response = httpClient.execute(request);

        } catch (Exception e) {
            e.printStackTrace();
        }

            return response;

    }



    public HttpResponse DeleteServise(String payload , String type)  {
        HttpResponse response = null;

        HttpDelete request= null;
        try {

            StringEntity entity = new StringEntity(payload);
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();

            if (type.contains("serviceregistry")) {
                if (OS.contains("Windows") || OS.contains("Mac")) {
                    System.out.println("http://localhost:8443/serviceregistry/unregister?" + payload);
                    request = new HttpDelete("http://localhost:8443/serviceregistry/unregister?" + payload);
                } else {
                    request = new HttpDelete("http://128.130.39.42:8443/serviceregistry/unregister?" + payload);

                }
            }
            if (type.contains("store")) {
                if (OS.contains("Windows") || OS.contains("Mac")) {
                    request = new HttpDelete("http://localhost:8441/orchestrator/mgmt/store/" + payload);
                } else {
                    request = new HttpDelete("http://128.130.39.42:8441/orchestrator/mgmt/store/" + payload);

                }
            }
            request.setHeader("Content-type", "application/json");
            //request.setEntity(entity);


            response = httpClient.execute(request);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;

    }






}
