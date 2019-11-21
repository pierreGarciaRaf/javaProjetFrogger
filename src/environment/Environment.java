package environment;

import java.util.ArrayList;
import java.util.Random;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {

	private final int MAXSPEED = 3;
	
	private Game game;
	private ArrayList<Lane> lanes = new ArrayList<>(0);

	public Environment(Game game) {
		this.game = game;

		lanes.add(new Lane(game, 0, 1, true, 0));

		Random rand = new Random();
		
		for (int ord = 1; ord < game.height - 1; ord++) {
			lanes.add(new Lane(game, ord, rand.nextInt(MAXSPEED) + 1, rand.nextBoolean(), game.defaultDensity));
		}

		lanes.add(new Lane(game, game.height - 1, 1, true, 0));
	}

	// TODO
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

	/**
	 * Effectue une �tape d'actualisation de l'environnement
	 */
	public void update() {
		for (Lane lane : lanes) {
			lane.update();
		}
	}

	@Override
	public void showCar() {
		for (Lane lane : lanes) {
			lane.show();
		}	
	}
}
