package logic;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player {
	private static int playerNumber = 1;
	private int id;
	private String name;
	private boolean isAI;
	private int square;
	private int balance;
	private int turnsInJail;
	private int turnsRemaining;
	private int doubles;
	private boolean inJail;
	private int roll1, roll2;
	private boolean manualRoll;
	private static int[] properties = new int[] { 3, 6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23, 24, 26, 27, 29, 31, 32, 34, 37, 39 };
	private static int[] stations = new int[] { 5, 15, 25, 35 };
	private static int[] utilities = new int[] { 12, 28 };
	private List<Integer> acquired;
	private List<Integer> suspended;

	private Texture token;

	public Player(boolean mr, String path) {
		id = playerNumber;
		name = "Player ";
		name += playerNumber;
		playerNumber++;
		isAI = false;
		square = 0;
		balance = 1500;
		turnsInJail = 0;
		roll1 = 0;
		roll2 = 0;
		manualRoll = mr;
		acquired = new ArrayList<Integer>();
		suspended = new ArrayList<Integer>();

		token = new Texture(path);
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isAI() {
		return isAI;
	}

	public List<Integer> getAcquired() {
		return acquired;
	}
	
	public List<Integer> getSuspended() {
		return suspended;
	}

	public List<Integer> getProperties() {
		List<Integer> ret = new ArrayList<Integer>();
		for(int i : acquired) {
			for (int j : properties) {
				if (i == j) {
					ret.add(i);
				}
			}
		}
		return ret;
	}
	
	public List<Integer> getStations() {
		List<Integer> ret = new ArrayList<Integer>();
		for(int i : acquired) {
			for (int j : stations) {
				if (i == j) {
					ret.add(i);
				}
			}
		}
		return ret;
	}
	
	public List<Integer> getUtilities() {
		List<Integer> ret = new ArrayList<Integer>();
		for(int i : acquired) {
			for (int j : utilities) {
				if (i == j) {
					ret.add(i);
				}
			}
		}
		return ret;
	}

	public int getPosition() {
		return square;
	}

	public int getBalance() {
		return balance;
	}

	public int getTurnsRemaining() {
		return turnsRemaining;
	}

	public int getDoubles() {
		return doubles;
	}

	public boolean isInJail() {
		return inJail;
	}

	public int getDiceRoll() {
		return roll1 + roll2;
	}

	public int[] getDice() {
		return new int[] { roll1, roll2 };
	}

	public Texture getToken() {
		return token;
	}

	private void rollDice() {
		if (manualRoll) {
			rollDiceManual();
		} else {
			Random dice = new Random();
			roll1 = dice.nextInt(6) + 1;
			roll2 = dice.nextInt(6) + 1;
		}
	}

	private void rollDiceManual() {
		Scanner sc = new Scanner(System.in);
		System.out.println("roll:");
		roll1 = sc.nextInt();
		roll2 = sc.nextInt();
		//sc.close();
	}
	
	public void move() {
		square += roll1 + roll2;

		//GameData.printSquare(square);
		
		checkGO();
		Square currentSquare = Board.getSquare(square);
		if (currentSquare instanceof CardSquare) {
			//drawCard
		} else if (currentSquare instanceof Purchasable) {
			checkOwner((Purchasable) currentSquare);
		}
	}

	private void checkGO() {
		if (square >= 40) {
			if (square == 40)
				balance += 200;
			balance += 200;
			square %= 40;
		}
	}

	public int checkJail() {
		if (turnsInJail > 0) {
			turnsInJail--;
			rollDice();
			if (roll1 == roll2)
				return 1;
			else
				return 2;
		}
		return 0;
	}
	
	private void checkOwner(Purchasable p) {
		if (p.isOwned() && p.isActive()) {
			p.playerLands(this);
		} else if (p.isOwned() && !p.isActive()) {
			return;
		} else if (!p.isOwned()) {
			//GameData.newPurchasableFound(this);
		}
	}

	public void play(int turns, int doubles) {
		boolean inJail = false;
		rollDice();

		if (roll1 == roll2) {
			turns++;
			doubles++;
		}

		if (doubles == 3) {
			goToJail();
			inJail = true;
		} else {
			move();
			turns--;
		}
		this.turnsInJail = turns;
		this.doubles = doubles;
		this.inJail = inJail;
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
		Square toPurchase = Board.getSquare(square);
		if (toPurchase instanceof Purchasable) {
			acquired.add(square);
			addToBalance(-((Purchasable) toPurchase).getLandCost());
			((Purchasable) toPurchase).setOwner(this);
		}
	}
	
	public void purchase(int position, int amount) {
		Square toPurchase = Board.getSquare(position);
		if (toPurchase instanceof Purchasable) {
			acquired.add(position);
			addToBalance(-amount);
			((Purchasable) toPurchase).setOwner(this);
		}
	}

	public void mortgage(int i) {
		Square toMortgage = Board.getSquare(i);
		if (toMortgage instanceof Purchasable) {
			suspended.add(i);
			balance += ((Purchasable) toMortgage).getMortgage();
			((Purchasable) toMortgage).suspend();
		}
	}
}