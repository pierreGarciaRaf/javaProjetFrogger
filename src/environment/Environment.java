package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
	
	private Game game;
	
	public Environment(Game game){
		this.game = game;
	}
		
	//TODO
	/**
	 * Teste si une case est sure, c'est � dire que la grenouille peut s'y poser
	 * sans mourir
	 * 
	 * @param c la case � tester
	 * @return vrai s'il n'y a pas danger
	 */
	public boolean isSafe(Case c) {
		return false;
		
	}

	/**
	 * Teste si la case est une case d'arrivee
	 * 
	 * @param c
	 * @return vrai si la case est une case de victoire
	 */
	public boolean isWinningPosition(Case c) {
		return false;
		
	}

	/**
	 * Effectue une �tape d'actualisation de l'environnement
	 */
	public void update() {
		
	}
}
