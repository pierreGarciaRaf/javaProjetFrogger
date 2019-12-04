package gameCommons;

import java.awt.Color;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

public class GameMulti extends Game{
	private Frogs frogs;

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
	public GameMulti(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super(graphic,width,height,minSpeedInTimerLoop,defaultDensity);
		frogs = new Frogs();

	}

	/**
	 * Lie l'objet frog � la partie
	 * 
	 * @param frog
	 */
	public void addFrog(IFrog frog) {
		this.frogs.add(frog);
		for (IFrog frogNow:frogs) {
			
		}
	}




	public void moveBecauseOfWater() {
		for (IFrog frog : frogs) {
			int hasToMove = super.environment.hasToMove(frog.getPosition());
			System.out.println(frog + " at "+ frog.getPosition().absc);
			if (hasToMove ==0) {
				continue;
			}
			frog.move(hasToMove > 0 ?
					Direction.right :
					Direction.left);
		}
	}
	/**
	 * Teste si la partie est perdue et lance un �cran de fin appropri� si tel est
	 * le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		int sum = 0;
		for (IFrog frog : frogs) {
			if (!environment.isSafe(frog.getPosition())) {
				
				sum+=1;
			}
		}
		if( sum == frogs.size()) {
			System.out.println("YOU LOSE!");
			graphic.endGameScreen("You lose, your score  "+environment.getScreenPosition(),time);
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
		for (IFrog frog : frogs) {
			if (environment.isWinningPosition(frog.getPosition())) {
				System.out.println("GAME!");
				graphic.endGameScreen("GAME!",time);
				return true;
			}
		}
		return false;
	}

	// gere l'affichage
	private void show() {
		environment.showCar();
		for (IFrog frog : frogs) {
			this.graphic.add(new Element(frog.getOnScreenPosition(), Color.GREEN));
		}
	}
	
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
}
