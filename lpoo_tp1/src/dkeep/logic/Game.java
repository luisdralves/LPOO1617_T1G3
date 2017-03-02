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
		currentLevel = 0;
		gameOver = false;
	}

	public void SetMap(GameMap map) {
		this.map = map;
		hero = new Hero(map.getHeroStartingPosX(), map.getHeroStartingPosY());
		if (map.heroHasClub())
			hero.aquiresClub();
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
		if (map.isKeyDropped())
			ret[map.getKeyPos().getY()][map.getKeyPos().getX()] = 'k';
		ret[hero.getY()][hero.getX()] = hero.getC();
		for (Guard guard : guards) {
			ret[guard.getY()][guard.getX()] = guard.getC();
		}
		for (Ogre ogre : ogres) {
			if (ogre.hasClub() && map.isFree(ogre.getClub())) {
				if (ogre.getClub().equals(map.getKeyPos()) && map.isKeyDropped())
					ret[ogre.getClub().getY()][ogre.getClub().getX()] = '$';
				else
					ret[ogre.getClub().getY()][ogre.getClub().getX()] = '*';
			}
			if (ogre.getCoords().equals(map.getKeyPos()) && map.isKeyDropped())
				ret[ogre.getY()][ogre.getX()] = '$';
			else
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

	public boolean[] update(Coords heroVecMov) {
		boolean ret[] = { false, false, false, false }; // win, lose,
														// invalid movement,
														// hero attacks
		Coords newHeroPos = heroVecMov.add(hero.getCoords());
		if (map.hasLevers() && map.getChar(newHeroPos) == 'k')
			map.toggleDoors();
		if (!(map.hasLevers()) && newHeroPos.equals(map.getKeyPos())) {
			hero.aquiresKey();
			map.keyAquired();
		}
		if (map.getChar(newHeroPos) == 'S') {
			currentLevel++;
			ret[0] = true;
			if (currentLevel > 1)
				gameOver = true;
			return ret;
		}
		if (hero.hasKey() && map.getChar(newHeroPos) == 'I') {
			map.openDoors();
		}
		boolean heroCaught[] = heroCaught();
		if (heroCaught[0]) {
			gameOver = true;
			ret[1] = true;
			return ret;
		}
		if (heroCaught[1]) {
			ret[3] = true;
		}
		if (map.isFree(newHeroPos)) {
			hero.move(newHeroPos);
		} else
			ret[2] = true;
		for (int i = 0; i < map.getGuardAmmount(); i++) {
			guards[i].move();
		}
		for (Ogre ogre : ogres) {
			Coords newOgrePos = ogre.newCoords();
			if (map.isFree(newOgrePos) && ogre.getC() == 'O')
				ogre.move(newOgrePos);
			else
				ogre.move(ogre.getCoords());
			if (ogre.hasClub())
				ogre.attack();
		}
		return ret;
	}

	private boolean[] heroCaught() {
		boolean ret[] = { false, false };
		for (Guard guard : guards) {
			if (guard.getC() == 'G') {
				if (hero.adjacent(guard)) {
					ret[0] = true;
					break;
				}
			}
		}
		for (Ogre ogre : ogres) {
			if (ogre.hasClub() && hero.getCoords().adjacent(ogre.getClub())) {
				ret[0] = true;
				break;
			}
			if (ogre.getC() == 'O') {
				if (hero.adjacent(ogre)) {
					if (hero.hasClub()) {
						ret[1] = true;
						ogre.sleepNow();
					} else {
						ret[0] = true;
						break;
					}
				}
			}
		}
		return ret;
	}

	public void nextLevel() {
		switch (currentLevel) {
		case 0:
			SetMap(new DungeonMap());
			break;
		case 1:
			SetMap(new KeepMap());
			break;
		}
		currentLevel++;
	}
}
