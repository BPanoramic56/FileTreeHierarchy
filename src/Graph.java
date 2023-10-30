package src;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.math.BigInteger;

public class Graph {

    private static Map<File, Vertex<File>> graphVertices = new HashMap<File, Vertex<File>>();

    public Graph(){
    }

    public static Map<File, Vertex<File>> createHierarchy(File sourceFolder){

        if (sourceFolder.listFiles() == null){
            return null;
        }

        for (File entry : sourceFolder.listFiles()){
            // try{
            //     Thread.sleep(100);
            // }
            // catch (Exception InterruptedException){
            //     ;
            // }
            if (entry.isDirectory()){

                // System.out.println(entry.getName());
                createEdge(sourceFolder, entry);
                createHierarchy(entry);
            }
            else{
                // System.out.println("\t" + entry.getName());
                createEdge(sourceFolder, entry);
            }
        }
        return graphVertices;
    }

    public static BigInteger fileCount(File sourceFolder, BigInteger count){

        if (sourceFolder.listFiles() == null){
            return BigInteger.ZERO;
        }
        System.out.println(count.toString());
        for (File entry : sourceFolder.listFiles()){
            // try{
            //     Thread.sleep(100);
            // }
            // catch (Exception InterruptedException){
            //     ;
            // }
            if (entry.isDirectory()){
                // System.out.println(entry.getName());
                createEdge(sourceFolder, entry);
                count.add(fileCount(entry, count));
            }
            else{
                // System.out.println("\t" + entry.getName());
                count.add(BigInteger.ONE);
                createEdge(sourceFolder, entry);
            }
        }
        return count;
    }
    
    public static int directoryCount(File sourceFolder, int count){

        if (sourceFolder.listFiles() == null){
            return count;
        }

        for (File entry : sourceFolder.listFiles()){
            // try{
            //     Thread.sleep(100);
            // }
            // catch (Exception InterruptedException){
            //     ;
            // }
            if (entry.isDirectory()){
                count = directoryCount(entry, count) + 1;
            }
        }
        return count;
    }

    public static String toString(File sourceFolder){
		StringBuilder finalString = new StringBuilder();

        // if (sourceFolder.listFiles() == null){
        //     return "";
        // }

        // for (File entry : sourceFolder.listFiles()){
        //     if (entry.isDirectory()){
        //         createEdge(sourceFolder, entry);
        //         finalString.append("\n" + entry.getName() + "\n\t");
        //         createHierarchy(entry);
        //     }
        //     else{
        //         createEdge(sourceFolder, entry);
        //         finalString.append(entry.getName() + "\n");
        //     }
        // }

        return finalString.toString();
    }

    private static void addVertex(File vertexData){
        graphVertices.put(vertexData, new Vertex<File>(vertexData));
    } 
    
    // private static void addVertex(Vertex<File> vertex){
    //     graphVertices.put(vertex.getData(), vertex);
    // }

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
