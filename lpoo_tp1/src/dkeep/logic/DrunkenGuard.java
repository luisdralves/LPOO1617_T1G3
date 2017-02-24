package dkeep.logic;

import java.util.Random;

public class DrunkenGuard extends Guard {
	protected int direction;
	public DrunkenGuard(int x, int y, String movement) {
		this.x = x;
		this.y = y;
		this.c = 'G';
		this.movement = movement;
		iterator = 0;
		direction = 1;
	}

	@Override
	public void move() {
		Random rand = new Random();
		int  prob = rand.nextInt(100);
		if (c == 'G') {
			if (prob >= 25) {
				switch (movement.charAt(iterator)) {
				case 'w':
					y--;
					break;
				case 's':
					y++;
					break;
				case 'a':
					x--;
					break;
				case 'd':
					x++;
					break;
				}
				iterator += direction;
				iterator %= movement.length();
				if (iterator == -1)
					iterator = 23;
			} else {
				c = 'g';
			}
		} else if (c == 'g') {
			if (prob < 25) {
				prob = rand.nextInt(1);
				if (prob < 50) {
					direction = -direction;
					reverse();
					iterator+=direction;
					iterator %= movement.length();
					if (iterator == -1)
						iterator = 23;
				}
				c = 'G';
			}
		}
	}
}
