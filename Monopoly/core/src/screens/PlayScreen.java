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

import logic.GameData;
import logic.Player;
import scenes.Board;
import scenes.Hud;

public class PlayScreen implements Screen {
    private Monopoly game;
    private OrthographicCamera cam;
    private Viewport vp;
    private Hud hud;
    private Board board;

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton btnEndTurn;
    private TextButton btnDice;
    private TextButton.TextButtonStyle btnStyle;
    private BitmapFont bmf;

    public PlayScreen(Monopoly m) {
        game = m;
        cam = new OrthographicCamera();
        vp = new FitViewport(Monopoly.WIDTH, Monopoly.HEIGHT, cam);
        hud = new Hud(game.spb);
        board = new Board();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        atlas = new TextureAtlas("btn2/btn2.pack");
        skin = new Skin(atlas);
        table = new Table(skin);
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

        table.setBounds(9 * Monopoly.WIDTH / 16, 0, 7 * Monopoly.WIDTH / 16, 2 * Monopoly.HEIGHT / 3);
        table.debug();
        table.top();
        table.add(btnDice);
        table.row();
        table.add(btnEndTurn);
        stage.addActor(table);

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
        resetButtons();
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

    private void resetButtons() {
        btnDice.setDisabled(false);
        btnDice.setTouchable(Touchable.enabled);
    }
}
