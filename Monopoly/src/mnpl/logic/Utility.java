package mnpl.logic;

import java.util.Arrays;

public class Utility extends Purchasable {
	private int totalUtilities;
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
	
	private void updateTotalUtilities() {
		totalUtilities = 0;		
		for(int i : owner.getAquired())
			if (i == 12 || i == 28)
				totalUtilities++;
	}
	
	@Override
	public int getRent() {
		updateTotalUtilities();
		return rent[totalUtilities];
	}

	@Override
	public String toString() {
		return title + ",\t\t" + "cost: " + cost + ", rent: " + Arrays.toString(rent) + ", mortgage: " + mortgage;
	}

	@Override
	public void playerLands(Player p) {
		p.transaction(owner, getRent() * p.getDiceRoll());
		
	}
}
