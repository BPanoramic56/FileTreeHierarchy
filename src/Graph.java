package src;

import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class Graph {

    private static Map<File, Vertex<File>> graphVertices = new HashMap<File, Vertex<File>>();
    private static File sourceFolder;


    public Graph(){
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
        graphVertices = new HashMap<File, Vertex<File>>();
        sourceFolder = sourceFolderInit;
        return fileCountRecursive(sourceFolder, count);
    }

    private static long fileCountRecursive(File sourceFolder, long count){

        if (sourceFolder.listFiles() == null){
            return 0;
        }

        for (File entry : sourceFolder.listFiles()){
            if (entry.isDirectory()){
                createEdge(sourceFolder, entry);
                count = fileCountRecursive(entry, count);
            }
            else{
                count++;
                createEdge(sourceFolder, entry);
            }
        }
        return count;
    }
    
    public static long directoryCount(File sourceFolderInit, long count){
        graphVertices = new HashMap<File, Vertex<File>>();
        sourceFolder = sourceFolderInit;
        return directoryCountRecursive(sourceFolder, count);
    }

    private static long directoryCountRecursive(File sourceFolder, long count){

        if (sourceFolder.listFiles() == null){
            return 0;
        }

         for (File entry : sourceFolder.listFiles()){
            if (entry.isDirectory()){
                createEdge(sourceFolder, entry);
                count = directoryCountRecursive(entry, count) + 1;
            }
        }
        return count;
    }

    public static long FileFolderAverage(){
        if (graphVertices == null || sourceFolder == null){
            return 0;
        }

        Vertex<File> sourceVertex = graphVertices.get(sourceFolder);

        return FileFolderAverageRecursive(sourceVertex, 0, 0);

    }

    private static long FileFolderAverageRecursive(Vertex<File> currentVert, long fileCount, long directoryCount){

        for (Edge<File> edge : currentVert.getEdges()){

            Vertex<File> destination = edge.getDestination();

            if (destination.getData().isDirectory()){
                directoryCount = FileFolderAverageRecursive(destination, fileCount, directoryCount) + 1;
            }
            else{
                fileCount++;
            }

        }

        return fileCount/directoryCount;

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