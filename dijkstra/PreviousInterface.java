package dijkstra;

import java.util.ArrayList;

public interface PreviousInterface {
	// D�finit le r�sultat d'un sommet
    public void setValue(VertexInterface vertex, VertexInterface value);
    // Retourne le r�siltat d'un sommet
    public VertexInterface getValue(VertexInterface vertex);
    // Retourne le chemin le plus court entre la racine et un sommet
    public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex);
}
