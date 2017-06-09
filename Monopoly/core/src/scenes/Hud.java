package scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo1617t1g3.Monopoly;

import logic.Player;

public class Hud {
    public Stage stage;
    private Viewport vp;
    private Texture bg, board;
    private Table table;

    private Label lblPlayerNo;
    private Label lblPlayerName;
    private Label lblPlayerBalance;
    private Label lblPlayerDice;
    private Label lblPlayerPos;

    private int playerNo;
    private String playerName;
    private int playerBalance;
    private int playerDice1, playerDice2;
    private int playerPos;

    public Hud(SpriteBatch spb){
        vp = new FitViewport(Monopoly.WIDTH, Monopoly.HEIGHT, new OrthographicCamera());
        bg = new Texture("hud_bg.png");
        board = new Texture("board.jpg");
        stage = new Stage(vp, spb);
        table = new Table();
        table.top();
        table.setFillParent(true);

        playerNo = 1;
        playerName = "Miguel";
        playerBalance = 1550;
        playerDice1 = 0;
        playerDice2 = 0;
        playerPos = 0;

        lblPlayerNo = new Label("game hasnt started yet!", Monopoly.lblStyle);
        lblPlayerName = new Label("game hasnt started yet!", Monopoly.lblStyle);
        lblPlayerBalance = new Label("game hasnt started yet!", Monopoly.lblStyle);
        lblPlayerDice = new Label("game hasnt started yet!", Monopoly.lblStyle);
        lblPlayerPos = new Label("game hasnt started yet!", Monopoly.lblStyle);

        int columnWidth = Monopoly.WIDTH/5;

        table.setBounds(93 * Monopoly.WIDTH / 160, 0, 0, Monopoly.HEIGHT);
        table.left().top();
        table.add(new Label("", Monopoly.lblStyle)).width(columnWidth);
        table.add(new Label("", Monopoly.lblStyle)).width(columnWidth);
        table.row();
        table.add(lblPlayerNo).width(columnWidth);
        table.add(lblPlayerName).right();
        table.row();
        table.add(new Label("Balance", Monopoly.lblStyle)).width(columnWidth);
        table.add(lblPlayerBalance).right();
        table.row();
        table.add(new Label("Dice roll", Monopoly.lblStyle)).width(columnWidth);
        table.add(lblPlayerDice).right();
        table.row();
        table.add(new Label("Position", Monopoly.lblStyle)).width(columnWidth);
        table.add(lblPlayerPos).right();

        stage.addActor(table);
    }

    public void update(Player p){
        playerNo = p.getID();
        playerName = p.getName();
        playerBalance = p.getBalance();
        playerDice1 = p.getDice()[0];
        playerDice2 = p.getDice()[1];
        playerPos = p.getPosition();

        lblPlayerNo.setText(String.format("Player %01d", playerNo));
        lblPlayerName.setText(playerName);
        lblPlayerBalance.setText(String.format("$ %05d", playerBalance));
        lblPlayerDice.setText(String.format("%01d + %01d", playerDice1, playerDice2));
        lblPlayerPos.setText(String.format("%01d", playerPos));
    }

    public void render(SpriteBatch spb) {
        spb.begin();
        spb.draw(bg, 9 * Monopoly.WIDTH / 16, 0);
        spb.draw(board, 0, 0, Monopoly.HEIGHT, Monopoly.HEIGHT);
        spb.end();
    }
}
