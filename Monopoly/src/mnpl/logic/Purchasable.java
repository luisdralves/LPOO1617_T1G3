package mnpl.logic;

public class Purchasable extends Square {
	protected int cost, mortgage;
	protected boolean owned;
	protected Player owner;
	
	public Purchasable() {
		super();
		cost = 0;
		mortgage = 0;
	}
	
	public Purchasable(String name, int cost, int mortgage) {
		super(name);
		this.cost = cost;
		this.mortgage = mortgage;
		owned = false;
	}
	
	public int getLandCost() {
		return cost;
	}

	public void setLandCost(int landCost) {
		this.cost = landCost;
	}
	
	public int getMortgage() {
		return mortgage;
	}

	public void setMortgage(int mortgage) {
		this.mortgage = mortgage;
	}
	
	public boolean isOwned() {
		return owned;
	}

}
