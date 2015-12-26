package maze;

import java.io.PrintWriter;

public class DBox extends MBox
{
    public DBox(int x, int y, Maze m) {
        super(m, y, x);
    }

    public final void writeTo(PrintWriter pw) {
        pw.print('D');
    }

	public int getX() {
		return 0;
	}

	public int getY() {
		return 0;
	}
}
