package com.mbtroads;

import org.graphwalker.core.machine.ExecutionContext;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

public class BasePage extends ExecutionContext {

    public void  StrSplit(String strMain,int result)
    {
        String[] arrSplit = strMain.split(",");
        int i = 1;
        String temp;
        int firstVertex = 0;
        int firstEdge= 0;
        int textCount=13;
       String[] summeryReport = new String[30];
        String[] summeryTest=new String[] {
                "totalNumberOfModels",
                "totalFailedNumberOfModels",
                "totalIncompleteNumberOfModels",
                "totalCompletedNumberOfModels",
                "totalNotExecutedNumberOfModels",
                "totalNumberOfEdges",
                "totalNumberOfVisitedEdges",
                "totalNumberOfUnvisitedEdges",
                "edgeCoverage",
                "totalNumberOfVertices",
                "totalNumberOfVisitedVertices",
                "totalNumberOfUnvisitedVertices",
                "vertexCoverage",
                };
       int summeryReportCount=0;

        if (result ==1)
        {
            ExtentReport.createAndGetNodeInstance("GraphWalker Result PASS");

        }

            else
        {
            ExtentReport.createAndGetNodeInstance("GraphWalker Result Summery");
            ExtentReport.node.fail("Error in the Model");
        }

        while (i < arrSplit.length)
        {
            if (result ==1)
                ExtentReport.node.pass(arrSplit[i]);
            else
                if (arrSplit[i].contains(("vertexName")))
                {
                    if(firstVertex==0)
                    {
                        ExtentReport.node.fail("=============> Failed Vercies <=============");
                        firstVertex = 1;
                    }
                //    temp = arrSplit[i] + " , "+ arrSplit[i+1];
                    temp = arrSplit[i];
                    temp =temp.replace("}","");
                    temp =temp.replace("]","");

                    ExtentReport.node.fail(temp);
                    i++;
                }
                else  if (arrSplit[i].contains(("edgeId")))
                {
                    if(firstEdge==0)
                    {
                        ExtentReport.node.fail("=============> Failed Edges <=============");
                        firstEdge = 1;
                    }
                  //  temp = arrSplit[i] + " , "+ arrSplit[i+1];
                    temp = arrSplit[i+1];

                    temp =temp.replace("}","");
                    temp =temp.replace("]","");
                   ExtentReport.node.fail(temp);
                    i++;
                }
                else  if (!arrSplit[i].contains(("modelName"))) {

                    summeryReport[summeryReportCount] = arrSplit[i];
                    summeryReportCount++;
                  // ExtentReport.node.fail(arrSplit[i]);
                }

            i++;
        }

        if (result ==0) {
            ExtentReport.node.fail("=============> Failed Summery <=============");

            for (int j = 0; j < summeryReportCount; j++) {
                summeryReport[j] = summeryReport[j].replace("\"", "");
                summeryReport[j] = summeryReport[j].replace("}", "");
            }

            for (int k = 0; k < textCount; k++) {

                for (int j = 0; j < summeryReportCount; j++) {

                    if (summeryReport[j].trim().contains(summeryTest[k].trim())) {
                        ExtentReport.node.fail(summeryReport[j]);
                        break;

                    }

                }
            }
        }
        }


    public void assestEqual(String expectedvValue, String actualValue)
    {
        try
        {

            assertEquals(expectedvValue , actualValue);
            ExtentReport.node.pass("Assertion Success Equal for Field: "+ expectedvValue);

        }catch(AssertionError  e)
        {
           // ExtentReport.reportError(e);
            ExtentReport.node.fail("Assertion Failed for Equal : "+expectedvValue);
            throw new CustomException("Expected Value: "+expectedvValue+" Actual Value: "+actualValue);
        }
    }



    public void assestContains(String expectedvValue, String actualValue)
    {


        try
        {
            Assert.assertTrue(actualValue.contains(expectedvValue));
            ExtentReport.node.pass("Assertion Success for Field Contains: "+ expectedvValue);


        }catch(AssertionError e)
        {
             ExtentReport.node.fail("Assertion Failed for Contain: " + expectedvValue);
             throw new CustomException("Expected Value: "+ expectedvValue + " Actual Value: "+actualValue);

        }
    }



    public void assestdonotContains(String expectedValue, String actualValue)
    {
        try
        {
            Assert.assertFalse(actualValue.contains(expectedValue));
            ExtentReport.node.pass("Assertion Success for Field don not Contain: "+ expectedValue);


        }catch(AssertionError e)
        {
            ExtentReport.node.fail("Assertion Failed for do not Contain: "+ expectedValue);
            throw new CustomException("Expected Value: "+expectedValue+" Actual Value: "+actualValue);
        }
    }


    public void infoReport(String info)
    {
        ExtentReport.node.info(info);

    }


    public void extendReport(String info)
    {
        ExtentReport.createAndGetNodeInstance(info);

}

}
