package logic;

import java.util.Arrays;

public class Station extends Purchasable {
    private int totalStations;

    public Station() {
        super();
    }

    public Station(int pos, String name) {
        super(pos, name + " Station", 200, new int[]{25, 50, 100, 200}, 100);
        totalStations = 0;
    }

    public Station(int pos, String name, int cost, int[] rent, int mortgage) {
        super(pos, name + " Station", cost, rent, mortgage);
        totalStations = 0;
    }

    public boolean ownedBy(Player p) {
        return p == owner;
    }

    private void updateTotalSations() {
        totalStations = 0;
        if (owner != null)
            for (int i : owner.getAcquired())
                if (i == 5 || i == 15 || i == 25 || i == 35)
                    totalStations++;
        if (totalStations > 0)
            totalStations--;
    }

    @Override
    public int getRent() {
        updateTotalSations();
        return rent[totalStations];
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


        p.transaction(owner, getRent());
    }
}
