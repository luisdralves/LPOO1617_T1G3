package mnpl.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mnpl.logic.Board;
import mnpl.logic.Player;
import mnpl.logic.Property;
import mnpl.logic.Purchasable;
import mnpl.logic.Square;

public class Main {
	public static void main(String[] args) {
		Board b = new Board();
		Scanner sc = new Scanner(System.in);
		int i = 0;
		while (true) {
			printBoard(b);
			Player currentPlayer = Board.getPlayer(i);
			int turnsRemaining = 1;
			int doublesOld = 0;
			int doublesNew = 0;
			while (turnsRemaining > 0 && doublesNew != -1) {
				System.out.println(currentPlayer.getName());
				turnsRemaining--;
				doublesNew = currentPlayer.play(doublesOld);
				if (doublesNew > doublesOld)
					turnsRemaining++;
				doublesOld = doublesNew;
				System.out.println("Dice roll: " + currentPlayer.getDiceRoll() + " (" + currentPlayer.getDice()[0]
						+ " + " + currentPlayer.getDice()[1] + ")");
				System.out.println("Total double throws: " + doublesNew);
				System.out.println("Balance: " + currentPlayer.getBalance());

				Square currentSquare = Board.getSquare(currentPlayer.getPosition());
				System.out.println("You land on " + currentSquare.getTitle() + '!');
				if (currentSquare instanceof Purchasable) {
					if (!((Purchasable) currentSquare).isOwned()) {
						System.out.println(currentSquare.getTitle() + " currently has no owner!");
						System.out.println("Do you buy it? (y/n)");
						char answer = sc.next().charAt(0);
						if (answer == 'y' || answer == 'Y') {
							currentPlayer.purchase();
							System.out.println("Congratulations on your purchase!");
							sc.nextLine();
						}

					}
				}

				sc.nextLine();
			}
			i++;
			i %= 2;
		}
	}

