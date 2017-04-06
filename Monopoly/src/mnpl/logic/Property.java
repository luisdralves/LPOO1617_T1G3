package mnpl.logic;

import java.util.Arrays;

public class Property extends Collectable {
	protected Colour set;
	protected int houseCost;
	
	public Property() {
		super();
		houseCost = 0;
		set = new Colour();
	}
	
	public Property(String name, int landCost, int[] rent, int mortgage, int houseCost, Colour set) {
		super(name, landCost, rent, mortgage);
		this.houseCost = houseCost;
		this.set = set;
	}

	public int getHouseCost() {
		return houseCost;
	}

	public void setHouseCost(int houseCost) {
		this.houseCost = houseCost;
	}

	public Colour getColourSet() {
		return set;
	}

	public void setColourSet(Colour set) {
		this.set = set;
	}
	
	@Override
	public String toString() {
		return title + ",\t\t" + "cost: " + cost + ", rent: " + Arrays.toString(rent) + ", mortgage: " + mortgage + ", house cost: " + houseCost + ", colour: " + set.getName();
	}
}
