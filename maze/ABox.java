package maze;

public class ABox extends MBox
{
    private int x;
    private int y;
    private Maze m;

    public ABox(int x, int y, Maze m) {
        super(x, y, m);
    }

    public String getLabel() {
        return "A";
    }

}
