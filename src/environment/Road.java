package environment;

import gameCommons.Game;

public class Road extends Lane {

	public Road(Game game, int ord, int speed, boolean leftToRight, double density) {
		super(game, ord, speed, leftToRight, density);
		for (int time = 0; time < 300; time += 1) {
			this.update();
		}
	}

	public void update() {
		super.update();

		// A chaque tic d'horloge, une voiture peut �tre ajout�e
		this.mayAddCar();

	}

	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				vehicles.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

}
