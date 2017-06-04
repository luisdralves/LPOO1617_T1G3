package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo1617t1g3.Monopoly;

import logic.*;

public class Hud {
    private Viewport vp;
    private Texture bg;
    public Stage stage;
    private Table table;

    private Label lblPlayerNo;
    private Label lblPlayerName;
    private Label lblPlayerBalance;
    private Label lblPlayerDice;
    private Label lblPlayerPos;
    private Label lblPlayerPosName;

    private int playerNo;
    private String playerName;
    private int playerBalance;
    private int playerDice1, playerDice2;
    private int playerPos;
    private String playerPosName;

    private Label.LabelStyle lblStyle;

    public Hud(SpriteBatch spb){
        vp = new FitViewport(Monopoly.WIDTH, Monopoly.HEIGHT, new OrthographicCamera());
        bg = new Texture("hud_bg.png");
        stage = new Stage(vp, spb);
        table = new Table();
        table.top();
        table.setFillParent(true);

        lblStyle = new Label.LabelStyle(new BitmapFont(Gdx.files.internal("Kabel.fnt")), Color.BLACK);

        playerNo = 1;
        playerName = "Miguel";
        playerBalance = 1550;
        playerDice1 = 0;
        playerDice2 = 0;
        playerPos = 0;
        playerPosName = "GO";

        lblPlayerNo = new Label("game hasnt started yet!", lblStyle);
        lblPlayerName = new Label("game hasnt started yet!", lblStyle);
        lblPlayerBalance = new Label("game hasnt started yet!", lblStyle);
        lblPlayerDice = new Label("game hasnt started yet!", lblStyle);
        lblPlayerPos = new Label("game hasnt started yet!", lblStyle);
        lblPlayerPosName = new Label("game hasnt started yet!", lblStyle);

        int columnWidth = Monopoly.WIDTH/5;

        table.right().top();
        table.add(new Label("", lblStyle)).width(columnWidth);
        table.add(new Label("", lblStyle)).width(columnWidth);
        table.row();
        table.add(lblPlayerNo).width(columnWidth);
        table.add(lblPlayerName).padRight(columnWidth/10).right();
        table.row();
        table.add(new Label("Balance", lblStyle)).width(columnWidth);
        table.add(lblPlayerBalance).padRight(columnWidth/10).right();
        table.row();
        table.add(new Label("Dice roll", lblStyle)).width(columnWidth);
        table.add(lblPlayerDice).padRight(columnWidth/10).right();
        table.row();
        table.add(new Label("Position", lblStyle)).width(columnWidth);
        table.add(lblPlayerPos).padRight(columnWidth/10).right();
        table.row();
        table.add(lblPlayerPosName).colspan(2).padRight(columnWidth/10);
        table.debug();

        stage.addActor(table);
    }

    public void update(Player p){
        playerNo = p.getID();
        playerName = p.getName();
        playerBalance = p.getBalance();
        playerDice1 = p.getDice()[0];
        playerDice2 = p.getDice()[1];
        playerPos = p.getPosition();
        playerPosName = logic.Board.getSquare(p.getPosition()).getTitle();

        lblPlayerNo.setText(String.format("Player %01d", playerNo));
        lblPlayerName.setText(playerName);
        lblPlayerBalance.setText(String.format("$ %05d", playerBalance));
        lblPlayerDice.setText(String.format("%01d + %01d", playerDice1, playerDice2));
        lblPlayerPos.setText(String.format("%01d", playerPos));
        lblPlayerPosName.setText(playerPosName);
    }

    public void render(SpriteBatch spb) {
        spb.begin();
        spb.draw(bg, 720, 0);
        spb.end();
    }
}
