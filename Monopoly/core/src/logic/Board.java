package logic;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public final class Board {
	private static List<Square> squares;
	private static int houses, hotels;

	static {
		houses = 32;
		hotels = 12;

		initSquares();
		initProperties();
		initStations();
		initUtilities();
		initCardSquares();
		initOtherSquares();
	}
	
	private static void initSquares() {
		squares = new ArrayList<Square>(0);
		for (int i = 0; i < 40; i++) {
			squares.add(new Square());
		}

		squares.set(0, new Square(0, "GO"));
	}
	
	private static void initProperties() {
		squares.set( 1, new Property( 1, "Old Kent Road",			 60, new int[]{ 2,  10,  30,   90,  160,  250},  50,  30, Color.BROWN));
		squares.set( 3, new Property( 3, "Whitechapel Road",		 60, new int[]{ 4,  20,  60,  180,  360,  450},  50,  30, Color.BROWN));
		squares.set( 6, new Property( 6, "The Angel Islington",		100, new int[]{ 6,  30,  90,  270,  400,  550},  50,  50, Color.CYAN));
		squares.set( 8, new Property( 8, "Euston Road",				100, new int[]{ 6,  30,  90,  270,  400,  550},  50,  50, Color.CYAN));
		squares.set( 9, new Property( 9, "Pentonville Road",		120, new int[]{ 8,  40, 100,  300,  450,  600},  60,  50, Color.CYAN));
		squares.set(11, new Property(11, "Pall Mall",				140, new int[]{10,  50, 150,  450,  625,  750},  70, 100, Color.PINK));
		squares.set(13, new Property(13, "Whitehall",				140, new int[]{10,  50, 150,  450,  625,  750},  70, 100, Color.PINK));
		squares.set(14, new Property(14, "Northumberland Avenue",	160, new int[]{12,  60, 180,  500,  700,  900},  80, 100, Color.PINK));
		squares.set(16, new Property(16, "Bow Street",				180, new int[]{14,  70, 200,  550,  750,  950},  90, 100, Color.ORANGE));
		squares.set(18, new Property(18, "Marlborough Street",		180, new int[]{14,  70, 200,  550,  750,  950},  90, 100, Color.ORANGE));
		squares.set(19, new Property(19, "Vine Street",				200, new int[]{16,  80, 220,  600,  800, 1000}, 100, 100, Color.ORANGE));
		squares.set(21, new Property(21, "The Strand",				220, new int[]{18,  90, 250,  700,  875, 1050}, 110, 150, Color.RED));
		squares.set(23, new Property(23, "Fleet Street",			220, new int[]{18,  90, 250,  700,  875, 1050}, 110, 150, Color.RED));
		squares.set(24, new Property(24, "Trafalgar Square",		240, new int[]{20, 100, 300,  750,  925, 1100}, 120, 150, Color.RED));
		squares.set(26, new Property(26, "Leicester Square",		260, new int[]{22, 110, 330,  800,  975, 1150}, 150, 150, Color.YELLOW));
		squares.set(27, new Property(27, "Coventry Street",			260, new int[]{22, 110, 330,  800,  975, 1150}, 150, 150, Color.YELLOW));
		squares.set(29, new Property(29, "Piccadilly",				280, new int[]{22, 120, 360,  850, 1025, 1200}, 150, 140, Color.YELLOW));
		squares.set(31, new Property(31, "Regent Street",			300, new int[]{26, 130, 390,  900, 1100, 1275}, 200, 150, Color.GREEN));
		squares.set(32, new Property(32, "Oxford Street",			300, new int[]{26, 130, 390,  900, 1100, 1275}, 200, 150, Color.GREEN));
		squares.set(34, new Property(34, "Bond Street",				320, new int[]{28, 150, 450, 1000, 1200, 1400}, 200, 160, Color.GREEN));
		squares.set(37, new Property(37, "Park Lane", 				350, new int[]{35, 175, 500, 1100, 1300, 1500}, 175, 200, Color.ROYAL));
		squares.set(39, new Property(39, "Mayfair", 				400, new int[]{50, 200, 600, 1400, 1700, 2000}, 200, 200, Color.ROYAL));
	}
	
	private static void initStations() {
		squares.set( 5, new Station( 5, "King's Cross"));
		squares.set(15, new Station(15, "Marylebone"));
		squares.set(25, new Station(25, "Fenchurch Street"));
		squares.set(35, new Station(35, "Liverpool Street"));
	}
	
	private static void initUtilities() {
		squares.set(12, new Utility(12, "Electric Company"));
		squares.set(28, new Utility(28, "Water Works"));
	}
	
	private static void initCardSquares() {
		squares.set(2,  new CardSquare(2,  "Community Chest"));
		squares.set(17, new CardSquare(17, "Community Chest"));
		squares.set(33, new CardSquare(33, "Community Chest"));
		squares.set(7,  new CardSquare(7,  "Chance"));
		squares.set(22, new CardSquare(22, "Chance"));
		squares.set(36, new CardSquare(36, "Chance"));
	}
	
	private static void initOtherSquares() {
		squares.set(4,  new Square(4,  "Income Tax (pay $200)"));
		squares.set(38, new Square(38, "Super Tax (pay $100)"));
		squares.set(10, new Square(10, "Jail"));
		squares.set(20, new Square(20, "Free parking"));
		squares.set(30, new Square(30, "Go to jail"));
	}
	
	public static Square getSquare(int i) {
		return squares.get(i);
	}
	
	public static List<Square> getSquares() {
		return squares;
	}

	public static int getHouses() {
		return houses;
	}
	
	public static void addToHouses(int amount) {
		houses += amount;
	}

	public static int getHotels() {
		return hotels;
	}
	
	public static void addToHotels(int amount) {
		hotels += amount;
	}
}
