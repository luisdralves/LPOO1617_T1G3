package lpoo_tp1;

import java.util.Scanner;

public class HelloWorld {
	public static void main(String[] args) {
		Character level1[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X' },
				{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
				{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X' },
				{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X' },
				{ 'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

		Character level2[][] = { { 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
				{ 'I', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'k', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

		Scanner input = new Scanner(System.in);
		int heroX = 1, heroY = 1;
		int leverX = 7, leverY = 8;
		int exitX = 0, exit1Y = 5, exit2Y = 6;
		int guardX = 8, guardY = 1;
		String guardMovement = "assssaaaaaasdddddddwwwww";
		char move;
		boolean level1NotOver = true, level1Win = false;
		boolean level2NotOver = true, level2Win = false;
		int iterator = 0;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(level1[i][j]);
				System.out.print(' ');
			}
			System.out.print('\n');
		}

		while (level1NotOver) {
			System.out.print("Direction of movement (wasd):");
			move = input.next().charAt(0);
			level1[heroY][heroX] = ' ';
			switch (move) {
			case 'w':
			case 'W':
				heroY--;
				break;
			case 's':
			case 'S':
				heroY++;
				break;
			case 'a':
			case 'A':
				heroX--;
				break;
			case 'd':
			case 'D':
				heroX++;
				break;
			}
			if (level1[heroY][heroX] == 'X' || level1[heroY][heroX] == 'I') {
				switch (move) {
				case 'w':
				case 'W':
					heroY++;
					break;
				case 's':
				case 'S':
					heroY--;
					break;
				case 'a':
				case 'A':
					heroX++;
					break;
				case 'd':
				case 'D':
					heroX--;
					break;
				}
				level1[heroY][heroX] = 'H';
				System.out.println("You're not a ghost");
			} else
				level1[heroY][heroX] = 'H';

			level1[guardY][guardX] = ' ';
			switch (guardMovement.charAt(iterator)) {
			case 'w':
				guardY--;
				break;
			case 's':
				guardY++;
				break;
			case 'a':
				guardX--;
				break;
			case 'd':
				guardX++;
				break;
			}
			level1[guardY][guardX] = 'G';

			if (heroX == leverX && heroY == leverY) {
				level1[exit1Y][exitX] = 'S';
				level1[exit2Y][exitX] = 'S';
			}

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					System.out.print(level1[i][j]);
					System.out.print(' ');
				}
				System.out.print('\n');
			}

			if (heroX == exitX && (heroY == exit1Y || heroY == exit2Y)) {
				System.out.println(
						"And just when you thought your captivity had ended, you realise you still have another challenge to overcome...go through the Keep's Crazy Ogre.");
				heroX = 1;
				heroY = 8;
				level1Win = true;
				level1NotOver = false;
			}

			if ((heroX == guardX && (heroY == guardY - 1 || heroY == guardY + 1))
					|| (heroY == guardY && (heroX == guardX - 1 || heroX == guardX + 1))) {
				System.out.println("Caught by the guard");
				level1NotOver = false;
			}

			iterator++;
			iterator %= 24;
		}
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(level2[i][j]);
				System.out.print(' ');
			}
			System.out.print('\n');
		}
		
		if (level1Win)
			while (level2NotOver) {
				System.out.print("Direction of movement (wasd):");
				move = input.next().charAt(0);
				level2[heroY][heroX] = ' ';
				switch (move) {
				case 'w':
				case 'W':
					heroY--;
					break;
				case 's':
				case 'S':
					heroY++;
					break;
				case 'a':
				case 'A':
					heroX--;
					break;
				case 'd':
				case 'D':
					heroX++;
					break;
				}
				if (level2[heroY][heroX] == 'X' || level2[heroY][heroX] == 'I') {
					switch (move) {
					case 'w':
					case 'W':
						heroY++;
						break;
					case 's':
					case 'S':
						heroY--;
						break;
					case 'a':
					case 'A':
						heroX++;
						break;
					case 'd':
					case 'D':
						heroX--;
						break;
					}
					level2[heroY][heroX] = 'H';
					System.out.println("Where do you think you're going?");
				} else
					level2[heroY][heroX] = 'H';
				
				
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						System.out.print(level2[i][j]);
						System.out.print(' ');
					}
					System.out.print('\n');
				}
			}
	}
}
