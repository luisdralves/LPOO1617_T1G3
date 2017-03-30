package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.logic.Coords;
import dkeep.logic.Game;

public class TestDungeonGameLogic {
	char[][] dungeon = new char[][] { 	{ 'X', 'X', 'X', 'X', 'X' }, 
										{ 'X', ' ', ' ', ' ', 'X' }, 
										{ 'I', ' ', ' ', ' ', 'X' },
										{ 'I', 'k', ' ', ' ', 'X' }, 
										{ 'X', 'X', 'X', 'X', 'X' } };
										
	char[][] keep = new char[][] { 		{ 'X', 'X', 'X', 'X', 'X' }, 
										{ 'X', ' ', ' ', ' ', 'X' }, 
										{ 'I', ' ', ' ', ' ', 'X' },
										{ 'X', ' ', ' ', ' ', 'X' }, 
										{ 'X', 'X', 'X', 'X', 'X' } };									

	@Test
	public void TestHeroMovesIntoFreeCell() {
		Game g = new Game(new TestDungeonMap(dungeon), 0);
		assertEquals(new Coords(1, 1), g.getHeroPos());
		g.moveHero('s');
		assertEquals(new Coords(1, 2), g.getHeroPos());
	}
	
	@Test
	public void TestHeroTriesToMoveIntoWall() {
		Game g = new Game(new TestDungeonMap(dungeon), 0);
		assertEquals(new Coords(1, 1), g.getHeroPos());
		g.moveHero('a');
		assertEquals(new Coords(1, 1), g.getHeroPos());
		assertFalse(g.isGameOver());
	}

	@Test
	public void TestHeroisCaughtByGuard() {
		Game g = new Game(new TestDungeonMap(dungeon), 0);
		assertFalse(g.isGameOver());
		g.moveHero('d');
		assertTrue(g.isGameOver());
	}
	
	@Test
	public void TestHeroTriesToLeaveThroughClosedDoors() {
		Game g = new Game(new TestDungeonMap(dungeon), 0);
		assertEquals(new Coords(1, 1), g.getHeroPos());
		g.moveHero('s');
		assertEquals(new Coords(1, 2), g.getHeroPos());
		g.moveHero('a');
		assertEquals(new Coords(1, 2), g.getHeroPos());
		assertFalse(g.isGameOver());
	}
	
	@Test
	public void TestHeroOpensDoors() {
		Game g = new Game(new TestDungeonMap(dungeon), 0);
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
		Game g = new Game(new TestDungeonMap(dungeon), 2);
		assertEquals(g.getMap(), "test_dungeon");
		assertEquals(new Coords(1, 1), g.getHeroPos());
		g.moveHero('s');
		assertEquals(new Coords(1, 2), g.getHeroPos());
		g.moveHero('s');
		assertEquals(g.getC(new Coords(0,2)), 'S');
		assertEquals(g.getC(new Coords(0,3)), 'S');
		g.moveHero('a');
		assertFalse(g.isGameOver());
		assertEquals(g.getMap(), "keep");
	}
	
	@Test
	public void TestHeroSlainedByOgre() {
		Game g = new Game(new TestKeepMap(keep), 0);
		assertEquals(new Coords(1, 1), g.getHeroPos());
		g.moveHero('d');
		assertTrue(g.isGameOver());
	}
	
	@Test
	public void TestHeroPicksUpKey() {
		Game g = new Game(new TestKeepMap(keep), 0);
		assertEquals(new Coords(1, 1), g.getHeroPos());
		g.moveHero('s');
		g.moveHero('s');
		assertEquals(g.getHeroChar(), 'K');
		assertTrue(g.heroHasKey());
	}
	
	@Test
	public void TestHeroTriesToOpenDoorWithoutKey() {
		Game g = new Game(new TestKeepMap(keep), 0);
		assertEquals(new Coords(1, 1), g.getHeroPos());
		g.moveHero('s');
		assertEquals(new Coords(1, 2), g.getHeroPos());
		g.moveHero('a');
		assertEquals(g.getC(new Coords(0,2)), 'I');
		assertEquals(new Coords(1, 2), g.getHeroPos());
		assertFalse(g.isGameOver());
	}
	
	@Test
	public void TestHeroOpensDoor() {
		Game g = new Game(new TestKeepMap(keep), 0);
		assertEquals(new Coords(1, 1), g.getHeroPos());
		g.moveHero('s');
		g.moveHero('s');
		assertEquals(g.getHeroChar(), 'K');
		assertTrue(g.heroHasKey());
		g.moveHero('w');
		g.moveHero('a');
		assertEquals(g.getC(new Coords(0,2)), 'S');
		assertFalse(g.isGameOver());
	}
	
	
	@Test
	public void TestHeroEscapes() {
		Game g = new Game(new TestKeepMap(keep), 0);
		assertEquals(new Coords(1, 1), g.getHeroPos());
		g.moveHero('s');
		g.moveHero('s');
		assertEquals(g.getHeroChar(), 'K');
		assertTrue(g.heroHasKey());
		g.moveHero('w');
		g.moveHero('a');
		assertEquals(g.getC(new Coords(0,2)), 'S');
		g.moveHero('a');
		assertTrue(g.isGameOver());
	}
}
