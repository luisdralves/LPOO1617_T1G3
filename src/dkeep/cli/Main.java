package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.Game;
import dkeep.logic.Coords;
import dkeep.logic.DungeonMap;
import dkeep.logic.KeepMap;

public class Main {

	public static void main(String[] args) {
		Game g = new Game(2);
		while (!g.isGameOver()) {
			draw(g.getGameMap());
			char dir = askUser("Direction of movement (wasd): ");
			Coords newHeroPos = g.newHeroPos(dir);
			boolean gameState[] = g.update(newHeroPos);
			if (gameState[0]) {
				
				g.nextLevel();
				System.out.println(g.getVictoryMessage());

			}
			if (gameState[1]) {
				System.out.println(g.getLossMessage());
				draw(g.getGameMap());
			}
			if (gameState[2])
				System.out.println("Invalid movement");
			if (gameState[3])
				System.out.println("You have beaten an ogre to sleep");

		}

	}

	public static void draw(char[][] map) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(map[i][j]);
				System.out.print(' ');
			}
			System.out.print('\n');
		}
	}

	public static char askUser(String msg) {
		Scanner input = new Scanner(System.in);
		System.out.print(msg);
		return input.next().charAt(0);
	}

}
