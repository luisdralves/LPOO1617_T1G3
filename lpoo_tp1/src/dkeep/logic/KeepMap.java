package dkeep.logic;

import java.util.Random;

public class KeepMap extends GameMap {
	public KeepMap() {
		int maxOgres = 4;
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
		Random rand = new Random();
		ogreAmmount = rand.nextInt(maxOgres) + 1;
		ogreStartingPosX = new int[ogreAmmount];
		ogreStartingPosY = new int[ogreAmmount];
		for (int i = 0; i < ogreAmmount; i++) {
			ogreStartingPosX[i] = rand.nextInt(8) + 1;
			ogreStartingPosY[i] = rand.nextInt(6) + 1;
		}
	}
}
