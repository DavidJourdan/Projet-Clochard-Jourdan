package MainProgramm;

import java.awt.event.*;
import java.util.Random;

import maze.*;
import interfaces.*;

public class MazeGameController extends GameController {

	
	public static void main(String[] args) {
		new MazeGameController("MyMaze",10,10,40,40);
	}

	private final GameModel  gameModel ;
	
	private Maze maze ;
	
	private int bw ;
	private int bh ;
	
	public MazeGameController(String name, int gameWidth, int gameHeight, int blockWidth, int blockHeight)
	{
		super(name,gameWidth,gameHeight,blockWidth,blockHeight) ;
		this.gameModel = new GameModel(gameWidth,gameHeight,blockWidth,blockHeight) ;
		
		this.bw = 0 ;
		this.bh = 0 ;
	}
	
	
	
	public void mouseClicked(MouseEvent e) {
		synchronized (gameModel) {
			bw = getGameX(e) ;
			bh = getGameY(e) ;
			byte c = (byte)3;
			gameModel.set(bw,bh,c);
			notify(gameModel) ;
		}

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

}
