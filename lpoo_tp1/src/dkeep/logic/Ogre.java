package dkeep.logic;

import java.util.Random;

public class Ogre {
	protected Coords coords;
	protected int clubX;
	protected int clubY;
	protected char c;

	public Ogre() {
		coords = new Coords();
		c = 'O';
	}

	public Ogre(int x, int y) {
		coords = new Coords(x, y);
		this.c = 'O';
	}

	public Coords newCoords() {
		Coords ret = getCoords();
		Random rand = new Random();
		int prob = rand.nextInt(3);
		switch (prob) {
		case 0:
			ret.addY(-1);
			break;
		case 1:
			ret.addY(1);
			break;
		case 2:
			ret.addX(-1);
			break;
		case 3:
			ret.addX(1);
			break;
		}
		return ret;
	}

	public void move(Coords newPos) {
		Random rand = new Random();
		int prob = rand.nextInt(3);
		if (c == 'O') {
			coords = newPos;
			prob = rand.nextInt(100);
			if (prob < 25) {
				c = '8';
			}
		} else if (c == '8') {
			prob = rand.nextInt(100);
			if (prob < 25) {
				c = 'O';
			}
		}
	}
	
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
}
