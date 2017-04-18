package mnpl.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
	protected String name;
	protected int square;
	protected int balance;
	protected int turnsInJail;
	protected List<Purchasable> properties;
	
	public Player() {
		square = 0;
		balance = 1500;
		turnsInJail = 0;
		properties = new ArrayList<Purchasable>();
	}

	public void play() {
		Random dice = new Random();

		int roll1 = 0;
		int roll2 = 0;

		if (turnsInJail > 0) {
			turnsInJail--;
			roll1 = dice.nextInt(6) + 1;
			roll2 = dice.nextInt(6) + 1;
		}

		int turnsRemaining = 1;
		int doubles = 0;

		if (roll1 == roll2)
			while (turnsRemaining > 0) {
				turnsRemaining--;
				roll1 = dice.nextInt(6) + 1;
				roll2 = dice.nextInt(6) + 1;

				if (roll1 == roll2) {
					turnsRemaining++;
					doubles++;
				}

				if (doubles == 3) {
					goToJail();
					return;
				}

				square += roll1 + roll2;
				if (square >= 40) {
					if (square == 40)
						balance += 200;
					balance += 200;
					square %= 40;
				}
			}
	}

	public void goToJail() {
		square = 10;
		turnsInJail = 3;
	}
}
