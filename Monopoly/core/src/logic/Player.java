package logic;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import screens.PlayScreen;

public class Player {
    public static int playerNumber = 1;
    private static int[] properties = new int[]{3, 6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23, 24, 26, 27, 29, 31, 32, 34, 37, 39};
    private static int[] stations = new int[]{5, 15, 25, 35};
    private static int[] utilities = new int[]{12, 28};
    private int id;
    private String name;
    private boolean isAI;
    private boolean realDice;
    private boolean yolo;
    private int square;
    private int balance;
    private int turnsInJail, turnsRemaining;
    private int doubles;
    private boolean inJail, diceHaveBeenRolled;
    private int roll1, roll2;
    private List<Integer> acquired;
    private List<Integer> suspended;

    private Texture token;
    private String status;

    public Player(boolean ai, boolean rd) {
        id = playerNumber;
        name = "Player ";
        name += playerNumber;
        playerNumber++;
        isAI = ai;
        realDice = rd;
        square = 0;
        balance = 1500;
        turnsInJail = 0;
        yolo = false;
        roll1 = 0;
        roll2 = 0;
        diceHaveBeenRolled = false;
        acquired = new ArrayList<Integer>();
        suspended = new ArrayList<Integer>();

//      token = new Texture("token" + id + ".png");
        status = "";
    }

