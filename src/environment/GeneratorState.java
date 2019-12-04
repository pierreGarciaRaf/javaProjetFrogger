package environment;

import java.util.Random;

public class GeneratorState {
	private int now;
	public int pseudoLoop;
	private float regularity;
	Random rn;

	/*
	 * Class constructor
	 * 
	 * @param pseudoLoop
	 * 
	 * @param regularityProba
	 */
	GeneratorState(int pseudoLoop, float regularityProba) {
		this.pseudoLoop = pseudoLoop;
		this.regularity = regularityProba;
		this.rn = new Random();
	}

	/*
	 * Overloded class constructor
	 * 
	 * @param pseudoLoop
	 */
	GeneratorState(int pseudoLoop) {
		this(pseudoLoop, 0.8f);
	}

	/*
	 * 
	 */
	public void update(int speed) {
		if (rn.nextFloat() < regularity) {
			now += speed;
		}
	}

	/*
	 * 
	 */
	public char generate() {
		if (now >= pseudoLoop) {
			now = 0;
			return 'e';
		} else if (now <= 3) {
			now += 1;
			return 'w';
		} else
			return 'r';
	}
}
