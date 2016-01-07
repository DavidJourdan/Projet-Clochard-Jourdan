package main;

import interfaces.*;

import java.awt.event.*;

public class SampleGameController extends GameController
{
	public static void main(String[] args) 
	{
		new SampleGameController("Sample",100,75,5,5) ;
	}
	private final boolean debug = true ;
	
	private final GameModel gameModel ;
	
	private final int gameWidth ;
	private final int gameHeight ;
	private final int blockWidth ;
	private final int blockHeight ;
	
	private final GameThread gameThread ;
	
	public SampleGameController(String name, int gameWidth, int gameHeight, int blockWidth, int blockHeight)
	{
		super(name,gameWidth,gameHeight,blockWidth,blockHeight) ;
		
		this.gameWidth   = gameWidth ;
		this.gameHeight  = gameHeight ;
		this.blockWidth  = blockWidth ;
		this.blockHeight = blockHeight ;
		
		this.gameModel   = new GameModel(gameWidth,gameHeight,blockWidth,blockHeight) ;
		
		gameThread = new GameThread(this,"tictac") ;
		gameThread.start() ;
	}
	
	public final synchronized void tictac()
	{
		if (debug)
			System.err.println("Thread calls tictac()") ;
	}

	/** Invoked when the mouse button has been clicked (pressed and released) on a component. */
	@Override
	public final synchronized void mouseClicked(MouseEvent e)
	{
		if (debug)
			System.err.println("Mouse clicked") ;	
	}
	
	/** Invoked when a mouse button has been pressed on a component. */
	@Override
	public final synchronized void mousePressed(MouseEvent e)
	{
		if (debug)
			System.err.println("Mouse pressed") ;																
	}
	
	/** Invoked when a mouse button has been released on a component. */
	@Override
	public final synchronized void mouseReleased(MouseEvent e)
	{
		if (debug)
			System.err.println("Mouse released") ;														
	}
	
	/** Invoked when the mouse enters a component. */
	@Override
	public final synchronized void mouseEntered(MouseEvent e)
	{
		if (debug)
			System.err.println("Mouse entered") ;	
	}
	
	/** Invoked when the mouse exits a component. */
	@Override
	public final synchronized void mouseExited(MouseEvent e)
	{
		if (debug)
			System.err.println("Mouse exited") ;	
	}
	
	/** Invoked when a mouse button is pressed on a component and then dragged. 
	 *  MOUSE_DRAGGED events will continue to be delivered to the component 
	 *  where the drag originated until the mouse button is 
	 *  released regardless of whether the mouse position is within the 
	 *  bounds of the component. 
	 */
	public final synchronized void mouseDragged(MouseEvent e)
	{
		if (debug)
			System.err.println("Mouse dragged") ;	
	}

	/** Invoked when the mouse cursor has been moved onto a 
	 *  component but no buttons have been pushed.
	 */
	public final synchronized void mouseMoved(MouseEvent e)
	{
		if (debug)
			System.err.println("Mouse moved") ;	
	}

	/** Invoked when the mouse wheel is rotated. */
	public final synchronized void mouseWheelMoved(MouseWheelEvent e)
	{
		if (debug)
			System.err.println("Mouse wheel moved") ;	
	}

	/** Invoked when a key has been typed. 
	 *  See the class description for KeyEvent for a definition of a key typed event.
	 */
	public final synchronized void keyTyped(KeyEvent e)
	{
		if (debug)
			System.err.println("Key typed: " + e.paramString()) ;	
	}

	/** Invoked when a key has been pressed. 
	 *  See the class description for KeyEvent for a definition of a key pressed event.
	 */
	public final synchronized void keyPressed(KeyEvent e)
	{
		if (debug)
			System.err.println("Key pressed: " + e.paramString()) ;	
	}

	/** Invoked when a key has been released. 
	 *  See the class description for KeyEvent for a definition of a key released event. 
	 */
	public final synchronized void keyReleased(KeyEvent e)
	{
		if (debug)
			System.err.println("Key released: "  + e.paramString()) ;	
	}

	/** Invoked when a component gains the keyboard focus. */
	public final synchronized void focusGained(FocusEvent e)
	{
		if (debug)
			System.err.println("Focus gained") ;		
	}

	/** Invoked when a component loses the keyboard focus. */
	public final synchronized void focusLost(FocusEvent e)
	{
		if (debug)
			System.err.println("Focus lost") ;	
	}

	/** Invoked the first time a window is made visible. */
	public final synchronized void windowOpened(WindowEvent e)
	{
		if (debug)
			System.err.println("Windows opened") ;	
	}

	/** Invoked when the user attempts to close the window from the window's system menu. */
	public final synchronized void windowClosing(WindowEvent e)
	{
		if (debug)
			System.err.println("Windows closing") ;	
	}

	/** Invoked when a window has been closed as the result of calling dispose on the window. */
	public final synchronized void windowClosed(WindowEvent e)
	{
		if (debug)
			System.err.println("Windows closed") ;
	}

	/** Invoked when a window is changed from a normal to a minimized state. 
	 * For many platforms, a minimized window is displayed as the icon specified in the window's iconImage property. 
	 */
	public final synchronized void windowIconified(WindowEvent e)
	{
		if (debug)
			System.err.println("Windows iconified") ;														
	}

	/** Invoked when a window is changed from a minimized to a normal state. */
	public final synchronized void windowDeiconified(WindowEvent e)
	{
		if (debug)
			System.err.println("Windows deiconified") ;																
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
		if (debug)
			System.err.println("Windows activated") ;																
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
		if (debug)
			System.err.println("Windows deactivated") ;																		
	}

}
