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
			head = "_ _\t: ".toCharArray();
			if (playersPos.get(0) == i) {
				head[0] = '1';
			}
			if (playersPos.get(1) == i) {
				head[2] = '2';
			}
			System.out.println(new String(head) + i + ", " + Board.getSquare(i));
		}
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
		int inputInt;
		System.out.println("Do you wish to improve one of your properties? (y/n)");
		inputChar = readYN();

		if (inputChar == 'y' || inputChar == 'Y') {
			boolean improveMore = true;
			while (improveMore) {
				int i = 1;
				System.out.println("Choose a property to improve:");
				for (int prop : cp.getProperties()) {
					System.out.println(i + ". " + Board.getSquare(prop));
					i++;
				}
				inputInt = readInt(1, i);

				Property property = (Property) Board.getSquare(cp.getProperties().get(inputInt));
				if (property.getHouses() == 5) {
					System.out.println("Your property is already developed to the max, you fool!");
				} else if (property.getHouses() == 4) {
					System.out.println("Buy a hotel?");
					inputChar = readYN();
					if (inputChar == 'y' || inputChar == 'Y') {
						if (Board.getHotels() > 0) {
							property.addToHouses(1);
							Board.addToHotels(-1);
							Board.addToHouses(4);
						} else {
							System.out.println("Sorry, no more hotels available to develop");
						}
					}
				} else if (property.getHouses() < 4) {
					System.out.println("Do you wish to buy:");
					for (i = 1; i < 5 - property.getHouses(); i++) {
						if (i == 1)
							System.out.println(i + ". a hotel?");
						else
							System.out.println(i + ". " + (6 - i) + " houses?");
					}
					inputInt = readInt(1, i);
				}
				System.out.println("Improve another property? (y/n)");
				inputChar = readYN();
				if (inputChar == 'y' || inputChar == 'Y')
					improveMore = true;
				else
					improveMore = false;
			}
		}
	}

	public static void mortgageProperties(Player currentPlayer) {
		// TODO Auto-generated method stub

	}

	public static void newPurchasableFound(Purchasable p) {
		// TODO Auto-generated method stub

	}
}
