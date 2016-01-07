package interfaces;

import javax.swing.* ;

/** The Menu Bar of the game frame. */

public final class GameMenuBar extends JMenuBar
{
	static final long serialVersionUID = 201503101423L ;
	
	public GameMenuBar(GameFrame gameWindow)
	{
		super() ;	
		add(new FileMenu(gameWindow)) ;
	}

}
