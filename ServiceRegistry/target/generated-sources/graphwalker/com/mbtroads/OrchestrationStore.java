// Generated by GraphWalker (http://www.graphwalker.org)
package com.mbtroads;

import org.graphwalker.java.annotation.Model;
import org.graphwalker.java.annotation.Vertex;
import org.graphwalker.java.annotation.Edge;

@Model(file = "com/mbtroads/OrchestratorStore.json")
public interface OrchestrationStore {

    @Edge()
    void e_Query_AllTopPriority();

    @Vertex()
    void v_CoreServiceAvailable();

    @Edge()
    void e_AuthoneticationSystemAvailabilty();

    @Edge()
    void e_OrchestrationCoreSystemAvailabilty();

    @Edge()
    void e_QueryOrchestratorStoreById();

    @Edge()
    void e_RegisterNewService_Consumer();

    @Vertex()
    void v_BadPayloadException();

    @Vertex()
    void v_CouldNotRemoveOrchestratorStore();

    @Edge()
    void e_CreateStoreWithBadPayload();

    @Vertex()
    void v_ReturnAllTopPriorityOrchestration();

    @Vertex()
    void v_End();

    @Edge()
    void e_RemoveOrchestrationIdExist();

    @Edge()
    void e_End();

    @Edge()
    void e_BackPriority();

    @Vertex()
    void v_ServiceRegisteredProvider();

    @Vertex()
    void v_RequestedIdReturned();

    @Vertex()
    void v_OrchestratorStoresRequestedParametersCreated();

    @Vertex()
    void v_AuthorizationInterCloudReturned();

    @Vertex()
    void v_ServiceRegisteredConsumer();

    @Edge()
    void e_RemoveOrchestrationIdNotExist();

    @Edge()
    void e_RequestAuthroizationCliud();

    @Vertex()
    void v_AuthoenticationServiceAvailable();

    @Vertex()
    void v_NoSuchOrchestratorStore();

    @Edge()
    void e_BackAuthorizationInterCloud();

    @Edge()
    void e_RegisterNewService_provider();

    @Vertex()
    void v_OrchestratorStoreRemoved();

    @Edge()
    void e_CreateStore();

    @Vertex()
    void v_Start();

    @Edge()
    void e_BackOrchestratorStores();

    @Edge()
    void e_QueryOrchestratorStoreById_NotExists();
}