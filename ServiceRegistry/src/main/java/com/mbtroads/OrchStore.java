package com.mbtroads;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.graphwalker.java.annotation.Edge;
import org.graphwalker.java.annotation.GraphWalker;
import org.graphwalker.java.annotation.Vertex;

import java.io.IOException;


@GraphWalker(value = "random(vertex_coverage(100))")
public class OrchStore extends BasePage implements OrchestrationStore , TestData, ISystemProperties
{
        HttpClient httpClient = new HttpClient();
        HttpResponse response;
        String content_p = null;
        String content_c = null;
        String content = null;

        String provider_name = null;
        String id_p = null;
        String id_c = null;
        String id_ser = null;
        String  id_store  = null;
        String id_interface = null;
        String service_name = null;
        String consumer_request = null;
        String  store_request  = null;
        boolean flag = true;


        @Override()
        public void v_Start(){ExtentReport.createAndGetNodeInstance("in Running: v_Start  ");}

        @Override()
        public void e_RegisterNewService_provider(){
                ExtentReport.createAndGetNodeInstance("Moving Through: e_RegisterNewService_provider");
                infoReport("Running : The API /serviceregistry/mgmt with valid Payload and Serrvice Defination Not Exist");
                response =  httpClient.sendPost_Query(NewService,"mgmt");

                HttpEntity entity1 = response.getEntity();
                try {
                        content_p = EntityUtils.toString(entity1);
                        infoReport("Responce Content = " + content_p);

                } catch (IOException e) {
                        e.printStackTrace();
                }
                id_p = httpClient.Get_id_p(content_p, "provider");
                provider_name  = httpClient.Get_id_p(content_p , "servicename");;
                infoReport("id : " + id_p);
                infoReport("provider name : " + provider_name);


        }

        @Override()
        public void v_ServiceRegisteredProvider(){

                ExtentReport.createAndGetNodeInstance("in Running: v_ServiceRegisteredProvider");
                assestEqual("201", String.valueOf(response.getStatusLine().getStatusCode()));
        }




        @Override()
        public void e_RegisterNewService_Consumer(){
                ExtentReport.createAndGetNodeInstance("Moving Through: e_RegisterNewService_Consumer");
                infoReport("Running : The API  /serviceregistry/mgmt with valid Payload and Serrvice Defination Not Exist");
                response =  httpClient.sendPost_Query(NewServiceConsumer,"mgmt");

                HttpEntity entity1 = response.getEntity();
                try {
                        content_c = EntityUtils.toString(entity1);
                        infoReport("Responce Content = " + content_c);

                } catch (IOException e) {
                        e.printStackTrace();
                }
                id_c = httpClient.Get_id_p(content_c , "provider");
                infoReport("id : " + id_c);

                id_ser = httpClient.Get_id_p(content_c , "serviceDefinition");
                infoReport("id serviceDefinition: " + id_ser);

                id_interface = httpClient.Get_id_p(content_c , "interface");
                infoReport("id interface: " + id_interface);

                service_name = httpClient.Get_id_p(content_c , "servicename");
                infoReport("id service_name: " + service_name);

        }


        @Override()
        public void v_ServiceRegisteredConsumer(){
                ExtentReport.createAndGetNodeInstance("in Running: v_ServiceRegisteredConsumer");
                assestEqual("201", String.valueOf(response.getStatusLine().getStatusCode()));
        }


        @Override()
        public void e_AuthoneticationSystemAvailabilty(){
                ExtentReport.createAndGetNodeInstance("Moving Through: e_AuthoneticationSystemAvailabilty");
                infoReport("Running ServiceAvailable API /authorization/echo");
                response =  httpClient.ServiceAvailable("authontication");
                HttpEntity entity1 = response.getEntity();
                try {
                        content = EntityUtils.toString(entity1);
                        infoReport("Responce Content = " + content);
                } catch (IOException e) {
                        e.printStackTrace();
                }

        }


        @Override()
        public void v_AuthoenticationServiceAvailable(){
                ExtentReport.createAndGetNodeInstance("in Running: v_AuthoenticationServiceAvailable");
               assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
               assestContains("Got it!", content);


        }

        @Override()
        public void e_RequestAuthroizationCliud(){
                ExtentReport.createAndGetNodeInstance("Moving Through: e_RequestAuthroizationCloud");
                infoReport("Running ServiceAvailable API /authorization/intracloud");

                consumer_request ="{ \"consumerId\": "+ id_c + ", \"interfaceIds\": [ "+ id_interface + " ], \"providerIds\": [ " + id_p + " ], \"serviceDefinitionIds\": [ " + id_ser +" ]}";
                infoReport("Running : The API authorization/intracloud");
                infoReport(consumer_request);

                response =  httpClient.sendPost_Query(consumer_request, "requesrauth");

                HttpEntity entity1 = response.getEntity();
                try {
                        content = EntityUtils.toString(entity1);
                        infoReport("Responce Content = " + content);
                        infoReport("Responce Content = " + String.valueOf(response.getStatusLine().getStatusCode()));


                } catch (IOException e) {
                        e.printStackTrace();
                }
        }



