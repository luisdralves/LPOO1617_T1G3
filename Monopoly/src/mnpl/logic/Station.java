package mnpl.logic;

public class Station extends Collectable {
	//some member about how many are owned
	public Station() {
		super();
	}
	
	public Station(String name) {
		super(name, 200, new int[]{25, 50, 100, 200}, 100);
	}
	
	public Station(String name, int cost, int[] rent, int mortgage) {
		super(name, cost, rent, mortgage);
	}
}
