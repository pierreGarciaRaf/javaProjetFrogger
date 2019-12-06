package environment;

import gameCommons.Game;

public class Road extends Lane {

	/**
	 * class constructor
	 * 
	 * @param game        a reference to the game
	 * @param ord         the ordinate of the road
	 * @param speed       the speed of car on the lane
	 * @param leftToRight the direction of car
	 * @param density     of car on the road
	 */
	public Road(Game game, int ord, int speed, boolean leftToRight, double density) {
		super(game, ord, speed, leftToRight, density);
		for (int time = 0; time < 300; time += 1) {
			this.update();
		}
	}

	/**
	 * actualize the psition of car. need to be called evry frame. spawn a car
	 * rendomly dependig ofthe density
	 */
	public void update() {
		super.update();

		// A chaque tic d'horloge, une voiture peut �tre ajout�e
		this.mayAddCar();

	}

	/**
	 * add a car rendomly depending of the density of car
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				vehicles.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

}
