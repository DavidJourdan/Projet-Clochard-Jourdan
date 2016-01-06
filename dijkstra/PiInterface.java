package dijkstra;

public interface PiInterface {
    // Définit la valeur d'un sommet
    void setValue(VertexInterface vertex, int value);
    // Retourne la valeur d'un sommet
    int getValue(VertexInterface vertex);
}
