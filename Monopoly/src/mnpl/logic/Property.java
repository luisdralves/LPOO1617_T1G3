package mnpl.logic;

import java.util.Arrays;

public class Property extends Purchasable {
	private Colour set;
	private int houseCost, houses;
	
	public Property() {
		super();
		houses = 0;
		houseCost = 0;
		set = new Colour();
	}
	
	public Property(String name, int landCost, int[] rent, int mortgage, int houseCost, Colour set) {
		super(name, landCost, rent, mortgage);
		houses = 0;
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
	public int getRent() {
		return rent[houses];
	}
	
	@Override
	public void playerLands(Player p) {
		p.transaction(owner, rent[houses]);
	}
	
	@Override
	public String toString() {
		return title + ",\t\t" + "cost: " + cost + ", rent: " + Arrays.toString(rent) + ", mortgage: " + mortgage + ", house cost: " + houseCost + ", colour: " + set.getName();
	}
}
