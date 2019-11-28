package environment;

import java.awt.Color;
import java.util.Random;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car extends Vehicle{

	public Car(Game game, Case leftPosition, boolean leftToRight) {
		super(game, leftPosition, leftToRight);
	}
	
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
