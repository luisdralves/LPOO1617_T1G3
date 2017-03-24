package dkeep.logic;

import java.util.Random;

public class KeepMap extends GameMap {
	public KeepMap(int ogreA) {
		int maxOgres = 4;
		name = "keep";
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
		keyPos = new Coords(8, 1);
		doorsAreOpen = false;
		doorsAmount = 1;
		doorsPos = new Coords[doorsAmount];
		doorsPos[0] = new Coords(0, 1);
		heroStartingPos = new Coords(1, 8);
		heroHasClub = true;
		guardAmount = 0;
		ogreMoves = true;
		ogreAttacks = true;
		Random rand = new Random();
		if (ogreA == -1) {
			ogreAmount = rand.nextInt(maxOgres) + 1;
		} else
			ogreAmount = ogreA;
		ogreStartingPos = new Coords[ogreAmount];
		for (int i = 0; i < ogreAmount; i++) {
			ogreStartingPos[i] = new Coords(rand.nextInt(8) + 1, rand.nextInt(5) + 1);
		}
	}

	public KeepMap(boolean handicapped) {
		this(1);
		ogreStartingPos = new Coords[ogreAmount];
		ogreStartingPos[0] = new Coords(5, 5);
		if (handicapped)
			ogreAttacks = false;
		else
			ogreMoves = false;
		map[7][1] = 'X';
		map[7][2] = 'X';
		map[8][2] = 'X';
	}
}
