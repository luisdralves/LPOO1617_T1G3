package scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.lpoo1617t1g3.Monopoly;

import java.util.ArrayList;
import java.util.List;

import logic.Board;
import logic.GameData;
import logic.Player;
import logic.Property;
import logic.Purchasable;
import logic.Square;

public class BoardScene {
    public Stage stage;
    private Texture house, hotel;
    private List<Label> titles;
    private List<Label> costs;
    private List<Label> rents;
    private List<Label> owners;
    private Table table;

    public BoardScene() {
        house = new Texture("house.png");
        hotel = new Texture("hotel.png");
        stage = new Stage();
        table = new Table();
        int size = logic.Board.getSquares().size();
        init(size);
        update(size);

        table.debug();
        table.top().left();
        table.setBounds(0, 0, Monopoly.WIDTH, Monopoly.HEIGHT);
        for(int i = 20; i <= 30; i++)
            table.add(titles.get(i));
        table.row();
        for(int i = 20; i <= 30; i++)
            table.add(costs.get(i));
        table.row();
        for(int i = 20; i <= 30; i++)
            table.add(rents.get(i));
        table.row();
        for(int i = 20; i <= 30; i++)
            table.add(owners.get(i));
        table.row();
        for(int i = 10; i >= 0; i--)
            table.add(titles.get(i));
        table.row();
        for(int i = 10; i >= 0; i--)
            table.add(costs.get(i));
        table.row();
        for(int i = 10; i >= 0; i--)
            table.add(rents.get(i));
        table.row();
        for(int i = 10; i >= 0; i--)
            table.add(owners.get(i));
        stage.addActor(table);
    }

    private void init(int size) {
        titles = new ArrayList<Label>(size);
        costs = new ArrayList<Label>(size);
        rents = new ArrayList<Label>(size);
        owners = new ArrayList<Label>(size);
        for(int i = 0; i < size; i++) {
            titles.add(new Label("", Monopoly.lblStyle));
            titles.get(i).setFontScale(0.4f);
            costs.add(new Label("", Monopoly.lblStyle));
            costs.get(i).setFontScale(0.4f);
            rents.add(new Label("", Monopoly.lblStyle));
            rents.get(i).setFontScale(0.4f);
            owners.add(new Label("", Monopoly.lblStyle));
            owners.get(i).setFontScale(0.4f);
        }
        for(int i = 0; i < size; i++) {
            Square sq = logic.Board.getSquare(i);
            titles.get(i).setText(sq.getTitle());
            if (sq instanceof Purchasable)
                costs.get(i).setText(String.format("Cost: $%d", ((Purchasable) sq).getLandCost()));
        }
    }

    public void update(int size) {
        for(int i = 0; i < size; i++) {
            Square sq = logic.Board.getSquare(i);
            if (sq instanceof Purchasable) {
                rents.get(i).setText(String.format("Rent: $%d", ((Purchasable) sq).getRent()));
                owners.get(i).setText(((Purchasable) sq).getOwnerName());
            }
        }
    }

    public void render(SpriteBatch spb) {
        spb.begin();

        for(Player p : GameData.getPlayers()) {
            spb.draw(p.getToken(), posToCoords(p.getPosition()).x, posToCoords(p.getPosition()).y);
        }

        for(int j = 0; j < Board.getSquares().size(); j++) {
            if(Board.getSquare(j) instanceof Property) {
                Property p = ((Property) Board.getSquare(j));
                if (p.getHouses() == 5) {
                    if(j < 10)
                        spb.draw(hotel, posToCoords(j).x + 20, posToCoords(j).y + 75, 20, 20);
                    else if (j < 20)
                        spb.draw(hotel, posToCoords(j).x + 75, posToCoords(j).y + 20, 20, 20);
                    else if (j < 30)
                        spb.draw(hotel, posToCoords(j).x + 20, posToCoords(j).y + 3, 20, 20);
                    else
                        spb.draw(hotel, posToCoords(j).x + 3, posToCoords(j).y + 20, 20, 20);
                }
                else {
                    if (j < 10)
                        for (int i = 0; i < p.getHouses(); i++)
                            spb.draw(house, posToCoords(j).x + i * 14, posToCoords(j).y + 75, 20, 20);
                    else if (j < 20)
                        for (int i = 0; i < p.getHouses(); i++)
                            spb.draw(house, posToCoords(j).x + 75, posToCoords(j).y + i * 14, 20, 20);
                    else if (j < 30)
                        for (int i = 0; i < p.getHouses(); i++)
                            spb.draw(house, posToCoords(j).x + i * 14, posToCoords(j).y + 3, 20, 20);
                    else
                        for (int i = 0; i < p.getHouses(); i++)
                            spb.draw(house, posToCoords(j).x + 3, posToCoords(j).y + i * 14, 20, 20);
                }
            }
        }
        spb.end();
    }

    public static Vector2 posToCoords(int pos) {
        int width = 59 * Monopoly.HEIGHT / 720;
        int height = 2 * Monopoly.HEIGHT / 15;

        if (pos < 10)
            return new Vector2(96 + (9 - pos) * 59, 0);
        else if (pos < 20)
            return new Vector2(0, 96 + (pos - 11) * 59);
        else if (pos < 30)
            return new Vector2(96 + (pos - 21) * 59, 624);
        else if (pos < 40)
            return new Vector2(624, 96 + (39 - pos) * 59);
        return new Vector2(0, 0);
    }
}
