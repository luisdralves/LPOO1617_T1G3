package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Coords;
import dkeep.logic.Game;
import dkeep.logic.KeepMap;

public class TestOgreRandomness {
	@Test
	public void TestHeroMovesIntoFreeCell() {
		Game g = new Game(new KeepMap(), 0);
		assertEquals(new Coords(1, 1), g.getHeroPos());
		g.moveHero('s');
		assertEquals(new Coords(1, 2), g.getHeroPos());
	}
}
