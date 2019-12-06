package environment;

import java.util.ArrayList;
import java.util.Random;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {

	private final int MAXSPEED = 40;
	private final int MINSPEED = 25;
	private Game game;
	private ArrayList<Lane> lanes = new ArrayList<>(0);

	/*
	 * Class constructor initialize evry lane
	 * 
	 * @param game a reference to the game
	 */
	public Environment(Game game) {
		this.game = game;

		lanes.add(new Lane(game, 0, 1, true, 0));

		Random rand = new Random();
		int ord = 1;
		for (; ord < game.height / 4 - 1; ord++) {
			lanes.add(new Road(game, ord, MINSPEED + rand.nextInt(MAXSPEED - MINSPEED) + 1, rand.nextBoolean(),
					game.defaultDensity));
		}
		lanes.add(new Lane(game, ord, 1, true, 0));
		ord += 1;
		lanes.add(new River(game, ord, MINSPEED + rand.nextInt(MAXSPEED - MINSPEED) + 1, rand.nextBoolean(),
				game.defaultDensity));
		ord += 1;
		for (; ord < 3 * game.height / 4 - 2; ord++) {
			lanes.add(new Road(game, ord, MINSPEED + rand.nextInt(MAXSPEED - MINSPEED) + 1, rand.nextBoolean(),
					game.defaultDensity));
		}
		lanes.add(new Lane(game, ord, 1, true, 0));
		ord += 1;
		lanes.add(new River(game, ord, MINSPEED + rand.nextInt(MAXSPEED - MINSPEED) + 1, rand.nextBoolean(),
				game.defaultDensity));
		ord += 1;
		lanes.add(new River(game, ord, MINSPEED + rand.nextInt(MAXSPEED - MINSPEED) + 1, rand.nextBoolean(),
				game.defaultDensity));
		ord += 1;
		for (; ord < game.height; ord++) {
			lanes.add(new Road(game, ord, MINSPEED + rand.nextInt(MAXSPEED - MINSPEED) + 1, rand.nextBoolean(),
					game.defaultDensity));
		}

		lanes.add(new Road(game, game.height - 1, 1, true, 0));
	}

	/**
	 * Teste si une case est sure, c'est � dire que la grenouille peut s'y poser
	 * sans mourir
	 * 
	 * @param c la case � tester
	 * @return vrai s'il n'y a pas danger
	 */
	public boolean isSafe(Case c) {
		return lanes.get(c.ord).isSafe(c);

	}

	/**
	 * Teste si la case est une case d'arrivee
	 * 
	 * @param c
	 * @return vrai si la case est une case de victoire
	 */
	public boolean isWinningPosition(Case c) {
		return c.ord == game.height - 1;
	}

	/*
	 * Check if an entity at the specified case have to move for some reason
	 * 
	 * @param c the position
	 * 
	 * @return an int : -1, 0 or 1 depending of the direction
	 */
	public int hasToMove(Case c) {
		return lanes.get(c.ord).hasToMove(c);
	}

	/**
	 * Effectue une �tape d'actualisation de l'environnement
	 */
	public void update() {
		for (Lane lane : lanes) {
			lane.update();
		}
	}

	/*
	 * Show evry car or log call the show method of evry element in the lane
	 */
	public void showCar() {
		for (Lane lane : lanes) {
			lane.show();
		}
	}

	public int getScreenPosition() {
		return 0;
	}
}
