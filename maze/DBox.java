package maze;

public class DBox extends MBox
{
    public Maze m;

    public DBox(int x, int y, Maze m) {
        super(x, y, m);
    }


    public String getLabel() {
        return "D";
    }
}
