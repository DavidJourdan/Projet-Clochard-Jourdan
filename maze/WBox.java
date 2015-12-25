package maze;

public class WBox extends MBox
{
    private int x;
    private int y;
    private Maze m;

    public WBox(int x, int y, Maze m) {
        super(x, y, m);
    }

    public String getLabel() {
        return "W";
    }

}
