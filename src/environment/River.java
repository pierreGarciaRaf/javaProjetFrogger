package environment;

import gameCommons.Game;

public class River extends Lane {

	public River(Game game, int ord, int speed, boolean leftToRight, double density) {
		super(game, ord, speed, leftToRight, density);
		for (int time = 0; time < 300; time += 1) {
			this.update();
		}
	}

	public void update() {

	}

	public void show(int ord) {
		
	}

	public void show() {
		show(this.ord);
	}
}
