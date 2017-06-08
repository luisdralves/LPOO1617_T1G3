package logic;

public class CardSquare extends Square {
	private boolean type;
	public CardSquare() {
		super();
		type = true;
	}
	
	public CardSquare(int pos, String title) {
		super(pos, title);
		if (title == "Community Chest")
			type = false;
		else
			type = true;
	}
	
	public boolean isChance() {
		return type;
	}
	
	public boolean isCommunity() {
		return !type;
	}
}
