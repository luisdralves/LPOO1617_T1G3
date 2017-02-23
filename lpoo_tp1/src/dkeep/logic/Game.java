package dkeep.logic;

public class Game {
	private GameMap map;
	private Hero hero;

	public Game(GameMap startingMap) {
		map = startingMap;
		hero = new Hero();
		hero.newPos(map.getHeroStartingPosX(), map.getHeroStartingPosY());
	}

	public void SetMap(GameMap map) {
		this.map = map;
		hero = new Hero();
		hero.newPos(map.getHeroStartingPosX(), map.getHeroStartingPosY());
	}
	
	public boolean isGameOver() {
		return false; //complete
	}
	
	public char[][] getGameMap()
	{
		char[][] ret = map.getMap();
		ret[hero.getY()][hero.getX()] = hero.getC();
		return ret;
	}
	
	public void update(char dir)
	{
		int newX = hero.getX();
		int newY = hero.getY();
		switch (dir) {
		case 'w':
		case 'W':
			newY--;
			break;
		case 's':
		case 'S':
			newY++;
			break;
		case 'a':
		case 'A':
			newX--;
			break;
		case 'd':
		case 'D':
			newX++;
			break;
		}
		if(map.isFree(newX, newY))
		{
			
			hero.newPos(newX, newY);
		}
	}
}