    public Player(String name, boolean ai, boolean rd, String path) {
        this(ai, rd);
        token = new Texture(path);
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAI() {
        return isAI;
    }

    public boolean realDice() {
        return realDice;
    }

    public List<Integer> getAcquired() {
        return acquired;
    }

    private void removeAcquired(int i) {
        acquired.remove(acquired.indexOf(i));
    }

    public List<Integer> getProperties() {
        List<Integer> ret = new ArrayList<Integer>();
        for (int i : acquired) {
            for (int j : properties) {
                if (i == j) {
                    ret.add(i);
                }
            }
        }
        return ret;
    }
    public int getPosition() {
        return square;
    }

    public int getBalance() {
        return balance;
    }

    public int getTurnsRemaining() {
        return turnsRemaining;
    }

    public int getDoubles() {
        return doubles;
    }

    public boolean isInJail() {
        return inJail;
    }

    public int getDiceRoll() {
        return roll1 + roll2;
    }

    public int[] getDice() {
        return new int[]{roll1, roll2};
    }

    public Texture getToken() {
        return token;
    }

    public void rollDice() {
        Random dice = new Random();
        roll1 = dice.nextInt(6) + 1;
        roll2 = dice.nextInt(6) + 1;
        diceHaveBeenRolled = true;
        roll1 = 3;
        roll2 = 4;
    }

    public void rollDice(int i1, int i2) {
        roll1 = i1;
        roll2 = i2;
        diceHaveBeenRolled = true;
    }

    public void move() {
        int pos1 = square;
        square += roll1 + roll2;

        checkCommCards();
        checkChanceCards();
        checkGO();
        checkSpecialSquares();
        Square currentSquare = Board.getSquare(square);
        if (currentSquare instanceof CardSquare) {
        } else if (currentSquare instanceof Purchasable) {
            checkOwner((Purchasable) currentSquare);
        }
    }

    private void checkSpecialSquares() {
        switch (square) {
            case 4:
                balance -= 200;
                break;
            case 30:
                goToJail();
                break;
            case 38:
                balance -= 100;
                break;
        }
    }

    private void checkGO() {
        if (square >= 40) {
            if (square == 40)
                balance += 200;
            balance += 200;
            square %= 40;
        }
    }

    private void checkCommCards() {
        Card card;
        if (square == 2 || square == 17 || square == 33) {
            card = GameData.getCommunityCard();
        } else
            return;
        switch (card.id) {
            case 17:
                square = 0;
                break;
            case 18:
                balance += 200;
                break;
            case 19:
                balance -= 50;
                break;
            case 20:
                balance += 45;
                break;
            case 21:
                yolo = true;
                break;
            case 22:
                goToJail();
                break;
            case 23:
                for (Player p : GameData.getPlayers())
                    transaction(p, -50);
                break;
            case 28:
            case 24:
                balance -= 100;
                break;
            case 25:
                balance += 20;
                break;
            case 26:
                for (Player p : GameData.getPlayers())
                    transaction(p, -10);
                break;
            case 27:
                balance += 100;
                break;
            case 29:
                balance -= 150;
                break;
            case 30:
                balance += 25;
                break;
            case 31:
                for (int i : acquired)
                    if (Board.getSquare(i) instanceof Property) {
                        if (((Property) Board.getSquare(i)).getHouses() < 5)
                            balance -= 40;
                        else
                            balance -= 115;
                    }
                break;
            case 32:
                balance += 10;
        }
        status += "Community chest:\n";
        status += card.getTitle() + '\n';
        for (int i = 0; i < card.getDesc().length(); i++) {
            status += card.getDesc().charAt(i);
            if (i % 55 == 54)
                status += '\n';
        }
    }

    private void checkChanceCards() {
        Card card;
        if (square == 7 || square == 22 || square == 36) {
            card = GameData.getChanceCard();
        } else
            return;
        switch (card.id) {
            case 1:
                square = 0;
                break;
            case 2:
                square = 24;
                break;
            case 3:
                square = 11;
                break;
            case 4:
                if (square < 12 || square > 28)
                    square = 12;
                else if (square < 28)
                    square = 28;
                break;
            case 5:
            case 6:
                if (square < 5 || square > 35)
                    square = 5;
                else if (square < 15)
                    square = 15;
                else if (square < 25)
                    square = 25;
                else if (square < 35)
                    square = 35;
                break;
            case 7:
                balance += 50;
                break;
            case 8:
                yolo = true;
                break;
            case 9:
                square -= 3;
                break;
            case 10:
                goToJail();
                break;
            case 11:
                for (int i : acquired)
                    if (Board.getSquare(i) instanceof Property) {
                        if (((Property) Board.getSquare(i)).getHouses() < 5)
                            balance -= 25;
                        else
                            balance -= 100;
                    }
                break;
            case 12:
                balance -= 15;
                break;
            case 13:
                if (square > 5)
                    balance += 200;
                square = 5;
                break;
            case 14:
                square = 39;
                break;
            case 15:
                for (Player p : GameData.getPlayers())
                    transaction(p, 50);
                break;
            case 16:
                balance += 150;
                break;
        }
        status += "Chance card:\n";
        status += card.getTitle() + '\n';
        for (int i = 0; i < card.getDesc().length(); i++) {
            status += card.getDesc().charAt(i);
            if (i % 55 == 54)
                status += '\n';
        }
    }

    public int checkJail() {
        if (turnsInJail > 0) {
            turnsInJail--;
            if (roll1 == roll2)
                return 1;
            else
                return 2;
        }
        return 0;
    }

    private void checkOwner(Purchasable p) {
        if (p.isOwned() && p.isActive()) {
            p.playerLands(this);
        } else if (p.isOwned() && !p.isActive()) {
            return;
        } else if (!p.isOwned()) {
        }
    }

    public void play(int turns, int doubles) {
        boolean inJail = false;

        if (roll1 == roll2) {
            turns++;
            doubles++;
        }

        if (doubles == 3) {
            goToJail();
            inJail = true;
        } else {
            move();
            turns--;
        }
        this.turnsInJail = turns;
        this.doubles = doubles;
        this.inJail = inJail;
        diceHaveBeenRolled = false;
    }

    public void goToJail() {
        square = 10;
        turnsInJail = 3;
    }

    public void addToBalance(int amount) {
        balance += amount;
    }

    public void transaction(Player p, int amount) {
        this.balance -= amount;
        p.balance += amount;
    }

    public void purchase() {
        Square toPurchase = Board.getSquare(square);
        if (toPurchase instanceof Purchasable) {
            acquired.add(square);
            addToBalance(-((Purchasable) toPurchase).getLandCost());
            ((Purchasable) toPurchase).setOwner(this);
            if (toPurchase instanceof Property)
                ((Property) toPurchase).updateOwnerOwnsInSet();
        }
        if (PlayScreen.initialized)
            PlayScreen.enableEndTurn();
    }

    public void purchase(int position, int amount) {
        Square toPurchase = Board.getSquare(position);
        if (toPurchase instanceof Purchasable) {
            if (((Purchasable) toPurchase).getOwnerID() != -1) {
                ((Purchasable) toPurchase).getOwner().removeAcquired(position);
                this.transaction(((Purchasable) toPurchase).getOwner(), amount);
            } else
                addToBalance(-amount);
            acquired.add(position);
            ((Purchasable) toPurchase).setOwner(this);
            if (toPurchase instanceof Property)
                ((Property) toPurchase).updateOwnerOwnsInSet();
        }
        if (PlayScreen.initialized)
            PlayScreen.enableEndTurn();
    }

    public void mortgage(int i) {
        Square toMortgage = Board.getSquare(i);
        if (toMortgage instanceof Purchasable) {
            suspended.add(i);
            balance += ((Purchasable) toMortgage).getMortgage();
            ((Purchasable) toMortgage).suspend();
        }
    }

    public void unmortgage(int i) {
        Square toUnmortgage = Board.getSquare(i);
        if (toUnmortgage instanceof Purchasable) {
            suspended.remove(suspended.indexOf(i));
            balance -= ((Purchasable) toUnmortgage).getMortgage();
            ((Purchasable) toUnmortgage).activate();
        }
    }

    public String getStatus() {
        return status;
    }

    public void resetStatus() {
        status = "";
    }
}
