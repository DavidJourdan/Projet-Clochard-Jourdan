package maze;

public class EBox extends MBox
{
    private Maze m;
    private int x;
    private int y;

    public EBox(int x, int y, Maze m) {
        super(x, y, m);
    }

    public String getLabel() {
        return "E";
    }
}

