package environment;

import java.awt.Color;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Log extends Vehicle{
	public Log(Game game, Case leftPosition, boolean leftToRight) {
		super(game, leftPosition, leftToRight,2,4);
	}

	public void addToGraphics(int ord) {
		for (int i = 0; i < length; i++) {
			game.getGraphic().add(new Element(leftPosition.absc + i, ord, new Color(0x442215)));
		}
	}
}
