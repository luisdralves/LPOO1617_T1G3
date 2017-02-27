package dkeep.logic;

//import java.util.Arrays;

public abstract class GameMap {
	public String victoryMessage;
	public String lossMessage;
	protected char[][] map;
	protected boolean hasLevers;
	protected boolean doorsAreOpen;
	protected int doorsAmmount;
	protected int[] doorsX;
	protected int[] doorsY;
	protected int heroStartingPosX;
	protected int heroStartingPosY;
	protected int guardAmmount;
	protected int[] guardStartingPosX;
	protected int[] guardStartingPosY;
	protected int ogreAmmount;
	protected int[] ogreStartingPosX;
	protected int[] ogreStartingPosY;
	protected String[] guardMovements;
	protected String[] guardTypes;

	public String getVictoryMessage() {
		return victoryMessage;
	}
	public String getLossMessage() {
		return lossMessage;
	}
	
	public char[][] getMap() {
		char[][] ret = new char[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				ret[i][j] = map[i][j];
			}
		}
		return ret;
	}
	
	public char getChar(Coords pos) {
		return map[pos.getY()][pos.getX()];
	}
	
	public void setChar(Coords pos, char c)
	{
		map[pos.getY()][pos.getX()] = c;
	}

	public boolean hasLevers() {
		return hasLevers;
	}

	boolean isFree(Coords coords) {
		return map[coords.getY()][coords.getX()] == ' ';
	}

	public int getHeroStartingPosX() {
		return heroStartingPosX;
	}

	public int getHeroStartingPosY() {
		return heroStartingPosY;
	}
	
	public int getGuardAmmount() {
		return guardAmmount;
	}
	
	public int getGuardStartingPosX(int i) {
		return guardStartingPosX[i];
	}
	
	public int getGuardStartingPosY(int i) {
		return guardStartingPosY[i];
	}
	
	public String getGuardMovement(int i) {
		return guardMovements[i];
	}
	
	public String getGuardTypes(int i) {
		return guardTypes[i];
	}

	public int getOgreAmmount() {
		return ogreAmmount;
	}
	public int getOgreStartingPosX(int i) {
		return ogreStartingPosX[i];
	}
	public int getOgreStartingPosY(int i) {
		return ogreStartingPosY[i];
	}
	public void openDoors() {
		for (int i = 0; i < doorsAmmount; i++)
		{
			map[doorsY[i]][doorsX[i]] = 'S';
		}
		doorsAreOpen = true;
	}
	
	public void closeDoors() {
		for (int i = 0; i < doorsAmmount; i++)
		{
			map[doorsY[i]][doorsX[i]] = 'I';
		}
		doorsAreOpen = false;
	}
	
	public void toggleDoors() {
		if (doorsAreOpen)
			closeDoors();
		else
			openDoors();
	}
}
