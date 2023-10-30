package src;

public class Edge<File> {
    
    private Vertex<File> destination;

    public Edge(Vertex<File> destinationInit){
        this.destination = destinationInit;
    }

    public Vertex<File> getDestination(){
        return this.destination;
    }
}
