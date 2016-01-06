package dijkstra;

import java.util.Hashtable;

public class Pi implements PiInterface {
    private final Hashtable <VertexInterface, Integer> pi;

    public Pi(){
        pi = new Hashtable<>() ;
    }

    public void setValue(VertexInterface vertex, int value) {
        pi.put(vertex, value);
    }

    public int getValue(VertexInterface vertex) {
        return pi.get(vertex);
    }
}