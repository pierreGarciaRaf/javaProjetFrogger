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

	protected final Color colorLtR = Color.BLUE;
	protected final Color colorRtL = Color.BLACK;
	
	public Vehicle(Game game, Case leftPosition, boolean leftToRight,int minLength, int maxLength) {
		if (minLength >= maxLength) {
			System.out.println("ERROR, minLength >= maxLength : "+minLength+" >= "+maxLength
					+"\n~~put minLength = maxLength - 1~~");
			minLength = maxLength - 1;
		}
		Random rn = new Random();
		this.game = game;
		this.leftToRight = leftToRight;
		this.length = minLength + Math.abs(rn.nextInt()) % (maxLength-minLength+1);
		this.leftPosition = new Case(leftPosition.absc - (leftToRight ? length :0),leftPosition.ord);
	}
	
	public Vehicle(Game game, Case leftPosition, boolean leftToRight) {
		this(game,leftPosition,leftToRight,1,3);
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
			Vehicle obj = (Vehicle) o;
			return this.leftPosition.equals(obj.getCase());
		}
		return false;
	}

}
