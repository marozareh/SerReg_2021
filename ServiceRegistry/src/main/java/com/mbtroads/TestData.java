package com.mbtroads;

import org.apache.http.HttpResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
public interface TestData {


//Service Register
    String InvaledServiceRegistery = "{ \"providerSystem\": { \"systemName\": \"InsecureTemperatureSensor\", \"address\": \"192.168.0.2\", \"port\": 8080, \"authenticationInfo\": \"eyJhbGciOiJIUzI1Ni...\"}, \"serviceUri\": \"temperature\", \"endOfValidity\": \"2019-12-05 12:00:00\", \"secure\": \"NOT_SECURE\", \"metadata\": { \"unit\": \"celsius\"}, \"version\": 1, \"interfaces\": [ \"HTTP-INSECURE-JSON\" ]}";
    String ValidServiceRegistery_Payload = "{ \"serviceDefinition\": \"IndoorTemperature\", \"providerSystem\": { \"systemName\": \"InsecureTemperatureSensor\", \"address\": \"192.168.0.2\", \"port\": 8080, \"authenticationInfo\": \"eyJhbGciOiJIUzI1Ni...\"}, \"serviceUri\": \"temperature\", \"endOfValidity\": \"2019-12-05 12:00:00\", \"secure\": \"NOT_SECURE\", \"metadata\": { \"unit\": \"celsius\"}, \"version\": 1, \"interfaces\": [ \"HTTP-INSECURE-JSON\" ]}";

    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss");
    String strDate = dateFormat.format(date);
    String NewService = "{ \"serviceDefinition\": \"IndoorTemperature\", \"providerSystem\": { \"systemName\": \"InsecureTemperatureSensor_"+ strDate + "\",\"address\": \"192.168.0.2\", \"port\": 8080, \"authenticationInfo\": \"eyJhbGciOiJIUzI1Ni...\"}, \"serviceUri\": \"temperature\", \"endOfValidity\": \"2019-12-05 12:00:00\", \"secure\": \"NOT_SECURE\", \"metadata\": { \"unit\": \"celsius\"}, \"version\": 1, \"interfaces\": [ \"HTTP-INSECURE-JSON\" ]}";
    String NewServiceNotExist = "{ \"serviceDefinition\": \"IndoorTemperature\", \"providerSystem\": { \"systemName\": \"InsecureTemperatureSensorNX_"+ strDate + "\",\"address\": \"192.168.0.2\", \"port\": 8080, \"authenticationInfo\": \"eyJhbGciOiJIUzI1Ni...\"}, \"serviceUri\": \"temperature\", \"endOfValidity\": \"2019-12-05 12:00:00\", \"secure\": \"NOT_SECURE\", \"metadata\": { \"unit\": \"celsius\"}, \"version\": 1, \"interfaces\": [ \"HTTP-INSECURE-JSON\" ]}";

    String NewServiceConsumer = "{ \"serviceDefinition\": \"IndoorTemperature\", \"providerSystem\": { \"systemName\": \"InsecureTemperatureSensorConsumer_"+ strDate + "\",\"address\": \"192.168.0.2\", \"port\": 8080, \"authenticationInfo\": \"eyJhbGciOiJIUzI1Ni...\"}, \"serviceUri\": \"temperature\", \"endOfValidity\": \"2019-12-05 12:00:00\", \"secure\": \"NOT_SECURE\", \"metadata\": { \"unit\": \"celsius\"}, \"version\": 1, \"interfaces\": [ \"HTTP-INSECURE-JSON\" ]}";

    String EXISTService = "{ \"serviceDefinition\": \"IndoorTemperature\", \"providerSystem\": { \"systemName\": \"InsecureTemperatureSensor\", \"address\": \"192.168.0.2\", \"port\": 8080, \"authenticationInfo\": \"eyJhbGciOiJIUzI1Ni...\"}, \"serviceUri\": \"temperature\", \"endOfValidity\": \"2019-12-05 12:00:00\", \"secure\": \"NOT_SECURE\", \"metadata\": { \"unit\": \"celsius\"}, \"version\": 1, \"interfaces\": [ \"HTTP-INSECURE-JSON\" ]}";

    //Query Service
    String InvaledServiceQuery = "{ }";
    String ValidServiceQuery_Payload = "{\"serviceDefinitionRequirement\": \"indoortemperature\"}";
    String InterfaceNotDefined = "{ \"interfaceRequirements\": [ \"TTP-INSECURE-JSON\" ], \"serviceDefinitionRequirement\": \"indoortemperature\" }";
    String InterfaceDefined ="{ \"interfaceRequirements\": [ \"HTTP-INSECURE-JSON\" ], \"serviceDefinitionRequirement\": \"indoortemperature\" }";
    String SecureNotDefined ="{ \"securityRequirements\": [ \"NO_SECURE\" ], \"serviceDefinitionRequirement\": \"indoortemperature\" }";
    String SecureDefined ="{ \"securityRequirements\": [ \"NOT_SECURE\" ], \"serviceDefinitionRequirement\": \"indoortemperature\" }";

    String VersionNotDefined ="{ \"serviceDefinitionRequirement\": \"indoortemperature\", \"versionRequirement\": 0}";
    String VersionDefined ="{ \"serviceDefinitionRequirement\": \"indoortemperature\", \"versionRequirement\": 1}";

    String MetaDataNotDefined ="{ \"metadataRequirements\": { \"unit\": \"celsius1\" }, \"serviceDefinitionRequirement\": \"indoortemperature\"}";
    String MetaDataDefined ="{ \"metadataRequirements\": { \"unit\": \"celsius\" }, \"serviceDefinitionRequirement\": \"indoortemperature\"}";

    String PingProviders ="{ \"pingProviders\": true, \"serviceDefinitionRequirement\": \"indoortemperature\"}";

    String UnregisterService = "address=192.168.0.2&port=8080&service_definition=indoortemperature&system_name=NOTEXIST";
    String registerService = "address=192.168.0.2&port=8080&service_definition=indoortemperature&system_name=";

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLACK = "\u001B[30m";



}