	public static void printBoard(Board b) {
		String ret = "------------------------------------------------------------------------------------------------------------------------------------------------\n";

		List<Square> squares = b.getSquares();
		List<Integer> playersPos = new ArrayList<Integer>();
		for (Player p : b.getPlayers()) {
			playersPos.add(p.getPosition());
		}

		// top row, line 0
		ret += "| ";
		for (int i = 20; i < 31; i++) {
			for (int j = 0; j < 10; j++) {
				if (squares.get(i) instanceof Property) {
					if (j < ((Property) squares.get(i)).getColourSet().getName().length())
						ret += ((Property) squares.get(i)).getColourSet().getName().charAt(j);
					else
						ret += ' ';
				} else
					ret += ' ';
			}
			ret += " | ";
		}
		ret += '\n';
		// top row, line 1
		ret += "| ";
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
		// top row, line 2
		ret += "| ";
		for (int i = 20; i < 31; i++) {
			for (int j = 10; j < 20; j++) {
				if (j < squares.get(i).getTitle().length())
					ret += squares.get(i).getTitle().charAt(j);
				else
					ret += ' ';
			}
			ret += " | ";
		}
		ret += '\n';
		// top row, line 3 player 1
		ret += "| ";
		for (int i = 0; i < 11; i++) {
			if (i + 20 == playersPos.get(0))
				ret += " Player 1 ";
			else
				for (int j = 0; j < 10; j++) {
					ret += ' ';
				}
			ret += " | ";
		}
		ret += '\n';
		// top row, line 4 player 2
		ret += "| ";
		for (int i = 0; i < 11; i++) {
			if (i + 20 == playersPos.get(1))
				ret += " Player 2 ";
			else
				for (int j = 0; j < 10; j++) {
					ret += ' ';
				}
			ret += " | ";
		}
		ret += '\n';

		ret += "------------------------------------------------------------------------------------------------------------------------------------------------\n";

		// middle rows
		for (int i = 1; i < 10; i++) {
			// line 0
			ret += "| ";
			for (int j = 0; j < 10; j++) {
				if (squares.get(20 - i) instanceof Property) {
					if (j < ((Property) squares.get(20 - i)).getColourSet().getName().length())
						ret += ((Property) squares.get(20 - i)).getColourSet().getName().charAt(j);
					else
						ret += ' ';
				} else
					ret += ' ';
			}
			ret += " |\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  | ";
			for (int j = 0; j < 10; j++) {
				if (squares.get(i + 30) instanceof Property) {
					if (j < ((Property) squares.get(i + 30)).getColourSet().getName().length())
						ret += ((Property) squares.get(i + 30)).getColourSet().getName().charAt(j);
					else
						ret += ' ';
				} else
					ret += ' ';
			}
			ret += " |\n";
			// line 1
			ret += "| ";
			for (int j = 0; j < 10; j++) {
				if (j < squares.get(20 - i).getTitle().length())
					ret += squares.get(20 - i).getTitle().charAt(j);
				else
					ret += ' ';
			}
			ret += " |\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  | ";
			for (int j = 0; j < 10; j++) {
				if (j < squares.get(i + 30).getTitle().length())
					ret += squares.get(i + 30).getTitle().charAt(j);
				else
					ret += ' ';
			}
			ret += " |\n";
			// line 2
			ret += "| ";
			for (int j = 10; j < 20; j++) {
				if (j < squares.get(20 - i).getTitle().length())
					ret += squares.get(20 - i).getTitle().charAt(j);
				else
					ret += ' ';
			}
			ret += " |\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  | ";
			for (int j = 10; j < 20; j++) {
				if (j < squares.get(i + 30).getTitle().length())
					ret += squares.get(i + 30).getTitle().charAt(j);
				else
					ret += ' ';
			}
			ret += " |\n";
			// line 3 player 1
			ret += "| ";
			if (20 - i == playersPos.get(0))
				ret += " Player 1 ";
			else
				for (int j = 0; j < 10; j++) {
					ret += ' ';
				}
			ret += " |\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  | ";
			if (i + 30 == playersPos.get(0))
				ret += " Player 1 ";
			else
				for (int j = 0; j < 10; j++) {
					ret += ' ';
				}
			ret += " |\n";
			// line 4 player 2
			ret += "| ";
			if (20 - i == playersPos.get(1))
				ret += " Player 2 ";
			else
				for (int j = 0; j < 10; j++) {
					ret += ' ';
				}
			ret += " |\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  | ";
			if (i + 30 == playersPos.get(1))
				ret += " Player 2 ";
			else
				for (int j = 0; j < 10; j++) {
					ret += ' ';
				}
			ret += " |\n";
			if (i < 9)
				ret += "--------------\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  --------------\n";
		}
		ret += "------------------------------------------------------------------------------------------------------------------------------------------------\n";

		// bottom row, line 0
		ret += "| ";
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 10; j++) {
				if (squares.get(10 - i) instanceof Property) {
					if (j < ((Property) squares.get(10 - i)).getColourSet().getName().length())
						ret += ((Property) squares.get(10 - i)).getColourSet().getName().charAt(j);
					else
						ret += ' ';
				} else
					ret += ' ';
			}
			ret += " | ";
		}
		ret += '\n';
		// bottom row, line 1
		ret += "| ";
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 10; j++) {
				if (j < squares.get(10 - i).getTitle().length())
					ret += squares.get(10 - i).getTitle().charAt(j);
				else
					ret += ' ';
			}
			ret += " | ";
		}
		ret += '\n';
		// bottom row, line 2
		ret += "| ";
		for (int i = 0; i < 11; i++) {
			for (int j = 10; j < 20; j++) {
				if (j < squares.get(10 - i).getTitle().length())
					ret += squares.get(10 - i).getTitle().charAt(j);
				else
					ret += ' ';
			}
			ret += " | ";
		}
		ret += '\n';
		// bottom row, line 3 player 1
		ret += "| ";
		for (int i = 0; i < 11; i++) {
			if (10 - i == playersPos.get(0))
				ret += " Player 1 ";
			else
				for (int j = 0; j < 10; j++) {
					ret += ' ';
				}
			ret += " | ";
		}
		ret += '\n';
		// bottom row, line 4 player 2
		ret += "| ";
		for (int i = 0; i < 11; i++) {
			if (10 - i == playersPos.get(1))
				ret += " Player 2 ";
			else
				for (int j = 0; j < 10; j++) {
					ret += ' ';
				}
			ret += " | ";
		}
		ret += '\n';

		ret += "------------------------------------------------------------------------------------------------------------------------------------------------\n";

		System.out.print(ret);
	}
}
