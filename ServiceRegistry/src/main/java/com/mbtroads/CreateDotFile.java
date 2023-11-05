package com.mbtroads;

import org.graphwalker.java.test.Result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CreateDotFile implements ISystemProperties {
    public static JSONParser jsonParser = new JSONParser();
    public static Object object;
    public static String[] targetVertex = new String[30];
    public static String[] sourceVertex = new String[30];
    public static String[] targetVertexName = new String[30];
    public static String[] sourceVertexName = new String[30];
    public static String[] failedVertexName = new String[30];
    public static String[] failedEdgeName = new String[30];


    public static String[] name = new String[30];
    public static String[] EdgeColor = new String[30];


    public static String[] VertexID = new String[30];
    public static String[] VertexName = new String[30];
    public static int countEdges = 0;
    public static int countVertx = 0;
    public static int countVertxFailed = 0;
    public static int countEdgeFailed = 0;






    int flag = 0;

    public static void ReadFile(String filename,int failtest,String strMain ) throws IOException {

        try {

            object = jsonParser.parse(new FileReader(currentDir + pathSeperator + "src" + pathSeperator + "main" + pathSeperator + "resources" + pathSeperator + "com" + pathSeperator + "mbtroads" + pathSeperator + filename + ".json"));

            JSONObject jsonObject = (JSONObject) object;

            JSONArray models = (JSONArray) jsonObject.get("models");
            ////// Find edges
            for (Object projectObj : models.toArray()) {
                JSONObject project = (JSONObject) projectObj;
                JSONArray edgesList = (JSONArray) project.get("edges");
                for (Object edgesObj : edgesList.toArray()) {
                    JSONObject edges = (JSONObject) edgesObj;
                    String targetVertexId = (String) edges.get("targetVertexId");
                    String sourceVertexId = (String) edges.get("sourceVertexId");
                    String nameid = (String) edges.get("name");

                    targetVertex[countEdges] = targetVertexId;
                    sourceVertex[countEdges] = sourceVertexId;
                    name[countEdges] = nameid;
                    countEdges = countEdges + 1;
                }
                // vertex
                System.out.println("\t>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  vertex <<<<<<<<<<<<<<<<<< ");
                JSONArray vertexList = (JSONArray) project.get("vertices");

                for (Object vertexObj : vertexList.toArray()) {
                    JSONObject vertex = (JSONObject) vertexObj;
                    String vertexId = (String) vertex.get("id");
                    String vertexName = (String) vertex.get("name");

                    VertexID[countVertx] = vertexId;
                    VertexName[countVertx] = vertexName;
                    countVertx = countVertx + 1;

                }
            }
            ///// replac id with name

            for (int i = 0; i < countEdges; i++) {
                for (int j = 0; j < countVertx; j++) {
                    if (targetVertex[i].equals(VertexID[j])) {
                        targetVertexName[i] = VertexName[j];
                        break;
                    }

                }

                for (int j = 0; j <= countVertx; j++) {

                    if (sourceVertex[i].equals(VertexID[j])) {
                        sourceVertexName[i] = VertexName[j];
                        break;
                    }
                }
            }

            if(failtest==1) {
                CreateFailNodes(strMain);
            }
            writeDot(filename);
            Createpng(filename);
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
    }


    static void writeDot(String filename) throws IOException {
        try {
            FileWriter myWriter = new FileWriter(currentDir + pathSeperator + "Drivers" + pathSeperator + filename + ".dot");

            myWriter.write("digraph OrchestratorStore{\n");
            myWriter.write("rankdir=TB\n");
            myWriter.write(" label=\"" + filename +"\"\n");
            myWriter.write("labelloc=top\n");
            myWriter.write("labeljust=left\n");
            myWriter.write("fontcolor = blue\n");
            myWriter.write("node [style=filled] \n");
            myWriter.write(" fontsize=30\n");


            if(countVertxFailed>0) {
                for (int k =0; k<countVertxFailed;k++)
                {
                    myWriter.write("\"" + failedVertexName[k] + "\"  [fillcolor=red]\n");

                }

            }
            for (int j = countEdges - 1; j >= 0; j--)
            {
                EdgeColor[j]="black";
            }

                if(countEdgeFailed>0) {
                for (int j = countEdges - 1; j >= 0; j--) {
                   for (int k = 0; k < countEdgeFailed; k++) {
                        if(name[j].trim().equals(failedEdgeName[k].trim()))
                        {
                            EdgeColor[j]="red";
                            break;
                        }
                    }
                }




            }



            for (int k = countEdges - 1; k >= 0; k--) {
                myWriter.write("\"" + sourceVertexName[k] + "\" -> \"" + targetVertexName[k] + "\"  [ label=\"" + name[k] + "\"color=\""+ EdgeColor[k]+"\"]\n");
            }
            myWriter.write("}");

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }


    public  static void Createpng(String filename)  {
        try{
            if (OS.contains("Windows")) {
                Process p = Runtime.getRuntime().exec(currentDir + pathSeperator + "Drivers" + pathSeperator + filename + ".bat");
                p.waitFor();
            }
            else
            {

                System.out.println(currentDir + pathSeperator + "Drivers" + pathSeperator + filename);
                Process p = Runtime.getRuntime().exec("sh "+ currentDir + pathSeperator + "Drivers" + pathSeperator + filename + ".sh");
                p.waitFor();

            }
        }catch( IOException e ){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }catch( InterruptedException e ){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public  static void CreateFailNodes(String strMain )
    {
        String[] arrSplit = strMain.split(",");
        int i = 1;
        String temp, vertxname, edgename;
        while (i < arrSplit.length)
        {
            if (arrSplit[i].contains(("vertexName")))
            {
                temp = arrSplit[i];
                vertxname = temp.substring(temp.indexOf(':') + 2);
                vertxname=vertxname.replace("\"","");
                failedVertexName[countVertxFailed]=vertxname;
                System.out.println("VERTEX >>>>>>>>>>>>>>>>>>>> "+ failedVertexName[countVertxFailed]);
                i++;
                countVertxFailed++;
            }
          else  if (arrSplit[i].contains(("edgeId")))
            {
                temp = arrSplit[i+1];
                edgename = temp.substring(temp.indexOf(':') + 2);
                edgename=edgename.replace("\"","");
                edgename=edgename.replace("}","");
                edgename=edgename.replace("]","");


                failedEdgeName[countEdgeFailed]=edgename;
                System.out.println("Edge >>>>>>>>>>>>>>>>>>>> "+ failedEdgeName[countEdgeFailed]);
                i++;
                countEdgeFailed++;
            }
              else
                System.out.println(arrSplit[i]);

            i++;
            //System.out.println(arrSplit[i]);
        }



    }



}