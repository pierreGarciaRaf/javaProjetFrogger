package environment;

import java.awt.Color;
import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class River extends Lane {

	public River(Game game, int ord, int speed, boolean leftToRight, double density) {
		super(game, ord, speed, leftToRight,Math.pow(density, 0.1));
		for (int time = 0; time < 300; time += 1) {
			this.update();
		}
	}
	
	private void addToGraphics() {
		for (int absc = 0; absc < super.game.width; absc += 1) {
			game.getGraphic().add(new Element(absc , super.ord - game.getScreenPosition(), new Color(0x654321)));
		}	
	}
	
	public void update() {
		this.timer += 1;
		
		if (this.timer == this.speed) {
			this.timer = 0;
			int increaser = -1;
			if (this.leftToRight) {
				increaser = 1;
			}
			for (Vehicle vehicle : this.vehicles) {
				vehicle.updateAndMoveTo(new Case(vehicle.getCase().absc + increaser, vehicle.getCase().ord));
			}
		}

		// A chaque tic d'horloge, une voiture peut �tre ajout�e
		this.mayAddWater();

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas
		ArrayList<Vehicle> buffer = new ArrayList<Vehicle>(this.vehicles);
		for (Vehicle vehicle : buffer) {
			if (vehicle.getCase().absc < -5 || vehicle.getCase().absc > this.game.width + 5) {
				this.vehicles.remove(vehicle);
			}
		}
	}
	
	public int hasToMove(Case c) {
		if (timer == this.speed-1) {
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



	private void mayAddWater() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				vehicles.add(new Water(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}
}
