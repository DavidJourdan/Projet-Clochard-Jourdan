package dijkstra;

import java.util.ArrayList;

public interface PreviousInterface {
    public void setValue(VertexInterface vertex, VertexInterface value);
    public VertexInterface getValue(VertexInterface vertex);
    public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex);
}
