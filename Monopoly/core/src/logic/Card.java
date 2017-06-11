package logic;

public class Card {
    private static int lastid = 0;
    public int id;
    private String title;
    private String desc;

    public Card() {
        id = ++lastid;
        title = "";
        desc = "";
    }

    public Card(String t, String d) {
        id = ++lastid;
        title = t;
        desc = d;
    }

    public String getDesc() {
        return desc;
    }

    public String getTitle() {
        return title;
    }
}
