package gameCommons;

public interface IEnvironment {
	/**
	 * Gives the screen position.
	 * 
	 * @return screen position.
	 */
	public int getScreenPosition();

	/**
	 * Teste si une case est sure, c'est � dire que la grenouille peut s'y poser
	 * sans mourir
	 * 
	 * @param c la case � tester
	 * @return vrai s'il n'y a pas danger
	 */
	public boolean isSafe(Case c);
	
	/**
	 * Tests if a case has to move, if the frog on it needs to move sideways.
	 * @param c case to test
	 * @return the distance the frog has to move horizontally
	 */
	public int hasToMove(Case c);

	/**
	 * Teste si la case est une case d'arrivee
	 * 
	 * @param c
	 * @return vrai si la case est une case de victoire
	 */
	public boolean isWinningPosition(Case c);

	/**
	 * Effectue une �tape d'actualisation de l'environnement
	 */
	public void update();

	/**
	 * Affiche les voitures
	 */
	public void showCar();

}
