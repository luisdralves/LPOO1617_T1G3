package dkeep.logic;

public class Game {
	private GameMap map;
	private Hero hero;
	private Guard[] guards;
	private int currentLevel;

	public Game(GameMap startingMap) {
		SetMap(startingMap);
		currentLevel = 1;
	}

	public void SetMap(GameMap map) {
		this.map = map;
		hero = new Hero();
		hero.newPos(map.getHeroStartingPosX(), map.getHeroStartingPosY());
		guards = new Guard[map.getGuardAmmount()];
		for (int i = 0; i < map.getGuardAmmount(); i++) {
			switch (map.getGuardTypes(i)) {
			case "rookie":
				guards[i] = new RookieGuard(map.getGuardStartingPosX(i), map.getGuardStartingPosY(i),
						map.getGuardMovement(i));
				break;
			}
		}
	}

	public boolean isGameOver() {
		return false; // complete
	}

	public char[][] getGameMap() {
		char[][] ret = map.getMap();
		ret[hero.getY()][hero.getX()] = hero.getC();
		for (int i = 0; i < map.getGuardAmmount(); i++) {
			ret[guards[i].getY()][guards[i].getX()] = guards[i].getC();
		}
		return ret;
	}

	public String getVictoryMessage() {
		return map.victoryMessage;
	}
	public String getLossMessage() {
		return map.lossMessage;
	}

	public int update(char dir) {
		for (int i = 0; i < map.getGuardAmmount(); i++) {
			guards[i].move();
		}
		int newX = hero.getX();
		int newY = hero.getY();
		switch (dir) {
		case 'w':
		case 'W':
			newY--;
			break;
		case 's':
		case 'S':
			newY++;
			break;
		case 'a':
		case 'A':
			newX--;
			break;
		case 'd':
		case 'D':
			newX++;
			break;
		}
		if (map.hasLevers() && map.getChar(newX, newY) == 'k') {
			map.toggleDoors();
		}
		if (map.getChar(newX, newY) == 'S') {
			switch (currentLevel) {
			case 1:
				currentLevel++;
				SetMap(new DungeonMap());
				break;
			}
			return 2;
		}
		if (heroCaught())
			return 3;
		if (map.isFree(newX, newY)) {
			hero.newPos(newX, newY);
		} else
			return 0;
		return 1;
	}

	private boolean heroCaught() {
		boolean ret = false;
		int x = hero.getX(), y = hero.getY();
		for (int i = 0; i < map.getGuardAmmount(); i++) {
			int guardX = guards[i].getX(), guardY = guards[i].getY();
			if ((x == guardX && (y == guardY - 1 || y == guardY + 1))
					|| (y == guardY && (x == guardX - 1 || x == guardX + 1)) || (x == guardX && y == guardY)) {
				ret = true;
				break;
			}
		}
		return ret;
	}
}
