package maze;

import java.io.PrintWriter;

public class WBox extends MBox
{

    public WBox(int x, int y, Maze m) {
        super(m, y, x);
    }

    // On ne peut pas se rendre à cette case
    public boolean cangothere(){
    	return false;
    }
    
    public final void writeTo(PrintWriter pw) {
        pw.print('W');
    }

	public int getX() {
		return 0;
	}

	public int getY() {
		return 0;
	}

}
