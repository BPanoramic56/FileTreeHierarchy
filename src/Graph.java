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
            else{
                createEdge(sourceFolder, entry);
            }
        }
        return graphVertices;
    }

    public static long fileCount(File sourceFolderInit, long count){
        return graphVertices.size();
    }

    public static void writeDot() throws IOException{
        try(BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("g.dot")))){
            out.write("digraph {"); 
            out.newLine();

            Vertex<File> sourceVertex = graphVertices.get(currentFolder);

            for(Edge<File> e: sourceVertex.getEdges()){
                out.write(sourceVertex +" -> "+ e.getDestination());
                out.newLine();
            }
            out.write("}");
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