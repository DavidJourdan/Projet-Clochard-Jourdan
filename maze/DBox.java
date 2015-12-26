package maze;

public class DBox extends MBox
{
    private int x;
    private int y;
    private Maze m;

    public DBox(int x, int y, Maze m) {
        super(m, y, x);
    }


    public String getLabel() {
        return "D";
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
