package dkeep.logic;

public class KeepMap extends GameMap {
	public KeepMap() {
		victoryMessage = "Congrats";
		lossMessage = "Slained by the ogre";
		map = new char[][] { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'k', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		hasLevers = false;
		doorsAreOpen = false;
		doorsAmmount = 1;
		doorsX = new int[doorsAmmount];
		doorsX[0] = 0;
		doorsY = new int[doorsAmmount];
		doorsY[0] = 1;
		heroStartingPosX = 1;
		heroStartingPosY = 8;
		guardAmmount = 0;
	}
}
