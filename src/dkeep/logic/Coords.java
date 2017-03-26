package dkeep.logic;

/**
 * This class is used to represent positions using x and y coordinates
 * 
 * @author Miguel
 *
 */
public class Coords {
	protected int x;
	protected int y;

	/**
	 * Constructor to initialise both coordinates at 0
	 */
	public Coords() {
		x = 0;
		y = 0;
	}

	/**
	 * Constructor that creates a new object based on the x and y parameters
	 * 
	 * @param x
	 *            X coordinate
	 * @param y
	 *            Y coordinate
	 */
	public Coords(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Calculates if a Coordinates object is adjacent to another
	 * 
	 * @param that
	 *            Coordinates object to be compared
	 * @return true if adjacent, false otherwise
	 */
	public boolean adjacent(Coords that) {
		int x1 = this.getX();
		int y1 = this.getY();
		int x2 = that.getX();
		int y2 = that.getY();
		return ((x1 == x2 && (y1 == y2 - 1 || y1 == y2 + 1)) || (y1 == y2 && (x1 == x2 - 1 || x1 == x2 + 1))
				|| (x1 == x2 && y1 == y2));
	}

	@Override
	public boolean equals(Object that) {
		return this.getX() == ((Coords) that).getX() && this.getY() == ((Coords) that).getY();
	}

	/**
	 * Adds the given parameter to the x coordinate
	 * 
	 * @param i
	 *            amount to increase x (may be negative)
	 */
	public void addX(int i) {
		x += i;
	}

	/**
	 * Adds the given parameter to the y coordinate
	 * 
	 * @param i
	 *            amount to increase y (may be negative)
	 */
	public void addY(int i) {
		y += i;
	}

	/**
	 * Adds two coordinates without changing the values of either objects
	 * 
	 * @param c2
	 *            Coordinates to be added
	 * @return Resulting coordinates after the operation
	 */
	public Coords add(Coords c2) {
		Coords ret = new Coords(this.getX(), this.getY());
		ret.addX(c2.getX());
		ret.addY(c2.getY());
		return ret;
	}

	/**
	 * Calculate the direction something moved given its current and previous
	 * position
	 * 
	 * @param that
	 *            Old coordinates
	 * @return 's' if down, 'a' if left, 'w' if up, 'd' if right or by default
	 */
	public char directionMoved(Coords that) {
		Coords c1 = new Coords(this.getX(), this.getY());
		Coords c2 = new Coords(that.getX(), that.getY());
		c2.setX(c2.getX() - c1.getX());
		c2.setY(c2.getY() - c1.getY());
		if (c2.getX() == 0 && c2.getY() > 0)
			return 's';
		else if (c2.getX() == 0 && c2.getY() < 0)
			return 'w';
		else if (c2.getX() > 0 && c2.getY() == 0)
			return 'd';
		else if (c2.getX() < 0 && c2.getY() == 0)
			return 'a';
		else
			return 'd';
	}

	/**
	 * Get current position in x
	 * 
	 * @return Integer with the same value as the position on the x axis
	 */
	public int getX() {
		return new Integer(x);
	}

	/**
	 * Change the x position
	 * 
	 * @param x
	 *            New x position
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Get current position in y
	 * 
	 * @return Integer with the same value as the position on the y axis
	 */
	public int getY() {
		return new Integer(y);
	}

	/**
	 * Change the y position
	 * 
	 * @param y
	 *            New y position
	 */
	public void setY(int y) {
		this.y = y;
	}

}
