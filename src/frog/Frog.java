package frog;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IFrog;

public class Frog implements IFrog {

	private Game m_game;
	private Case m_position;
	private Direction m_direction;

	public Frog(Game game, Case position, Direction direction) {
		m_game = game;
		m_position = position;
		m_direction = direction;
	}

	public Frog(Game game) {
		this(game, new Case(0, 0), Direction.up);
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
	 * D�place la grenouille dans la direction donn�e et teste la fin de partie
	 * 
	 * @param key
	 */
	public void move(Direction key) {
		switch (key) {
		case up:
			m_position = new Case(m_position.absc, m_position.ord + 1);
			break;
		case down:
			m_position = new Case(m_position.absc, m_position.ord - 1);
			break;
		case left:
			m_position = new Case(m_position.absc - 1, m_position.ord);
			break;
		case right:
			m_position = new Case(m_position.absc + 1, m_position.ord);
			break;
		}
	}

}
