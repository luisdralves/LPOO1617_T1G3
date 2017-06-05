package scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.lpoo1617t1g3.Monopoly;

import java.util.ArrayList;
import java.util.List;

import logic.GameData;
import logic.Player;
import logic.Purchasable;
import logic.Square;

public class BoardScene {
    public Stage stage;
    private Texture board;
    private List<Label> titles;
    private List<Label> costs;
    private List<Label> rents;
    private List<Label> owners;
    private Table table;

    public BoardScene() {
        board = new Texture("board.jpg");
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

    public void handleInput() {

    }


    public void render(SpriteBatch spb) {
        spb.begin();
        spb.draw(board, 0, 0);
        for(Player p : GameData.getPlayers()) {
            spb.draw(p.getToken(), posToCoords(p.getPosition())[0], posToCoords(p.getPosition())[1]);
        }
        spb.end();
        //stage.draw();
    }

    private int[] posToCoords(int pos) {
        if(pos < 10)
            return new int[]{(10 - pos) * 50, 0};
        else if(pos < 20)
            return new int[]{0, (pos - 10) * 50};
        else if(pos < 30)
            return new int[]{(pos-20) * 50, 500};
        else if(pos < 40)
            return new int[]{500, (40 - pos) * 50};
        return new int[]{0, 0};
    }
}
