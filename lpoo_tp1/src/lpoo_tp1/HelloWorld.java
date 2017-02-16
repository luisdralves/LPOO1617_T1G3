package lpoo_tp1;

import java.util.Scanner; 

public class HelloWorld
{
	public static void main(String[] args)
	{
		Character board[][] = {{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
							   {'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X'},
							   {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
							   {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
							   {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
							   {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
							   {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
							   {'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
							   {'X', ' ', 'I', ' ', 'I', ' ', 'X', 'K', ' ', 'X'},
							   {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
		};
		
		Scanner input = new Scanner(System.in);
		int heroX = 1, heroY = 1;
		int leverX = 7, leverY = 8;
		int exitX = 0, exit1Y = 5, exit2Y = 6;
		int guardX = 8, guardY = 1;
		String guardMovement = "assssaaaaaasdddddddwwwww";
		char move;
		boolean gameNotOver = true;
		int iterator = 0;
		
		
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				System.out.print(board[i][j]);
				System.out.print(' ');
			} 
			System.out.print('\n');
		}
		
		while (gameNotOver)
		{
			System.out.print("Direction of movement (wasd):");
			move = input.next().charAt(0);
			board[heroY][heroX] = ' ';
			switch (move) {
			case 'w':
				heroY--;
				break;
			case 's':
				heroY++;
				break;
			case 'a':
				heroX--;
				break;
			case 'd':
				heroX++;
				break;
			}
			if (board[heroY][heroX] == 'X' || board[heroY][heroX] == 'I')
			{
				switch (move) {
				case 'w':
					heroY++;
					break;
				case 's':
					heroY--;
					break;
				case 'a':
					heroX++;
					break;
				case 'd':
					heroX--;
					break;
				}
				board[heroY][heroX] = 'H';
				System.out.println("You're not a ghost");
			}
			else
				board[heroY][heroX] = 'H';
			
			board[guardY][guardX] = ' ';
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
			board[guardY][guardX] = 'G';
			
			if (heroX == leverX && heroY == leverY)
			{
				board[exit1Y][exitX] = 'S';
				board[exit2Y][exitX] = 'S';
			}
			
			for (int i = 0; i < 10; i++)
			{
				for (int j = 0; j < 10; j++)
				{
					System.out.print(board[i][j]);
					System.out.print(' ');
				} 
				System.out.print('\n');
			}
			
			if (heroX == exitX && (heroY == exit1Y || heroY == exit2Y))
			{
				System.out.println("You win");
				gameNotOver = false;
			}
			
			if ((heroX == guardX && (heroY == guardY-1 || heroY == guardY+1)) || (heroY == guardY && (heroX == guardX-1 || heroX == guardX+1)))
			{
				System.out.println("Caught by the guard");
				gameNotOver = false;
			}
			
			iterator++;
			iterator %= 24;
		}
	} 
}
