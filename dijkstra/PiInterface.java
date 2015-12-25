package dijkstra;

public interface PiInterface {
	// Définit la valeur d'un sommet
    public void setValue(int value, VertexInterface vertex);
    // Retourne la valeur d'un sommet
    public int getValue(VertexInterface vertex);
}
