package dkeep.logic;

public class Coords {
	protected int x;
	protected int y;

	public Coords() {
		x = 0;
		y = 0;
	}

	public Coords(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean adjacent(Coords that) {
		int x1 = this.getX();
		int y1 = this.getY();
		int x2 = that.getX();
		int y2 = that.getY();
		return ((x1 == x2 && (y1 == y2 - 1 || y1 == y2 + 1)) || (y1 == y2 && (x1 == x2 - 1 || x1 == x2 + 1))
				|| (x1 == x2 && y1 == y2));
	}

	public boolean equals(Coords that) {
		return this.getX() == that.getX() && this.getY() == that.getY();
	}

	public void addX(int i) {
		x += i;
	}

	public void addY(int i) {
		y += i;
	}

	public Coords add(Coords c2) {
		Coords ret = new Coords(this.getX(), this.getY());
		ret.addX(c2.getX());
		ret.addY(c2.getY());
		return ret;
	}

	public int getX() {
		return new Integer(x);
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return new Integer(y);
	}

	public void setY(int y) {
		this.y = y;
	}

}
