package logic;

public class Square {
    protected String title;
    protected int position;

    public Square() {
        position = 0;
        title = "";
    }

    public Square(int position, String title) {
        this.position = position;
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
 