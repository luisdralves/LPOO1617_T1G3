package lpoo_tp1;

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
