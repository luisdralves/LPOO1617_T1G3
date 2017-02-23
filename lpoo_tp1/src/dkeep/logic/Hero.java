package dkeep.logic;

public class Hero {
	protected int x;
	protected int y;
	protected char c;
	protected boolean hasKey;

	public boolean hasKey() {
		return hasKey;
	}

	public void aquiresKey() {
		this.hasKey = true;
		c = 'K';
	}
	
	public void dropsKey() {
		this.hasKey = false;
		c = 'H';
	}

	public Hero(int x, int y) {
		this.x = x;
		this.y = y;
		c = 'H';
		hasKey = false;
	}

	public Hero() {
		x = 0;
		y = 0;
		c = 'H';
		hasKey = false;
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
