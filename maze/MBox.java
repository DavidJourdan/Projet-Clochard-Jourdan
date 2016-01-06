package maze;

// Classe abstraite
// Chaque case est définie par une ligne (x) et une colonne (y)

public abstract class MBox implements dijkstra.VertexInterface{
    private int x;
    private int y;

    public MBox(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getLabel() {
        return "(" + x + "," + y + ")";
    }

    public abstract String getType();
}
