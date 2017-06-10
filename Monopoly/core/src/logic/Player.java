package logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import scenes.BoardScene;
import screens.PlayScreen;

public class Player {
    private static int playerNumber = 1;
    private static int[] properties = new int[]{3, 6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23, 24, 26, 27, 29, 31, 32, 34, 37, 39};
    private static int[] stations = new int[]{5, 15, 25, 35};
    private static int[] utilities = new int[]{12, 28};
    private int id;
    private String name;
    private boolean isAI;
    private boolean realDice;
    private int square;
    private int balance;
    private int turnsInJail;
    private int turnsRemaining;
    private int doubles;
    private boolean inJail;
    private int roll1, roll2;
    private boolean dicesHaveBeenRolled;
    private List<Integer> acquired;
    private List<Integer> suspended;

    private Texture token;
    private Vector2 position;
    private Vector2 destination;
    private Vector2 velocity;

    public Player(boolean ai, boolean rd, String path) {
        id = playerNumber;
        name = "Player ";
        name += playerNumber;
        playerNumber++;
        isAI = ai;
        realDice = rd;
        square = 0;
        balance = 1500;
        turnsInJail = 0;
        roll1 = 0;
        roll2 = 0;
        dicesHaveBeenRolled = false;
        acquired = new ArrayList<Integer>();
        suspended = new ArrayList<Integer>();

        token = new Texture(path);
        position = new Vector2(0, 0);
        destination = new Vector2(0, 0);
        velocity = new Vector2(0, 0);
    }

    public Player(String name, boolean ai, boolean rd, String path) {
        this(ai, rd, path);
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

    public List<Integer> getSuspended() {
        return suspended;
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

    public List<Integer> getStations() {
        List<Integer> ret = new ArrayList<Integer>();
        for (int i : acquired) {
            for (int j : stations) {
                if (i == j) {
                    ret.add(i);
                }
            }
        }
        return ret;
    }

    public List<Integer> getUtilities() {
        List<Integer> ret = new ArrayList<Integer>();
        for (int i : acquired) {
            for (int j : utilities) {
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

    public boolean haveDicesBeenRolled() {
        return dicesHaveBeenRolled;
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
        dicesHaveBeenRolled = true;
    }

    public void rollDice(int i1, int i2) {
        roll1 = i1;
        roll2 = i2;
        dicesHaveBeenRolled = true;
    }

    public void move() {
        int pos1 = square;
        square += roll1 + roll2;

        checkGO();
        checkSpecialSquares();
        Square currentSquare = Board.getSquare(square);
        if (currentSquare instanceof CardSquare) {
        } else if (currentSquare instanceof Purchasable) {
            checkOwner((Purchasable) currentSquare);
        }
        position = BoardScene.posToCoords(pos1);
        destination = BoardScene.posToCoords(square);
        velocity = new Vector2(destination.x, destination.y);
        velocity.add(-position.x, -position.y);
        velocity.scl(velocity.len());
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
        dicesHaveBeenRolled = false;
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

    public void update() {
        position.add(velocity);
    }

    public Vector2 getPositionVec() {
        return position;
    }
}
