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

import logic.Player;

public class Hud {
    private Viewport vp;
    private Texture bg;
    public Stage stage;
    private Table table;

    private Label lblPlayerNo;
    private Label lblPlayerName;
    private Label lblPlayerBalance;
    private Label lblPlayerDice;

    private int playerNo;
    private String playerName;
    private int playerBalance;
    private int playerDice1, playerDice2;

    private Label.LabelStyle lblStyle;

    public Hud(SpriteBatch spb){
        vp = new FitViewport(Monopoly.WIDTH, Monopoly.HEIGHT, new OrthographicCamera());
        bg = new Texture("hud_bg.jpg");
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

        lblPlayerNo = new Label("game hasnt started yet!", lblStyle);
        lblPlayerName = new Label("game hasnt started yet!", lblStyle);
        lblPlayerBalance = new Label("game hasnt started yet!", lblStyle);
        lblPlayerDice = new Label("game hasnt started yet!", lblStyle);

        table.right().top();
        table.add(lblPlayerNo).width(250).padTop(40);
        table.add(lblPlayerName).width(250).padTop(40);
        table.row();
        table.add(new Label("Balance", lblStyle)).width(250);
        table.add(lblPlayerBalance).width(250);
        table.row();
        table.add(new Label("Dice roll", lblStyle)).width(250);
        table.add(lblPlayerDice).width(250);
        table.row();
        table.row();

        stage.addActor(table);
    }

    public void update(Player p){
        playerNo = p.getID();
        playerName = p.getName();
        playerBalance = p.getBalance();
        playerDice1 = p.getDice()[0];
        playerDice2 = p.getDice()[1];

        lblPlayerNo.setText(String.format("Player %01d", playerNo));
        lblPlayerName.setText(playerName);
        lblPlayerBalance.setText(String.format("$ %05d", playerBalance));
        lblPlayerDice.setText(String.format("%01d + %01d", playerDice1, playerDice2));
    }

    public void render(SpriteBatch spb) {
        spb.begin();
        spb.draw(bg, 720, 0);
        spb.end();
    }
}
