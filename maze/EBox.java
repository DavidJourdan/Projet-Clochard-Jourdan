package maze;

public class EBox extends MBox
{
    private Maze m;
    private int x;
    private int y;

    public EBox(int x, int y, Maze m) {
        super(m, y, x);
    }

    public String getLabel() {
        return "E";
    }

	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}
}

