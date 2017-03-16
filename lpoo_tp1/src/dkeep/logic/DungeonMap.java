package dkeep.logic;

import java.util.Random;

public class DungeonMap extends GameMap {
	public DungeonMap(String guardType) {
		name = "dungeon";
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
		doorsAmount = 2;
		doorsPos = new Coords[doorsAmount];
		doorsPos[0] = new Coords(0, 5);
		doorsPos[1] = new Coords(0, 6);
		heroStartingPos = new Coords(1, 1);
		heroHasClub = false;
		guardMoves = true;
		guardAmount = 1;
		guardMovements = new String[guardAmount];
		guardMovements[0] = "assssaaaaaasdddddddwwwww";
		guardStartingPos = new Coords[guardAmount];
		guardStartingPos[0] = new Coords(8, 1);
		guardTypes = new String[guardAmount];
		if (guardType == "") {
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
		} else {
			guardType = guardType.substring(0,1).toLowerCase() + guardType.substring(1);
			guardTypes[0] = guardType;
		}
		ogreAmount = 0;
	}
}
