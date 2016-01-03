package dijkstra;

import java.util.HashSet;

public class ASet implements ASetInterface {
	
	private HashSet<VertexInterface> aSet;
	
	public void Aset() {
		aSet = new HashSet<VertexInterface>();
	}
	
    public void add(VertexInterface vertex) {
    	aSet.add(vertex);

    }
    public boolean contains(VertexInterface vertex) {
        return aSet.contains(vertex);
    }
}
