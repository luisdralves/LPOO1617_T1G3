package dkeep.logic;

public abstract class Guard {
	protected int x;
	protected int y;
	protected String movement;
	protected int iterator;
	protected char c;

	public Guard() {
		x = 0;
		y = 0;
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public char getC() {
		return c;
	}
}
