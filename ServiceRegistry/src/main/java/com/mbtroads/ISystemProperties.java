package com.mbtroads;

import java.net.InetAddress;

  public interface ISystemProperties {
        String machineName = getMachineName();
        String OS = System.getProperty("os.name");
        String currentDir = System.getProperty("user.dir");
        String pathSeperator = OS.contains("Mac") ? "/" : "\\";
        String env = System.getProperty("SuiteEnv");
        String testCaseName = System.getProperty("test");
        String pipelineName = String.valueOf(System.getProperty("Pipeline"));

        static String getMachineName() {
            try
            {
                InetAddress addr;
                addr = InetAddress.getLocalHost();
                return addr.getHostName().toUpperCase();
            }
            catch (Exception ex) {
                throw new CustomException(ex);
            }
        }

    }