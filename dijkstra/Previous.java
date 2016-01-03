package dijkstra;

import java.util.ArrayList;
import java.util.Hashtable;

public class Previous implements PreviousInterface {
   private final Hashtable<VertexInterface,VertexInterface> prev;
	
   public Previous(){
	   prev = new Hashtable<VertexInterface, VertexInterface>();
   }
   
	public void setValue(VertexInterface vertex, VertexInterface value) {
		prev.put(vertex, value);
    }

    public VertexInterface getValue(VertexInterface vertex) {
        return prev.get(vertex);
    }

    /** Liste pour montrer le chemin le plus court
     *la racine et un sommet */
    public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex) {
        ArrayList<VertexInterface> path = new ArrayList<VertexInterface>();
        while (vertex!=null){ // quand la branche existe 
        	path.add(vertex); // on l'ajoute à la liste
        	vertex=getValue(vertex);
        }
        return path;
    }
}
