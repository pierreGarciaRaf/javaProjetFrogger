package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;

public class Lane {
	protected int timer;
	protected int phase = 100;

	protected Game game;
	protected int ord;
	protected int speed;
	protected ArrayList<Vehicle> vehicles = new ArrayList<>();
	protected boolean leftToRight;
	protected double density;

	/*
	 * class constructor
	 * 
	 * @param game
	 * 
	 * @param ord
	 * 
	 * @param speed
	 * 
	 * @param phase
	 * 
	 * @param leftToRight
	 * 
	 * @param density
	 */
	public Lane(Game game, int ord, int speed, int phase, boolean leftToRight, double density) {
		this.game = game;
		this.ord = ord;
		this.speed = speed;
		this.phase = phase;
		this.leftToRight = leftToRight;
		this.density = density;
	}

	/*
	 * Overloded class constructor
	 * 
	 * @param game
	 * 
	 * @param ord
	 * 
	 * @param speed
	 * 
	 * @param leftToRight
	 * 
	 * @param density
	 */
	public Lane(Game game, int ord, int speed, boolean leftToRight, double density) {
		this(game, ord, speed, 100, leftToRight, density);
	}

	/*
	 * 
	 */
	public void update() {
		this.timer += this.speed;

		if (this.timer >= phase) {
			this.timer %= phase;
			int increaser = -1;
			if (this.leftToRight) {
				increaser = 1;
			}
			for (Vehicle vehicle : this.vehicles) {
				vehicle.updateAndMoveTo(new Case(vehicle.getCase().absc + increaser, vehicle.getCase().ord));
			}
		}
		ArrayList<Vehicle> buffer = new ArrayList<Vehicle>(this.vehicles);
		for (Vehicle vehicle : buffer) {
			if (vehicle.getCase().absc < -5 || vehicle.getCase().absc > this.game.width + 5) {
				this.vehicles.remove(vehicle);
			}
		}

	}

	/*
	 * Check if the case is safe
	 * 
	 * @param c
	 */
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

	/*
	 * 
	 */
	protected Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	/*
	 * 
	 */
	Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

	/*
	 * @param ord
	 */
	public void show(int ord) {
		for (Vehicle vehicle : this.vehicles) {
			vehicle.addToGraphics(ord);
		}
	}

	/*
	 * @param c
	 */
	public int hasToMove(Case c) {
		return 0;
	}

	/*
	 * 
	 */
	public void show() {
		show(this.ord);
	}

}
