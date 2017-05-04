package mnpl.logic;

public class CardSquare extends Square {
	private boolean type;
	public CardSquare() {
		super();
		type = true;
	}
	
	public CardSquare(String title) {
		super(title);
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
