package com.mbtroads;


import java.io.*;
import java.util.Iterator;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import guru.nidi.graphviz.*;

public class ReadJSONExample implements ISystemProperties  {
    public static JSONParser jsonParser = new JSONParser();
    public static Object object;
    public static String[] targetVertex=new String[30];
    public static String[] sourceVertex=new String[30];
    public static String[] targetVertexName=new String[30];
    public static String[] sourceVertexName=new String[30];
    public static String[] name=new String[30];

    public static String[] VertexID=new String[30];
    public static String[] VertexName=new String[30];
    public static int countEdges = 0;
    public static int countVertx = 0;

    int flag = 0;

    public static void main(String[] args) throws IOException {





        try {

            //object = jsonParser.parse(new FileReader("D:/ArrowheadTest_Graphwalker/ServiceRegistry/src/main/resources/com/mbtroads/OrchestratorStore.json"));
            object = jsonParser.parse(new FileReader(currentDir + pathSeperator + "ServiceRegistry"+ pathSeperator +"src"+ pathSeperator +"main"+pathSeperator +"resources"+pathSeperator +"com"+pathSeperator +"mbtroads"+pathSeperator +"OrchestratorStore.json"));
            JSONObject jsonObject = (JSONObject) object;

            JSONArray models = (JSONArray) jsonObject.get("models");


            System.out.println("\tmodels: " + models.get(0));
            ////// Find edges
            for(Object projectObj: models.toArray()){
                JSONObject project = (JSONObject)projectObj;
                JSONArray edgesList = (JSONArray) project.get("edges");
                for(Object edgesObj: edgesList.toArray())
                {
                    JSONObject edges = (JSONObject) edgesObj;
                    String targetVertexId = (String) edges.get("targetVertexId");
                    String sourceVertexId = (String) edges.get("sourceVertexId");
                    String nameid = (String) edges.get("name");

                     targetVertex[countEdges]=targetVertexId;
                     sourceVertex[countEdges]=sourceVertexId;
                     name[countEdges]=nameid;



                    //  System.out.println("\tedges: " + edges);
                     System.out.println("\ttargetVertexId: " +  targetVertex[countEdges]);
                     System.out.println("\tsourceVertexId: " + sourceVertex[countEdges]);
                      System.out.println("\tnameid: " +  name[countEdges]);
                    countEdges=countEdges+1;


                }
                System.out.println(countEdges);
                // vertex
                System.out.println("\t>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  vertex <<<<<<<<<<<<<<<<<< ");
                JSONArray vertexList = (JSONArray) project.get("vertices");

                for(Object vertexObj: vertexList.toArray()){
                    JSONObject vertex = (JSONObject) vertexObj;
                    String vertexId = (String) vertex.get("id");
                    String vertexName = (String) vertex.get("name");

                    VertexID[countVertx]= vertexId;
                    VertexName[countVertx]= vertexName;



                    //  System.out.println("\tedges: " + vertex);
                     System.out.println("\tvertexId: " +  VertexID[countVertx]);
                     System.out.println("\tvertexName: " + VertexName[countVertx]);

                    countVertx=countVertx+1;

                }
            }

            System.out.println(countVertx);


            ///// replac id with name

            for (int i=0; i<countEdges; i++)
            {
                for (int j=0; j<countVertx; j++)
                {
                    if (targetVertex[i].equals(VertexID[j])) {
                        targetVertexName[i]=VertexName[j];
                        break;
                    }

                }




                for (int j=0; j<=countVertx; j++)
                {

                      if (sourceVertex[i].equals(VertexID[j])) {
                       sourceVertexName[i]=VertexName[j];
                          break;
                     }



                }


            }



            System.out.println(countEdges);

            for (int k=0; k<countEdges; k++)
            {
               System.out.println("\ttargetVertexId: " + targetVertex[k]);
                System.out.println("\tsourceVertexId: " + sourceVertex[k]);
                System.out.println("\ttargetVertexName: " + targetVertexName[k]);
                System.out.println("\tsourceVertexName: " + sourceVertexName[k]);
                System.out.println("\tnameEdge: " + name[k]);
                System.out.println("\t==========================");
            }

            writeDot();
         Createpng("OrchestratorStore");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



    static void writeDot() throws IOException {




        try {
           // FileWriter myWriter = new FileWriter("D:/ArrowheadTest_Graphwalker/lib/OrchestratorStore.dot");

            FileWriter myWriter = new FileWriter(currentDir + pathSeperator + "ServiceRegistry"+ pathSeperator + "Drivers" + pathSeperator + "OrchestratorStore.dot");
                    myWriter.write("digraph OrchestratorStore{\n");
            myWriter.write("rankdir=TB\n");
            myWriter.write("node [style=filled] \n");

           // for (int k=0; k<countEdges,; k++)
            for (int k=countEdges-1;k>=0; k--)

                {
                myWriter.write("\"" + sourceVertexName[k] + "\" -> \"" + targetVertexName[k]  + "\"  [ label=\"" + name[k] + "\" ]\n");
            }
            myWriter.write("}");

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }




    }



    public  static void Createpng(String filename)  {
        //try {
           /* Runtime rt = Runtime.getRuntime();
            String dotPath=currentDir + pathSeperator  + "ServiceRegistry"+ pathSeperator +"Drivers" + pathSeperator + filename + ".dot";
            File myfile = new File(dotPath);

            String pngPath = currentDir + pathSeperator + "ServiceRegistry"+ pathSeperator + "Drivers" + pathSeperator + filename + ".png";
            File distfile = new File(pngPath);

            Graphviz.fromFile(myfile).render(Format.PNG).toFile(distfile);*/
        System.out.println("I am in bach.");

       // ProcessBuilder processBuilder = new ProcessBuilder();
         //   processBuilder.command("D:\\ArrowheadTest_Graphwalker\\ServiceRegistry\\Drivers\\OrchestratorStore.bat");

        //} catch (IOException e) {
          //  System.out.println("An error occurred.");
            //e.printStackTrace();
      //  }



        try{
            Process p = Runtime.getRuntime().exec("D:\\ArrowheadTest_Graphwalker\\ServiceRegistry\\Drivers\\OrchestratorStore.bat");
            p.waitFor();

        }catch( IOException ex ){
            //Validate the case the file can't be accesed (not enought permissions)

        }catch( InterruptedException ex ){
            //Validate the case the process is being stopped by some external situation

        }
    }

}

