package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.DungeonMap;
import dkeep.logic.Game;

public class Main {

	public static void main(String[] args) {
		Game g = new Game(new DungeonMap());
		while (!g.isGameOver()) {
			draw(g.getGameMap());
			char dir = askUser();
			int gameState = g.update(dir);
			switch (gameState) {
			case 0:
				System.out.println("invalid movement");
				break;
			case 2:
				System.out.println(g.getVictoryMessage());
				break;
			case 3:
				System.out.println(g.getLossMessage());
				break;
			}
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

	public static char askUser() {
		Scanner input = new Scanner(System.in);
		System.out.print("Direction of movement (wasd):");
		return input.next().charAt(0);
	}

}
