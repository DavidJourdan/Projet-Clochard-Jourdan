package MainProgram;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import maze.*;
import interfaces.*;
import dijkstra.*;

import javax.swing.*;

public class MazeGameController extends GameController {

	
	public static void main(String[] args) {
		new MazeGameController("Labyrinthe de Kei-Saburo Clochard et David Jourdan",10,10,40,40);
	}

	private final GameModel  gameModel ;
	
	private Maze maze;
    private final int height, width;


	public MazeGameController(String name, int gameWidth, int gameHeight, int blockWidth, int blockHeight) {
		super(name,gameWidth+2,gameHeight+2,blockWidth,blockHeight) ; // On decale les indices pour creer le contour du cadre
        height = gameHeight;
        width = gameWidth;
		this.gameModel = new GameModel(gameWidth+2,gameHeight+2,blockWidth,blockHeight) ;
		maze = new Maze(gameHeight, gameWidth);
        updateAll();

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
				case ((byte)10) :				// Quand on clique sur une case arrivee, elle devient une case vide
                    maze.setBox(bh, bw, 'E');
                    c=(byte)0;
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

    private void updateAll() { // Affectation de chaque case a une couleur comme explicite dans le programme precedent
        for(int i=0; i<height+2; i++) {
            for(int j=0; j<width+2; j++) {
                char type = maze.getBox(i,j).getType().charAt(0);
                byte c=0;
                switch(type) {
                    case 'A':
                        c=(byte)10; break;
                    case 'D':
                        c=(byte)2; break;
                    case 'E':
                        c=(byte)0; break;
                    case 'W':
                        c=(byte)4; break;
                }
                gameModel.set(j, i, c);
                notify(gameModel);
            }
        }
    }

	public void keyPressed(KeyEvent e) {
        try {
            switch(e.getKeyChar()) {
                case('s'):											// Sauvegarde du fichier
                    JFileChooser fileChooser = new JFileChooser();
                    if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        String fileName = file.getAbsolutePath();
                        maze.saveToTextFile(fileName);
                    }
                    break;
                case('o'):											// Ouverture du fichier
                    fileChooser = new JFileChooser();
                    if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        String fileName = file.getAbsolutePath();
                        maze.initFromTextFile(fileName);
                        updateAll();
                    } else return;
                    break;
                case('r'):											// Resolution du plus court chemin du labyrinthe
                    ArrayList<VertexInterface> path = maze.solveMaze();
                    for(VertexInterface vertex : path) {
                        MBox box = (MBox) vertex;
                        int x = box.getX();
                        int y = box.getY();
                        if(!box.getType().equals("A") && !box.getType().equals("D")) {
                            gameModel.set(y, x, (byte) 12);
                            notify(gameModel);
                        }
                    }
                    break;
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (MazeReadingException e1) {
            e1.printStackTrace();
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
