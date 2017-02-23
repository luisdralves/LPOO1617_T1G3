package dkeep.logic;

public abstract class GameMap {
	protected char[][] map;
	protected int heroStartingPosX;
	protected int heroStartingPosY;

	public char[][] getMap() {
		return map;
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
