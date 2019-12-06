package environment;

import java.awt.Color;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class River extends Lane {

	private boolean hasToMove = false;

	/**
	 * class constructor
	 * 
	 * @param game        a reference to the game
	 * 
	 * @param ord         the ordinate of the river
	 * 
	 * @param speed       of log and water on the river
	 * 
	 * @param leftToRight direction of the river
	 * 
	 * @param density     density of log on the river
	 */
	public River(Game game, int ord, int speed, boolean leftToRight, double density) {
		super(game, ord, speed, leftToRight, density);
		for (int time = 0; time < 300; time += 1) {
			this.update();
		}
		hasToMove = false;
	}

	/**
	 * show the river in blue
	 */
	private void addToGraphics() {
		for (int absc = 0; absc < super.game.width; absc += 1) {
			game.getGraphic().add(new Element(absc, super.ord - game.getScreenPosition(), new Color(0x0000f8)));
		}
	}

	/**
	 * actualize the lane
	 */
	public void update() {
		this.mayAddLog();
		super.update();
		if (this.timer >= phase - speed) {
			hasToMove = true;
		} else {
			hasToMove = false;
		}

		// A chaque tic d'horloge, une voiture peut �tre ajout�e

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas
	}

	/**
	 * test if the case is safe. An entity wont die here
	 * 
	 * @return true if the case is safe
	 */
	public boolean isSafe(Case c) {
		return !super.isSafe(c);
	}

	/**
	 * test if a ententy have to move at this case
	 * 
	 * @return an int depending of the direrction of the lane
	 */
	public int hasToMove(Case c) {
		if (hasToMove) {
			return super.leftToRight ? 1 : -1;

		} else {
			return 0;
		}

	}

	/**
	 * show the river and evry log
	 */
	public void show(int ord) {
		addToGraphics();
		super.show(ord);
	}

	/**
	 * if a log can spawn
	 */
	private void mayAddLog() {
		if (!isSafe(getFirstCase()) && !isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				vehicles.add(new Log(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}
}
