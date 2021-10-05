import com.mbtroads.*;
import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.TestExecutor;
import org.graphwalker.websocket.WebSocketServer;
import org.junit.Test;

import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.substringAfter;


// mvn -q clean test -Dtest=Test_ServiceREgistery

public class Test_ServiceQuery extends BaseClass {

    @Test
    public void testExecutor() throws IOException, InterruptedException {
        TestExecutor executor = new TestExecutor(
                QueryService.class
        );

        int failtest=0;

        Result result = executor.execute(true);
        if (result.hasErrors()) {
            StrSplit(result.getResults().toString(2),0);
            failtest=1;
            CreateDotFile.ReadFile("QueryService", failtest, result.getResults().toString(2) );
            ExtentReport.reportError();
        }
        else {
            StrSplit(result.getResults().toString(2),1);
            CreateDotFile.ReadFile("QueryService", failtest, result.getResults().toString(2) );
        }

    }


//mvn -q clean graphwalker:generate-sources compile test -Dtest=Test_ServiceQuery






}
