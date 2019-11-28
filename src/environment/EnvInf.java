package environment;

import java.util.ArrayList;
import java.util.Random;


import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import gameCommons.IFrog;

public class EnvInf implements IEnvironment {

	private final int MAXSPEED = 3;
	private int screenPosition;
	private Game game;
	private ArrayList<Lane> lanes = new ArrayList<>(0);
	private IFrog frog;
	Random rand = new Random();

	public EnvInf(Game game, IFrog frog) {
		screenPosition = 0;
		this.game = game;
		this.frog = frog;
		
		lanes.add(new Road(this.game, 0, 1, true, 0));
		lanes.add(new Road(this.game, 1, 1, true, 0));
		
		for (int ord = 2; ord < this.game.height; ord++) {
			lanes.add(new Road(this.game, ord, this.rand.nextInt(MAXSPEED) + 1, this.rand.nextBoolean(), this.game.defaultDensity));
		}
	}

	@Override
	public boolean isSafe(Case c) {
		return lanes.get(c.ord).isSafe(c);
	}

	@Override
	public boolean isWinningPosition(Case c) {
		return false;
	}

	@Override
	public void update() {
		this.screenPosition = frog.getPosition().ord - 1;		
		int loadedStart = this.screenPosition;
		int loadedEnd = loadedStart + this.game.height;
		
		int i = this.lanes.size() - 1;
		while(i < loadedEnd) {
			i=i+1;
			lanes.add(new Lane(this.game, i, this.rand.nextInt(MAXSPEED) + 1, this.rand.nextBoolean(), this.game.defaultDensity));
		}
		
		for(i = loadedStart; i < loadedEnd; i++) {
				this.lanes.get(i).update();
		}
	}
	
	@Override
	public void showCar() {
		int loadedStart = this.screenPosition;
		int loadedEnd = loadedStart + this.game.height;
		for(int i = loadedStart; i < loadedEnd; i++) {
				this.lanes.get(i).show(i - loadedStart);
		}	
	}
	public int getScreenPosition() {
		return screenPosition;
	}

}
