package dkeep.logic;

public class RookieGuard extends Guard {
	public RookieGuard(int x, int y, String movement, boolean moving)
	{
		coords = new Coords(x, y);
		this.c = 'G';
		this.movement = movement;
		this.moving = moving;
		iterator = 0;
	}

	@Override
	public void move() {
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
		iterator++;
		iterator %= movement.length();
	}
}
