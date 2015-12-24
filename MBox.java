package maze;

public class MBox implements dijkstra.VertexInterface{
    private int x;
    private int y;
    private Maze m;

    public MBox(int x, int y, Maze m)
    {
        this.x = x;
        this.y = y;
        this.m = m;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Maze getM()
    {
        return m;
    }

    public String getLabel()
    {
        return "M";

    }

}
