package mnpl.logic;

public class Property extends Square {
	protected int landCost, mortgage, houseCost;
	protected int[] rent;
	protected Colour set;
	
	public Property() {
		super();
		landCost = 0;
		rent = new int[] {0,0,0,0,0,0};
		mortgage = 0;
		houseCost = 0;
		set = new Colour();
	}
	
	public Property(String name, int landCost, int[] rent, int mortgage, int houseCost, Colour set) {
		super(name);
		this.landCost = landCost;
		this.rent = rent;
		this.mortgage = mortgage;
		this.houseCost = houseCost;
		this.set = set;
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

	public int getMortgage() {
		return mortgage;
	}

	public void setMortgage(int mortgage) {
		this.mortgage = mortgage;
	}

	public int getHouseCost() {
		return houseCost;
	}

	public void setHouseCost(int houseCost) {
		this.houseCost = houseCost;
	}

	public int getLandCost() {
		return landCost;
	}

	public void setLandCost(int landCost) {
		this.landCost = landCost;
	}

	public Colour getColourSet() {
		return set;
	}

	public void setColourSet(Colour set) {
		this.set = set;
	}
}
