package scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.lpoo1617t1g3.Monopoly;

import logic.Board;
import logic.GameData;
import logic.Player;
import logic.Purchasable;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.actor;

/**
 * Created by up201405308 on 07/06/2017.
 */

public class AuctionScene {
    private Stage stage;
    private Table table;
    private TextButton btnNext;
    private TextButton btnExit;
    private List<IndexedSlider> amountSliders;
    private List<Label> amountText;
    private int min, max, firstBid;
    public int winnerAmount, winnerID;
    public boolean winnerFound;

    public AuctionScene() {
        stage = new Stage();
        table = new Table();
        amountSliders = new ArrayList<IndexedSlider>();
        amountText = new ArrayList<Label>();

        btnNext = new TextButton("Raise the stakes", Monopoly.btnStyle);
        btnNext.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)  {
                int bidMax = min - 1;
                for (int i = 0; i < amountSliders.size(); i++) {
                    if (amountSliders.get(i).getValue() > bidMax) {
                        bidMax = (int) amountSliders.get(i).getValue();
                    }
                }
                min = bidMax;
                max = bidMax * 4;
                initTable();
            }
        });

        btnExit = new TextButton("Accept results", Monopoly.btnStyle);
        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)  {
                winnerAmount = 0;
                for (IndexedSlider is : amountSliders) {
                    if (is.getValue() > winnerAmount) {
                        winnerAmount = (int) is.getValue();
                        winnerID = is.id;
                    }
                }

                int howManyWinners = 0;
                for (IndexedSlider is : amountSliders) {
                    if (is.getValue() == winnerAmount) {
                        howManyWinners++;
                    }
                }
                if (howManyWinners != 1 && winnerAmount <= firstBid) {
                    GameData.getPlayer().purchase(GameData.getPlayer().getPosition(), 0);
                } else {
                    GameData.getPlayer(winnerID).purchase(GameData.getPlayer().getPosition(), winnerAmount);
                }
                winnerFound = true;
                SquareScene.exitAuction();
                SquareScene.view(GameData.getPlayer().getPosition());
            }
        });

        table.setBounds(0, 0, Monopoly.WIDTH, Monopoly.HEIGHT);
        table.debug();

        stage.addActor(table);
    }

    public void render() {
        Gdx.input.setInputProcessor(stage);
        Monopoly.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Monopoly.shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 0.6f);
        Monopoly.shapeRenderer.rect(0, 0, Monopoly.WIDTH, Monopoly.HEIGHT);
        Monopoly.shapeRenderer.setColor(Color.WHITE);
        Monopoly.shapeRenderer.rect(Monopoly.WIDTH/3, Monopoly.HEIGHT/3, Monopoly.WIDTH/3, Monopoly.HEIGHT/3);
        Monopoly.shapeRenderer.end();
        stage.act();
        stage.draw();
    }

    public void initTable() {
        table.clear();
        amountSliders.clear();
        amountText.clear();
        for(int i = 0; i < GameData.getPlayers().size(); i++) {
            if (GameData.getPlayer().getID() -1 != i) {
                table.add(new Label(GameData.getPlayer(i).getName(), Monopoly.lblStyle));
                amountText.add(new Label("", Monopoly.lblStyle));
                final IndexedSlider sldTmp = new IndexedSlider(min, max, i);
                sldTmp.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                        amountText.get(sldTmp.id).setText("$" + Integer.toString((int) sldTmp.getValue()));
                    }
                });
                amountSliders.add(sldTmp);
                table.add(amountSliders.get(i));
                table.add(amountText.get(i)).width(80).row();
            } else {
                amountText.add(new Label("", Monopoly.lblStyle));
                amountSliders.add(new IndexedSlider(0, 300, -1));
            }
        }
        table.add(btnNext);
        table.add(btnExit);
    }

    public void auction() {
        Purchasable p = (Purchasable) Board.getSquare(GameData.getPlayer().getPosition());
        firstBid = p.getLandCost() / 2;
        min = firstBid;
        max = firstBid * 4;
        winnerFound = false;
        initTable();
    }
}

