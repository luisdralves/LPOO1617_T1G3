package dkeep.logic;

import java.util.List;
import java.util.Vector;

public class Game {
	private GameMap map;
	private Hero hero;
	private Guard[] guards;
	private List<Ogre> ogres;
	private int currentLevel;
	private boolean gameOver;

	public Game(GameMap startingMap) {
		SetMap(startingMap);
		currentLevel = 1;
		gameOver = false;
	}

	public void SetMap(GameMap map) {
		this.map = map;
		hero = new Hero(map.getHeroStartingPosX(), map.getHeroStartingPosY());
		guards = new Guard[map.getGuardAmmount()];
		for (int i = 0; i < map.getGuardAmmount(); i++) {
			switch (map.getGuardTypes(i)) {
			case "rookie":
				guards[i] = new RookieGuard(map.getGuardStartingPosX(i), map.getGuardStartingPosY(i),
						map.getGuardMovement(i));
				break;
			case "drunken":
				guards[i] = new DrunkenGuard(map.getGuardStartingPosX(i), map.getGuardStartingPosY(i),
						map.getGuardMovement(i));
				break;
			case "suspicious":
				guards[i] = new SuspiciousGuard(map.getGuardStartingPosX(i), map.getGuardStartingPosY(i),
						map.getGuardMovement(i));
				break;
			}
		}
		ogres = new Vector<Ogre>();
		for (int i = 0; i < map.getOgreAmmount(); i++) {
			ogres.add(new Ogre(map.getOgreStartingPosX(i), map.getOgreStartingPosY(i)));
		}
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public char[][] getGameMap() {
		char[][] ret = map.getMap();
		ret[hero.getY()][hero.getX()] = hero.getC();
		for (Guard guard : guards) {
			ret[guard.getY()][guard.getX()] = guard.getC();
		}
		for (Ogre ogre : ogres) {
			if (map.isFree(ogre.getClub()))
				ret[ogre.getClub().getY()][ogre.getClub().getX()] = '*';
			ret[ogre.getY()][ogre.getX()] = ogre.getC();
		}
		return ret;
	}

	public String getVictoryMessage() {
		return map.victoryMessage;
	}

	public String getLossMessage() {
		return map.lossMessage;
	}

	public int update(Coords heroVecMov) {
		int ret = 1;
		Coords newHeroPos = heroVecMov.add(hero.getCoords());
		if (map.hasLevers() && map.getChar(newHeroPos) == 'k') {
			map.toggleDoors();
		} else if (map.getChar(newHeroPos) == 'k') {
			map.setChar(newHeroPos, ' ');
			hero.aquiresKey();
		}
		if (map.getChar(newHeroPos) == 'S') {
			switch (currentLevel) {
			case 1:
				currentLevel++;
				SetMap(new KeepMap());
				break;
			case 2:
				gameOver = true;
			}
			return 2;
		}
		if (hero.hasKey() && map.getChar(newHeroPos) == 'I') {
			map.openDoors();
		}
		if (heroCaught()) {
			gameOver = true;
			ret = 3;
		}
		if (map.isFree(newHeroPos)) {
			hero.move(newHeroPos);
		} else
			ret = 0;
		for (int i = 0; i < map.getGuardAmmount(); i++) {
			guards[i].move();
		}
		for (Ogre ogre : ogres) {
			Coords newOgrePos = ogre.newCoords();
			if (map.isFree(newOgrePos) && ogre.getC() == 'O')
				ogre.move(newOgrePos);
			else
				ogre.move(ogre.getCoords());
		}
		return ret;
	}

	private boolean heroCaught() {
		boolean ret = false;
		int x = hero.getX(), y = hero.getY();
		for (int i = 0; i < map.getGuardAmmount(); i++) {
			if (guards[i].getC() == 'G') {
				int guardX = guards[i].getX(), guardY = guards[i].getY();
				if ((x == guardX && (y == guardY - 1 || y == guardY + 1))
						|| (y == guardY && (x == guardX - 1 || x == guardX + 1)) || (x == guardX && y == guardY)) {
					ret = true;
					break;
				}
			}
		}
		for (Ogre ogre : ogres)
		{
			int clubX = ogre.getClub().getX(), clubY = ogre.getClub().getY();
			if ((x == clubX && (y == clubY - 1 || y == clubY + 1))
					|| (y == clubY && (x == clubX - 1 || x == clubX + 1)) || (x == clubX && y == clubY)) {
				ret = true;
				break;
			}
			if (ogre.getC() == 'O') {
				int ogreX = ogre.getX(), ogreY = ogre.getY();
				if ((x == ogreX && (y == ogreY - 1 || y == ogreY + 1))
						|| (y == ogreY && (x == ogreX - 1 || x == ogreX + 1)) || (x == ogreX && y == ogreY)) {
					ret = true;
					break;
				}
			}
		}
		return ret;
	}
}
