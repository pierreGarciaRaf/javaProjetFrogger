package frog;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;

public class FrogInf extends Frog {
	public FrogInf(Game game, Case position, Direction direction,ArrayList<Integer> controls) {
		super(game,position, direction, controls);
	}

	public FrogInf(Game game, Case position, Direction direction) {
		super(game, position, direction);
	}

	public FrogInf(Game game) {
		super(game, new Case(game.width/2, 1), Direction.up);
	}

	public Case getOnScreenPosition() {
		return new Case(m_position.absc, m_position.ord - m_game.getScreenPosition());
	}

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
	
	public void move(Direction key) {
		switch (key) {
		case up:
			m_position = limitedCase(m_position.absc, m_position.ord+1);
			break;
		case down:
			m_position = limitedCase(m_position.absc, m_position.ord-1);
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