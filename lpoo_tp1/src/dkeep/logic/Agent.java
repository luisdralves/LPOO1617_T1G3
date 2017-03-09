package dkeep.logic;

public class Agent {
	protected Coords coords;
	protected char c;
	
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
	
	public boolean adjacent(Agent that) {
		return this.getCoords().adjacent(that.getCoords());
	}
}
