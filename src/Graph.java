package src;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Graph {

    private static Map<File, Vertex<File>> graphVertices;
    private static File currentFolder;

    public Graph(File f){

        if (f == currentFolder){
            System.out.println("Current map is already made from the given file");
            return;
        }
        
        if (f.isDirectory())
            currentFolder = f;

        graphVertices = new HashMap<File, Vertex<File>>();
        createHierarchy(f);
    }

    public static int size(){
        return graphVertices.size();
    }

    public static Map<File, Vertex<File>> createHierarchy(File sourceFolder){
        if (sourceFolder.listFiles() == null){
            return null;
        }

        for (File entry : sourceFolder.listFiles()){
            if (entry.isDirectory()){
                createEdge(sourceFolder, entry);
                createHierarchy(entry);
            }
            else
                createEdge(sourceFolder, entry);
        }
        return graphVertices;
    }

    public static long fileCount(File sourceFolderInit, long count){
        return graphVertices.size();
    }

    public static void writeDot(String graphName) throws IOException{
        try(BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(graphName + ".dot")))){
            out.write("digraph " + graphName + "{"); 
            out.newLine();

            Vertex<File> sourceVertex = graphVertices.get(currentFolder);

            writeDotRecursive(sourceVertex, out);

            out.write("}");
        }
    }

    public static void writeDotRecursive(Vertex<File> currenVertex, BufferedWriter out){
        System.out.println(currenVertex.getEdges().size());
        try{
            for(Edge<File> e: currenVertex.getEdges()){
                System.out.println(e.getDestination().getData().toString());
               
                String[] fullNameDest = e.getDestination().getData().toString().split("/");
                String[] fullNameSrc = currenVertex.getData().toString().split("/");
                String actualNameDest = fullNameDest[0];
                String actualNameSrc = fullNameDest[0];

                if (fullNameDest.length == 1)
                    actualNameDest = fullNameDest[0];
                else{
                    actualNameDest = fullNameDest[fullNameDest.length - 2] + "/" + fullNameDest[fullNameDest.length - 1];
                }

                if (fullNameSrc.length == 1)
                    actualNameSrc = fullNameSrc[0];
                else{
                    actualNameSrc = fullNameSrc[fullNameDest.length - 2] + "/" + fullNameSrc[fullNameSrc.length - 1];
                }


                out.write("\t\"" + actualNameSrc + "\"" + " -> " + "\"" + actualNameDest + "\";");
                out.newLine();
                if (e.getDestination().getEdges().size() != 0)
                    writeDotRecursive(e.getDestination(), out);
            }
        }
        catch (Exception e){
            System.out.println("Excpetion Reached: " + e);
        }
    }

    private static void addVertex(File vertexData){
        graphVertices.put(vertexData, new Vertex<File>(vertexData));
    } 
    
    private static void createEdge(File vertex1Data, File vertex2Data){
        Vertex<File> vertex1 = graphVertices.get(vertex1Data);
        Vertex<File> vertex2 = graphVertices.get(vertex2Data);

        if (vertex1 == null)
            addVertex(vertex1Data);
        
        if (vertex2 == null)
            addVertex(vertex2Data);

        vertex1 = graphVertices.get(vertex1Data);
        vertex2 = graphVertices.get(vertex2Data);

        vertex1.addEdge(vertex2);
    }
}