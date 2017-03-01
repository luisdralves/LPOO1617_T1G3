package dkeep.logic;

import java.util.Random;

public class Ogre extends Agent {
	Coords club;

	public Ogre() {
		coords = new Coords();
		club = new Coords();
		c = 'O';
	}

	public Ogre(int x, int y) {
		coords = new Coords(x, y);
		club = new Coords(x, y);
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
	}

}
