package com.mbtroads;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.graphwalker.java.annotation.GraphWalker;

import java.io.IOException;


@GraphWalker(value = "random(vertex_coverage(100))")
public class QueryService extends BasePage implements SericeQuery , TestData, ISystemProperties {

    HttpClient httpClient = new HttpClient();
    HttpResponse response;
    String content = null;
    String id = null;
    boolean flag = true;



    @Override
    public void v_Start()
    {

        extendReport("v_Start");
        infoReport("Start Service Query test case");
    }

    @Override
    public void e_start(){
        extendReport("e_start");
        infoReport("Moving Through: e_start");
        infoReport("Running Service Available /serviceregistry/echo");
        response =  httpClient.ServiceAvailable("serviceregistery");
        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
        } catch (IOException e) {
            ExtentReport.node.fail("Service has a problem");
            e.printStackTrace();
        }

    };


    @Override
    public void v_QueryService(){
        extendReport("v_QueryService");
        infoReport("Validating in:  v_QueryService");
      if (flag == true) {
            assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
            assestContains("Got it!", content);
        }
    };

    @Override
    public void e_InvaledServiceQuerForm(){
        extendReport("e_InvaledServiceQuerForm");
        infoReport("Moving Through: e_InvaledServiceQuerForm");
        infoReport("Running : The API /serviceregistry/query with invalid Payload");
        response =  httpClient.sendPost_Query(InvaledServiceQuery, "query");

        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    @Override
    public void v_BadPayloadException(){
        extendReport("v_BadPayloadException");
        infoReport("Validating in: v_BadPayloadException");
        assestEqual("400", String.valueOf(response.getStatusLine().getStatusCode()));
        assestContains("BAD_PAYLOAD", content);

    };

    @Override
    public void e_back_QueryService(){
        extendReport("e_back_QueryService");
        infoReport("Moving Through: Moving Through: e_back_QueryService");


                flag = false;

    };

    @Override
    public void e_validServiceQueryForm(){
        extendReport("e_validServiceQueryForm");

       // infoReport("Moving Through: e_validServiceQueryForm");
        infoReport("Running : The API /serviceregistry/query with valid Payload");
        response =  httpClient.sendPost_Query(ValidServiceQuery_Payload, "query");
        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void v_ValidPayload(){
        extendReport("v_ValidPayload");

        infoReport("Validating in: v_ValidPayload");
        assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
        assestdonotContains("BAD_PAYLOAD", content);

    };


    @Override
    public void e_QueryInterfaceNotDefined(){
        extendReport("e_QueryInterfaceNotDefined");

        infoReport("Moving Through: e_QueryInterfaceNotDefined");
        infoReport("Running : The API /serviceregistry/query with valid Payload and Not defined Interface");
        response =  httpClient.sendPost_Query(InterfaceNotDefined, "query");

        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void v_ServiceQueryInterfaceNotDefined(){
        extendReport("v_ServiceQueryInterfaceNotDefined");
        infoReport("Validating in: v_ServiceQueryInterfaceNotDefined");
        assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
        assestContains("serviceQueryData", content);
        assestContains("[]", content);
        assestdonotContains("indoortemperature", content);

    };

    @Override
    public void e_QueryInterfaceDefined(){
        extendReport("e_QueryInterfaceDefined");

        infoReport("Moving Through: e_QueryInterfaceNotDefined");
        infoReport("Running : The API /serviceregistry/query with valid Payload and Not defined Interface");

        response =  httpClient.sendPost_Query(InterfaceDefined, "query");
        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
        } catch (IOException e) {
            e.printStackTrace();
        }



    };


    @Override
    public void v_FilterOnInterface(){
        extendReport("v_FilterOnInterface");

        infoReport("Validating in: v_FilterOnInterface");
        assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
        assestContains("serviceQueryData", content);
        assestdonotContains("[]", content);
        assestContains("indoortemperature", content);

    };


    @Override
    public  void e_QuerySecurityTypeNotDefined(){
        extendReport("e_QuerySecurityTypeNotDefined");

        infoReport("Moving Through: e_QuerySecurityTypeNotDefined");
        infoReport("Running : The API /serviceregistry/query with valid Payload and Not defined Security");
        response =  httpClient.sendPost_Query(SecureNotDefined, "query");

        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void v_ServiceQuerySecurityTypeNotDefined(){
        extendReport("v_ServiceQuerySecurityTypeNotDefined");
        infoReport("Validating in: v_ServiceQuerySecurityTypeNotDefined");
        assestEqual("400", String.valueOf(response.getStatusLine().getStatusCode()));

    }

    @Override
    public void e_QuerySecurtyDefined(){
        extendReport("e_QuerySecurtyDefined");

        infoReport("Moving Through: e_QuerySecurtyDefined");
        infoReport("Running : The API /serviceregistry/query with valid Payload and  defined Security");
        response =  httpClient.sendPost_Query(SecureDefined, "query");

        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    @Override
    public void v_FilterOnSecurity(){
        extendReport("v_FilterOnSecurity");
        infoReport("Validating in: v_FilterOnSecurity");
        assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
        assestContains("serviceQueryData", content);
        assestdonotContains("[]", content);
        assestContains("indoortemperature", content);

    };

    @Override
    public void e_QueryVersionNotDefined(){
        extendReport("e_QueryVersionNotDefined");

        infoReport("Moving Through: e_QueryVersionNotDefined");
        infoReport("Running : The API /serviceregistry/query with valid Payload and Not defined Version");

        response =  httpClient.sendPost_Query(VersionNotDefined, "query");

        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
        } catch (IOException e) {
            e.printStackTrace();
        }
       };

    @Override
    public void v_ServiceQueryVersionNotDefined(){
        extendReport("v_ServiceQueryVersionNotDefined");
        infoReport("Validating in: v_ServiceQueryVersionNotDefined");
        assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
        assestContains("serviceQueryData", content);
        assestContains("[]", content);
        assestdonotContains("indoortemperature", content);

    };

    @Override
    public void e_QueryVersionDefined(){
        extendReport("e_QueryVersionDefined");

        infoReport("Moving Through: e_QueryVersionDefined");
        infoReport("Running : The API /serviceregistry/query with valid Payload and  defined Version");

//        response =  httpClient.sendPost(VersionDefined,"http://localhost:8443/serviceregistry/query");
        response =  httpClient.sendPost_Query(VersionDefined, "query");

        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
       //     infoReport("Responce Content = " + content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void v_FilterOnVersion(){
        extendReport("v_FilterOnVersion");
        infoReport("Validating in: v_FilterOnVersion");
        assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
        assestContains("serviceQueryData", content);
        assestdonotContains("[]", content);
        assestContains("indoortemperature", content);
    }


    @Override
    public void e_back_ValidPayload()
    {
        extendReport(  "e_back_ValidPayload");
    }

    @Override
    public void e_QueryMetadatNotDefined(){
        extendReport("e_QueryMetadatNotDefined");
        infoReport("Moving Through: e_QueryMetadatNotDefined");

        infoReport("Running : The API /serviceregistry/query with valid Payload and Not defined MetaData");

        //response =  httpClient.sendPost(MetaDataNotDefined,"http://localhost:8443/serviceregistry/query");
        response =  httpClient.sendPost_Query(MetaDataNotDefined, "query");

        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
        } catch (IOException e) {
            e.printStackTrace();
        }}

    @Override
    public  void v_ServiceQueryMetadataNotDefined(){
        extendReport("v_ServiceQueryMetadataNotDefined");
        infoReport("Validating in: v_ServiceQueryMetadataNotDefined");
        assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
        assestContains("serviceQueryData", content);
        assestContains("[]", content);
        assestdonotContains("indoortemperature", content);

    };


    @Override
    public void e_QueryMetadataDefined(){
        extendReport("e_QueryMetadataDefined");
        infoReport("Moving Through: e_QueryMetadataDefined");

        infoReport("Running : The API /serviceregistry/query with valid Payload and defined MetaData");
        response =  httpClient.sendPost_Query(MetaDataDefined, "query");

        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public  void v_FilterOnMetadata(){
        extendReport("v_FilterOnMetadata");
        infoReport("Validating in: v_FilterOnMetadata");
        assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
        assestContains("serviceQueryData", content);
        assestdonotContains("[]", content);
        assestContains("indoortemperature", content);

    };


    @Override
    public  void e_PingProvider(){
        extendReport("e_PingProvider");
        infoReport("Moving Through: e_PingProvider");

        infoReport("Running : The API /serviceregistry/query with valid Payload and Ping Providers TRUE");
        response =  httpClient.sendPost_Query(PingProviders, "query");

        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };


    @Override
    public  void v_QueryResponce(){
        extendReport("v_QueryResponce");
        infoReport("Validating in: v_QueryResponce");
        assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
        assestContains("serviceQueryData", content);
        assestContains("[]", content);
        assestdonotContains("indoortemperature", content);

    }


}
