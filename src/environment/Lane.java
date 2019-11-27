package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;

public class Lane {
	private int timer;

	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;

	// TODO : Constructeur(s)
	public Lane(Game game, int ord, int speed, boolean leftToRight, double density) {
		this.game = game;
		this.ord = ord;
		this.speed = speed;
		this.leftToRight = leftToRight;
		this.density = density;

	}

	public void update() {
		// TODO

		// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
		// d'horloge" �gal � leur vitesse
		// Notez que cette m�thode est appel�e � chaque tic d'horloge
		this.timer += 1;
		if (this.timer == this.speed) {
			this.timer = 0;
			int increaser = -1;
			if (this.leftToRight) {
				increaser = 1;
			}
			for (Car car : this.cars) {
				car.updateAndMoveTo(new Case(car.getCase().absc + increaser, car.getCase().ord));
			}
		}

		// A chaque tic d'horloge, une voiture peut �tre ajout�e
		this.mayAddCar();

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas
		ArrayList<Car> buffer = new ArrayList<Car>(this.cars);
		for (Car car : buffer) {
			if (car.getCase().absc < -5 || car.getCase().absc > this.game.width + 5) {
				this.cars.remove(car);
			}
		}

	}

	// TODO : ajout de methodes

	public boolean isSafe(Case c) {
		if (c.ord != ord) {
			System.out.println("{"+c.ord+"} != {"+ord+"}");
			return true;
		}else {
		}
		for (Car car : cars) {
			if (car.getCase().absc <= c.absc && car.getCase().absc + car.getLength() > c.absc) {
				// System.out.println("not safe");
				return false;
			} else {
				// System.out.println("safe");
			}
		}
		return true;
	}
	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase()
	 */

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la densit�,
	 * si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

	public void show(int ord) {
		for (Car car : this.cars) {
			car.addToGraphics(ord);
		}
	}
	
	public void show() {
		show(this.ord);
	}

}
