package dijkstra;

import java.util.ArrayList;

public interface GraphInterface {
	// Retourne dans une liste tous les sommets
    public ArrayList<VertexInterface> getAllVertices();
    // Retourne dans une liste tous les descendants d'un sommet
    public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex);
    // Retourne le poids d'un arc
    public int getWeight(VertexInterface src, VertexInterface dst);
}
