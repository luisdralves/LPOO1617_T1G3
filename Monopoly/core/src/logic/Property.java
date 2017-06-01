package logic;

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
	
	public int getHouses() {
		return houses;
	}
	
	public void addToHouses(int amount) {
		houses += amount;
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
		String ret = title + ',';
		for (int i = title.length(); i < 30; i++) {
			ret += ' ';
		}
		ret += "owned: " + owned + ", active: " + active + ", houses: " + houses + ", cost: " + cost + ", rent: " + Arrays.toString(rent) + ", mortgage: " + mortgage + ", house cost: " + houseCost + ", colour: " + set.getName();
		return ret;
	}
}
