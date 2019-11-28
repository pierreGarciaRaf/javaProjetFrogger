package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;

public class Lane {
	protected int timer;

	protected Game game;
	protected int ord;
	protected int speed;
	protected ArrayList<Vehicle> vehicles = new ArrayList<>();
	protected boolean leftToRight;
	protected double density;

	// TODO : Constructeur(s)
	public Lane(Game game, int ord, int speed, boolean leftToRight, double density) {
		this.game = game;
		this.ord = ord;
		this.speed = speed;
		this.leftToRight = leftToRight;
		this.density = density;
	}

	public void update() {

	}

	public boolean isSafe(Case c) {
		if (c.ord != ord) {
			return true;
		} else {
		}
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getCase().absc <= c.absc && vehicle.getCase().absc + vehicle.getLength() > c.absc) {
				// System.out.println("not safe");
				return false;
			} else {
				// System.out.println("safe");
			}
		}
		return true;
	}

	protected Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

	public void show(int ord) {
		for (Vehicle vehicle : this.vehicles) {
			vehicle.addToGraphics(ord);
		}
	}

	public int hasToMove(Case c) {
		return 0;
	}

	public void show() {
		show(this.ord);
	}

}
