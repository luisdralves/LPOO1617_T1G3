package dkeep.logic;

/**
 * This is the superclass for game agents that can move around the map and have
 * an associated position
 * 
 * @author Miguel
 *
 */
public class Agent {
	protected Coords coords;
	protected char c;

	/**
	 * Find the coordinates of a game agent
	 * 
	 * @return New Coords object corresponding to the requested coordinates
	 */
	public Coords getCoords() {
		return new Coords(coords.getX(), coords.getY());
	}

	/**
	 * Find the X coordinate of a game agent
	 * 
	 * @return Agent's position within the x axis
	 */
	public int getX() {
		return coords.getX();
	}

	/**
	 * Find the Y coordinate of a game agent
	 * 
	 * @return Agent's position within the y axis
	 */
	public int getY() {
		return coords.getY();
	}

	/**
	 * Get the character used to represent the game agent
	 * 
	 * @return Character used
	 */
	public char getC() {
		return c;
	}

	/**
	 * Calculate if a an agent is in an adjacent position to other agent
	 * 
	 * @param that
	 *            Agent to compare positions
	 * @return true if adjacent, false otherwise
	 */
	public boolean adjacent(Agent that) {
		return this.getCoords().adjacent(that.getCoords());
	}
}
