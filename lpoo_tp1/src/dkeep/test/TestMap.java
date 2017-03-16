package dkeep.test;

import dkeep.logic.Coords;
import dkeep.logic.GameMap;

public class TestMap extends GameMap {
	public TestMap(char[][] map) {
		name = "test";
		this.map = map;
		hasLevers = true;
		doorsAreOpen = false;
		doorsAmount = 2;
		doorsPos = new Coords[doorsAmount];
		doorsPos[0] = new Coords(0,2);
		doorsPos[1] = new Coords(0,3);
		heroStartingPos = new Coords(1,1);
		heroHasClub = false;
		guardMoves = false;
		guardAmount = 1;
		guardStartingPos = new Coords[guardAmount];
		guardStartingPos[0] = new Coords(3,1);
		guardTypes = new String[guardAmount];
		guardTypes[0] = "rookie";
		guardMovements = new String[guardAmount];
		guardMovements[0] = "";
		ogreAmount = 0;
	}
}
