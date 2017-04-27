package mnpl.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player {
	private static int playerNumber = 1;
	private String name;
	private int square;
	private int balance;
	private int turnsInJail;
	private int roll1, roll2;
	private Board b;
	private List<Integer> aquired;
	private List<Integer> suspended;

	public Player() {
		name = "Player ";
		name += playerNumber;
		playerNumber++;
		square = 0;
		balance = 1500;
		turnsInJail = 0;
		aquired = new ArrayList<Integer>();
		suspended = new ArrayList<Integer>();
	}

	public String getName() {
		return name;
	}

	public List<Integer> getAquired() {
		return aquired;
	}

	public int getPosition() {
		return square;
	}

	public int getBalance() {
		return balance;
	}

	public int getDiceRoll() {
		return roll1 + roll2;
	}

	public int[] getDice() {
		return new int[] { roll1, roll2 };
	}

	private void rollDice() {
		/*Random dice = new Random();
		roll1 = dice.nextInt(6) + 1;
		roll2 = dice.nextInt(6) + 1;*/
		
		Scanner sc = new Scanner(System.in);
		System.out.println("roll:");
		roll1 = sc.nextInt();
		roll2 = sc.nextInt();
	}

	private void checkGO() {
		if (square >= 40) {
			if (square == 40)
				balance += 200;
			balance += 200;
			square %= 40;
		}
	}

	private boolean checkJail() {
		if (turnsInJail > 0) {
			turnsInJail--;
			rollDice();
			if (roll1 == roll2)
				return true;
			else
				return false;
		}
		return true;
	}

	private void checkIfOwned() {
		Square toPay = b.getSquare(square);
		if (toPay instanceof Purchasable && ((Purchasable) toPay).isOwned() && ((Purchasable) toPay).isActive()) {
			((Purchasable) toPay).playerLands(this);
		}
	}

	public int play(int doubles) {
		if (checkJail()) {
			rollDice();

			if (doubles == 3) {
				goToJail();
				return -1;
			}

			square += roll1 + roll2;
			checkGO();
			checkIfOwned();

			if (roll1 == roll2) {
				return doubles + 1;
			}
		}
		return 0;
	}

	public void goToJail() {
		square = 10;
		turnsInJail = 3;
	}
	
	public void addToBalance(int amount) {
		balance += amount;
	}

	public void transaction(Player p, int amount) {
		this.balance -= amount;
		p.balance += amount;
	}

	public void purchase() {
		Square toPurchase = b.getSquare(square);
		if (toPurchase instanceof Purchasable) {
			aquired.add(square);
			balance -= ((Purchasable) toPurchase).getLandCost();
			((Purchasable) toPurchase).setOwner(this);
		}
	}

	public void mortgage(int i) {
		Square toMortgage = b.getSquare(i);
		if (toMortgage instanceof Purchasable) {
			suspended.add(i);
			balance += ((Purchasable) toMortgage).getMortgage();
			((Purchasable) toMortgage).suspend();
		}
	}
}
