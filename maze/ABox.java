package maze;

import java.io.PrintWriter;

public class ABox extends MBox
{
    public ABox(int x, int y, Maze m) {
        super(m, y, x);
    }

    public final void writeTo(PrintWriter pw) {
        pw.print('A');
    }

	public int getX() {
		return 0;
	}

	public int getY() {
		return 0;
	}

}
