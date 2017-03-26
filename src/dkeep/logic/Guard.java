package dkeep.logic;

public abstract class Guard extends Agent {
	protected String movement;
	protected int iterator;
	protected boolean moving;

	public Guard() {
		coords = new Coords();
		movement = "";
		moving = true;
		iterator = 0;
	}

	public abstract void move();

	protected void reverse() {
		String newMovement = "";
		for (int i = 0; i < movement.length(); i++) {
			switch(movement.charAt(i)) {
			case 'a':
				newMovement += 'd';
				break;
			case 'd':
				newMovement += 'a';
				break;
			case 's':
				newMovement += 'w';
				break;
			case 'w':
				newMovement += 's';
				break;
			}
		}
		movement = newMovement;
		//iterator = movement.length() - iterator;
	}
}
