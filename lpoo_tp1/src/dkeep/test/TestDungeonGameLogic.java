package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Coords;
import dkeep.logic.Game;

public class TestDungeonGameLogic {
	char[][] map = new char[][] { 	{ 'X', 'X', 'X', 'X', 'X' }, 
									{ 'X', 'H', ' ', 'G', 'X' }, 
									{ 'I', ' ', ' ', ' ', 'X' },
									{ 'I', 'k', ' ', ' ', 'X' }, 
									{ 'X', 'X', 'X', 'X', 'X' } };

	@Test
	public void TestMoveHeroIntoFreeCell() {
		Game g = new Game(new TestMap(map));
		assertEquals(new Coords(1, 1), g.getHeroPos());
		g.moveHero('s');
		assertEquals(new Coords(1, 2), g.getHeroPos());
	}

	@Test
	public void TestHeroisCaughtByGuard() {

	}
}
