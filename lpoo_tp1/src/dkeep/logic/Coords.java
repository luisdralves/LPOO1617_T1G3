package dkeep.logic;

public class Coords {
	protected int x;
	protected int y;

	Coords() {
		x = 0;
		y = 0;
	}

	Coords(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void addX(int i) {
		x += i;
	}

	public void addY(int i) {
		y += i;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
