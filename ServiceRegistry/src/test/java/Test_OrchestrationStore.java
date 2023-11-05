import com.mbtroads.BaseClass;
import com.mbtroads.ExtentReport;
import com.mbtroads.CreateDotFile;

import com.mbtroads.OrchStore;
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
        int failtest=0;
        Result result = executor.execute(true);
        if (result.hasErrors()) {
            StrSplit(result.getResults().toString(2),0);
            failtest=1;
            CreateDotFile.ReadFile("OrchestratorStore", failtest, result.getResults().toString(2) );
             //ExtentReport.reportError();

        }
        else {
            StrSplit(result.getResults().toString(2),1);
            CreateDotFile.ReadFile("OrchestratorStore", failtest, result.getResults().toString(2) );

        }

    }


//mvn -q clean graphwalker:generate-sources compile test -Dtest=Test_OrchestrationStore




}
