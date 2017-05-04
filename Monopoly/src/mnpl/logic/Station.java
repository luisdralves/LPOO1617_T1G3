package mnpl.logic;

import java.util.Arrays;
import java.util.List;

public class Station extends Purchasable {
	private int totalStations;
	public Station() {
		super();
	}
	
	public Station(String name) {
		super(name + " Station", 200, new int[]{25, 50, 100, 200}, 100);
		totalStations = 0;
	}
	
	public Station(String name, int cost, int[] rent, int mortgage) {
		super(name + " Station", cost, rent, mortgage);
		totalStations = 0;
	}
	
	public boolean ownedBy(Player p) {
		return p == owner;
	}
	
	private void updateTotalSations() {
		totalStations = 0;		
		for(int i : owner.getAcquired())
			if (i == 5 || i == 15 || i == 25 || i == 35)
				totalStations++;
	}
	
	@Override
	public int getRent() {
		updateTotalSations();
		return rent[totalStations];
	}
	
	@Override
	public String toString() {
		return title + ",\t\t" + "cost: " + cost + ", rent: " + Arrays.toString(rent) + ", mortgage: " + mortgage;
	}

	@Override
	public void playerLands(Player p) {
		
		
		p.transaction(owner, getRent());
	}
}
