package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import screens.NewGameScreen;


public final class GameData {
	public static int currentPlayerInt;
	private static List<Player> players;
	private static List<Card> chanceCards;
	private static List<Card> commCards;

	static {
		currentPlayerInt = -1;

		initChanceCards();
		initCommunityCards();
		initPlayers();
	}

	public static void initPlayers() {
		players = new ArrayList<Player>();
		Player.playerNumber = 1;
		for (int i = 0; i < NewGameScreen.playerCount; i++) {
			players.add(new Player(NewGameScreen.playerNames.get(i).getText(), NewGameScreen.isAI.get(i).isChecked(), NewGameScreen.realDice.get(i).isChecked(), "token" + (i + 1) + ".png"));
		}
	}

	public static void initPlayersTest() {
		currentPlayerInt = -1;
		Player.playerNumber = 1;
		players = new ArrayList<Player>();
		for (int i = 0; i < 4; i++) {
			players.add(new Player(false, false));
		}
	}

	private static void initChanceCards() {
		chanceCards = new ArrayList<Card>();
		chanceCards.add(new Card("Advance to Go", "(Collect $200)"));                                                                                                                                                //1
		chanceCards.add(new Card("Advance to Trafalgar Square", "If you pass Go, collect $200"));                                                                                                                    //2
		chanceCards.add(new Card("Advance to Pall Mall", "If you pass Go, collect $200"));                                                                                                                            //3
		chanceCards.add(new Card("Advance token to nearest Utility", "If unowned, you may buy it from the Bank."));                                                                                                    //4
		chanceCards.add(new Card("Advance token to the nearest Railroad", "If Railroad is unowned, you may buy it from the Bank."));                                                                                //5
		chanceCards.add(new Card("Advance token to the nearest Railroad", "If Railroad is unowned, you may buy it from the Bank."));                                                                                //6
		chanceCards.add(new Card("Bank pays you dividend of $50", ""));																																				//7
		chanceCards.add(new Card("Get out of Jail Free", "This card may be kept until needed or sold"));																											//8
		chanceCards.add(new Card("Go Back 3 Spaces", ""));																																							//9
		chanceCards.add(new Card("Go directly to Jail", "Do not pass Go, do not collect $200"));                                                                                                                    //10
		chanceCards.add(new Card("Make general repairs on all your property", "For each house pay $25, for each hotel $100"));                                                                                        //11
		chanceCards.add(new Card("Pay poor tax of $15", ""));                                                                                                                                                        //12
		chanceCards.add(new Card("Take a trip to King's Cross Station", "If you pass Go, collect $200"));                                                                                                            //13
		chanceCards.add(new Card("Take a walk on Mayfair", "Advance to Mayfair"));																																	//14
		chanceCards.add(new Card("You have been elected Chairman of the Board", "Pay each player $50"));                                                                                                            //15
		chanceCards.add(new Card("Your building loan matures", "Collect $150"));                                                                                                                                    //16
		Collections.shuffle(chanceCards);
	}

	private static void initCommunityCards() {
		commCards = new ArrayList<Card>();
		commCards.add(new Card("Advance to Go", "(Collect $200)"));                                                                                                                                                    //17
		commCards.add(new Card("Bank error in your favor", "Collect $200"));                                                                                                                                        //18
		commCards.add(new Card("Doctor's fees", "Pay $50"));                                                                                                                                                        //19
		commCards.add(new Card("From sale of stock you get $45", ""));                                                                                                                                                //20
		commCards.add(new Card("Get out of Jail Free", "This card may be kept until needed or sold"));																												//21
		commCards.add(new Card("Go directly to Jail", "Do not pass Go, do not collect $200"));                                                                                                                        //22
		commCards.add(new Card("Grand opera opening", "Collect $50 from every player for opening night seats"));                                                                                                    //23
		commCards.add(new Card("Holiday fund matures", "Collect $100"));                                                                                                                                            //24
		commCards.add(new Card("Income tax refund", "Collect $20"));                                                                                                                                                //25
		commCards.add(new Card("It's your birthday", "Collect $10 from each player"));																																//26
		commCards.add(new Card("Life insurance matures", "Collect $100"));                                                                                                                                            //27
		commCards.add(new Card("Pay hospital fees of $100", ""));                                                                                                                                                    //28
		commCards.add(new Card("Pay school fees of $150", ""));                                                                                                                                                        //29
		commCards.add(new Card("Receive $25 consultancy fee", ""));                                                                                                                                                    //30
		commCards.add(new Card("You are assessed for street repairs", "$40 per house, $115 per hotel"));                                                                                                            //31
		commCards.add(new Card("You have won second prize in a beauty contest", "Collect $10"));                                                                                                                    //32
		Collections.shuffle(commCards);
	}

	public static Player getPlayer() {
		return players.get(currentPlayerInt);
	}

	public static Player getPlayer(int i) {
		return players.get(i);
	}

	public static List<Player> getPlayers() {
		return players;
	}

	public static Card getCommunityCard() {
		Card ret = commCards.get(0);
		commCards.remove(0);
		commCards.add(ret);
		return ret;
	}

	public static Card getChanceCard() {
		Card ret = chanceCards.get(0);
		chanceCards.remove(0);
		chanceCards.add(ret);
		return ret;
	}
}
