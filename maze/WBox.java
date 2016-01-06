package maze;

public class WBox extends MBox {
    private int x;
    private int y;

    public WBox(int x, int y) {
        super(x, y);
    }

    @Override
    public String getType() {
        return "W";
    }

}
