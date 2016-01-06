package maze;

public class ABox extends MBox
{
    private int x;
    private int y;

    public ABox(int x, int y) {
        super(x, y);
    }

    @Override
    public String getType() {
        return "A";
    }

}
