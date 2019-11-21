package frog;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;

public class FrogInf extends Frog {

	public FrogInf(Game game, Case position, Direction direction) {
		super(game, position, direction);
	}

	public FrogInf(Game game) {
		super(game, new Case(game.width/2, 1), Direction.up);
	}

	public Case getOnScreenPosition() {
		return new Case(m_position.absc, 1);
	}

	public void move(Direction key) {
		switch (key) {
		case up:
			m_position = limitedCase(m_position.absc, m_position.ord + 1);
			m_game.testLose();
			break;
		case down:
			m_position = limitedCase(m_position.absc, m_position.ord - 1);
			m_game.testLose();
			break;
		case left:
			m_position = limitedCase(m_position.absc - 1, m_position.ord);
			m_game.testLose();
			break;
		case right:
			m_position = limitedCase(m_position.absc + 1, m_position.ord);
			m_game.testLose();
			break;
		}
	}
}