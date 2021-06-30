package com.mbtroads;

import org.graphwalker.core.machine.ExecutionContext;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

public class BasePage extends ExecutionContext {

    public void  StrSplit(String strMain)
    {
        String[] arrSplit = strMain.split(",");
        for (int i=0; i < arrSplit.length; i++)
        {
            System.out.println(arrSplit[i]);
        }
    }


    public void assestEqual(String expectedvValue, String actualValue)
    {
        try
        {

            assertEquals(expectedvValue , actualValue);
            ExtentReport.node.pass("Assertion Success Equal for Field: "+ expectedvValue);

        }catch(Error e)
        {
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


        }catch(Error e)
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


        }catch(Error e)
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
