package dkeep.logic;

import java.util.Random;

public class KeepMap extends GameMap {
	public KeepMap() {
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
		keyPos = new Coords (8,1);
		doorsAreOpen = false;
		doorsAmmount = 1;
		doorsPos = new Coords[doorsAmmount];
		doorsPos[0] = new Coords(0, 1);
		heroStartingPos = new Coords(1,8);
		heroHasClub = true;
		guardAmmount = 0;
		ogreMoves = true;
		ogreAttacks = true;
		Random rand = new Random();
		ogreAmmount = rand.nextInt(maxOgres) + 1;
		ogreStartingPos = new Coords[ogreAmmount];
		for (int i = 0; i < ogreAmmount; i++) {
			ogreStartingPos[i] = new Coords(rand.nextInt(8) + 1, rand.nextInt(5) + 1);
		}
	}
	public KeepMap(boolean handicapped) {
		this();
		this.ogreAmmount = 1;
		ogreStartingPos = new Coords[ogreAmmount];
		ogreStartingPos[0] = new Coords(5,5);
		if(handicapped)
			ogreAttacks = false;
		else
			ogreMoves = false;
		map[7][1] = 'X';
		map[7][2] = 'X';
		map[8][2] = 'X';
	}
}
