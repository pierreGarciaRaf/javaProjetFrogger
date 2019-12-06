package environment;

import java.awt.Color;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car extends Vehicle {

	/*
	 * Class constructor
	 * 
	 * @param game the game
	 * 
	 * @param leftPosition
	 * 
	 * @param leftToRight
	 */
	public Car(Game game, Case leftPosition, boolean leftToRight) {
		super(game, leftPosition, leftToRight);
	}

	/*
	 * Show the car on the screen at it own
	 * 
	 * @param ord the ordinate of the car on the screen
	 */
	public void addToGraphics(int ord) {
		for (int i = 0; i < length; i++) {
			Color color = Color.BLACK;
			if (this.leftToRight) {
				color = Color.BLACK;
			}
			game.getGraphic().add(new Element(leftPosition.absc + i, ord, color));
		}
	}
}
