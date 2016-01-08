package interfaces;

import javax.swing.* ;

/** The Quit menu item inside the File menu. */

import java.awt.event.* ;

public final class QuitMenuItem extends JMenuItem
	implements ActionListener
{
	static final long serialVersionUID = 201503101423L ;
	
	/** The current Java frame. */
	private final GameFrame gameWindow ;
	
	/** Constructor:
	 * @param gameWindow The current Java frame.
	 */
	public QuitMenuItem(GameFrame gameWindow)
	{
		super("Quit") ;
		this.gameWindow = gameWindow ;
		addActionListener(this) ;
	}
	
	/** Action for the menu. */
	public final void actionPerformed(ActionEvent evt)
	{
		gameWindow.quit() ;
	}
	
}
