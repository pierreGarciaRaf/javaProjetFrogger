package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;

public class Road extends Lane{

	public Road(Game game, int ord, int speed, boolean leftToRight, double density) {
		super(game, ord, speed, leftToRight, density);
		for (int time = 0; time < 300; time+= 1) {
			this.update();
		}	
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
	
	public void show(int ord) {
		for (Car car : this.cars) {
			car.addToGraphics(ord);
		}
	}

	public void show() {
		show(this.ord);
	}

}
