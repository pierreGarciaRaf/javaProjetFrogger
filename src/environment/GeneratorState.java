package environment;

import java.util.Random;

public class GeneratorState {
	private int now;
	public int pseudoLoop;
	private float regularity;
	Random rn;

	/**
	 * Class constructor
	 * this class allows a map generation of this form:
	 * 2 or 4 waters, then roads, then one empty lane before 
	 * next waters.
	 * 
	 * @param pseudoLoop : the minimum time the generator can take to repeat
	 * 
	 * @param regularityProba : between zero and one:
	 * 							-at 0 : minimum repeating length always get chose
	 * 							-between : repeating length is usually longer if closer to 1.
	 * 							-at 1 : there's no repeat
	 */
	GeneratorState(int pseudoLoop, float regularityProba) {
		this.pseudoLoop = pseudoLoop;
		this.regularity = regularityProba;
		this.rn = new Random();
	}

	/**
	 * Overloded class constructor
	 * 
	 * @param pseudoLoop the minimum time the generator can take to repeat.
	 */
	GeneratorState(int pseudoLoop) {
		this(pseudoLoop, 0.8f);
	}

	/**
	 * speed higher gets closer to the end of the pseudo - cycle
	 * sometimes (based on regularity) does nothing. 
	 */
	public void update(int speed) {
		if (rn.nextFloat() < regularity) {
			now += speed;
		}
	}

	/**
	 * allows to get the generator to tell what type of lane to put and change itself.
	 * @return char : the type of lane to put:
	 * 					-'e' : empty lane.
	 * 					-'w' : river/water lane.
	 * 					-'r' : road lane.
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
