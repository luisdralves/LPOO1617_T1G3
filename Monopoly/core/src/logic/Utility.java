package logic;

import java.util.Arrays;

public class Utility extends Purchasable {
    private int totalUtilities;

    public Utility() {
        super();
        rent = new int[]{4, 10};
    }

    public Utility(int pos, String name) {
        super(pos, name, 150, new int[]{4, 10}, 75);
    }

    public int getRent(int i, int diceRoll) {
        return rent[i] * diceRoll;
    }

    private void updateTotalUtilities() {
        totalUtilities = 0;
        if (owner != null)
            for (int i : owner.getAcquired())
                if (i == 12 || i == 28)
                    totalUtilities++;
        if (totalUtilities > 0)
            totalUtilities--;
    }

    @Override
    public int getRent() {
        updateTotalUtilities();
        return rent[totalUtilities];
    }

    @Override
    public String toString() {
        String ret = title + ',';
        for (int i = title.length(); i < 30; i++) {
            ret += ' ';
        }
        ret += "owned: " + owned + ", active: " + active + ", cost: " + cost + ", rent: " + Arrays.toString(rent) + ", mortgage: " + mortgage;
        return ret;
    }

    @Override
    public void playerLands(Player p) {
        p.transaction(owner, getRent() * p.getDiceRoll());

    }
}
