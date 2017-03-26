package dkeep.logic;

import java.util.Random;

public class SuspiciousGuard extends Guard {
	protected int direction;

	public SuspiciousGuard(int x, int y, String movement, boolean moving) {
		coords = new Coords(x, y);
		this.c = 'G';
		this.movement = movement;
		this.moving = moving;
		iterator = 0;
		direction = 1;
	}

	@Override
	public void move() {
		Random rand = new Random();
		int prob = rand.nextInt(100);
		switch (movement.charAt(iterator)) {
		case 'w':
			coords.addY(-1);
			break;
		case 's':
			coords.addY(1);
			break;
		case 'a':
			coords.addX(-1);
			break;
		case 'd':
			coords.addX(1);
			break;
		}
		iterator += direction;
		iterator %= movement.length();
		if (iterator == -1)
			iterator = 23;
		if (prob < 15) {
			direction = -direction;
			reverse();
			iterator += direction;
			iterator %= movement.length();
			if (iterator == -1)
				iterator = 23;
		}
	}
}
