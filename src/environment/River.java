package environment;

import java.awt.Color;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class River extends Lane {

	private boolean hasToMove = false;

	public River(Game game, int ord, int speed, boolean leftToRight, double density) {
		super(game, ord, speed, leftToRight, density);
		for (int time = 0; time < 300; time += 1) {
			this.update();
		}
		hasToMove = false;
	}
	
	private void addToGraphics() {
		for (int absc = 0; absc < super.game.width; absc += 1) {
			game.getGraphic().add(new Element(absc , super.ord - game.getScreenPosition(), new Color(0x0000f8)));
		}	
	}
	
	public void update() {
		this.mayAddLog();
		super.update();
		if (this.timer >= phase -speed) {
			hasToMove = true;
		}
		else {
			hasToMove = false;
		}

		// A chaque tic d'horloge, une voiture peut �tre ajout�e
		

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas
	}
	
	public boolean isSafe(Case c) {
		return !super.isSafe(c);
	}
	
	public int hasToMove(Case c) {
		if (hasToMove) {
			this.hasToMove = false;
			return super.leftToRight ? 1 : -1;
			
		}
		else {
			return 0;
		}
		
	}
	
	public void show(int ord) {
		addToGraphics();
		super.show(ord);
	}



	private void mayAddLog() {
		if (! isSafe(getFirstCase()) && ! isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				vehicles.add(new Log(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}
}
