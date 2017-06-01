package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public final class GameData {
	private static List<Player> players;
	private static List<Card> chanceCards;
	private static List<Card> commCards;
	public static int currentPlayerInt;
	public static boolean useGUI;
	public static boolean gameOver;
	
	static {
		currentPlayerInt = -1;
		useGUI = true;
		gameOver = false;

		initChanceCards();
		initCommunityCards();
		initPlayers();
	}
	
	private static void initPlayers() {
		players = new ArrayList<Player>();
		players.add(new Player(false, "token1.png"));
		players.add(new Player(false, "token2.png"));
		players.add(new Player(false, "token3.png"));
		players.add(new Player(false, "token4.png"));
	}

	private static void initChanceCards() {
		chanceCards = new ArrayList<Card>();
		chanceCards.add(new Card("Advance to Go", "(Collect £200)"));																																				//1
		chanceCards.add(new Card("Advance to Trafalgar Square", "If you pass Go, collect £200"));																													//2
		chanceCards.add(new Card("Advance to Pall Mall", "If you pass Go, collect £200"));																															//3
		chanceCards.add(new Card("Advance token to nearest Utility", "If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown"));							//4
		chanceCards.add(new Card("Advance token to the nearest Railroad", "Pay owner twice the rental to which they are otherwise entitled. If Railroad is unowned, you may buy it from the Bank."));				//5
		chanceCards.add(new Card("Advance token to the nearest Railroad", "Pay owner twice the rental to which they are otherwise entitled. If Railroad is unowned, you may buy it from the Bank."));				//6
		chanceCards.add(new Card("Bank pays you dividend of $50", ""));																																				//7
		chanceCards.add(new Card("Get out of Jail Free", "This card may be kept until needed or sold"));																											//8
		chanceCards.add(new Card("Go Back 3 Spaces", ""));																																							//9
		chanceCards.add(new Card("Go directly to Jail", "Do not pass Go, do not collect £200"));																													//10
		chanceCards.add(new Card("Make general repairs on all your property", "For each house pay £25, for each hotel £100"));																						//11
		chanceCards.add(new Card("Pay poor tax of £15", ""));																																						//12
		chanceCards.add(new Card("Take a trip to King's Cross Station", "If you pass Go, collect £200"));																											//13
		chanceCards.add(new Card("Take a walk on Mayfair", "Advance to Mayfair"));																																	//14
		chanceCards.add(new Card("You have been elected Chairman of the Board", "Pay each player £50"));																											//15
		chanceCards.add(new Card("Your building loan matures", "Collect £150"));																																	//16
		Collections.shuffle(chanceCards);
	}

	private static void initCommunityCards() {
		commCards = new ArrayList<Card>();
		commCards.add(new Card("Advance to Go", "(Collect £200)"));																																					//17
		commCards.add(new Card("Bank error in your favor", "Collect £200"));																																		//18
		commCards.add(new Card("Doctor's fees", "Pay £50"));																																						//19
		commCards.add(new Card("From sale of stock you get £45", ""));																																				//20
		commCards.add(new Card("Get out of Jail Free", "This card may be kept until needed or sold"));																												//21
		commCards.add(new Card("Go directly to Jail", "Do not pass Go, do not collect £200"));																														//22
		commCards.add(new Card("Grand opera opening", "Collect £50 from every player for opening night seats"));																									//23
		commCards.add(new Card("Holiday fund matures", "Collect £100"));																																			//24
		commCards.add(new Card("Income tax refund", "Collect £20"));																																				//25
		commCards.add(new Card("It's your birthday", "Collect $10 from each player"));																																//26
		commCards.add(new Card("Life insurance matures", "Collect £100"));																																			//27
		commCards.add(new Card("Pay hospital fees of £100", ""));																																					//28
		commCards.add(new Card("Pay school fees of £150", ""));																																						//29
		commCards.add(new Card("Receive £25 consultancy fee", ""));																																					//30
		commCards.add(new Card("You are assessed for street repairs", "£40 per house, £115 per hotel"));																											//31
		commCards.add(new Card("You have won second prize in a beauty contest", "Collect £10"));																													//32
		Collections.shuffle(commCards);
	}

	public static Player getPlayer() { return players.get(currentPlayerInt); }

	public static Player getPlayer(int i) {
		return players.get(i);
	}

	public static List<Player> getPlayers() {
		return players;
	}

	/*
	private static void printBoard() {
		if (!useGUI) {
			TextBased.printBoard();
		} else {
			//implement gui
		}
	}
	
	public static void printSquare(int square) {
		if (!useGUI) {
			TextBased.printSquare(square);
		} else {
			//implement gui
		}
	}

	private static void printPlayer(Player currentPlayer) {
		if (!useGUI) {
			TextBased.printPlayer(currentPlayer);
		} else {
			//implement gui
		}
	}
	
	private static void printPlayerDice(Player currentPlayer) {
		if (!useGUI) {
			TextBased.printPlayerDice(currentPlayer);
		} else {
			//implement gui
		}
	}

	private static void improveProperties(Player currentPlayer) {
		if (!currentPlayer.isAI()) {
			if (!useGUI) {
				TextBased.improveProperties(currentPlayer);
			} else {
				// implement gui
			}
		} else {
			// implement ai
		}
	}

	private static void mortgageProperties(Player currentPlayer) {
		if (!currentPlayer.isAI()) {
			if (!useGUI) {
				TextBased.mortgageProperties(currentPlayer);
			} else {
				// implement gui
			}
		} else {
			// implement ai
		}
	}

	public static void newPurchasableFound(Player cp) {
		if (!useGUI) {
			TextBased.newPurchasableFound(cp);
		} else {
			//implement gui
		}
	}

	*/
}
