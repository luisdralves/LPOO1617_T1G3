package dkeep.logic;

/**
 * Superclass to the all guards, contains common methods and variables
 * 
 * @author Miguel
 *
 */
public abstract class Guard extends Agent {
	protected String movement;
	protected int iterator;
	protected boolean moving;

	/**
	 * Empty constructor initialising the guard with default values
	 */
	public Guard() {
		coords = new Coords();
		movement = "";
		moving = true;
		iterator = 0;
	}

	/**
	 * Abstract function to move the guard
	 */
	public abstract void move();

	/**
	 * Reverses the path that the guard takes along the map
	 */
	protected void reverse() {
		String newMovement = "";
		for (int i = 0; i < movement.length(); i++) {
			switch (movement.charAt(i)) {
			case 'a':
				newMovement += 'd';
				break;
			case 'd':
				newMovement += 'a';
				break;
			case 's':
				newMovement += 'w';
				break;
			case 'w':
				newMovement += 's';
				break;
			}
		}
		movement = newMovement;
		// iterator = movement.length() - iterator;
	}
}
