package logic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
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

    //// TODO: 11/06/2017
}