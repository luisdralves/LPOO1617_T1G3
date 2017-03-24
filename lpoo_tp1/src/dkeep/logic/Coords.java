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

	@Override
	public boolean equals(Object that) {
		return this.getX() ==  ((Coords) that).getX() && this.getY() == ((Coords) that).getY();
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
