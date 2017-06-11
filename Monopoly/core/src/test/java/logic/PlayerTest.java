package logic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    @Test
    public void TestParams() throws Exception {
        Player p = new Player(false, true);
        assertEquals("Player 5", p.getName());
        assertEquals(5, p.getID());
        assertEquals(false, p.isAI());
        assertEquals(true, p.realDice());
        assertEquals(1500, p.getBalance());
        assertEquals(0, p.getPosition());
    }

    @Test
    public void TestPositionEqualsDiceRoll() throws Exception {
        GameData.initPlayersTest();
        GameData.currentPlayerInt++;
        GameData.getPlayer().rollDice(5, 6);
        GameData.getPlayer().move();
        assertEquals(GameData.getPlayer().getPosition(), 11);
        GameData.getPlayer().rollDice(3, 1);
        GameData.getPlayer().move();
        assertEquals(GameData.getPlayer().getPosition(), 15);
    }

    @Test
    public void TestAcquirePorperty() throws Exception {
        Player p = new Player(false, true);
        assertEquals(0, p.getAcquired().size());
        p.purchase(1, 500);
        assertEquals(1, (int) p.getAcquired().get(0));
        assertEquals(1, p.getAcquired().size());
        assertEquals(1000, p.getBalance());
        p.purchase(3, 500);
        assertEquals(3, (int) p.getAcquired().get(1));
        assertEquals(2, p.getAcquired().size());
        assertEquals(500, p.getBalance());
    }
}