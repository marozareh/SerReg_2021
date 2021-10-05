
import com.mbtroads.CreateDotFile;
import com.mbtroads.SomeSmallTest;
import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.TestExecutor;
import org.junit.Test;
import java.io.IOException;
import com.mbtroads.BaseClass;
import com.mbtroads.ExtentReport;


// mvn -q clean test -Dtest=Test_ServiceREgistery

public class Test_ServiceREgistery extends BaseClass {

    @Test
    public void testExecutor() throws IOException, InterruptedException {
        TestExecutor executor = new TestExecutor(
                SomeSmallTest.class);


        Result result = executor.execute(true);
        int failtest=0;
        if (result.hasErrors()) {

         //   ExtentReport.createAndGetNodeInstance("GraphWalker Result Summery");
            //ExtentReport.node.fail("Error in the Model");
            StrSplit(result.getResults().toString(2),0);
            failtest=1;
            CreateDotFile.ReadFile("ServiceRegistry", failtest, result.getResults().toString(2) );

            ExtentReport.reportError();



        }
        else {
        //    ExtentReport.createAndGetNodeInstance("GraphWalker Result PASS");
            StrSplit(result.getResults().toString(2),1);
            CreateDotFile.ReadFile("ServiceRegistry", failtest, result.getResults().toString(2) );

        }


    }


//mvn -q clean graphwalker:generate-sources compile test -Dtest=Test_ServiceREgistery




}
