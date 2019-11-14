package frog;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IFrog;

public class Frog implements IFrog {

	private Game m_game;
	private int m_x;
	private int m_y;
	private Direction m_direction;

	public Frog(Game game, int x, int y, Direction direction) {
		m_game = game;
		m_x = x;
		m_y = y;
		m_direction = direction;
	}

	public Frog(Game game) {
		this(game, 0, 0, Direction.up);
	}

	/**
	 * Donne la position actuelle de la grenouille
	 * 
	 * @return
	 */
	public Case getPosition() {

	}

	/**
	 * Donne la direction de la grenouille, c'est � dire de son dernier mouvement
	 * 
	 * @return
	 */
	public Direction getDirection() {

	}

	/**
	 * D�place la grenouille dans la direction donn�e et teste la fin de partie
	 * 
	 * @param key
	 */
	public void move(Direction key) {

	}

}
