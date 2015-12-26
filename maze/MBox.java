package maze;

import java.io.PrintWriter;

// Classe abstraite
// Chaque case est définie par une ligne (x) et une colonne (y)

public abstract class MBox implements dijkstra.VertexInterface{
    private final int x;
    private final int y;
    private final Maze m;

    public MBox(Maze m, int x, int y) {
        this.m = m;
    	this.x = x;
        this.y = y;
    }
    
    // On peut aller vers cette case
    public boolean cangothere(){
    	return true;
    }

    public String getLabel() {
        return "(" + x + "," + y + ")";
    }
    
    public final int getLine(){
    	return x;
    }
    
    public final int getColumn(){
    	return y;
    }

    public abstract void writeTo(PrintWriter pw);
}
