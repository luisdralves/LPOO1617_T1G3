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
		doorsAmmount = 1;
		doorsPos = new Coords[doorsAmmount];
		doorsPos[0] = new Coords(0,2);
		heroStartingPos = new Coords(1,1);
		heroHasClub = false;
		guardAmmount = 0;
		ogreMoves = true;
		ogreAmmount = 1;
		ogreStartingPos = new Coords[ogreAmmount];
		ogreStartingPos[0] = new Coords(3,1);
	}
}
