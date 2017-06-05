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

		squares.set(0, new Square("GO"));
	}
	
	private static void initProperties() {
		squares.set(1, new Property("Old Kent Road",			 60, new int[]{ 2,  10,  30,   90,  160,  250},  50,  30, Color.BROWN));
		squares.set(3, new Property("Whitechapel Road",			 60, new int[]{ 4,  20,  60,  180,  360,  450},  50,  30, Color.BROWN));
		squares.set(6, new Property("The Angel Islington",		100, new int[]{ 6,  30,  90,  270,  400,  550},  50,  50, Color.CYAN));
		squares.set(8, new Property("Euston Road",				100, new int[]{ 6,  30,  90,  270,  400,  550},  50,  50, Color.CYAN));
		squares.set(9, new Property("Pentonville Road",			120, new int[]{ 8,  40, 100,  300,  450,  600},  60,  50, Color.CYAN));
		squares.set(11, new Property("Pall Mall",				140, new int[]{10,  50, 150,  450,  625,  750},  70, 100, Color.PINK));
		squares.set(13, new Property("Whitehall",				140, new int[]{10,  50, 150,  450,  625,  750},  70, 100, Color.PINK));
		squares.set(14, new Property("Northumberland Avenue",	160, new int[]{12,  60, 180,  500,  700,  900},  80, 100, Color.PINK));
		squares.set(16, new Property("Bow Street",				180, new int[]{14,  70, 200,  550,  750,  950},  90, 100, Color.ORANGE));
		squares.set(18, new Property("Marlborough Street",		180, new int[]{14,  70, 200,  550,  750,  950},  90, 100, Color.ORANGE));
		squares.set(19, new Property("Vine Street",				200, new int[]{16,  80, 220,  600,  800, 1000}, 100, 100, Color.ORANGE));
		squares.set(21, new Property("The Strand",				220, new int[]{18,  90, 250,  700,  875, 1050}, 110, 150, Color.RED));
		squares.set(23, new Property("Fleet Street",			220, new int[]{18,  90, 250,  700,  875, 1050}, 110, 150, Color.RED));
		squares.set(24, new Property("Trafalgar Square",		240, new int[]{20, 100, 300,  750,  925, 1100}, 120, 150, Color.RED));
		squares.set(26, new Property("Leicester Square",		260, new int[]{22, 110, 330,  800,  975, 1150}, 150, 150, Color.YELLOW));
		squares.set(27, new Property("Coventry Street",			260, new int[]{22, 110, 330,  800,  975, 1150}, 150, 150, Color.YELLOW));
		squares.set(29, new Property("Piccadilly",				280, new int[]{22, 120, 360,  850, 1025, 1200}, 150, 140, Color.YELLOW));
		squares.set(31, new Property("Regent Street",			300, new int[]{26, 130, 390,  900, 1100, 1275}, 200, 150, Color.GREEN));
		squares.set(32, new Property("Oxford Street",			300, new int[]{26, 130, 390,  900, 1100, 1275}, 200, 150, Color.GREEN));
		squares.set(34, new Property("Bond Street",				320, new int[]{28, 150, 450, 1000, 1200, 1400}, 200, 160, Color.GREEN));
		squares.set(37, new Property("Park Lane", 350, new int[]{35, 175, 500, 1100, 1300, 1500}, 175, 200, Color.ROYAL));
		squares.set(39, new Property("Mayfair", 400, new int[]{50, 200, 600, 1400, 1700, 2000}, 200, 200, Color.ROYAL));
	}
	
	private static void initStations() {
		squares.set( 5, new Station("King's Cross"));
		squares.set(15, new Station("Marylebone"));
		squares.set(25, new Station("Fenchurch Street"));
		squares.set(35, new Station("Liverpool Street"));
	}
	
	private static void initUtilities() {
		squares.set(12, new Utility("Electric Company"));
		squares.set(28, new Utility("Water Works"));
	}
	
	private static void initCardSquares() {
		squares.set(2, new CardSquare("Community Chest"));
		squares.set(17, new CardSquare("Community Chest"));
		squares.set(33, new CardSquare("Community Chest"));
		squares.set(7, new CardSquare("Chance"));
		squares.set(22, new CardSquare("Chance"));
		squares.set(36, new CardSquare("Chance"));
	}
	
	private static void initOtherSquares() {
		squares.set(4, new Square("Income Tax (pay $200)"));
		squares.set(38, new Square("Super Tax (pay $100)"));
		squares.set(10, new Square("Jail"));
		squares.set(20, new Square("Free parking"));
		squares.set(30, new Square("Go to jail"));
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
