package com.mbtroads;

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
    public static String[] name = new String[30];

    public static String[] VertexID = new String[30];
    public static String[] VertexName = new String[30];
    public static int countEdges = 0;
    public static int countVertx = 0;

    int flag = 0;

    public static void ReadFile(String filename) throws IOException {

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
            myWriter.write("node [style=filled] \n");
            for (int k = countEdges - 1; k >= 0; k--) {
                myWriter.write("\"" + sourceVertexName[k] + "\" -> \"" + targetVertexName[k] + "\"  [ label=\"" + name[k] + "\" ]\n");
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
            Process p = Runtime.getRuntime().exec(currentDir + pathSeperator + "Drivers" + pathSeperator + filename + ".bat");
            p.waitFor();

        }catch( IOException e ){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }catch( InterruptedException e ){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}