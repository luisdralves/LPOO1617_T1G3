package logic;

public class Square {
	protected String title;
	
	public Square() {
		title = "";
	}
	
	public Square(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	@Override
	public String toString() {
		return title;
	}
}
 