package environment;

import java.util.ArrayList;
import java.util.Random;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class EnvInf implements IEnvironment {

	private final int MAXSPEED = 40;
	private final int MINSPEED = 25;
	private int screenPosition;
	private Game game;
	private ArrayList<Lane> lanes = new ArrayList<>(0);

	Random rand = new Random();
	GeneratorState genLanes;

	/*
	 * class constructor
	 * 
	 * @param game
	 */
	public EnvInf(Game game) {
		screenPosition = 0;
		this.game = game;
		this.genLanes = new GeneratorState(6, 0.4f);
		this.lanes = new ArrayList<Lane>(0);
		lanes.add(new Road(this.game, 0, 1, true, 0));
		lanes.add(new Road(this.game, 1, 1, true, 0));

		for (int ord = 2; ord < this.game.height; ord++) {
			addAndUpdateGenerator(1, ord);
		}
	}

	/*
	 * test if a position is safe
	 * 
	 * @param c
	 */
	public boolean isSafe(Case c) {
		return lanes.get(c.ord).isSafe(c);
	}

	/*
	 * test if a position is a wining position. Since the game is infinite, always
	 * return false
	 * 
	 * @param c
	 */
	public boolean isWinningPosition(Case c) {
		return false;
	}

	/*
	 * check if an object at this position must move
	 * 
	 * @param c
	 */
	public int hasToMove(Case c) {
		return lanes.get(c.ord).hasToMove(c);
	}

	/*
	 * give a random int in the speed interval
	 */
	private int generateSpeed() {
		return this.rand.nextInt(MAXSPEED - MINSPEED) + MINSPEED + 1;
	}

	/*
	 * add a lane using the generator, may be any type of lane
	 * 
	 * @param speed the speed of objects in the lane
	 * 
	 * @param ord ordinate of the lane
	 */
	private void addAndUpdateGenerator(int speed, int ord) {
		switch (genLanes.generate()) {
		case 'e':
			lanes.add(new Lane(this.game, ord, 0, false, 0));
			break;
		case 'w':
			lanes.add(new River(this.game, ord, generateSpeed(), this.rand.nextBoolean(), this.game.defaultDensity));
			break;
		case 'r':
			lanes.add(new Road(this.game, ord, generateSpeed(), this.rand.nextBoolean(), this.game.defaultDensity));
			break;

		}
		genLanes.update(speed);
	}

	/*
	 * update every lanes
	 */
	public void update() {
		this.screenPosition = game.getLastFrogPos().ord - 1;
		int loadedStart = this.screenPosition;
		int loadedEnd = loadedStart + this.game.height;

		int i = this.lanes.size() - 1;
		while (i < loadedEnd) {
			i = i + 1;
			addAndUpdateGenerator(1, i);
		}

		for (i = loadedStart; i < loadedEnd; i++) {
			this.lanes.get(i).update();
		}
	}

	/*
	 * show every cars and logs on screen
	 */
	public void showCar() {
		int loadedStart = this.screenPosition;
		int loadedEnd = loadedStart + this.game.height;
		for (int i = loadedStart; i < loadedEnd; i++) {
			this.lanes.get(i).show(i - loadedStart);
		}
	}

	/*
	 * return the postition of the screen
	 */
	public int getScreenPosition() {
		return screenPosition;
	}

}
