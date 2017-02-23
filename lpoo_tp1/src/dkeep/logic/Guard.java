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
