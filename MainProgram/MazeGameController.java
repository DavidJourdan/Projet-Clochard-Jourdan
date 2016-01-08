package MainProgram;

import java.awt.event.*;

import maze.*;
import interfaces.*;
import dijkstra.*;

public class MazeGameController extends GameController {

	
	public static void main(String[] args) {
		new MazeGameController("MyMaze",10,10,40,40);
	}

	private final GameModel  gameModel ;
	
	private Maze maze ;
	
	public MazeGameController(String name, int gameWidth, int gameHeight, int blockWidth, int blockHeight)
	{
		super(name,gameWidth+2,gameHeight+2,blockWidth,blockHeight) ;
		this.gameModel = new GameModel(gameWidth+2,gameHeight+2,blockWidth,blockHeight) ;
		maze = new Maze(gameHeight, gameWidth);
		for (int i=0;i<gameHeight+2;i++){
			byte b=(byte)4;
			maze.setBox(i, gameWidth+1, 'W');
			maze.setBox(i, 0, 'W');
			gameModel.set(gameWidth+1, i, b);
			gameModel.set(0, i, b);
		}
		for (int j=0;j<gameWidth+2;j++){
			byte b=(byte)4;
			maze.setBox(gameHeight+1, j, 'W');
			maze.setBox(0, j, 'W');
			gameModel.set(j, gameHeight+1, b);
			gameModel.set(j, 0, b);
		}
		for (int i=1; i<gameHeight+1; i++){
			for (int j=1; j<gameWidth+1; j++){
				byte b=(byte)0;
				maze.setBox(i, j, 'E');
				gameModel.set(j, i, b);
			}
		}
		
		this.bw = 0 ;
		this.bh = 0 ;
	}
	
	private int bw ;
	private int bh ;
	
	
	public final synchronized void mouseClicked(MouseEvent e) {
		synchronized (gameModel) {
			bw = getGameX(e) ;
			bh = getGameY(e) ;
			byte c;
			if (bw==0 || bw==gameWidth-1 || bh==0 || bh==gameHeight-1){
				return ;
			}
			if ((e.getModifiers() & InputEvent.SHIFT_MASK) != 0) {
				switch (gameModel.get(bw, bh)) {
				case ((byte)0) :				// Quand on clique sur une case vide, elle devient une case depart
					c=(byte)2;
				maze.setBox(bh, bw, 'D');
				gameModel.set(bw, bh, c);
				notify(gameModel);
				break;
				case ((byte)2) :				// Quand on clique sur une case depart, elle devient une case arrivee
					maze.setBox(bh, bw, 'A');
				c=(byte)10;
				gameModel.set(bw, bh, c);
				notify(gameModel);
				break;
				case ((byte)10) :				// Quand on clique sur une case arrivee, elle devient une case depart
					maze.setBox(bh, bw, 'D');
				c=(byte)2;
				gameModel.set(bw, bh, c);
				notify(gameModel);
				break;
				}
			}
			
			
			switch (gameModel.get(bw, bh)) { 
			case ((byte)0) :				// Quand on clique sur une case vide, elle devient un mur
				c=(byte)4;
			maze.setBox(bh, bw, 'W');
			gameModel.set(bw, bh, c);
			notify(gameModel);
			break;
			case ((byte)4) :				// Quand on clique sur un mur, il devient une case vide
				maze.setBox(bh, bw, 'E');
			c=(byte)0;
			gameModel.set(bw, bh, c);
			notify(gameModel);
			break;
			}
			
			

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
