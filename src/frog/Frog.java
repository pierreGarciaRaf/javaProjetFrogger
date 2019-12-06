package frog;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IFrog;

public class Frog implements IFrog {

	protected static Game m_game;
	protected Case m_position;
	protected Direction m_direction;
	protected HashMap<Integer, Direction> controls;

	/**
	 * class constructor
	 * 
	 * @param game      a reference to the game
	 * @param position  the starting position of the frog
	 * @param direction
	 * @param keys      the keyboard keys that control the frog
	 */
	public Frog(Game game, Case position, Direction direction, ArrayList<Integer> keys) {
		m_game = game;
		m_position = position;
		m_direction = direction;
		controls = new HashMap<Integer, Direction>(0);
		controls.put(keys.get(0), Direction.left);
		controls.put(keys.get(1), Direction.up);
		controls.put(keys.get(2), Direction.right);
		controls.put(keys.get(3), Direction.down);
	}

	/**
	 * default control
	 * 
	 * @return
	 */
	private static ArrayList<Integer> basicControls() {
		ArrayList<Integer> controls = new ArrayList<Integer>(0);
		controls.add(KeyEvent.VK_LEFT);
		controls.add(KeyEvent.VK_UP);
		controls.add(KeyEvent.VK_RIGHT);
		controls.add(KeyEvent.VK_DOWN);
		return controls;
	}

	/**
	 * overloded class constructor
	 * 
	 * @param game
	 * @param position
	 * @param direction
	 */
	public Frog(Game game, Case position, Direction direction) {
		this(game, position, direction, basicControls());
	}

	/**
	 * overloaded class constructor
	 * 
	 * @param game
	 */
	public Frog(Game game) {
		this(game, new Case(game.width / 2, 0), Direction.up);
	}

	/**
	 * give the curent position of the frog
	 * 
	 * @return a case
	 */
	public Case getPosition() {
		return m_position;
	}

	/**
	 * @return the last movement of the frog
	 */
	public Direction getDirection() {
		return m_direction;
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
		while (ord > m_game.height - 1) {
			ord -= 1;
		}
		while (ord < 0) {
			ord += 1;
		}
		return new Case(abs, ord);
	}

	/**
	 * move the frog in the direction binded on the key
	 * 
	 * @param a keyboard key
	 */
	public void moveFromInput(int key) {
		Direction whereToGo = controls.get(key);
		if (whereToGo != null) {
			move(controls.get(key));
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
			m_game.testWin();
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
		default:
			break;
		}
	}

	/**
	 * @return the current position
	 */
	public Case getOnScreenPosition() {
		return m_position;
	}
}
