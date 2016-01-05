package dijkstra;

public interface ASetInterface {
	// Ajouter un sommet à l'ensemble A
    public void add(VertexInterface vertex);
    // Tester si un sommet apartient à A
    public boolean contains(VertexInterface vertex);
}
