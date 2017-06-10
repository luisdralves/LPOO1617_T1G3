package logic;

public abstract class Purchasable extends Square {
	protected int cost, mortgage;
	protected boolean owned, active;
	protected Player owner;
	protected int[] rent;
	
	public Purchasable() {
		super();
		cost = 0;
		mortgage = 0;
		rent = new int[]{0,0,0,0,0,0};
	}
	
	public Purchasable(int pos, String name, int cost, int[] rent, int mortgage) {
		super(pos, name);
		this.cost = cost;
		this.rent = rent;
		this.mortgage = mortgage;
		owned = false;
		active = false;
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
	
	public boolean isActive() {
		return active;
	}
	
	public void toggleMortgage() {
		if (active) {
			active = false;
			owner.mortgage(position);
		}
		else {
			active = true;
			owner.unmortgage(position);
		}
	}

	public String getOwnerName() {
		return (owner != null ? owner.getName() : "Not owned");
	}

	public int getOwnerID() {
		return (owner != null ? owner.getID() : -1);
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player p) {
		owned = true;
		active = true;
		owner = p;
	}

	public void suspend() {
		active = false;
	}

	public void activate() {
		active = true;
	}

	public int getRent(int i) {
		return rent[i];
	}
	
	public abstract int getRent();

	public void setRent(int[] rent) {
		this.rent = rent;
	}
	
	public void setRent(int rent, int i) {
		this.rent[i] = rent;
	}
	
	public abstract void playerLands(Player p);

}
