package mnpl.logic;

import java.util.Arrays;

public class Utility extends Collectable {
	//some member about how many are owned
	public Utility() {
		super();
		rent = new int[] {4, 10};
	}
	
	public Utility(String name) {
		super(name, 150, new int[] {4, 10}, 75);
	}
	
	public int getRent(int i, int diceRoll) {
		return rent[i] * diceRoll;
	}
	
	@Override
	public String toString() {
		return title + ",\t\t" + "cost: " + cost + ", rent: " + Arrays.toString(rent) + ", mortgage: " + mortgage;
	}
}
