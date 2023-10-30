package src;

import java.util.ArrayList;

public class Vertex<File> {
    
    private File data;
    private ArrayList<Edge<File>> edgeList = new ArrayList<Edge<File>>();

    public Vertex(File dataInit){
        this.data = dataInit;
    }

    public void addEdge(Edge<File> newEdge){
        edgeList.add(newEdge);
    } 
    
    public void addEdge(Vertex<File> destination){
        edgeList.add(new Edge<File>(destination));
    }

    public File getData(){
        return this.data;
    }
    public ArrayList<Edge<File>> getEdges(){
        return this.edgeList;
    }
}