        @Override()
        public void v_AuthorizationInterCloudReturned(){
                ExtentReport.createAndGetNodeInstance("in Running: v_AuthorizationInterCloudReturned");
                assestEqual("201", String.valueOf(response.getStatusLine().getStatusCode()));
                assestContains("consumer", content);
                assestContains(id_c, content);
        }

        @Override()
        public void e_OrchestrationCoreSystemAvailabilty(){
                ExtentReport.createAndGetNodeInstance("Moving Through: e_OrchestrationCoreSystemAvailabilty");
                infoReport("Running ServiceAvailable API /orchestrator/echo");
                response =  httpClient.ServiceAvailable("orchestration");
                HttpEntity entity1 = response.getEntity();
                try {
                        content = EntityUtils.toString(entity1);
                        infoReport("Responce Content = " + content);
                } catch (IOException e) {
                        e.printStackTrace();
                }

        }


        @Override()
        public void v_CoreServiceAvailable(){
                ExtentReport.createAndGetNodeInstance("in Running: v_CoreServiceAvailable");
                if (flag == true) {
                        assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
                        assestContains("Got it!", content);
                }
        }

        @Override()
        public void e_CreateStoreWithBadPayload(){

                ExtentReport.createAndGetNodeInstance("Moving Through: e_CreateStoreWithBadPayload");
                infoReport("Running ServiceAvailable API &orchestrator/mgmt/store");


                store_request = "[ { \"cloud\": { \"authenticationInfo\": null, \"gatekeeperRelayIds\": [ 0 ], \"gatewayRelayIds\": [ 0 ], \"name\": \"default_insecure_cloud\", \"neighbor\": true, \"operator\": \"default_operator\", \"secure\": true }, \"consumerSystemId\": , \"priority\": 10, \"providerSystem\": { \"address\": \"192.168.0.2\", \"authenticationInfo\": \"eyJhbGciOiJIUzI1Ni...\", \"port\": 8080, \"systemName\": \"insecuretemperaturesensor\" }, \"serviceDefinitionName\": \"indoortemperature\", \"serviceInterfaceName\": \"HTTP-INSECURE-JSON\" }]";
                infoReport(" store_request = " + store_request);
                response =  httpClient.sendPost_Query(store_request, "createstore");

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
                ExtentReport.createAndGetNodeInstance("in Running: v_BadPayloadException");
                assestEqual("400", String.valueOf(response.getStatusLine().getStatusCode()));

        }


        @Override()
        public void e_BackAuthorizationInterCloud(){
                ExtentReport.createAndGetNodeInstance("Moving Through: e_BackAuthorizationInterCloud");
                flag = false;
        }

        @Override()
        public void e_CreateStore(){
                ExtentReport.createAndGetNodeInstance("Moving Through: e_CreateStore");
                infoReport("Running ServiceAvailable API &orchestrator/mgmt/store");


                store_request =      "[ { \"cloud\": { \"authenticationInfo\": \"default-insecure-cloud\", \"gatekeeperRelayIds\": [ 0 ], \"gatewayRelayIds\": [ 0 ], \"name\": \"default-insecure-cloud\", \"neighbor\": false, \"operator\": \"default-operator\", \"secure\": false }, \"consumerSystemId\":" + id_c + ", \"priority\": 10, \"providerSystem\": { \"address\": \"192.168.0.2\", \"authenticationInfo\": \"eyJhbGciOiJIUzI1Ni...\", \"metadata\": { \"unit\": \"celsius\" }, \"port\": 8080, \"systemName\": \""+provider_name +"\" }, \"serviceDefinitionName\": \"indoortemperature\", \"serviceInterfaceName\": \"HTTP-INSECURE-JSON\" }]";
                infoReport("store_request " + store_request);
                infoReport("store_request " + store_request);
                infoReport("=========================================== " );
                response =  httpClient.sendPost_Query(store_request, "createstore");

                HttpEntity entity1 = response.getEntity();
                try {
                        content = EntityUtils.toString(entity1);
                        infoReport("Store Content" + content);
                        infoReport("Responce Content = " + String.valueOf(response.getStatusLine().getStatusCode()));

                } catch (IOException e) {
                        e.printStackTrace();
                }
                id_store = httpClient.Get_id_p(content , "storeid");
                infoReport("id store: " + id_store);
                flag = true;

        }

