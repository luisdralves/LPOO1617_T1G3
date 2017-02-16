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
		char move;
		boolean gameNotOver = true;
		
		
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
				board[heroY][heroX] = 'H';
			case 's':
				heroY++;
				board[heroY][heroX] = 'H';
			case 'a':
				heroX--;
				board[heroY][heroX] = 'H';
			case 'd':
				heroX++;
				board[heroY][heroX] = 'H';
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
		}
	} 
}
