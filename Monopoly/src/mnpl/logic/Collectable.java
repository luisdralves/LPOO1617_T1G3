package mnpl.logic;

public class Collectable extends Purchasable {
	protected int[] rent;
	
	public Collectable() {
		super();
		rent = new int[] {0,0,0,0,0,0};
	}
	
	public Collectable(String name, int cost, int[] rent, int mortgage) {
		super(name, cost, mortgage);
		this.rent = rent;
	}
	
	public int getRent(int i) {
		return rent[i];
	}

	public void setRent(int[] rent) {
		this.rent = rent;
	}
	
	public void setRent(int rent, int i) {
		this.rent[i] = rent;
	}
	
	public abstract void playerLands(Player p);
}
