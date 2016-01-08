package interfaces;

import java.awt.* ;

import javax.swing.* ;

/** This is the JFrame class for the main game window. */

public final class GameFrame extends JFrame
{
	static final long serialVersionUID = 201503101423L ;
	
	/** The game panel, ie. the content of the window. */
	private final GamePanel gamePanel ;
	
	/** The constructor :
	 * @param gameController The game controller.
	 * @param name The game name to be displayed at the top of the window.
	 * @param gameWidth The width of the game in blocks.
	 * @param gameHeight The height of the game in blocks.
	 * @param blockWidth The width in pixel of a screen block.
	 * @param blockHeight The height in pixel of a screen block.
	 */
	public GameFrame(GameController gameController, String name, int gameWidth, int gameHeight, int blockWidth, int blockHeight)
	{
		super(name) ;
		
		// Widgets

		setJMenuBar(new GameMenuBar(this)) ;
		
		setContentPane(gamePanel = new GamePanel(gameController,gameWidth,gameHeight,blockWidth,blockHeight)) ;
		
		// Epilogue
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		
		int sizeW = blockWidth  * gameWidth  ;
		int sizeH = blockHeight * gameHeight ;

		setSize(sizeW,sizeH) ;
		setVisible(true) ;
		
		Dimension dimension = gamePanel.getSize() ;
		
		int widthToAdd  = (int)(sizeW - dimension.getWidth()) ;
		int heightToAdd = (int)(sizeH - dimension.getHeight()) ;
		
		setSize(sizeW + widthToAdd,sizeH + heightToAdd) ;
		setResizable(false) ;	
		
		addKeyListener(gameController) ;
	}
	
	public final void set(GameModel gameModel)
	{
		gamePanel.set(gameModel);
		repaint() ;
	}
	
	public final void quit()
	{
		   int response 
		   	= JOptionPane.showInternalOptionDialog(
		   			 gamePanel,
                   "Really quit ?",
                   "Quit application",
                   JOptionPane.YES_NO_OPTION,
                   JOptionPane.WARNING_MESSAGE,
                   null,null,null) ;
		   switch (response) {
		   case JOptionPane.OK_OPTION:
			   System.exit(0) ;
		   case JOptionPane.NO_OPTION:
			   break ;
		   }		
	}

}
