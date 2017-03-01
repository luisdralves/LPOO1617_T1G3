package dkeep.cli;

import java.util.Scanner;
import dkeep.logic.Game;
import dkeep.logic.Coords;
import dkeep.logic.DungeonMap;
import dkeep.logic.KeepMap;

public class Main {

	public static void main(String[] args) {
		Game g = new Game(new DungeonMap());
		while (!g.isGameOver()) {
			draw(g.getGameMap());
			char dir = askUser("Direction of movement (wasd): ");
			Coords newHeroPos = newHeroPos(dir);
			int gameState = g.update(newHeroPos);
			switch (gameState) {
			case 0:
				System.out.println("Invalid movement");
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

	public static char askUser(String msg) {
		Scanner input = new Scanner(System.in);
		System.out.print(msg);
		return input.next().charAt(0);
	}

	public static Coords newHeroPos(char dir) {
		Coords ret = new Coords();
		switch (dir) {
		case 'w':
		case 'W':
			ret.addY(-1);
			break;
		case 's':
		case 'S':
			ret.addY(1);
			break;
		case 'a':
		case 'A':
			ret.addX(-1);
			break;
		case 'd':
		case 'D':
			ret.addX(1);
			break;
		}
		return ret;
	}

}
