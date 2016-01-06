package maze;

public class EBox extends MBox {
    private int x;
    private int y;

    public EBox(int x, int y) {
        super(x, y);
    }

    @Override
    public String getType() {
        return "E";
    }
}

