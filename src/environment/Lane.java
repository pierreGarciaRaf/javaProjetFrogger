package environment;

import java.util.ArrayList;
import java.util.Timer;

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

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut �tre ajout�e
		timer += 1;
		if (timer == speed) {
			timer = 0;
			int increaser = -1;
			if (leftToRight) {
				increaser = 1;
			}
			for (Car car : cars) {
				car.updateAndMoveTo(new Case(car.getCase().absc + increaser, car.getCase().ord));
			}
		}

		this.mayAddCar();

	}

	// TODO : ajout de methodes

	public boolean isSafe(Case c) {
		if (c.ord != ord) {
			return true;
		}
		for (Car car : cars) {
			if (car.getCase().absc <= c.absc && car.getCase().absc + car.getLength() > c.absc) {
				System.out.println("not safe");
				return false;
			} else {
				System.out.println("safe");
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

}
