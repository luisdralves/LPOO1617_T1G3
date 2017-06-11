package logic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Miguel on 11/06/2017.
 */
public class PurchasableTest {
    @Test
    public void TestParams() throws Exception {
        Property p = (Property) Board.getSquare(1);
        Player player = new Player(false, false);
        assertEquals(60, p.getLandCost());
        assertEquals(50, p.getMortgage());
        assertEquals(30, p.getHouseCost());
        assertEquals("Old Kent Road", p.getTitle());
    }

    @Test
    public void TestPurchase() throws Exception {
        Property p = (Property) Board.getSquare(1);
        Player player = new Player(false, false);
        assertEquals(false, p.isOwned());
        player.purchase(1, 0);
        assertEquals(player, p.getOwner());
        assertEquals(true, p.isOwned());
    }
}