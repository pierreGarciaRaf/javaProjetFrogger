package environment;

import java.awt.Color;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Water extends Vehicle{

	public Water(Game game, Case leftPosition, boolean leftToRight) {
		super(game, leftPosition, leftToRight);
	}

	public void addToGraphics(int ord) {
		for (int i = 0; i < length; i++) {
			Color color = Color.BLUE;
			if (this.leftToRight) {
				color = Color.BLUE;
			}
			game.getGraphic().add(new Element(leftPosition.absc + i, ord, color));
		}
	}
}
