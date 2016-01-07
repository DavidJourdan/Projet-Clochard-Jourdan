package interfaces;

import java.awt.* ;
import javax.swing.* ;

/** The frame JPanel containing the game. */

public final class GamePanel extends JPanel
{
	static final long serialVersionUID = 201503101423L ;
	
	/** The model of the game. */
	private final GameModel gameModel ;
	
	/** Constructor:
	 * @param gameController The game controller.
	 * @param gameWidth The width of the game in blocks.
	 * @param gameHeight The height of the game in blocks.
	 * @param blockWidth The width in pixel of a screen block.
	 * @param blockHeight The height in pixel of a screen block.
	 */
	public GamePanel(GameController gameController, int gameWidth, int gameHeight, int blockWidth, int blockHeight)
	{
		super() ;
		
		addMouseListener(gameController) ;
		addMouseMotionListener(gameController) ;
		addMouseWheelListener(gameController) ;
		addKeyListener(gameController) ;
		addFocusListener(gameController) ;
		
		this.gameModel = new GameModel(gameWidth,gameHeight,blockWidth,blockHeight) ;
	}
	
	/** The paintComponent method paints the gameModel. */
	protected final void paintComponent(Graphics g)
	{
		synchronized (gameModel) {
			gameModel.paintGame(g) ;
		}
	}

	/** Sets the gameModel by copying it on its gameModel.
	 * @param gameModel The GameModel to be copied.
	 */
	public final void set(GameModel gameModel)
	{
		synchronized (this.gameModel) { 
			this.gameModel.copy(gameModel);
		}
	}

}
