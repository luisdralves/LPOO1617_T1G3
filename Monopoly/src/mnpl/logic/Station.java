package mnpl.logic;

import java.util.Arrays;

public class Station extends Collectable {
	//some member about how many are owned
	public Station() {
		super();
	}
	
	public Station(String name) {
		super(name + " Station", 200, new int[]{25, 50, 100, 200}, 100);
	}
	
	public Station(String name, int cost, int[] rent, int mortgage) {
		super(name + " Station", cost, rent, mortgage);
	}
	
	@Override
	public String toString() {
		return title + ",\t\t" + "cost: " + cost + ", rent: " + Arrays.toString(rent) + ", mortgage: " + mortgage;
	}
}
