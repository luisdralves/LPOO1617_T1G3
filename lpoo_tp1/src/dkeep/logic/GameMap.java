package dkeep.logic;

import java.util.Arrays;

public abstract class GameMap {
	protected char[][] map;
	protected int heroStartingPosX;
	protected int heroStartingPosY;

	public char[][] getMap() {
		char[][] ret = new char[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ret[i][j] = map[i][j];
			}
		}
		return ret;
	}

	boolean isFree(int x, int y) {
		return map[y][x] == ' ';
	}

	public int getHeroStartingPosX() {
		return heroStartingPosX;
	}

	public int getHeroStartingPosY() {
		return heroStartingPosY;
	}
}
