package dijkstra;

public interface ASetInterface {
	// Ajouter un sommet � l'ensemble A
    public void add(VertexInterface vertex);
    // Tester si un sommet apartient � A
    public boolean contains(VertexInterface vertex);
}
