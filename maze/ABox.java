package maze;

public class ABox extends MBox
{
    public Maze m;

    public ABox(int x, int y, Maze m) {
        super(x, y, m);
    }

    public String getLabel() {
        return "A";
    }

}
