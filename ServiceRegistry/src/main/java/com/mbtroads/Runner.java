package com.mbtroads;

import com.mbtroads.SomeSmallTest;
import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.TestExecutor;
import org.graphwalker.websocket.WebSocketServer;

import java.io.IOException;

public class Runner  extends BaseClass{
  public static void main(String[] args) throws IOException, InterruptedException {
    TestExecutor executor = new TestExecutor(
      SomeSmallTest.class
    );

    //WebSocketServer server = new WebSocketServer(8887, executor.getMachine());
    //server.start();

    //ExtentReport.createAndGetNodeInstance("GraphWalker Traverse");
    //driver.get("file:///users/marokrikoor/Desktop/ProjectFinal/lib/index.html?wsURI=localhost:8887");


    Result result = executor.execute(true);

    System.out.println("Done: [" + result.getResults().toString(2) + "]");


    ExtentReport.createAndGetNodeInstance("GraphWalker Result");
    ExtentReport.node.info("Done: [" + result.getResults().toString(5) + "]");
    //StrSplit(result.getResults().toString(2));
    //ExtentReport.reportImage();
    //delayInMillis(5000);
    //server.stop();


  }
}
