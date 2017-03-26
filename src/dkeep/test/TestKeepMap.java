package dkeep.test;

import java.util.Random;

import dkeep.logic.Coords;
import dkeep.logic.GameMap;

public class TestKeepMap extends GameMap {
	public TestKeepMap(char[][] map) {
		name = "test_dungeon";
		this.map = map;
		hasLevers = false;
		keyDropped = true;
		keyPos = new Coords (1,3);
		doorsAreOpen = false;
		doorsAmount = 1;
		doorsPos = new Coords[doorsAmount];
		doorsPos[0] = new Coords(0,2);
		heroStartingPos = new Coords(1,1);
		heroHasClub = false;
		guardAmount = 0;
		ogreMoves = true;
		ogreAmount = 1;
		ogreStartingPos = new Coords[ogreAmount];
		ogreStartingPos[0] = new Coords(3,1);
	}
}
