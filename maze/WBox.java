package maze;

public class WBox extends MBox
{

    public WBox(int x, int y, Maze m) {
        super(m, y, x);
    }

    // On ne peut pas se rendre à cette case
    public boolean cangothere(){
    	return false;
    }
    
    public String getLabel() {
        return "W";
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
