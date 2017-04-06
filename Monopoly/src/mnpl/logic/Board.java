package mnpl.logic;

import java.util.ArrayList;
import java.util.List;

public class Board {
	protected List<Square> squares;
	
	public Board() {
		squares = new ArrayList<Square>();
		Colour brown 		= new Colour("Brown", 		128, 064, 000);
		Colour lightBlue 	= new Colour("Light blue", 	128, 255, 255);
		Colour pink 		= new Colour("Pink", 		255, 000, 255);
		Colour orange 		= new Colour("Orange", 		255, 128, 000);
		Colour red	 		= new Colour("Red", 		255, 000, 000);
		Colour yellow 		= new Colour("Yellow", 		255, 255, 000);
		Colour green 		= new Colour("Green", 		000, 255, 000);
		Colour darkBlue		= new Colour("Dark blue",	000, 128, 255);
		squares.add(new Square("GO"));
		squares.add(new Property("Old Kent Road",			 60, new int[]{ 2,  10,  30,   90,  160,  250},  50,  30, brown));
		squares.add(new Property("Whitechapel Road",		 60, new int[]{ 4,  20,  60,  180,  360,  450},  50,  30, brown));
		squares.add(new Property("The Angel Islington",		100, new int[]{ 6,  30,  90,  270,  400,  550},  50,  50, lightBlue));
		squares.add(new Property("Euston Road",				100, new int[]{ 6,  30,  90,  270,  400,  550},  50,  50, lightBlue));
		squares.add(new Property("Pentonville Road",		120, new int[]{ 8,  40, 100,  300,  450,  600},  60,  50, lightBlue));
		squares.add(new Property("Pall Mall",				140, new int[]{10,  50, 150,  450,  625,  750},  70, 100, pink));
		squares.add(new Property("Whitehall",				140, new int[]{10,  50, 150,  450,  625,  750},  70, 100, pink));
		squares.add(new Property("Northumberland Avenue",	160, new int[]{12,  60, 180,  500,  700,  900},  80, 100, pink));
		squares.add(new Property("Bow Street",				180, new int[]{14,  70, 200,  550,  750,  950},  90, 100, orange));
		squares.add(new Property("Marlborough Street",		180, new int[]{14,  70, 200,  550,  750,  950},  90, 100, orange));
		squares.add(new Property("Vine Street",				200, new int[]{16,  80, 220,  600,  800, 1000}, 100, 100, orange));
		squares.add(new Property("The Strand",				220, new int[]{18,  90, 250,  700,  875, 1050}, 110, 150, red));
		squares.add(new Property("Fleet Street",			220, new int[]{18,  90, 250,  700,  875, 1050}, 110, 150, red));
		squares.add(new Property("Trafalgar Square",		240, new int[]{20, 100, 300,  750,  925, 1100}, 120, 150, red));
		squares.add(new Property("Leicester Square",		260, new int[]{22, 110, 330,  800,  975, 1150}, 150, 150, yellow));
		squares.add(new Property("Coventry Street",			260, new int[]{22, 110, 330,  800,  975, 1150}, 150, 150, yellow));
		squares.add(new Property("Piccadilly",				280, new int[]{22, 120, 360,  850, 1025, 1200}, 150, 140, yellow));
		squares.add(new Property("Regent Street",			300, new int[]{26, 130, 390,  900, 1100, 1275}, 200, 150, green));
		squares.add(new Property("Oxford Street",			300, new int[]{26, 130, 390,  900, 1100, 1275}, 200, 150, green));
		squares.add(new Property("Bond Street",				320, new int[]{28, 150, 450, 1000, 1200, 1400}, 200, 160, green));
		squares.add(new Property("Park Lane",				350, new int[]{35, 175, 500, 1100, 1300, 1500}, 175, 200, darkBlue));
		squares.add(new Property("Mayfair",					400, new int[]{50, 200, 600, 1400, 1700, 2000}, 200, 200, darkBlue));
	}
}
