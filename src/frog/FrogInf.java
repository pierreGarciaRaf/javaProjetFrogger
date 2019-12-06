package frog;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;

public class FrogInf extends Frog {

	/**
	 * class constructor
	 * 
	 * @param game      a reference to the game
	 * @param position  the starting position of the frog
	 * @param direction
	 * @param keys      the keyboard keys that control the frog
	 */
	public FrogInf(Game game, Case position, Direction direction, ArrayList<Integer> controls) {
		super(game, position, direction, controls);
	}

	/**
	 * overloded class constructor
	 * 
	 * @param game
	 * @param position
	 * @param direction
	 */
	public FrogInf(Game game, Case position, Direction direction) {
		super(game, position, direction);
	}

	/**
	 * overloaded class constructor
	 * 
	 * @param game
	 */
	public FrogInf(Game game) {
		super(game, new Case(game.width / 2, 1), Direction.up);
	}

	/**
	 * @return the position of the frog on the screen
	 */
	public Case getOnScreenPosition() {
		return new Case(m_position.absc, m_position.ord - m_game.getScreenPosition());
	}

	/**
	 * Returns a case that is within game bounds.
	 * 
	 * @param abs to limit with width
	 * @param ord to limit with height
	 * 
	 * @return Case gives a case that is the closest to given coords, while being in
	 *         bounds.
	 */
	protected static Case limitedCase(int abs, int ord) {
		while (abs > m_game.width - 1) {
			abs -= 1;
		}
		while (abs < 0) {
			abs += 1;
		}
		while (ord <= 0) {
			ord += 1;
		}
		return new Case(abs, ord);
	}

	public void moveFromInput(int key) {
		Direction whereToGo = controls.get(key);
		if (whereToGo != null) {
			move(super.controls.get(key));
		}
	}

	/**
	 * try to move the frog in the given direction
	 * 
	 * @param key
	 */
	public void move(Direction key) {
		switch (key) {
		case up:
			m_position = limitedCase(m_position.absc, m_position.ord + 1);
			break;
		case down:
			m_position = limitedCase(m_position.absc, m_position.ord - 1);
			break;
		case left:
			m_position = limitedCase(m_position.absc - 1, m_position.ord);
			break;
		case right:
			m_position = limitedCase(m_position.absc + 1, m_position.ord);
			break;
		}
	}
}