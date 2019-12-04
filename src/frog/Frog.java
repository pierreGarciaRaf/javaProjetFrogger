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
	protected HashMap<Integer,Direction> controls;
	
	public Frog(Game game, Case position, Direction direction, ArrayList<Integer> keys){
		m_game = game;
		m_position = position;
		m_direction = direction;
		controls = new HashMap<Integer,Direction>(0);
		controls.put(keys.get(0), Direction.left);
		controls.put(keys.get(1), Direction.up);
		controls.put(keys.get(2), Direction.right);
		controls.put(keys.get(3), Direction.down);
	}
	private static ArrayList<Integer>basicControls() {
		ArrayList<Integer> controls = new ArrayList<Integer>(0);
		controls.add(KeyEvent.VK_LEFT);
		controls.add(KeyEvent.VK_UP);
		controls.add(KeyEvent.VK_RIGHT);
		controls.add(KeyEvent.VK_DOWN);
		return controls;
	}
	
	public Frog(Game game, Case position, Direction direction) {
		this(game,position,direction,basicControls());
	}

	public Frog(Game game) {
		this(game, new Case(game.width / 2, 0), Direction.up);
	}
	

	/**
	 * Donne la position actuelle de la grenouille
	 * 
	 * @return
	 */
	public Case getPosition() {
		return m_position;
	}

	/**
	 * Donne la direction de la grenouille, c'est � dire de son dernier mouvement
	 * 
	 * @return
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

	
	public void moveFromInput(int key) {
		Direction whereToGo = controls.get(key);
		if (whereToGo != null) {
			move(controls.get(key));
		}
	}
	/**
	 * D�place la grenouille dans la direction donn�e et teste la fin de partie
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

	@Override
	public Case getOnScreenPosition() {
		return m_position;
	}
}
