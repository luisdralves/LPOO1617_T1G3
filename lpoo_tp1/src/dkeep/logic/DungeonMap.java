package dkeep.logic;

import java.util.Random;

public class DungeonMap extends GameMap {
	public DungeonMap() {
		victoryMessage = "And just when you thought your captivity had ended, you realise you still have another challenge to overcome...go through the Keep's Crazy Ogre.";
		lossMessage = "Caught by the guard";
		map = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'X', ' ', ' ', ' ', 'I', ' ', 'X', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
				{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
				{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' },
				{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		hasLevers = true;
		doorsAreOpen = false;
		doorsAmmount = 2;
		doorsPos = new Coords[doorsAmmount];
		doorsPos[0] = new Coords(0,5);
		doorsPos[1] = new Coords(0,6);
		heroStartingPos = new Coords(1,1);
		heroHasClub = false;
		guardAmmount = 1;
		guardMovements = new String[guardAmmount];
		guardMovements[0] = "assssaaaaaasdddddddwwwww";
		guardStartingPos = new Coords[guardAmmount];
		guardStartingPos[0] = new Coords(8,1);
		guardTypes = new String[guardAmmount];
		Random rand = new Random();
		int prob = rand.nextInt(3);
		switch (prob) {
		case 0:
			guardTypes[0] = "rookie";
			break;
		case 1:
			guardTypes[0] = "drunken";
			break;
		case 2:
			guardTypes[0] = "suspicious";
			break;
		}
		ogreAmmount = 0;
	}
}
