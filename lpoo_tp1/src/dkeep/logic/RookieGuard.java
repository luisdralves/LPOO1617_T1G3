package dkeep.logic;

public class RookieGuard extends Guard {
	public RookieGuard(int x, int y, String movement)
	{
		this.x = x;
		this.y = y;
		this.c = 'G';
		this.movement = movement;
		iterator = 0;
	}

	@Override
	public void move() {
		switch(movement.charAt(iterator)) {
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
		iterator++;
		iterator %= movement.length();
	}
}
