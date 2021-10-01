
import com.mbtroads.CreateDotFile;
import com.mbtroads.SomeSmallTest;
import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.TestExecutor;
import org.junit.Test;
import java.io.IOException;
import com.mbtroads.BaseClass;
import com.mbtroads.ExtentReport;
import org.graphwalker.websocket.WebSocketServer;

import static org.apache.commons.lang3.StringUtils.substringAfter;


// mvn -q clean test -Dtest=Test_ServiceREgistery

public class Test_ServiceREgistery extends BaseClass {

    @Test
    public void testExecutor() throws IOException, InterruptedException {
        TestExecutor executor = new TestExecutor(
                SomeSmallTest.class);


       CreateDotFile.ReadFile("ServiceRegistry");

        Result result = executor.execute(true);
        if (result.hasErrors()) {
            for (String error : result.getErrors()) {
             //   System.out.println(error);
                ExtentReport.createAndGetNodeInstance("GraphWalker Result FAIL");
                ExtentReport.node.fail("Error in the Model");


            }
           // System.out.println("Done: [" + result.getResults().toString(2) + "]");
            ExtentReport.createAndGetNodeInstance("GraphWalker Result Summery");
            StrSplit(result.getResults().toString(2),0);

//            ExtentReport.node.fail("Done: [" + result.getResults().toString(5) + "]");
  //          ExtentReport.createAndGetNodeInstance("GraphWalker Result");
    //        ExtentReport.node.fail("Graphwalker Result: [" + substringAfter(result.getResults().toString(5), "more\\n\"}],") + "]");

            // ExtentReport.createAndGetNodeInstance("GraphWalker Summery");
            //ExtentReport.node.fail("Graphwalker Result: [vertexCoverage " + substringAfter(result.getResults().toString(5), "vertexCoverage") + "]");
            //ExtentReport.createAndGetNodeInstance("Service Registry Graph Coverage");
      //      ExtentReport.reportError();
             ExtentReport.reportError();

        }
        else {
           // System.out.println("Done: [" + result.getResults().toString(2) + "]");
            ExtentReport.createAndGetNodeInstance("GraphWalker Result PASS");
            //ExtentReport.node.pass("Done: [" + result.getResults().toString(5) + "]");
            StrSplit(result.getResults().toString(2),1);
           // ExtentReport.reportImage();
        }
    }


//mvn -q clean graphwalker:generate-sources compile test -Dtest=Test_ServiceREgistery




}
