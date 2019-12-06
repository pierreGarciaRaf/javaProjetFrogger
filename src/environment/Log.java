package environment;

import java.awt.Color;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

/**
 * class constructor
 * 
 * @param game         a reference to the game
 * 
 * @param leftPosition the position of the log
 * 
 * @param leftToRight  the direction of the log on the lane
 * 
 */
public class Log extends Vehicle {
	public Log(Game game, Case leftPosition, boolean leftToRight) {
		super(game, leftPosition, leftToRight, 2, 4);
	}

	/**
	 * show on the screen
	 * 
	 * @param the ordodinate of the lane
	 */
	public void addToGraphics(int ord) {
		for (int i = 0; i < length; i++) {
			game.getGraphic().add(new Element(leftPosition.absc + i, ord, new Color(0x442215)));
		}
	}
}
