package environment;

import java.awt.Color;
import java.util.Random;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Vehicle {
	protected Game game;
	protected Case leftPosition;
	protected boolean leftToRight;
	protected int length;
	protected int maxLength = 4;
	protected final Color colorLtR = Color.BLUE;
	protected final Color colorRtL = Color.BLACK;

	public Vehicle(Game game, Case leftPosition, boolean leftToRight) {
		Random rn = new Random();
		this.game = game;
		this.leftToRight = leftToRight;
		this.length = rn.nextInt() % (maxLength);
		this.leftPosition = new Case(leftPosition.absc - (leftToRight ? length :0),leftPosition.ord);
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
		// addToGraphics();

	}

	public void update() {
		updateAndMoveTo(this.leftPosition);
	}

	/*
	 * Fourni : addToGraphics() permettant d'ajouter un element graphique
	 * correspondant a la voiture
	 */
	public void addToGraphics(int ord) {
		
	}

	public boolean equals(Object o) {
		if (getClass() == o.getClass()) {
			Car obj = (Car) o;
			return this.leftPosition.equals(obj.getCase());
		}
		return false;
	}

}
