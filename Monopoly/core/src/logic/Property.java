package logic;

import com.badlogic.gdx.graphics.Color;

import java.util.Arrays;

public class Property extends Purchasable {
	private Color set;
	private int houseCost, houses, ownerOwnsInSet, totalInSet;
	private boolean setComplete;
	
	public Property() {
		super();
		houses = 0;
		houseCost = 0;
		ownerOwnsInSet = 0;
		totalInSet = 0;
		set = Color.BLACK;
		setComplete = false;
	}
	
	public Property(int pos, String name, int landCost, int[] rent, int mortgage, int houseCost, Color set, int totalInSet) {
		super(pos, name, landCost, rent, mortgage);
		houses = 0;
		this.houseCost = houseCost;
		this.set = set;
		this.totalInSet = totalInSet;
		setComplete = false;
	}	
	
	public int getHouses() {
		return houses;
	}
	
	public void addToHouses(int amount) {
		if(houses < 5) {
			GameData.getPlayer().addToBalance(-houseCost);
			houses += amount;
		}
	}

	public void updateOwnerOwnsInSet() {
		if (!setComplete) {
			ownerOwnsInSet = 0;
			if (owner != null)
				for (int i : owner.getAcquired())
					if (i == 5 || i == 15 || i == 25 || i == 35)
						ownerOwnsInSet++;
			if (ownerOwnsInSet > 0)
				ownerOwnsInSet--;
		}
		if (ownerOwnsInSet == totalInSet)
			setComplete = true;
	}

	public boolean isSetComplete() {
		return setComplete;
	}

	public int getHouseCost() {
		return houseCost;
	}

	public Color getColourSet() {
		return set;
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
		ret += "owned: " + owned + ", active: " + active + ", houses: " + houses + ", cost: " + cost + ", rent: " + Arrays.toString(rent) + ", mortgage: " + mortgage + ", house cost: " + houseCost + ", colour: " + set.toString();
		return ret;
	}
}