        @Override()
        public void v_OrchestratorStoresRequestedParametersCreated(){
                ExtentReport.createAndGetNodeInstance("in Running: v_OrchestratorStoresRequestedParametersCreated");
                if (flag == true) {
                        assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
                }
        }


        @Override()
        public void e_QueryOrchestratorStoreById_NotExists(){
                ExtentReport.createAndGetNodeInstance("e_QueryOrchestratorStoreById_NotExists");
                infoReport("Running : The API /orchestrator/mgmt/store/{id}" + " To get specific store");

                response =  httpClient.sendGet(id_store +"1", "store");
                HttpEntity entity1 = response.getEntity();
                try {
                        content = EntityUtils.toString(entity1);
                        infoReport("Responce Content = " + content);
                } catch (IOException e) {
                        e.printStackTrace();
                }

        }


        @Override()
        public void v_NoSuchOrchestratorStore(){
                ExtentReport.createAndGetNodeInstance("in Running: v_NoSuchOrchestratorStore");
                assestEqual("400", String.valueOf(response.getStatusLine().getStatusCode()));
                assestContains("errorMessage", content);
                assestContains("not found", content);


        }

        @Override()
        public void e_BackOrchestratorStores(){

                flag = false;
        }





        @Override()
        public void e_QueryOrchestratorStoreById(){
                ExtentReport.createAndGetNodeInstance("e_QueryOrchestratorStoreById_NotExists");
                infoReport("Running : The API /orchestrator/mgmt/store/{id}" + " To get specific store");

                response =  httpClient.sendGet(id_store, "store");
                HttpEntity entity1 = response.getEntity();
                try {
                        content = EntityUtils.toString(entity1);
                        infoReport("Responce Content = " + content);
                } catch (IOException e) {
                        e.printStackTrace();
                }

        }


        @Override()
        public void v_RequestedIdReturned(){
                ExtentReport.createAndGetNodeInstance("in Running: v_RequestedIdReturned");
                assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));

        }

        @Override()
        public void e_Query_AllTopPriority(){

                ExtentReport.createAndGetNodeInstance("Moving Through: e_Query_AllTopPriority");
                infoReport("Running : The API 7orchestrator/mgmt/store/all_top_priority?direction=ASC&sort_field=id to get all saved Services");
                response =  httpClient.sendGet_All("storepriority");
                HttpEntity entity1 = response.getEntity();
                try {
                        content = EntityUtils.toString(entity1);
                        infoReport("Responce Content = " + content);
                } catch (IOException e) {
                        e.printStackTrace();
                }
                flag = true;
        }

        @Override()
        public void v_ReturnAllTopPriorityOrchestration(){
                if (flag == true) {
                        ExtentReport.createAndGetNodeInstance("in Running: v_ReturnAllTopPriorityOrchestration");
                        assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));
                }
        }


        @Override()
        public void e_RemoveOrchestrationIdNotExist(){

                ExtentReport.createAndGetNodeInstance("Moving Through: e_RemoveOrchestrationIdNotExist");
                infoReport("Running : The API /orchestrator/mgmt/store/ with invalid id store Defination Not Exist");
                infoReport("/orchestrator/mgmt/store/+UnregisterService/"+id_store +"1");

                response =  httpClient.DeleteServise(id_store +"1", "store");

                HttpEntity entity1 = response.getEntity();
                try {
                        content = EntityUtils.toString(entity1);
                        infoReport("Responce Content = " + content);

                } catch (IOException e) {
                        e.printStackTrace();
                }


        }


        @Override()
        public void v_CouldNotRemoveOrchestratorStore(){
                ExtentReport.createAndGetNodeInstance("in Running: v_NoSuchOrchestratorStore");
                assestEqual("400", String.valueOf(response.getStatusLine().getStatusCode()));
                assestContains("OrchestratorStore is not available in database", content);

        }

        @Override()
        public void e_BackPriority(){
                ExtentReport.createAndGetNodeInstance("e_BackPriority");
                flag = false;
        }




        @Override()
        public void e_RemoveOrchestrationIdExist(){
                ExtentReport.createAndGetNodeInstance("Moving Through: e_RemoveOrchestrationIdExist");
                infoReport("Running : The API /orchestrator/mgmt/store/ with valid id store Defination Exist");
                infoReport("/orchestrator/mgmt/store/+UnregisterService/"+id_store);

                response =  httpClient.DeleteServise(id_store, "store");

                HttpEntity entity1 = response.getEntity();
                try {
                        content = EntityUtils.toString(entity1);

                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        @Override()
        public void v_OrchestratorStoreRemoved(){
                ExtentReport.createAndGetNodeInstance("in Running: v_OrchestratorStoreRemoved");
                assestEqual("200", String.valueOf(response.getStatusLine().getStatusCode()));

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
