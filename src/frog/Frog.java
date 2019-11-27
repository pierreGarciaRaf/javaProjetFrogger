package frog;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IFrog;

public class Frog implements IFrog {

	protected static Game m_game;
	protected static Case m_position;
	protected static Direction m_direction;

	public Frog(Game game, Case position, Direction direction) {
		m_game = game;
		m_position = position;
		m_direction = direction;
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
		}
	}

	@Override
	public Case getOnScreenPosition() {
		return m_position;
	}
}
