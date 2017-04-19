package mnpl.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
	
	private void rollDice() {
		Random dice = new Random();
		roll1 = dice.nextInt(6) + 1;
		roll2 = dice.nextInt(6) + 1;
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
			((Collectable) toPay).playerLands(this);
		}
	}

	public void play() {
		int turnsRemaining = 1;
		int doubles = 0;
		
		if(checkJail());
			while (turnsRemaining > 0) {
				turnsRemaining--;
				rollDice();

				if (roll1 == roll2) {
					turnsRemaining++;
					doubles++;
				}

				if (doubles == 3) {
					goToJail();
					return;
				}

				square += roll1 + roll2;
				checkGO();
				checkIfOwned();
			}
	}

	public void goToJail() {
		square = 10;
		turnsInJail = 3;
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
