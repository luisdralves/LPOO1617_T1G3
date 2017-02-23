package lpoo_tp1;

import java.util.Scanner;
import java.util.Random;

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
		int shrekX = 4, shrekY = 1;
		int shrekClubX = 4, shrekClubY = 1;
		String guardMovement = "assssaaaaaasdddddddwwwww";
		char move;
		boolean level1NotOver = false, level1Win = true;
		boolean level2NotOver = true, level2Win = false;
		int iterator = 0;
		Random rn = new Random();

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
				level1Win = true;
				level1NotOver = false;
			}

			if ((heroX == guardX && (heroY == guardY - 1 || heroY == guardY + 1))
					|| (heroY == guardY && (heroX == guardX - 1 || heroX == guardX + 1))
					|| (heroY == guardY && heroX == guardX)) {
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

		if (level1Win) {
			heroX = 1;
			heroY = 8;
			leverX = 8;
			leverY = 1;
			exit1Y = 1;
			exitX = 0;
			int shrekMove;
			int shrekClub;
			boolean hasKey = false;
			boolean openDoor = false;
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
					if (hasKey && level2[heroY][heroX] == 'I')
						openDoor = true;
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
					if (hasKey)
						level2[heroY][heroX] = 'K';
					else
						level2[heroY][heroX] = 'H';

					if (openDoor)
						level2[heroY][heroX - 1] = 'S';
					else
						System.out.println("Where do you think you're going?");
				} else if (hasKey)
					level2[heroY][heroX] = 'K';
				else
					level2[heroY][heroX] = 'H';

				shrekMove = rn.nextInt(4);
				if (level2[shrekY][shrekX] == '$')
					level2[shrekY][shrekX] = 'k';
				level2[shrekY][shrekX] = ' ';
				level2[shrekClubY][shrekClubX] = ' ';
				switch (shrekMove) {
				case 0:
					shrekY--;
					break;
				case 1:
					shrekY++;
					break;
				case 2:
					shrekX--;
					break;
				case 3:
					shrekX++;
					break;
				}
				if (level2[shrekY][shrekX] == 'X' || level2[shrekY][shrekX] == 'I' || level2[shrekY][shrekX] == 'S') {
					switch (shrekMove) {
					case 0:
						shrekY++;
						break;
					case 1:
						shrekY--;
						break;
					case 2:
						shrekX++;
						break;
					case 3:
						shrekX--;
						break;
					}
					level2[shrekY][shrekX] = 'O';
				} else if (shrekY == leverY && shrekX == leverX) {
					level2[shrekY][shrekX] = '$';
				} else
					level2[shrekY][shrekX] = 'O';

				shrekClub = rn.nextInt(4);
				switch (shrekClub) {
				case 0:
					shrekClubX = shrekX;
					shrekClubY = shrekY - 1;
					break;
				case 1:
					shrekClubX = shrekX;
					shrekClubY = shrekY + 1;
					break;
				case 2:
					shrekClubY = shrekY;
					shrekClubX = shrekX - 1;
					break;
				case 3:
					shrekClubY = shrekY;
					shrekClubX = shrekX + 1;
					break;
				}
				if (!(level2[shrekClubY][shrekClubX] == 'X' || level2[shrekClubY][shrekClubX] == 'I')) {
					level2[shrekClubY][shrekClubX] = '*';
				} else {
					shrekClubX = shrekX;
					shrekClubY = shrekY;
				}

				if (heroX == leverX && heroY == leverY) {
					// level2[exit1Y][exitX] = 'S';
					level2[heroY][heroX] = 'K';
					hasKey = true;
				}

				

				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						System.out.print(level2[i][j]);
						System.out.print(' ');
					}
					System.out.print('\n');
				}
				
				if ((heroX == shrekX && (heroY == shrekY - 1 || heroY == shrekY + 1))
						|| (heroY == shrekY && (heroX == shrekX - 1 || heroX == shrekX + 1))
						|| (heroY == shrekY && heroX == shrekX)
						|| (heroX == shrekClubX && (heroY == shrekClubY - 1 || heroY == shrekClubY + 1))
						|| (heroY == shrekClubY && (heroX == shrekClubX - 1 || heroX == shrekClubX + 1))
						|| (heroY == shrekClubY && heroX == shrekClubX)) {
					System.out.println("Slained by the ogre!!");
					level2NotOver = false;
				}

				if (heroX == exitX && heroY == exit1Y) {
					System.out.println("You escape the Crazy Ogre.");
					heroX = 1;
					heroY = 8;
					level2Win = true;
					level2NotOver = false;
				}
			}
		}
	}
}
