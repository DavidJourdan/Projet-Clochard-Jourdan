package dijkstra;

import java.util.ArrayList;

public interface GraphInterface {
	// Retourne dans une liste tous les sommets
    ArrayList<VertexInterface> getAllVertices();
    // Retourne dans une liste tous les descendants d'un sommet
    ArrayList<VertexInterface> getSuccessors(VertexInterface vertex);
    // Retourne le poids d'un arc
    int getWeight(VertexInterface src, VertexInterface dst);
}
