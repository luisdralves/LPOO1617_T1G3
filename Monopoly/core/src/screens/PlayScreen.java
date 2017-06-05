package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo1617t1g3.Monopoly;

import logic.Board;
import logic.GameData;
import logic.Player;
import scenes.BoardScene;
import scenes.Hud;
import scenes.PropertyScene;

public class PlayScreen implements Screen {
    private static boolean viewingAProp;
    private Monopoly game;
    private OrthographicCamera cam;
    private Viewport vp;
    private Hud hud;
    private BoardScene board;
    private PropertyScene propertyScene;
    private Stage stage;
    private Table table;
    private TextButton btnEndTurn;
    private TextButton btnDice;
    private TextButton btnViewProp;

    public PlayScreen(Monopoly m) {
        game = m;
        cam = new OrthographicCamera();
        vp = new FitViewport(Monopoly.WIDTH, Monopoly.HEIGHT, cam);
        hud = new Hud(game.spb);
        board = new BoardScene();
        propertyScene = new PropertyScene();
        viewingAProp = false;

        stage = new Stage();
        table = new Table(Monopoly.skin);

        btnEndTurn = new TextButton("End turn", Monopoly.btnStyle);
        btnEndTurn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)  {
                gameCycle();
            }
        });

        btnDice = new TextButton("Roll dice", Monopoly.btnStyle);
        btnDice.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)  {
                moveLoop();
            }
        });

        btnViewProp = new TextButton("GO", Monopoly.btnStyle);
        btnViewProp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)  {
                viewingAProp = true;
                PropertyScene.viewProperty();
            }
        });

        table.setBounds(9 * Monopoly.WIDTH / 16, 0, 7 * Monopoly.WIDTH / 16, 25 * Monopoly.HEIGHT / 36);
        table.debug();
        table.top();
        table.add(btnViewProp).width(2 * Monopoly.WIDTH / 5);
        table.row();
        table.add(btnDice).padTop(1 * Monopoly.HEIGHT / 16);
        table.row();
        table.add(btnEndTurn);
        stage.addActor(table);

        gameCycle();
    }

    public static void exitPropertyScene() {
        viewingAProp = false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(stage);

        board.handleInput();
        board.render(game.spb);

        hud.update(GameData.getPlayer());
        hud.render(game.spb);
        game.spb.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        stage.act(delta);
        stage.draw();
        if(viewingAProp)
            propertyScene.render(game.spb);
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
        } else if (!currentPlayer.getAcquired().isEmpty()) {
        }
        int jailStatus = currentPlayer.checkJail();
        if (jailStatus == 0) {

        }
        else if (jailStatus == 1) {
            currentPlayer.move();
        }
        else if (jailStatus == 2) {
        }
        resetUI();
    }

    private void moveLoop(){
        Player currentPlayer;
        currentPlayer = GameData.getPlayer();
        currentPlayer.rollDice();
        currentPlayer.play(1, 0);
        int turnsRemaining = currentPlayer.getTurnsRemaining();
        int doubles = currentPlayer.getDoubles();
        boolean inJail = currentPlayer.isInJail();
        if ((inJail || turnsRemaining < 1) && (doubles < 1 || doubles > 2)) {
            btnDice.setDisabled(true);
            btnDice.setTouchable(Touchable.disabled);
            btnEndTurn.setDisabled(false);
            btnEndTurn.setTouchable(Touchable.enabled);
        }
        btnViewProp.setText(Board.getSquare(currentPlayer.getPosition()).getTitle());
    }

    private void resetUI() {
        btnDice.setDisabled(false);
        btnDice.setTouchable(Touchable.enabled);
        btnEndTurn.setDisabled(true);
        btnEndTurn.setTouchable(Touchable.disabled);
        btnViewProp.setText(Board.getSquare(GameData.getPlayer().getPosition()).getTitle());
    }
}
