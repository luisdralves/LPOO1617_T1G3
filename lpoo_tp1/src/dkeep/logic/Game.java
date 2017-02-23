package dkeep.logic;

public class Game {
	private GameMap map;
	private Hero hero;
	private Guard[] guards;
	private int currentLevel;
	private boolean gameOver;

	public Game(GameMap startingMap) {
		SetMap(startingMap);
		currentLevel = 1;
		gameOver = false;
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
		return gameOver;
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
		int newX = hero.getX();
		int newY = hero.getY();
		int ret = 1;
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
				SetMap(new KeepMap());
				break;
			}
			return 2;
		}
		if (heroCaught()) {
			gameOver = true;
			ret = 3;
		}
		if (map.isFree(newX, newY)) {
			hero.newPos(newX, newY);
		} else
			ret = 0;
		for (int i = 0; i < map.getGuardAmmount(); i++) {
			guards[i].move();
		}
		return ret;
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
