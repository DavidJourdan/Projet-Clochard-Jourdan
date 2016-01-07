package main;
import interfaces.*;

import java.awt.event.*;
import java.util.* ;

public class AleaGameController extends GameController
{
	public static void main(String[] args) 
	{
		new AleaGameController("Alea",50,30,10,10) ;
	}

	private final GameModel  gameModel ;
	private final GameThread gameThread ;
	
	public AleaGameController(String name, int gameWidth, int gameHeight, int blockWidth, int blockHeight)
	{
		super(name,gameWidth,gameHeight,blockWidth,blockHeight) ;
		
		this.gameModel = new GameModel(gameWidth,gameHeight,blockWidth,blockHeight) ;
		
		this.random = new Random() ;
		
		this.bw = 0 ;
		this.bh = 0 ;
		
		gameThread = new GameThread(this,"tictac") ;
		gameThread.start() ;
	}
	
	private final Random random ; 
	
	private int bw ;
	private int bh ;
	
	public final synchronized void tictac()
	{
		synchronized (gameModel) {
			
			int dw = random.nextInt(3) - 1 ;
			int ww = bw + dw ;
			if (0 <= ww && ww < gameWidth)
				bw = ww ;
			
			int dh = random.nextInt(3) - 1 ;
			int hh = bh + dh ;
			if (0 <= hh && hh < gameHeight)
				bh = hh ;
			
			byte c = (byte)(random.nextInt(13) + 1) ;
			
			gameModel.set(bw,bh,c);
			
			notify(gameModel) ;
		}
	}

	/** Invoked when the mouse button has been clicked (pressed and released) on a component. */
	
	public final synchronized void mouseClicked(MouseEvent e)
	{
		synchronized (gameModel) {
			bw = getGameX(e) ;
			bh = getGameY(e) ;
			byte c = (byte)(random.nextInt(13) + 1) ;
			gameModel.set(bw,bh,c);
			notify(gameModel) ;
		}
	}
	
	/** Invoked when a mouse button has been pressed on a component. */
	public final synchronized void mousePressed(MouseEvent e)
	{
		
	}
	
	/** Invoked when a mouse button has been released on a component. */

	public final synchronized void mouseReleased(MouseEvent e)
	{
			
	}
	
	/** Invoked when the mouse enters a component. */
	public final synchronized void mouseEntered(MouseEvent e)
	{
				
	}
	
	/** Invoked when the mouse exits a component. */
	public final synchronized void mouseExited(MouseEvent e)
	{
				
	}
	
	/** Invoked when a mouse button is pressed on a component and then dragged. 
	 *  MOUSE_DRAGGED events will continue to be delivered to the component 
	 *  where the drag originated until the mouse button is 
	 *  released regardless of whether the mouse position is within the 
	 *  bounds of the component. 
	 */
	public final synchronized void mouseDragged(MouseEvent e)
	{
		
	}

	/** Invoked when the mouse cursor has been moved onto a 
	 *  component but no buttons have been pushed.
	 */
	public final synchronized void mouseMoved(MouseEvent e)
	{
		
	}

	/** Invoked when the mouse wheel is rotated. */
	public final synchronized void mouseWheelMoved(MouseWheelEvent e)
	{
		
	}

	/** Invoked when a key has been typed. 
	 *  See the class description for KeyEvent for a definition of a key typed event.
	 */
	public final synchronized void keyTyped(KeyEvent e)
	{
		
		char key    = e.getKeyChar() ;
		long period = gameThread.getPeriod() ;
		
		switch (key) {
		case 'f' :
			period = period - 10L ;
			if (period < 0L)
				period = 0L ;
			break ;
		case 'F' :
			period = period / 2L ;
			if (period < 0L)
				period = 0L ;
			break ;
		case 's' :
			period = period + 10L ;
			if (period > 10000L)
				period = 10000L ;
			break ;
		case 'S' :
			period = period * 2L ;
			if (period > 10000000000L)
				period = 10000000000L ;
			break ;
		}
		
		gameThread.setPeriod(period) ;
		System.out.println(period) ;
	}

	/** Invoked when a key has been pressed. 
	 *  See the class description for KeyEvent for a definition of a key pressed event.
	 */
	public final synchronized void keyPressed(KeyEvent e)
	{
		
	}

	/** Invoked when a key has been released. 
	 *  See the class description for KeyEvent for a definition of a key released event. 
	 */
	public final synchronized void keyReleased(KeyEvent e)
	{
		
	}

	/** Invoked when a component gains the keyboard focus. */
	public final synchronized void focusGained(FocusEvent e)
	{
		
	}

	/** Invoked when a component loses the keyboard focus. */
	public final synchronized void focusLost(FocusEvent e)
	{
		
	}

	/** Invoked the first time a window is made visible. */
	public final synchronized void windowOpened(WindowEvent e)
	{
		
	}

	/** Invoked when the user attempts to close the window from the window's system menu. */
	public final synchronized void windowClosing(WindowEvent e)
	{
		
	}

	/** Invoked when a window has been closed as the result of calling dispose on the window. */
	public final synchronized void windowClosed(WindowEvent e)
	{
		
	}

	/** Invoked when a window is changed from a normal to a minimized state. 
	 * For many platforms, a minimized window is displayed as the icon specified in the window's iconImage property. 
	 */
	public final synchronized void windowIconified(WindowEvent e)
	{
		
	}

	/** Invoked when a window is changed from a minimized to a normal state. */
	public final synchronized void windowDeiconified(WindowEvent e)
	{
		
	}

	/** Invoked when the Window is set to be the active Window. 
	 * Only a Frame or a Dialog can be the active Window. 
	 * The native windowing system may denote the active Window or 
	 * its children with special decorations, such as a highlighted title bar. 
	 * The active Window is always either the focused Window, 
	 * or the first Frame or Dialog that is an owner of the focused Window. 
	 */
	public final synchronized void windowActivated(WindowEvent e)
	{
		
	}

	/** Invoked when a Window is no longer the active Window. 
	 * Only a Frame or a Dialog can be the active Window. 
	 * The native windowing system may denote the active Window or its children 
	 * with special decorations, such as a highlighted title bar. 
	 * The active Window is always either the focused Window, 
	 * or the first Frame or Dialog that is an owner of the focused Window. 
	 */
	public final synchronized void windowDeactivated(WindowEvent e)
	{
		
	}

}
