package environment;

import java.awt.Color;
import java.util.Random;

import gameCommons.Case;
import gameCommons.Game;

public class Vehicle {
	protected Game game;
	protected Case leftPosition;
	protected boolean leftToRight;
	protected int length;

	protected final Color colorLtR = Color.BLUE;
	protected final Color colorRtL = Color.BLACK;

	/**
	 * class constructor
	 * 
	 * @param game         a reference to the game
	 * @param leftPosition position of the vehicle
	 * @param leftToRight  direction of the vehicle
	 * @param minLength
	 * @param maxLength
	 */
	public Vehicle(Game game, Case leftPosition, boolean leftToRight, int minLength, int maxLength) {
		if (minLength >= maxLength) {
			System.out.println("ERROR, minLength >= maxLength : " + minLength + " >= " + maxLength
					+ "\n~~put minLength = maxLength - 1~~");
			minLength = maxLength - 1;
		}
		Random rn = new Random();
		this.game = game;
		this.leftToRight = leftToRight;
		this.length = minLength + Math.abs(rn.nextInt()) % (maxLength - minLength + 1);
		this.leftPosition = new Case(leftPosition.absc - (leftToRight ? length : 0), leftPosition.ord);
	}

	/**
	 * overloded constructor
	 * 
	 * @param game
	 * @param leftPosition
	 * @param leftToRight
	 */
	public Vehicle(Game game, Case leftPosition, boolean leftToRight) {
		this(game, leftPosition, leftToRight, 1, 3);
	}

	/**
	 * @return the position of the vehicle
	 */
	public Case getCase() {
		return new Case(leftPosition.absc, leftPosition.ord);
	}

	/**
	 * @return the lenght of the vehicle
	 */
	public int getLength() {
		return length;
	}

	/**
	 * move the vehicle to caseToGo
	 * 
	 * @param caseToGo
	 */
	public void updateAndMoveTo(Case caseToGo) {
		leftPosition = caseToGo;
		// addToGraphics();

	}

	public void update() {
		updateAndMoveTo(this.leftPosition);
	}

	/**
	 * show the vehicle on screen
	 * 
	 * @param ord the ordinate of the vehicle
	 */
	public void addToGraphics(int ord) {

	}

	/**
	 * is equal if on the same case
	 */
	public boolean equals(Object o) {
		if (getClass() == o.getClass()) {
			Vehicle obj = (Vehicle) o;
			return this.leftPosition.equals(obj.getCase());
		}
		return false;
	}

}
