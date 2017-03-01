package dkeep.logic;

public class Hero extends Agent {
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
		coords = new Coords(x, y);
		c = 'H';
		hasKey = false;
	}

	public Hero() {
		coords = new Coords();
		c = 'H';
		hasKey = false;
	}

	public void move(Coords newPos) {
		coords = newPos;
	}

}
