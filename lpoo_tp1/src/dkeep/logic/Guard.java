package dkeep.logic;

public abstract class Guard {
	protected Coords coords;
	protected String movement;
	protected int iterator;
	protected char c;

	public Guard() {
		coords = new Coords();
		movement = "";
		iterator = 0;
	}

	public abstract void move();

	protected void reverse() {
		String newMovement = "";
		for (int i = 0; i < movement.length(); i++) {
			switch(movement.charAt(i)) {
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
		//iterator = movement.length() - iterator;
	}

	public Coords getCoords() {
		return new Coords(coords.getX(), coords.getY());
	}

	public int getX() {
		return coords.getX();
	}

	public int getY() {
		return coords.getY();
	}

	public char getC() {
		return c;
	}
}
