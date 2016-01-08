package MainProgram;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import maze.*;
import interfaces.*;
import dijkstra.*;

import javax.swing.*;

public class MazeGameController extends GameController {

	
	public static void main(String[] args) {
		new MazeGameController("MyMaze",10,10,40,40);
	}

	private final GameModel  gameModel ;
	
	private Maze maze ;

    private int mazeNb = 1;
	
	public MazeGameController(String name, int gameWidth, int gameHeight, int blockWidth, int blockHeight) {
		super(name,gameWidth,gameHeight,blockWidth,blockHeight) ;
		this.gameModel = new GameModel(gameWidth,gameHeight,blockWidth,blockHeight) ;
		maze = new Maze(gameHeight, gameWidth);
		for (int i=0;i<gameHeight;i++){
			byte b=(byte)4;
			maze.setBox(i, gameWidth-1, 'W');
			maze.setBox(i, 0, 'W');
			gameModel.set(gameWidth-1, i, b);
			gameModel.set(0, i, b);
		}
		for (int j=0;j<gameWidth;j++){
			byte b=(byte)4;
			maze.setBox(gameHeight-1, j, 'W');
			maze.setBox(0, j, 'W');
			gameModel.set(j, gameHeight-1, b);
			gameModel.set(j, 0, b);
		}
		for (int i=1; i<gameHeight-1; i++){
			for (int j=1; j<gameWidth-1; j++){
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
		    if (gameModel.get(bw, bh)==(byte)0){ // Quand on clique sur une case vide, elle devient un mur
		    	c=(byte)4;
		    	maze.setBox(bh, bw, 'W');
		    	gameModel.set(bw, bh, c);
		    }
		    if (gameModel.get(bw, bh)==(byte)4){ // Quand on clique sur un mur, il devient une case vide
		    	c=(byte)0;
		    	maze.setBox(bh, bw, 'E');
		    	gameModel.set(bw, bh, c);
		    }
			if ((e.getModifiers() & InputEvent.SHIFT_MASK) != 0) {
			    if (gameModel.get(bw, bh)==(byte)0){ // Quand on Shift+clique sur une case vide, elle devient une case d�part
			    	c=(byte)2;
			    	maze.setBox(bh, bw, 'D');
			    	gameModel.set(bw, bh, c);
			    }
			    if (gameModel.get(bw, bh)==(byte)2){ // Quand on clique sur une case d�part, elle devient une case arriv�e
			    	c=(byte)10;
			    	maze.setBox(bh, bw, 'A');
			    	gameModel.set(bw, bh, c);
			    }
				/** if (maze.getBox(bh, bw).getType()=="D"){
					maze.setBox(bh, bw, 'A');
					c=(byte)4;
				}*/				
			}
			
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

	public void keyPressed(KeyEvent e) {
        try {
            switch(e.getKeyChar()) {
                case('s'):
                    String fileName = "data/labyrinthe" + Integer.toString(mazeNb) + ".txt";
                    JFileChooser fileChooser = new JFileChooser();
                    if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        fileName = file.getName();
                    }
                    // sauvegarde dans tous les cas (au pire avec un nom choisi automatiquement)
                    maze.saveToTextFile(fileName);
                    break;
                case('o'):
                    fileChooser = new JFileChooser();
                    if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        fileName = file.getName();
                        maze.initFromTextFile(fileName);
                    } else return;
                    break;
                case('r'):
                    break;
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (MazeReadingException e1) {
            e1.printStackTrace();
        }
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
