package dkeep.logic;

//import java.util.Arrays;

public abstract class GameMap {
	public String victoryMessage;
	public String lossMessage;
	protected char[][] map;
	protected boolean hasLevers;
	protected Coords keyPos;
	protected boolean keyDropped;
	protected boolean doorsAreOpen;
	protected int doorsAmmount;
	protected Coords[] doorsPos;
	protected Coords heroStartingPos;
	protected boolean heroHasClub;
	protected boolean guardMoves;
	protected int guardAmmount;
	protected Coords[] guardStartingPos;
	protected int ogreAmmount;
	protected Coords[] ogreStartingPos;
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

	public void setChar(Coords pos, char c) {
		map[pos.getY()][pos.getX()] = c;
	}

	public boolean hasLevers() {
		return hasLevers;
	}

	public Coords getKeyPos() {
		return keyPos;
	}

	public void keyAquired() {
		keyDropped = false;
	}

	public boolean isKeyDropped() {
		return keyDropped;
	}

	boolean isFree(Coords coords) {
		return map[coords.getY()][coords.getX()] == ' ';
	}

	public int getHeroStartingPosX() {
		return heroStartingPos.getX();
	}

	public int getHeroStartingPosY() {
		return heroStartingPos.getY();
	}

	public boolean heroHasClub() {
		return heroHasClub;
	}

	public boolean areGuardsMoving() {
		return guardMoves;
	}

	public int getGuardAmmount() {
		return guardAmmount;
	}

	public int getGuardStartingPosX(int i) {
		return guardStartingPos[i].getX();
	}

	public int getGuardStartingPosY(int i) {
		return guardStartingPos[i].getY();
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
		return ogreStartingPos[i].getX();
	}

	public int getOgreStartingPosY(int i) {
		return ogreStartingPos[i].getY();
	}

	public void openDoors() {
		for (int i = 0; i < doorsAmmount; i++) {
			setChar(doorsPos[i], 'S');
		}
		doorsAreOpen = true;
	}

	public void closeDoors() {
		for (int i = 0; i < doorsAmmount; i++) {
			setChar(doorsPos[i], 'I');
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
