package com.mbtroads;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.graphwalker.java.annotation.Edge;
import org.graphwalker.java.annotation.GraphWalker;
import org.graphwalker.java.annotation.Vertex;

import java.io.IOException;


@GraphWalker(value = "random(vertex_coverage(100))")
public class QueryService extends BasePage implements SericeQuery , TestData, ISystemProperties {

    HttpClient httpClient = new HttpClient();
    HttpResponse response;
    String content = null;
    String id = null;
    boolean flag = true;



    @Override
    public void v_Start(){
        extendReport("v_Start");
    }

    @Override
    public void e_start(){
        extendReport("e_start");
        infoReport("Moving Through: e_start");
        infoReport("Running ServiceAvailable API http://localhost:8443/serviceregistry/echo");
        response =  httpClient.ServiceAvailable();
        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
           // infoReport("Responce Content = " + content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    };


    @Override
    public void v_QueryService(){
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

        response =  httpClient.sendPost(InvaledServiceQuery,"http://localhost:8443/serviceregistry/query");
        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
           // infoReport("Responce Content = " + content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    @Override
    public void v_BadPayloadException(){
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

        infoReport("Moving Through: Moving Through: e_validServiceQueryForm");
        infoReport("Running : The API /serviceregistry/query with valid Payload");

        response =  httpClient.sendPost(ValidServiceQuery_Payload,"http://localhost:8443/serviceregistry/query");
        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
           // infoReport("Responce Content = " + content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void v_ValidPayload(){
        infoReport("Validating in: v_ValidPayload");
        assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
        assestdonotContains("BAD_PAYLOAD", content);

    };


    @Override
    public void e_QueryInterfaceNotDefined(){
        extendReport("e_QueryInterfaceNotDefined");

        infoReport("Moving Through: e_QueryInterfaceNotDefined");
        infoReport("Running : The API /serviceregistry/query with valid Payload and Not defined Interface");

        response =  httpClient.sendPost(InterfaceNotDefined,"http://localhost:8443/serviceregistry/query");
        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
           // infoReport("Responce Content = " + content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void v_ServiceQueryInterfaceNotDefined(){
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

        response =  httpClient.sendPost(InterfaceDefined,"http://localhost:8443/serviceregistry/query");
        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
         //   infoReport("Responce Content = " + content);

        } catch (IOException e) {
            e.printStackTrace();
        }



    };


    @Override
    public void v_FilterOnInterface(){
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

        response =  httpClient.sendPost(SecureNotDefined,"http://localhost:8443/serviceregistry/query");
        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
          //  infoReport("Responce Content = " + content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void v_ServiceQuerySecurityTypeNotDefined(){
        infoReport("Validating in: v_ServiceQuerySecurityTypeNotDefined");

        assestEqual("400", String.valueOf(response.getStatusLine().getStatusCode()));

    }

    @Override
    public void e_QuerySecurtyDefined(){
        extendReport("e_QuerySecurtyDefined");

        infoReport("Moving Through: e_QuerySecurtyDefined");
        infoReport("Running : The API /serviceregistry/query with valid Payload and  defined Security");

        response =  httpClient.sendPost(SecureDefined,"http://localhost:8443/serviceregistry/query");
        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
        //    infoReport("Responce Content = " + content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    @Override
    public void v_FilterOnSecurity(){
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

        response =  httpClient.sendPost(VersionNotDefined,"http://localhost:8443/serviceregistry/query");
        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
      //      infoReport("Responce Content = " + content);

        } catch (IOException e) {
            e.printStackTrace();
        }
       };

    @Override
    public void v_ServiceQueryVersionNotDefined(){
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

        response =  httpClient.sendPost(VersionDefined,"http://localhost:8443/serviceregistry/query");
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
        infoReport("Validating in: v_FilterOnVersion");
        assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
        assestContains("serviceQueryData", content);
        assestdonotContains("[]", content);
        assestContains("indoortemperature", content);
    }


    @Override
    public void e_back_ValidPayload()
    {
        extendReport(  "e_back_ValidPayload");  }

    @Override
    public void e_QueryMetadatNotDefined(){
        extendReport("e_QueryMetadatNotDefined");
        infoReport("Moving Through: e_QueryMetadatNotDefined");

        infoReport("Running : The API /serviceregistry/query with valid Payload and Not defined MetaData");

        response =  httpClient.sendPost(MetaDataNotDefined,"http://localhost:8443/serviceregistry/query");
        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
     //       infoReport("Responce Content = " + content);

        } catch (IOException e) {
            e.printStackTrace();
        }}

    @Override
    public  void v_ServiceQueryMetadataNotDefined(){
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

        response =  httpClient.sendPost(MetaDataDefined,"http://localhost:8443/serviceregistry/query");
        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
     //       infoReport("Responce Content = " + content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }











    @Override
    public  void v_FilterOnMetadata(){
        infoReport("Validating in: v_FilterOnMetadata");
        assestEqual("2001", String.valueOf(response.getStatusLine().getStatusCode()));
        assestContains("serviceQueryData", content);
        assestdonotContains("[]", content);
        assestContains("indoortemperature", content);

    };


    @Override
    public  void e_PingProvider(){
        extendReport("e_PingProvider");
        infoReport("Moving Through: e_PingProvider");

        infoReport("Running : The API /serviceregistry/query with valid Payload and Ping Providers TRUE");

        response =  httpClient.sendPost(PingProviders,"http://localhost:8443/serviceregistry/query");
        HttpEntity entity1 = response.getEntity();
        try {
            content = EntityUtils.toString(entity1);
     //       infoReport("Responce Content = " + content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    };


    @Override
    public  void v_QueryResponce(){
        infoReport("Validating in: v_QueryResponce");
        assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
        assestContains("serviceQueryData", content);
        assestContains("[]", content);
        assestdonotContains("indoortemperature", content);

    }


}
