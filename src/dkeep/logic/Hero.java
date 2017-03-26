package dkeep.logic;

/**
 * Implements the player controllable game agent
 * 
 * @author Miguel
 *
 */
public class Hero extends Agent {
	protected boolean hasKey;
	protected boolean hasClub;

	/**
	 * Initialises a hero on the specified position
	 * 
	 * @param x
	 *            X position
	 * @param y
	 *            Y position
	 */
	public Hero(int x, int y) {
		coords = new Coords(x, y);
		c = 'H';
		hasKey = false;
		hasClub = false;
	}

	public Hero() {
		coords = new Coords();
		c = 'H';
		hasKey = false;
		hasClub = false;
	}

	/**
	 * Does the hero have the main door key?
	 * 
	 * @return true if he has, false otherwise
	 */
	public boolean hasKey() {
		return hasKey;
	}

	/**
	 * Hero picks up the key, changing its character to 'K'
	 */
	public void aquiresKey() {
		this.hasKey = true;
		c = 'K';
	}

	/**
	 * Hero drops the key, changing its character accordingly
	 */
	public void dropsKey() {
		this.hasKey = false;
		if (hasClub)
			c = 'A';
		else
			c = 'H';
	}

	/**
	 * Does the hero have a club?
	 * 
	 * @return true if he has, false otherwise
	 */
	public boolean hasClub() {
		return hasClub;
	}

	/**
	 * Hero picks up a club, changing its character to 'A'
	 */
	public void aquiresClub() {
		this.hasClub = true;
		c = 'A';
	}

	/**
	 * Hero drops the club, changing its character accordingly
	 */
	public void dropsClub() {
		this.hasClub = false;
		c = 'H';
	}

	/**
	 * Move the hero to a new position
	 * 
	 * @param newPos
	 *            Hero's destination
	 */
	public void move(Coords newPos) {
		coords = newPos;
	}

}
