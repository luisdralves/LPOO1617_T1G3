package mnpl.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
	private List<Square> squares;
	private List<Player> players;
	private List<Card> chanceCards;
	private List<Card> commCards;
	private int houses, hotels;
	
	public Board() {
		houses = 32;
		hotels = 12;
		
		squares = new ArrayList<Square>(0);
		for (int i = 0; i < 40; i++) {
			squares.add(new Square());
		}
		Colour brown 		= new Colour("Brown", 		128, 064, 000);
		Colour lightBlue 	= new Colour("Light blue", 	128, 255, 255);
		Colour pink 		= new Colour("Pink", 		255, 000, 255);
		Colour orange 		= new Colour("Orange", 		255, 128, 000);
		Colour red	 		= new Colour("Red", 		255, 000, 000);
		Colour yellow 		= new Colour("Yellow", 		255, 255, 000);
		Colour green 		= new Colour("Green", 		000, 255, 000);
		Colour darkBlue		= new Colour("Dark blue",	000, 128, 255);
		squares.set(0, new Square("GO"));
		
		//properties
		squares.set(1, new Property("Old Kent Road",			 60, new int[]{ 2,  10,  30,   90,  160,  250},  50,  30, brown));
		squares.set(3, new Property("Whitechapel Road",			 60, new int[]{ 4,  20,  60,  180,  360,  450},  50,  30, brown));
		squares.set(6, new Property("The Angel Islington",		100, new int[]{ 6,  30,  90,  270,  400,  550},  50,  50, lightBlue));
		squares.set(8, new Property("Euston Road",				100, new int[]{ 6,  30,  90,  270,  400,  550},  50,  50, lightBlue));
		squares.set(9, new Property("Pentonville Road",			120, new int[]{ 8,  40, 100,  300,  450,  600},  60,  50, lightBlue));
		squares.set(11, new Property("Pall Mall",				140, new int[]{10,  50, 150,  450,  625,  750},  70, 100, pink));
		squares.set(13, new Property("Whitehall",				140, new int[]{10,  50, 150,  450,  625,  750},  70, 100, pink));
		squares.set(14, new Property("Northumberland Avenue",	160, new int[]{12,  60, 180,  500,  700,  900},  80, 100, pink));
		squares.set(16, new Property("Bow Street",				180, new int[]{14,  70, 200,  550,  750,  950},  90, 100, orange));
		squares.set(18, new Property("Marlborough Street",		180, new int[]{14,  70, 200,  550,  750,  950},  90, 100, orange));
		squares.set(19, new Property("Vine Street",				200, new int[]{16,  80, 220,  600,  800, 1000}, 100, 100, orange));
		squares.set(21, new Property("The Strand",				220, new int[]{18,  90, 250,  700,  875, 1050}, 110, 150, red));
		squares.set(23, new Property("Fleet Street",			220, new int[]{18,  90, 250,  700,  875, 1050}, 110, 150, red));
		squares.set(24, new Property("Trafalgar Square",		240, new int[]{20, 100, 300,  750,  925, 1100}, 120, 150, red));
		squares.set(26, new Property("Leicester Square",		260, new int[]{22, 110, 330,  800,  975, 1150}, 150, 150, yellow));
		squares.set(27, new Property("Coventry Street",			260, new int[]{22, 110, 330,  800,  975, 1150}, 150, 150, yellow));
		squares.set(29, new Property("Piccadilly",				280, new int[]{22, 120, 360,  850, 1025, 1200}, 150, 140, yellow));
		squares.set(31, new Property("Regent Street",			300, new int[]{26, 130, 390,  900, 1100, 1275}, 200, 150, green));
		squares.set(32, new Property("Oxford Street",			300, new int[]{26, 130, 390,  900, 1100, 1275}, 200, 150, green));
		squares.set(34, new Property("Bond Street",				320, new int[]{28, 150, 450, 1000, 1200, 1400}, 200, 160, green));
		squares.set(37, new Property("Park Lane",				350, new int[]{35, 175, 500, 1100, 1300, 1500}, 175, 200, darkBlue));
		squares.set(39, new Property("Mayfair",					400, new int[]{50, 200, 600, 1400, 1700, 2000}, 200, 200, darkBlue));
		
		//stations
		squares.set( 5, new Station("King's Cross"));
		squares.set(15, new Station("Marylebone"));
		squares.set(25, new Station("Fenchurch Street"));
		squares.set(35, new Station("Liverpool Street"));
		
		//utilities
		squares.set(12, new Utility("Electric Company"));
		squares.set(28, new Utility("Water Works"));
		
		//cards
		squares.set(2, new Square("Community Chest"));
		squares.set(17, new Square("Community Chest"));
		squares.set(33, new Square("Community Chest"));
		squares.set(7, new Square("Chance"));
		squares.set(22, new Square("Chance"));
		squares.set(35, new Square("Chance"));
		
		chanceCards = new ArrayList<Card>();
		chanceCards.add(new Card("Advance to Go", "(Collect £200)"));
		chanceCards.add(new Card("Advance to Trafalgar Square", "If you pass Go, collect £200"));
		chanceCards.add(new Card("Advance to Pall Mall", "If you pass Go, collect £200"));
		chanceCards.add(new Card("Advance token to nearest Utility", "If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown"));
		chanceCards.add(new Card("Advance token to the nearest Railroad", "Pay owner twice the rental to which they are otherwise entitled. If Railroad is unowned, you may buy it from the Bank."));
		chanceCards.add(new Card("Advance token to the nearest Railroad", "Pay owner twice the rental to which they are otherwise entitled. If Railroad is unowned, you may buy it from the Bank."));
		chanceCards.add(new Card("Bank pays you dividend of $50", ""));
		chanceCards.add(new Card("Get out of Jail Free", "This card may be kept until needed or sold"));
		chanceCards.add(new Card("Go Back 3 Spaces", ""));
		chanceCards.add(new Card("Go directly to Jail", "Do not pass Go, do not collect £200"));
		chanceCards.add(new Card("Make general repairs on all your property", "For each house pay £25, for each hotel £100"));
		chanceCards.add(new Card("Pay poor tax of £15", ""));
		chanceCards.add(new Card("Take a trip to King's Cross Station", "If you pass Go, collect £200"));
		chanceCards.add(new Card("Take a walk on Mayfair", "Advance to Mayfair"));
		chanceCards.add(new Card("You have been elected Chairman of the Board", "Pay each player £50"));
		chanceCards.add(new Card("Your building loan matures", "Collect £150"));
		Collections.shuffle(chanceCards);
		
		commCards = new ArrayList<Card>();
		commCards.add(new Card("Advance to Go", "(Collect £200)"));
		commCards.add(new Card("Bank error in your favor", "Collect £200"));
		commCards.add(new Card("Doctor's fees", "Pay £50"));
		commCards.add(new Card("From sale of stock you get £45", ""));
		commCards.add(new Card("Get out of Jail Free", "This card may be kept until needed or sold"));
		commCards.add(new Card("Go directly to Jail", "Do not pass Go, do not collect £200"));
		commCards.add(new Card("Grand opera opening", "Collect £50 from every player for opening night seats"));
		commCards.add(new Card("Holiday fund matures", "Collect £100"));
		commCards.add(new Card("Income tax refund", "Collect £20"));
		commCards.add(new Card("It's your birthday", "Collect $10 from each player"));
		commCards.add(new Card("Life insurance matures", "Collect £100"));
		commCards.add(new Card("Pay hospital fees of £100", ""));
		commCards.add(new Card("Pay school fees of £150", ""));
		commCards.add(new Card("Receive £25 consultancy fee", ""));
		commCards.add(new Card("You are assessed for street repairs", "£40 per house, £115 per hotel"));
		commCards.add(new Card("You have won second prize in a beauty contest", "Collect £10"));
		Collections.shuffle(commCards);
		
		players = new ArrayList<Player>();
	}
	
	public Square getSquare(int i) {
		return squares.get(i);
	}
	
	@Override
	public String toString() {
		String ret = "";
		/*for (Square sq : squares) {
			ret += sq;
			ret += '\n';
		}*/
		for (int i = 20; i < 31; i++) {
			for (int j = 0; j < 10; j++) {
				if (j < squares.get(i).getTitle().length())
					ret += squares.get(i).getTitle().charAt(j);
				else
					ret += ' ';
			}
			ret += " | ";
		}
		ret += '\n';
		ret += '\n';
		for (int i = 1; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (j < squares.get(20-i).getTitle().length())
					ret += squares.get(20-i).getTitle().charAt(j);
				else
					ret += ' ';
			}
			ret += "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ";
			for (int j = 0; j < 10; j++) {
				if (j < squares.get(i+30).getTitle().length())
					ret += squares.get(i+30).getTitle().charAt(j);
				else
					ret += ' ';
			}
			ret += '\n';
			ret += '\n';
		}
		ret += '\n';
		ret += '\n';
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 10; j++) {
				if (j < squares.get(10-i).getTitle().length())
					ret += squares.get(10-i).getTitle().charAt(j);
				else
					ret += ' ';
			}
			ret += " | ";
		}
		return ret;
	}
}
