package dkeep.logic;

import java.util.Random;

public class Ogre extends Agent {
	Coords club;
	protected int sleeping;

	public Ogre() {
		coords = new Coords();
		club = new Coords();
		sleeping = 0;
		c = 'O';
	}

	public Ogre(int x, int y) {
		coords = new Coords(x, y);
		club = new Coords(x, y);
		sleeping = 0;
		this.c = 'O';
	}

	public Coords newCoords() {
		Coords ret = getCoords();
		Random rand = new Random();
		int prob = rand.nextInt(4);
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
		int prob;
		if (c == 'O') {
			coords = newPos;
		} else if (c == '8') {
			if (sleeping < 2)
				sleeping++;
			else
				c = 'O';
		}
		prob = rand.nextInt(4);
		club = getCoords();
		switch (prob) {
		case 0:
			club.addY(-1);
			break;
		case 1:
			club.addY(1);
			break;
		case 2:
			club.addX(-1);
			break;
		case 3:
			club.addX(1);
			break;
		}
	}

	public Coords getClub() {
		return new Coords(club.getX(), club.getY());
	}

	public void sleepNow() {
		c = '8';
		sleeping = 0;
	}

}
