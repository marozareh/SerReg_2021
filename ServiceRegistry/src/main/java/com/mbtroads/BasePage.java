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
        while (i < arrSplit.length)

     //   for (int i=1; i < arrSplit.length; i++)
        {
            if (result ==1)
                ExtentReport.node.pass(arrSplit[i]);
            else
                if (arrSplit[i].contains(("vertexName")))
                {
                    temp = arrSplit[i] + " , "+ arrSplit[i+1];
                    ExtentReport.node.fail(temp);
                    i++;
                }
                else  if (arrSplit[i].contains(("edgeId")))
                {
                    temp = arrSplit[i] + " , "+ arrSplit[i+1];
                    ExtentReport.node.fail(temp);
                    i++;
                }
                else
                    ExtentReport.node.fail(arrSplit[i]);

            i++;
            //System.out.println(arrSplit[i]);
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
            ExtentReport.node.pass("Assertion Success for Field don ot Contain: "+ expectedValue);


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
