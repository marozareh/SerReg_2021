package com.mbtroads;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.graphwalker.java.annotation.Edge;
import org.graphwalker.java.annotation.GraphWalker;
import org.graphwalker.java.annotation.Vertex;

import java.io.IOException;


@GraphWalker(value = "random(vertex_coverage(100))")
public class UnregService extends BasePage implements UnregisterService , TestData, ISystemProperties
{
        HttpClient httpClient = new HttpClient();
        HttpResponse response;
        String content = null;
        String id = null;
        String ServiceName = null;
        boolean flag = true;

        @Override()
        public void v_Start(){
                ExtentReport.createAndGetNodeInstance("in Running: v_Start  ");
        }

        @Override()
        public void e_RegisterNewService(){
                ExtentReport.createAndGetNodeInstance("Moving Through: e_RegisterNewService");
                infoReport("Running : The API http://localhost:8443/serviceregistry/mgmt with valid Payload and Serrvice Defination Not Exist");
                response =  httpClient.sendPost_Query(NewService,"mgmt");

                HttpEntity entity1 = response.getEntity();
                try {
                        content = EntityUtils.toString(entity1);
                        infoReport("Responce Content = " + content);
                        infoReport("NewService = " + NewService);



                } catch (IOException e) {
                        e.printStackTrace();
                }
                ServiceName = httpClient.Get_name(content);
                infoReport("New ServiceName >>>>>>>> "+ ServiceName);
                id = httpClient.Get_id(content);

        }

        @Override()
        public void v_RegisteredSuccessfully()
        {
                ExtentReport.createAndGetNodeInstance("in Running: v_RegisterService");
                if (flag == true) {
                        assestEqual("201", String.valueOf(response.getStatusLine().getStatusCode()));
                }
        }


        @Override()
        public void e_InvaledUnRegisterServiceForm(){

                ExtentReport.createAndGetNodeInstance("Moving Through: e_InvaledUnRegisterServiceForm");
                infoReport("Running : The API /serviceregistry/unregister with invalid Payload and Serrvice Defination Not Exist");
                infoReport("/serviceregistry/unregister?"+UnregisterService);

                 response =  httpClient.DeleteServise(UnregisterService, "serviceregistry");

                HttpEntity entity1 = response.getEntity();
                try {
                        content = EntityUtils.toString(entity1);
                        infoReport("Responce Content = " + content);

                } catch (IOException e) {
                        e.printStackTrace();
                }

        }

        @Override()
        public void v_BadPayloadException(){
                infoReport("Validating in:  v_BadPayloadException");

                assestEqual("400", String.valueOf(response.getStatusLine().getStatusCode()));
                assestContains("INVALID_PARAMETER", content);
        }


        @Override()
        public void e_back_RegisterService(){
                ExtentReport.createAndGetNodeInstance("Moving Through: e_back_RegisterService");
                flag = false;
        }

        @Override()
        public void e_ValedUnRegisterServiceForm(){
                ExtentReport.createAndGetNodeInstance("Moving Through: e_ValedUnRegisterServiceForm");
                infoReport("Running : The API http://localhost:8443/serviceregistry/unregister with valid Payload and Serrvice Defination  Exist");
                infoReport("http://localhost:8443/serviceregistry/unregister?"+registerService+ServiceName);

                response =  httpClient.DeleteServise(registerService+ServiceName, "serviceregistry");

                HttpEntity entity1 = response.getEntity();
                try {
                        content = EntityUtils.toString(entity1);
                        infoReport("Responce Content = " + content);

                } catch (IOException e) {
                        e.printStackTrace();
                }
        }


        @Override()
        public void v_RemoveServiceRegisteryEntry(){
                ExtentReport.createAndGetNodeInstance("in Running: v_RemoveServiceRegisteryEntry");
                infoReport("Responce codeeee    Content = " + HttpClient.failCase_Test);
                if(HttpClient.failCase_Test.equals("false"))
                        assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
                else
                        assestEqual("201", String.valueOf(response.getStatusLine().getStatusCode()));

        }


        @Override()
        public void e_QueeryRemovedService() {
                ExtentReport.createAndGetNodeInstance("Moving Through: e_QueeryRemovedService");

                infoReport("Running : The API http://localhost:8443/serviceregistry/mgmt/"+id + " To get specific service");

                response =  httpClient.sendGet(id, "serviceregistry");
                HttpEntity entity1 = response.getEntity();
                try {
                        content = EntityUtils.toString(entity1);
                        infoReport("Responce Content = " + content);
                        System.out.println(">>>>>>>>>>>>hallo ");
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }





        @Override()
        public void v_ServiceNotExist(){
                ExtentReport.createAndGetNodeInstance("in Running: v_ServiceNotExist");
                assestEqual("400", String.valueOf(response.getStatusLine().getStatusCode()));
                assestContains("does not exist", content);

        }

        @Override()
        public void e_End(){
                ExtentReport.createAndGetNodeInstance("Moving Through: e_End");

        }

        @Override()
        public void v_End(){
                ExtentReport.createAndGetNodeInstance("in Running: v_End");

        }










}
