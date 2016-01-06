package maze;

public class DBox extends MBox
{
    private int x;
    private int y;

    public DBox(int x, int y) {
        super(x, y);
    }

    @Override
    public String getType() {
        return "D";
    }
}
