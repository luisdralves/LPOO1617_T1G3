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
	public void TestHeroMovesIntoFreeCell() {
		Game g = new Game(new TestMap(map), 0);
		assertEquals(new Coords(1, 1), g.getHeroPos());
		g.moveHero('s');
		assertEquals(new Coords(1, 2), g.getHeroPos());
	}
	
	@Test
	public void TestHeroTriesToMoveIntoWall() {
		Game g = new Game(new TestMap(map), 0);
		assertEquals(new Coords(1, 1), g.getHeroPos());
		g.moveHero('a');
		assertEquals(new Coords(1, 1), g.getHeroPos());
		assertFalse(g.isGameOver());
	}

	@Test
	public void TestHeroisCaughtByGuard() {
		Game g = new Game(new TestMap(map), 0);
		assertFalse(g.isGameOver());
		g.moveHero('d');
		assertTrue(g.isGameOver());
	}
	
	@Test
	public void TestHeroTriesToLeaveThroughClosedDoors() {
		Game g = new Game(new TestMap(map), 0);
		assertEquals(new Coords(1, 1), g.getHeroPos());
		g.moveHero('s');
		assertEquals(new Coords(1, 2), g.getHeroPos());
		g.moveHero('a');
		assertEquals(new Coords(1, 2), g.getHeroPos());
		assertFalse(g.isGameOver());
	}
	
	@Test
	public void TestHeroOpensDoors() {
		Game g = new Game(new TestMap(map), 0);
		assertEquals(new Coords(1, 1), g.getHeroPos());
		g.moveHero('s');
		assertEquals(new Coords(1, 2), g.getHeroPos());
		g.moveHero('s');
		assertEquals(g.getC(new Coords(0,2)), 'S');
		assertEquals(g.getC(new Coords(0,3)), 'S');
		assertFalse(g.isGameOver());
	}
	
	@Test
	public void TestHeroOpensDoorsAndLeaves() {
		Game g = new Game(new TestMap(map), 0);
		assertEquals(new Coords(1, 1), g.getHeroPos());
		g.moveHero('s');
		assertEquals(new Coords(1, 2), g.getHeroPos());
		g.moveHero('s');
		assertEquals(g.getC(new Coords(0,2)), 'S');
		assertEquals(g.getC(new Coords(0,3)), 'S');
		g.moveHero('a');
		assertTrue(g.isGameOver());
	}
}
