package dijkstra;

public interface PiInterface {
	// D�finit la valeur d'un sommet
    public void setValue(int value, VertexInterface vertex);
    // Retourne la valeur d'un sommet
    public int getValue(VertexInterface vertex);
}
