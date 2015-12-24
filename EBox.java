package maze;

public class EBox extends MBox
{
    public Maze m;

    public EBox(int x, int y, Maze m) {
        super(x, y, m);
        // TODO Auto-generated constructor stub
    }

    public String getLabel()
    {
        return "E";

    }
}

