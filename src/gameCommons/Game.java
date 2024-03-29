package gameCommons;

import java.awt.Color;
import java.util.Random;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

public class Game {
	// variables qui évoluent pendant la partie.
	public int time;
	public final Random randomGen = new Random();
	protected boolean gameFinished;

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;

	// Lien aux objets utilis�s
	protected IEnvironment environment;
	private IFrog frog;
	protected IFroggerGraphics graphic;

	public Case getLastFrogPos() {
		return frog.getPosition();
	}

	/**
	 * 
	 * @param graphic             l'interface graphique
	 * @param width               largeur en cases
	 * @param height              hauteur en cases
	 * @param minSpeedInTimerLoop Vitesse minimale, en nombre de tour de timer avant
	 *                            d�placement
	 * @param defaultDensity      densite de voiture utilisee par defaut pour les
	 *                            routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		this.gameFinished = false;

	}

	/**
	 * Lie l'objet frog � la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Moves the frog if needed (if it's on a river).
	 */
	public void moveBecauseOfWater() {
		int hasToMove = environment.hasToMove(frog.getPosition());
		if (hasToMove == 0) {
			return;
		}
		frog.move(hasToMove > 0 ? Direction.right : Direction.left);
	}

	/**
	 * Teste si la partie est perdue et lance un écran de fin approprié si tel est
	 * le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		if (!environment.isSafe(frog.getPosition())) {
			System.out.println("YOU LOSE!");
			graphic.endGameScreen("You lose, your score  " + environment.getScreenPosition(), time);
			return true;
		}
		return false;
	}

	/**
	 * Teste si la partie est gagnee et lance un �cran de fin appropri� si tel est
	 * le cas
	 * 
	 * @return true si la partie est gagn�e
	 */
	public boolean testWin() {
		if (environment.isWinningPosition(frog.getPosition())) {
			System.out.println("GAME!");
			graphic.endGameScreen("GAME!", time);
			return true;
		}
		return false;
	}

	// gere l'affichage
	private void show() {
		environment.showCar();
		this.graphic.add(new Element(frog.getOnScreenPosition(), Color.GREEN));
	}
	
	//Gives the screen position from environment.
	public int getScreenPosition() {
		return environment.getScreenPosition();
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de partie.
	 */
	public void update() {
		if (!gameFinished) {
			moveBecauseOfWater();
			environment.update();

			graphic.clear();
			show();

			gameFinished = testLose();
			gameFinished = testWin() || gameFinished;
		}
	}
	//Same thing as setFrog in this class.
	public void addFrog(IFrog frog2) {
		setFrog(frog2);
	}
}
