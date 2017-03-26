package dkeep.logic;

import java.util.Random;

/**
 * Implements the enemy for the second level (may be used elsewhere)
 * 
 * @author Miguel
 *
 */
public class Ogre extends Agent {
	protected Coords club;
	protected int sleeping;
	protected boolean hasClub;
	protected boolean moving;

	/**
	 * Creates an ogre with default values
	 */
	public Ogre() {
		coords = new Coords();
		hasClub = true;
		if (hasClub())
			club = new Coords();
		sleeping = 0;
		moving = false;
		c = 'O';
	}

	/**
	 * Creates an ogre with the specified position and behaviour
	 * 
	 * @param x
	 *            X position
	 * @param y
	 *            Y position
	 * @param move
	 *            Whether the ogre moves or not
	 * @param attack
	 *            Whether the ogre attacks or not
	 */
	public Ogre(int x, int y, boolean move, boolean attack) {
		coords = new Coords(x, y);
		hasClub = true;
		if (hasClub())
			club = new Coords(x, y);
		sleeping = 0;
		hasClub = attack;
		moving = move;
		this.c = 'O';
	}

	/**
	 * The ogre moves in a random direction, this method decides its new
	 * position
	 * 
	 * @return Coords object corresponding to the ogre's new position
	 */
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

	/**
	 * Moves the ogre to the specified position if awake, decreases remaining
	 * sleep time if sleeping
	 * 
	 * @param newPos
	 */
	public void move(Coords newPos) {
		if (c == 'O' && moving) {
			coords = newPos;
		} else if (c == '8') {
			if (sleeping < 2)
				sleeping++;
			else
				c = 'O';
		}
	}

	/**
	 * Updates the ogre's club's postion, as it attacks in a random direction
	 */
	public void attack() {
		Random rand = new Random();
		int prob = rand.nextInt(4);
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

	/**
	 * Get the ogre's club's position
	 * 
	 * @return Coords object with the position of the club
	 */
	public Coords getClub() {
		return new Coords(club.getX(), club.getY());
	}

	/**
	 * Is the ogre allowed to attack?
	 * 
	 * @return true if yes, false otherwise
	 */
	public boolean hasClub() {
		return hasClub;
	}

	/**
	 * The ogre was beaten by the hero and sleeps for 2 turns
	 */
	public void sleepNow() {
		c = '8';
		sleeping = 0;
	}

}
