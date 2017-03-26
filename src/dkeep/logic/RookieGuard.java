package dkeep.logic;

/**
 * The most simple guard
 * 
 * @author Miguel
 *
 */
public class RookieGuard extends Guard {
	/**
	 * Creates a new Rookie Guard with the specified position and path
	 * 
	 * @param x
	 *            X position
	 * @param y
	 *            Y position
	 * @param movement
	 *            Path the guard takes
	 * @param moving
	 *            true if the guard moves, false otherwise
	 */
	public RookieGuard(int x, int y, String movement, boolean moving) {
		coords = new Coords(x, y);
		this.c = 'G';
		this.movement = movement;
		this.moving = moving;
		iterator = 0;
	}

	/**
	 * Advances one position on its path, never changing
	 */
	@Override
	public void move() {
		switch (movement.charAt(iterator)) {
		case 'w':
			coords.addY(-1);
			break;
		case 's':
			coords.addY(1);
			break;
		case 'a':
			coords.addX(-1);
			break;
		case 'd':
			coords.addX(1);
			break;
		}
		iterator++;
		iterator %= movement.length();
	}
}
