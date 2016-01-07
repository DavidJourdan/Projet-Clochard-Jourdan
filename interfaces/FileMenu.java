package interfaces;

import javax.swing.* ;

/** The File menu inside the menu bar. */

public final class FileMenu extends JMenu
{
	static final long serialVersionUID = 201503101423L ;
	
	/** Constructor:
	 * @param gameFrame The current Java frame.
	 */
	public FileMenu(GameFrame gameFrame)
	{
		super("File") ;
		add(new QuitMenuItem(gameFrame)) ;
	}
}
