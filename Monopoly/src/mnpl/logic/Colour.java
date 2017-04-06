package mnpl.logic;

public class Colour {
	protected String name;
	protected int r, g, b;
	
	public Colour() {
		name = "";
		r = 0;
		g = 0;
		b = 0;
	}
	
	public Colour(String n) {
		name = n;
		r = 0;
		g = 0;
		b = 0;
	}
	
	public Colour(String n, int red, int green, int blue) {
		name = "";
		r = red;
		g = green;
		b = blue;
	}
}
