package gameCommons;

public interface IFrog {

	public Case getOnScreenPosition();

	/**
	 * Donne la position actuelle de la grenouille
	 * 
	 * @return
	 */
	public Case getPosition();

	/**
	 * Donne la direction de la grenouille, c'est � dire de son dernier mouvement
	 * 
	 * @return
	 */
	public Direction getDirection();
	
	/**
	 * moves the frog using it's personnal inputs
	 * @param key used.
	 */
	public void moveFromInput(int key);

	/**
	 * D�place la grenouille dans la direction donn�e et teste la fin de partie
	 * 
	 * @param key
	 */
	public void move(Direction key);

}
