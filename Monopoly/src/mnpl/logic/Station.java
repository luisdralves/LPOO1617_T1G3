package mnpl.logic;

import java.util.Arrays;
import java.util.List;

public class Station extends Purchasable {
	//some member about how many are owned
	public Station() {
		super();
	}
	
	public Station(String name) {
		super(name + " Station", 200, new int[]{25, 50, 100, 200}, 100);
	}
	
	public Station(String name, int cost, int[] rent, int mortgage) {
		super(name + " Station", cost, rent, mortgage);
	}
	
	public boolean ownedBy(Player p) {
		return p == owner;
	}
	
	@Override
	public String toString() {
		return title + ",\t\t" + "cost: " + cost + ", rent: " + Arrays.toString(rent) + ", mortgage: " + mortgage;
	}

	@Override
	public void playerLands(Player p) {
		int totalStations = -1;
		/*List<Square> squares = Board.getSquares();
		for(Square sq : squares) {
			if (sq instanceof Station)
				if (((Station) sq).ownedBy(owner))
					totalStations++;
					
		}*/
		
		for(int i : owner.getAquired())
			if (i == 5 || i == 15 || i == 25 || i == 35)
				totalStations++;
		
		p.transaction(owner, rent[totalStations]);
	}
}
