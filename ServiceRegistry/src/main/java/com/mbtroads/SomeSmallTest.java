package com.mbtroads;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.graphwalker.java.annotation.GraphWalker;
import org.graphwalker.java.annotation.Vertex;
import java.io.IOException;


@GraphWalker(value = "random(vertex_coverage(100))")
public class SomeSmallTest extends BasePage implements SericeRegistry1 , TestData, ISystemProperties {

  HttpClient httpClient = new HttpClient();
  HttpResponse response;
  String content = null;
  String id = null;
  boolean flag = true;


  @Override
  public void v_Start()  {
    ExtentReport.createAndGetNodeInstance("in Running: v_Start  ");
  }

  @Override
  public void e_start() {
    ExtentReport.createAndGetNodeInstance("Moving Through: e_start");
    infoReport("Running ServiceAvailable API http://localhost:8443/serviceregistry/echo");
    response =  httpClient.ServiceAvailable();
    HttpEntity entity1 = response.getEntity();
    try {
      content = EntityUtils.toString(entity1);
      infoReport("Responce Content = " + content);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void v_RegisterService()  {
    ExtentReport.createAndGetNodeInstance("in Running: v_RegisterService");
    if (flag == true) {
      assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
      assestContains("Got it!", content);
    }
  }


  @Override
  public void e_InvaledServiceRegisteryForm(){
    ExtentReport.createAndGetNodeInstance("Moving Through: e_InvaledServiceRegisteryForm");
    infoReport("Running : The API http://localhost:8443/serviceregistry/mgmt with invalid Payload");

        response =  httpClient.sendPost(InvaledServiceRegistery, "http://localhost:8443/serviceregistry/mgmt");
        HttpEntity entity1 = response.getEntity();
        try {
          content = EntityUtils.toString(entity1);
          infoReport("Responce Content = " + content);

        } catch (IOException e) {
        e.printStackTrace();
        }


  }

  @Override
  public void v_BadPayloadException()  {
    ExtentReport.createAndGetNodeInstance("in Running: v_BadPayloadException");
    assestEqual("400", String.valueOf(response.getStatusLine().getStatusCode()));
    assestContains("BAD_PAYLOAD", content);
  }

  @Override
  public void e_back_RegisterService() {
    ExtentReport.createAndGetNodeInstance("Moving Through: e_back_RegisterService");
    flag = false;
  }


  @Override
  public void e_validServiceRegisteryForm() {
    ExtentReport.createAndGetNodeInstance("Moving Through: e_validServiceRegisteryForm");
    infoReport("Running : The API http://localhost:8443/serviceregistry/mgmt with valid Payload");
    response =  httpClient.sendPost(ValidServiceRegistery_Payload,"http://localhost:8443/serviceregistry/mgmt");
    HttpEntity entity1 = response.getEntity();
    try {
      content = EntityUtils.toString(entity1);
      infoReport("Responce Content = " + content);
    } catch (IOException e) {
      e.printStackTrace();
    }


  }



  @Override
  public void v_ValidPayload()  {

    ExtentReport.createAndGetNodeInstance("in Running: v_ValidPayload");
    assestdonotContains("BAD_PAYLOAD", content);
  }


  @Override
  public void e_SerrviceDefinationNotExist()  {

    ExtentReport.createAndGetNodeInstance("Moving Through: e_SerrviceDefinationNotExist");
    infoReport("Running : The API http://localhost:8443/serviceregistry/mgmt with valid Payload and Serrvice Defination Not Exist");
    response =  httpClient.sendPost(NewService,"http://localhost:8443/serviceregistry/mgmt");
    HttpEntity entity1 = response.getEntity();
    try {
      content = EntityUtils.toString(entity1);
      infoReport("Responce Content = " + content);

    } catch (IOException e) {
      e.printStackTrace();
    }
    id = httpClient.Get_id(content);
 }

  @Override
  public void v_RegisteredService()  {
    ExtentReport.createAndGetNodeInstance("in Running: v_RegisteredService");
    assestEqual("201", String.valueOf(response.getStatusLine().getStatusCode()));
  }

  @Override
  public void e_SaveService()  {
    ExtentReport.createAndGetNodeInstance("Moving Through: e_SaveService");
    infoReport("Running : The API http://localhost:8443/serviceregistry/mgmt?direction=ASC&sort_field=id to get all saved Services");
    response =  httpClient.sendGet_All();
    HttpEntity entity1 = response.getEntity();
    try {
      content = EntityUtils.toString(entity1);
      infoReport("Responce Content = " + content);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void v_QueryService() {
    ExtentReport.createAndGetNodeInstance("in Running: v_QueryService");
    assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
    assestContains("\"id\":"+id+",", content);
  }



  @Override
  public void e_NewServiceExist()  {
    ExtentReport.createAndGetNodeInstance("Moving Through: e_NewServiceExist");
    infoReport("Running : The API http://localhost:8443/serviceregistry/mgmt/"+id + " To get specific service");

    response =  httpClient.sendGet(id);
    HttpEntity entity1 = response.getEntity();
    try {
      content = EntityUtils.toString(entity1);
      infoReport("Responce Content = " + content);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Vertex()
  public void v_SucessMesssage() {
    ExtentReport.createAndGetNodeInstance("in Running: v_SucessMesssage");
    assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
  }

  @Override
  public void e_back_ValidPayload() {
    ExtentReport.createAndGetNodeInstance("Moving Through: e_back_ValidPayload");
  }


  @Override
  public void e_SerrviceDefinationtExist() {
    ExtentReport.createAndGetNodeInstance("Moving Through: e_SerrviceDefinationtExist");
    infoReport("Running : The API http://localhost:8443/serviceregistry/mgmt with valid Payload and Serrvice Defination Exist");

    response =  httpClient.sendPost(EXISTService, "http://localhost:8443/serviceregistry/mgmt");
    HttpEntity entity1 = response.getEntity();
    try {
      content = EntityUtils.toString(entity1);
      infoReport("Responce Content = " + content);

    } catch (IOException e) {
      e.printStackTrace();
    }
    id = httpClient.Get_id(content);

  }

  @Override
  public void v_end()  {
    ExtentReport.createAndGetNodeInstance("in Running: v_end");
    assestEqual("400", String.valueOf(response.getStatusLine().getStatusCode()));
    assestContains("Service Registry entry with provider: (insecuretemperaturesensor, 192.168.0.2:8080) and service definition: indoortemperature already exists.", content);
  }

  @Override
  public void e_NewEdge()  {
    System.out.println("Running: e_FirstAction");
  }




}
