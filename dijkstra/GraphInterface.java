package dijkstra;

import java.util.ArrayList;

public interface GraphInterface {
    ArrayList<VertexInterface> getAllVertices();
    ArrayList<VertexInterface> getSuccessors(VertexInterface vertex);
    int getWeight(VertexInterface src, VertexInterface dst);
}
