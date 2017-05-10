package mnpl.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mnpl.logic.Board;
import mnpl.logic.Game;
import mnpl.logic.Player;
import mnpl.logic.Property;
import mnpl.logic.Purchasable;
import mnpl.logic.Square;

public class TextBased {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Game.gameLoop();
		sc.close();
	}

	public static char readYN() {
		char inputChar;
		do {
			inputChar = sc.next().charAt(0);
		} while (inputChar != 'y' && inputChar != 'n' && inputChar != 'Y' && inputChar != 'N');
		return inputChar;
	}

	public static int readInt(int min, int max) {
		int inputInt;
		do {
			inputInt = sc.nextInt();
		} while (inputInt >= max || inputInt < min);
		return inputInt;
	}

	public static void printBoard() {
		char[] head;
		List<Square> squares = Board.getSquares();
		List<Integer> playersPos = new ArrayList<Integer>();
		for (Player p : Game.getPlayers()) {
			playersPos.add(p.getPosition());
		}

		for (int i = 0; i < squares.size(); i++) {
			head = "_ _ _ _ : ".toCharArray();
			for (int j = 0; j < playersPos.size(); j++) {
				
				if (playersPos.get(j) == i) {
					head[j*2] = (char) (j+1+'0');
				}
			}
			/*
			if (playersPos.get(0) == i) {
				head[0] = '1';
			}
			if (playersPos.get(1) == i) {
				head[2] = '2';
			}
			if (playersPos.get(2) == i) {
				head[4] = '3';
			}
			if (playersPos.get(3) == i) {
				head[6] = '4';
			}
			*/
			System.out.print(new String(head));
			if (i < 10)
				System.out.print('0');
			System.out.print(i + ", " + Board.getSquare(i) + '\n');
		}
	}
	
	public static void printSquare(int i) {
		System.out.println("You land on " + Board.getSquare(i).getTitle());
		System.out.println(Board.getSquare(i));
	}

	public static void printPlayer(Player cp) {
		System.out.println(cp.getName());
		System.out.println("Balance: " + cp.getBalance());
	}

	public static void printPlayerDice(Player cp) {
		System.out.println("Dice roll: " + cp.getDiceRoll() + " (" + cp.getDice()[0] + " + " + cp.getDice()[1] + ")");
		System.out.println("Total double throws: " + cp.getDoubles());

	}

	public static void improveProperties(Player cp) {
		char inputChar;
		System.out.println("Do you wish to improve one of your properties? (y/n)");
		inputChar = readYN();

		if (inputChar == 'y' || inputChar == 'Y') {
			improveMore(cp);
		}
	}
	
	private static void improveMore(Player cp) {
		char inputChar;
		int inputInt;
		boolean improveMore = true;
		while (improveMore) {
			int i = 1;
			System.out.println("Choose a property to improve:");
			for (int prop : cp.getProperties()) {
				System.out.println(i + ". " + Board.getSquare(prop));
				i++;
			}
			inputInt = readInt(1, i);

			Property property = (Property) Board.getSquare(cp.getProperties().get(inputInt - 1));
			if (property.getHouses() == 5) {
				System.out.println("Your property is already developed to the max, you fool!");
			} else if (property.getHouses() == 4) {
				buyHotel(cp, property);
			} else if (property.getHouses() < 4) {
				buyHouses(cp, property);
			}
			System.out.println("Improve another property? (y/n)");
			inputChar = readYN();
			if (inputChar == 'y' || inputChar == 'Y')
				improveMore = true;
			else
				improveMore = false;
		}
	}
	
	private static void buyHotel(Player cp, Property p) {
		char inputChar;
		System.out.println("Buy a hotel?");
		inputChar = readYN();
		if (inputChar == 'y' || inputChar == 'Y') {
			if (Board.getHotels() > 0) {
				p.addToHouses(1);
				Board.addToHotels(-1);
				Board.addToHouses(4);
			} else {
				System.out.println("Sorry, no more hotels available to develop");
			}
		}
	}
	
	private static void buyHouses(Player cp, Property p) {
		int inputInt;
		System.out.println("Do you wish to buy:");
		int i;
		for (i = 1; i <= 5 - p.getHouses(); i++) {
			if (i == 5 - p.getHouses())
				System.out.println(i + ". a hotel?");
			else
				System.out.println(i + ". " + i + " houses?");
		}
		inputInt = readInt(1, i);
		p.addToHouses(inputInt);
		if (p.getHouses() == 5) {
			Board.addToHotels(-1);
			Board.addToHouses(4);
		} else {
			Board.addToHouses(-inputInt);
		}
	}

	public static void mortgageProperties(Player cp) {
		char inputChar;
		System.out.println("Do you wish to mortgage or unmortgage one of your properties? (y/n)");
		inputChar = readYN();

		if (inputChar == 'y' || inputChar == 'Y') {
			mortgageMore(cp);
		}
	}
	
	private static void mortgageMore(Player cp) {
		char inputChar;
		int inputInt;
		boolean improveMore = true;
		while (improveMore) {
			int i = 1;
			System.out.println("Choose a property to toggle mortgage/unmortgage:");
			for (int prop : cp.getAcquired()) {
				System.out.println(i + ". " + Board.getSquare(prop) + " (" + (((Purchasable) Board.getSquare(prop)).isActive() ? "active" : "mortgaged") + ")");
				i++;
			}
			inputInt = readInt(1, i);

			Purchasable purchasable = (Purchasable) Board.getSquare(cp.getAcquired().get(inputInt - 1));
			if (purchasable.isActive()) {
				cp.addToBalance(purchasable.getMortgage());
			} else {
				cp.addToBalance(-purchasable.getMortgage());
			}
			purchasable.toggleMortgage();
			
			System.out.println("Mortgage or unmortgage another property? (y/n)");
			inputChar = readYN();
			if (inputChar == 'y' || inputChar == 'Y')
				improveMore = true;
			else
				improveMore = false;
		}
	}
	
	public static void newPurchasableFound(Player cp) {
		char inputChar;
		Purchasable p = (Purchasable) Board.getSquare(cp.getPosition());
		
		System.out.println("Do you wish to buy it? (y/n)");
		inputChar = readYN();
		if (inputChar == 'y' || inputChar == 'Y') {
			cp.purchase();
		} else {
			auction(cp);
		}
	}
	
	public static void auction(Player playerWhoRefused) {
		char inputChar;
		int inputInt;
		Purchasable p = (Purchasable) Board.getSquare(playerWhoRefused.getPosition());
		int bid = p.getLandCost() / 2;
		int bidMin = bid;
		int bidMax = bid * 4 + 1;
		int greatestBidder = 0;
		int turnsWithoutNewBid = 0;
		int totalPlayers = Game.getPlayers().size();
		List<Integer> playersNotBidding = new ArrayList<Integer>();
		playersNotBidding.add(Game.getPlayers().indexOf(playerWhoRefused));
		
		System.out.println(p.getTitle() + " goes on auction!");
		
		int i;
		for (i = 0; turnsWithoutNewBid < totalPlayers; i++, i%=totalPlayers, turnsWithoutNewBid++) {
			if (!playersNotBidding.contains(i)) {
				Player cp = Game.getPlayer(i);
				System.out.println(cp.getName() + ", do you wish to place a bid? (y/n)");
				inputChar = readYN();
				if (inputChar == 'n' || inputChar == 'N') {
					playersNotBidding.add(i);
				} else {
					turnsWithoutNewBid = 0;
					System.out.println("Current bid: " + bid);
					System.out.println("Minimum bid: " + bidMin);
					System.out.println("Maximum bid: " + (bidMax - 1));
					inputInt = readInt(bidMin, bidMax);
					bid = inputInt;
					bidMin = bid + (bid/4);
					bidMax = bid * 4 + 1;
					greatestBidder = i;
				}
			}
		}
		
		if (playersNotBidding.size() + 1 == Game.getPlayers().size()) {
			Game.getPlayer(greatestBidder).purchase(playerWhoRefused.getPosition(), bid);
		}
	}
}
