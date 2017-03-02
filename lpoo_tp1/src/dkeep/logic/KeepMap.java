package dkeep.logic;

import java.util.Random;

public class KeepMap extends GameMap {
	public KeepMap() {
		int maxOgres = 4;
		victoryMessage = "Congrats";
		lossMessage = "Slained by the ogre";
		map = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		hasLevers = false;
		keyDropped = true;
		keyPos = new Coords (8,1);
		doorsAreOpen = false;
		doorsAmmount = 1;
		doorsPos = new Coords[doorsAmmount];
		doorsPos[0] = new Coords(0, 1);
		heroStartingPos = new Coords(1,8);
		heroHasClub = true;
		guardAmmount = 0;
		Random rand = new Random();
		ogreAmmount = rand.nextInt(maxOgres) + 1;
		ogreStartingPos = new Coords[ogreAmmount];
		for (int i = 0; i < ogreAmmount; i++) {
			ogreStartingPos[i] = new Coords(rand.nextInt(8) + 1, rand.nextInt(5) + 1);
		}
	}
}
