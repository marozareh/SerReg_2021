import com.mbtroads.BaseClass;
import com.mbtroads.ExtentReport;
import com.mbtroads.CreateDotFile;

import com.mbtroads.OrchStore;
import com.mbtroads.QueryService;
import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.TestExecutor;
import org.junit.Test;

import java.io.IOException;


// mvn -q clean test -Dtest=Test_ServiceREgistery

public class Test_OrchestrationStore extends BaseClass {

    @Test
    public void testExecutor() throws IOException, InterruptedException {
        TestExecutor executor = new TestExecutor(
                OrchStore.class
        );
        CreateDotFile.ReadFile("OrchestratorStore");
      //  CreateDotFile.Createpng("OrchestratorStore");

        Result result = executor.execute(true);
        if (result.hasErrors()) {
            for (String error : result.getErrors()) {
                System.out.println(error);
                ExtentReport.createAndGetNodeInstance("GraphWalker Result FAIL");
                ExtentReport.node.fail("Error in the Model");


            }
            ExtentReport.createAndGetNodeInstance("GraphWalker Result Summery");
            StrSplit(result.getResults().toString(2),0);
            ExtentReport.reportError();

        }
        else {
            ExtentReport.createAndGetNodeInstance("GraphWalker Result PASS");
            StrSplit(result.getResults().toString(2),1);
        }

    }


//mvn -q clean graphwalker:generate-sources compile test -Dtest=Test_OrchestrationStore




}
