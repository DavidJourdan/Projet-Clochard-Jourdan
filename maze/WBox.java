package maze;

public class WBox extends MBox
{
    public Maze m;

    public WBox(int x, int y, Maze m) {
        super(x, y, m);
    }

    public String getLabel() {
        return "W";
    }

}
