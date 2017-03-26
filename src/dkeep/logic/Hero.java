package dkeep.logic;

public class Hero extends Agent {
	protected boolean hasKey;
	protected boolean hasClub;

	public Hero(int x, int y) {
		coords = new Coords(x, y);
		c = 'H';
		hasKey = false;
		hasClub = false;
	}

	public Hero() {
		coords = new Coords();
		c = 'H';
		hasKey = false;
		hasClub = false;
	}
	
	public boolean hasKey() {
		return hasKey;
	}

	public void aquiresKey() {
		this.hasKey = true;
		c = 'K';
	}

	public void dropsKey() {
		this.hasKey = false;
		if (hasClub)
			c = 'A';
		else
		c = 'H';
	}
	
	public boolean hasClub() {
		return hasClub;
	}

	public void aquiresClub() {
		this.hasClub = true;
		c = 'A';
	}

	public void dropsClub() {
		this.hasClub = false;
		c = 'H';
	}

	public void move(Coords newPos) {
		coords = newPos;
	}

}
