package dkeep.logic;

public class Hero {
	protected int x;
	protected int y;
	protected char c;

	public Hero(int x, int y) {
		this.x = x;
		this.y = y;
		c = 'H';
	}

	public Hero() {
		x = 0;
		y = 0;
		c = 'H';
	}

	public void newPos(int x, int y) {
		this.x = x;
		this.y = y;
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
