package environment;

import java.awt.Color;
import java.util.Random;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private int maxLength = 4;
	private final Color colorLtR = Color.BLUE;
	private final Color colorRtL = Color.BLACK;

	// TODO Constructeur(s)
	public Car(Game game, Case leftPosition, boolean leftToRight) {
		this.game = game;
		this.leftPosition = leftPosition;
		this.leftToRight = leftToRight;
		Random rn = new Random();

		this.length = rn.nextInt() % (maxLength);
		this.addToGraphics();
	}

	public Case getCase() {
		return new Case(leftPosition.absc, leftPosition.ord);
	}

	public int getLength() {
		return length;
	}

	// TODO : ajout de methodes

	public void updateAndMoveTo(Case caseToGo) {
		leftPosition = caseToGo;
		//addToGraphics();

	}

	public void update() {
		updateAndMoveTo(this.leftPosition);
	}

	/*
	 * Fourni : addToGraphics() permettant d'ajouter un element graphique
	 * correspondant a la voiture
	 */
	public void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			//if (this.leftToRight) {
			//	color = colorLtR;
			//}
			game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

}
