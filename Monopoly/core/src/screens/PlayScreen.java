package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo1617t1g3.Monopoly;

import logic.Board;
import logic.GameData;
import logic.Player;
import logic.Purchasable;
import logic.Square;
import scenes.BoardScene;
import scenes.Hud;

public class PlayScreen implements Screen {
    private Monopoly game;
    private OrthographicCamera cam;
    private Viewport vp;
    private Hud hud;
    private BoardScene board;
    private Label.LabelStyle lblStyle;

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table tableButtons;
    private Table tableProperty;
    private Label lblTP1, lblTP2, lblTP3, lblTP4;
    private TextButton btnEndTurn;
    private TextButton btnDice;
    private TextButton btnViewProp;
    private TextButton.TextButtonStyle btnStyle;
    private BitmapFont bmf;

    public PlayScreen(Monopoly m) {
        game = m;
        cam = new OrthographicCamera();
        vp = new FitViewport(Monopoly.WIDTH, Monopoly.HEIGHT, cam);
        hud = new Hud(game.spb);
        board = new BoardScene();
        lblStyle = new Label.LabelStyle(new BitmapFont(Gdx.files.internal("Kabel.fnt")), Color.BLACK);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        atlas = new TextureAtlas("btn2/btn2.pack");
        skin = new Skin(atlas);
        tableButtons = new Table(skin);
        bmf = new BitmapFont(Gdx.files.internal("Kabel.fnt"));

        btnStyle = new TextButton.TextButtonStyle();
        btnStyle.up = skin.getDrawable("btn_up");
        btnStyle.down = skin.getDrawable("btn_down");
        btnStyle.over = skin.getDrawable("btn_hover");
        btnStyle.disabled = skin.getDrawable("btn_dis");
        btnStyle.font = bmf;

        btnEndTurn = new TextButton("End turn", btnStyle);
        btnEndTurn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)  {
                gameCycle();
            }
        });

        btnDice = new TextButton("Roll dice", btnStyle);
        btnDice.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)  {
                moveLoop();
            }
        });

        btnViewProp = new TextButton("View property", btnStyle);
        btnViewProp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)  {
                viewProperty();
            }
        });

        tableButtons.setBounds(9 * Monopoly.WIDTH / 16, 0, 7 * Monopoly.WIDTH / 16, 3 * Monopoly.HEIGHT / 5);
        tableButtons.debug();
        tableButtons.top();
        tableButtons.add(btnDice);
        tableButtons.row();
        tableButtons.add(btnViewProp);
        tableButtons.row();
        tableButtons.add(btnEndTurn);
        stage.addActor(tableButtons);

        tableProperty = new Table();
        tableProperty.setBounds(0, 0, Monopoly.WIDTH, Monopoly.HEIGHT);
        lblTP1 = new Label("", lblStyle);
        tableProperty.add(lblTP1).row();
        lblTP2 = new Label("", lblStyle);
        tableProperty.add(lblTP2).row();
        lblTP3 = new Label("", lblStyle);
        tableProperty.add(lblTP3).row();
        lblTP4 = new Label("", lblStyle);
        tableProperty.add(lblTP4).row();
        stage.addActor(tableProperty);

        gameCycle();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        board.handleInput();
        board.render(game.spb);

        hud.update(GameData.getPlayer());
        hud.render(game.spb);
        game.spb.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        vp.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void gameCycle() {
        GameData.currentPlayerInt++;
        GameData.currentPlayerInt %= GameData.getPlayers().size();
        Player currentPlayer = GameData.getPlayer();
        if (!currentPlayer.getProperties().isEmpty() && (logic.Board.getHouses() > 0 || logic.Board.getHotels() > 0)) {
            //improveProperties(currentPlayer);
            //mortgageProperties(currentPlayer);
        } else if (!currentPlayer.getAcquired().isEmpty()) {
            //mortgageProperties(currentPlayer);
        }
        int jailStatus = currentPlayer.checkJail();
        if (jailStatus == 0) {
            //not in jail

        }
        else if (jailStatus == 1) {
            //in jail but gets out
            currentPlayer.move();
        }
        else if (jailStatus == 2) {
            //still in jail
        }
        resetUI();
    }

    private void moveLoop(){
        GameData.getPlayer().rollDice();
        GameData.getPlayer().play(1, 0);
        int turnsRemaining = GameData.getPlayer().getTurnsRemaining();
        int doubles = GameData.getPlayer().getDoubles();
        boolean inJail = GameData.getPlayer().isInJail();
        if ((inJail || turnsRemaining < 1) && (doubles < 1 || doubles > 2)) {
            btnDice.setDisabled(true);
            btnDice.setTouchable(Touchable.disabled);
        }
    }

    private void resetUI() {
        btnDice.setDisabled(false);
        btnDice.setTouchable(Touchable.enabled);
        lblTP1.setText("");
        lblTP2.setText("");
        lblTP3.setText("");
        lblTP4.setText("");
    }

    private void viewProperty() {
        Square sq = Board.getSquare(GameData.getPlayer().getPosition());
        lblTP1.setText(sq.getTitle());
        lblTP2.setText(String.format("Cost: $%d", ((Purchasable) sq).getLandCost()));
        lblTP3.setText(String.format("Rent: $%d", ((Purchasable) sq).getRent()));
        lblTP4.setText(((Purchasable) sq).getOwnerName());
    }
}
