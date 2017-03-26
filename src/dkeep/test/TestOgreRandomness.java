package dkeep.test;

import static org.junit.Assert.*;
import org.junit.Test;

import dkeep.cli.Main;
import dkeep.logic.Coords;
import dkeep.logic.Game;
import dkeep.logic.KeepMap;

public class TestOgreRandomness {
	@Test(timeout=1000)
	public void TestOgreMovement() {
		Game g = new Game(new KeepMap(true), 0);
		assertEquals(new Coords(1, 8), g.getHeroPos());
		g.moveHero('s');
		assertEquals(new Coords(1, 8), g.getHeroPos());
		Coords oldOgrePos, newOgrePos = g.getOgrePos(0);
		int countA=0, countD=0, countW=0, countS=0, total=1;
		while(total < 1000001) {
			oldOgrePos = newOgrePos;
			g.moveHero('s');
			newOgrePos = g.getOgrePos(0);
			switch (newOgrePos.directionMoved(oldOgrePos)) {
			case 'w':
				countW++;
				break;
			case 's':
				countS++;
				break;
			case 'a':
				countA++;
				break;
			case 'd':
				countD++;
				break;
			default:
				total--;
				break;
			}
			total++;
		}
		total--;
		assertEquals(0.25, ((double)countA)/total, 0.001);
		assertEquals(0.25, ((double)countD)/total, 0.001);
		assertEquals(0.25, ((double)countW)/total, 0.001);
		assertEquals(0.25, ((double)countS)/total, 0.001);
		assertEquals(total, countA + countD + countW + countS);
	}
	
	@Test(timeout=1000)
	public void TestOgreAttack() {
		Game g = new Game(new KeepMap(false), 0);
		assertEquals(new Coords(1, 8), g.getHeroPos());
		g.moveHero('s');
		assertEquals(new Coords(1, 8), g.getHeroPos());
		Coords ogreClub, ogrePos = g.getOgrePos(0);
		int countA=0, countD=0, countW=0, countS=0, total=1;
		while(total < 1000001) {
			g.moveHero('s');
			ogreClub = g.getOgreClub(0);
			switch (ogrePos.directionMoved(ogreClub)) {
			case 'w':
				countW++;
				break;
			case 's':
				countS++;
				break;
			case 'a':
				countA++;
				break;
			case 'd':
				countD++;
				break;
			default:
				total--;
				break;
			}
			total++;
		}
		total--;
		assertEquals(0.25, ((double)countA)/total, 0.001);
		assertEquals(0.25, ((double)countD)/total, 0.001);
		assertEquals(0.25, ((double)countW)/total, 0.001);
		assertEquals(0.25, ((double)countS)/total, 0.001);
		assertEquals(total, countA + countD + countW + countS);
	}
}
