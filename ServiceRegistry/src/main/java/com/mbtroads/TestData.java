package com.mbtroads;

import org.apache.http.HttpResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
public interface TestData {

    String InvaledServiceRegistery = "{ \"providerSystem\": { \"systemName\": \"InsecureTemperatureSensor\", \"address\": \"192.168.0.2\", \"port\": 8080, \"authenticationInfo\": \"eyJhbGciOiJIUzI1Ni...\"}, \"serviceUri\": \"temperature\", \"endOfValidity\": \"2019-12-05 12:00:00\", \"secure\": \"NOT_SECURE\", \"metadata\": { \"unit\": \"celsius\"}, \"version\": 1, \"interfaces\": [ \"HTTP-INSECURE-JSON\" ]}";
    String ValidServiceRegistery_Payload = "{ \"serviceDefinition\": \"IndoorTemperature\", \"providerSystem\": { \"systemName\": \"InsecureTemperatureSensor\", \"address\": \"192.168.0.2\", \"port\": 8080, \"authenticationInfo\": \"eyJhbGciOiJIUzI1Ni...\"}, \"serviceUri\": \"temperature\", \"endOfValidity\": \"2019-12-05 12:00:00\", \"secure\": \"NOT_SECURE\", \"metadata\": { \"unit\": \"celsius\"}, \"version\": 1, \"interfaces\": [ \"HTTP-INSECURE-JSON\" ]}";

    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss");
    String strDate = dateFormat.format(date);
    String NewService = "{ \"serviceDefinition\": \"IndoorTemperature\", \"providerSystem\": { \"systemName\": \"InsecureTemperatureSensor_"+ strDate + "\",\"address\": \"192.168.0.2\", \"port\": 8080, \"authenticationInfo\": \"eyJhbGciOiJIUzI1Ni...\"}, \"serviceUri\": \"temperature\", \"endOfValidity\": \"2019-12-05 12:00:00\", \"secure\": \"NOT_SECURE\", \"metadata\": { \"unit\": \"celsius\"}, \"version\": 1, \"interfaces\": [ \"HTTP-INSECURE-JSON\" ]}";
    String EXISTService = "{ \"serviceDefinition\": \"IndoorTemperature\", \"providerSystem\": { \"systemName\": \"InsecureTemperatureSensor\", \"address\": \"192.168.0.2\", \"port\": 8080, \"authenticationInfo\": \"eyJhbGciOiJIUzI1Ni...\"}, \"serviceUri\": \"temperature\", \"endOfValidity\": \"2019-12-05 12:00:00\", \"secure\": \"NOT_SECURE\", \"metadata\": { \"unit\": \"celsius\"}, \"version\": 1, \"interfaces\": [ \"HTTP-INSECURE-JSON\" ]}";



    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLACK = "\u001B[30m";
}
