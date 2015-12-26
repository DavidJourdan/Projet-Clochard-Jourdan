package maze;

import java.io.PrintWriter;

public class EBox extends MBox
{
    public EBox(int x, int y, Maze m) {
        super(m, y, x);
    }

    // Dans pw : caractère correspondant
    public final void writeTo(PrintWriter pw) {
        pw.print('E');
    }

	public int getX() {
		return 0;
	}

	public int getY() {
		return 0;
	}
}

