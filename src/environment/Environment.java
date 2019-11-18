package environment;

import java.util.ArrayList;
import java.util.Iterator;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {

	private Game game;
	private ArrayList<Lane> lanes = new ArrayList<>(0);

	public Environment(Game game) {
		this.game = game;
		lanes.add(new Lane(game, 0, 1, true, 0));
		for (int ord = 1; ord < game.height; ord += 1) {
			lanes.add(new Lane(game, ord, 1, (ord % 4 > 2), game.defaultDensity));
		}
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
}
