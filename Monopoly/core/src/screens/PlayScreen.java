package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo1617t1g3.Monopoly;

import java.util.ArrayList;
import java.util.List;

import logic.Board;
import logic.GameData;
import logic.Player;
import logic.Purchasable;
import scenes.BoardScene;
import scenes.DiceScene;
import scenes.Hud;
import scenes.IndexedButton;
import scenes.SquareScene;


public class PlayScreen implements Screen {
    public static boolean viewingASquare;
    public static boolean rollingDice;
    public static boolean diceReady;
    public static boolean initialized = false;
    private static TextButton btnEndTurn;
    private static TextButton btnDice;
    private Monopoly game;
    private OrthographicCamera cam;
    private Viewport vp;
    private Hud hud;
    private BoardScene board;
    private SquareScene squareScene;
    private DiceScene diceScene;
    private Stage stage;
    private Table tblButtons;
    private Table tblSquares;
    private TextButton btnMyProps;
    private TextButton btnViewProp;
    private TextButton btnExit;
    private List<Button> btnSq;

    public PlayScreen(Monopoly m) {
        game = m;
        game.isHappening = true;
        cam = new OrthographicCamera();
        vp = new FitViewport(Monopoly.WIDTH, Monopoly.HEIGHT, cam);
        hud = new Hud(game.spb);
        board = new BoardScene();
        squareScene = new SquareScene();
        diceScene = new DiceScene();
        viewingASquare = false;
        rollingDice = false;
        diceReady = false;

        stage = new Stage(vp);

        initSqTable();
        initButtons();
        initBtnTable();
        initialized = true;
        gameCycle();
    }

    public static void exitPropertyScene() {
        viewingASquare = false;
    }

    public static void enableEndTurn() {
        if (GameData.getPlayer().getTurnsRemaining() > 0) {
            btnDice.setDisabled(false);
            btnDice.setTouchable(Touchable.enabled);
        } else {
            btnEndTurn.setDisabled(false);
            btnEndTurn.setTouchable(Touchable.enabled);
        }
    }

    private void initSqTable() {
        btnSq = new ArrayList<Button>();
        tblSquares = new Table(Monopoly.skin);
        tblSquares.setBounds(0, 0, Monopoly.HEIGHT, Monopoly.HEIGHT);

        for (int i = 0; i < 40; i++) {
            int width = 59 * Monopoly.HEIGHT / 720;
            int height = 2 * Monopoly.HEIGHT / 15;
            final IndexedButton btnTmp = new IndexedButton(i);
            btnTmp.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    viewingASquare = true;
                    SquareScene.view(btnTmp.id);
                }
            });
            btnSq.add(btnTmp);
            if (i < 11) {
                if (i == 0 || i == 10)
                    tblSquares.add(btnSq.get(i)).width(height).height(height);
                else
                    tblSquares.add(btnSq.get(i)).width(width).height(height);
                if (i == 10)
                    tblSquares.row();
            } else if (i < 29) {
                if (i % 2 == 1) {
                    tblSquares.add(btnSq.get(i)).height(width).width(height).left();
                } else {
                    tblSquares.add().colspan(9);
                    tblSquares.add(btnSq.get(i)).height(width).width(height).right().row();
                }
            } else {
                if (i == 29 || i == 39)
                    tblSquares.add(btnSq.get(i)).width(height).height(height);
                else
                    tblSquares.add(btnSq.get(i)).width(width).height(height);
            }
        }
        stage.addActor(tblSquares);
    }

    private void initButtons() {
        btnEndTurn = new TextButton("End turn", Monopoly.btnStyle);
        btnEndTurn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameCycle();
            }
        });

        btnExit = new TextButton("Exit", Monopoly.btnStyle);
        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game));
            }
        });

        btnMyProps = new TextButton("My properties", Monopoly.btnStyle);
        btnMyProps.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!GameData.getPlayer().getAcquired().isEmpty()) {
                    viewingASquare = true;
                    SquareScene.view(0, GameData.getPlayer().getAcquired());
                }
            }
        });

        btnDice = new TextButton("Roll dice", Monopoly.btnStyle);
        btnDice.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (GameData.getPlayer().realDice()) {
                    rollingDice = true;
                    diceReady = false;
                    diceScene.initDice();
                } else
                    moveLoop(0, 0);
            }
        });

        btnViewProp = new TextButton("GO", Monopoly.btnStyle);
        btnViewProp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                viewingASquare = true;
                SquareScene.view(GameData.getPlayer().getPosition());
            }
        });
    }

    private void initBtnTable() {
        tblButtons = new Table(Monopoly.skin);
        tblButtons.setBounds(9 * Monopoly.WIDTH / 16, 0, 7 * Monopoly.WIDTH / 16, 20 * Monopoly.HEIGHT / 36);
        tblButtons.top();
        tblButtons.add(btnViewProp).width(2 * Monopoly.WIDTH / 5);
        tblButtons.row();
        tblButtons.add(btnDice).width(Monopoly.WIDTH / 4).padTop(1 * Monopoly.HEIGHT / 16);
        tblButtons.row();
        tblButtons.add(btnMyProps).width(Monopoly.WIDTH / 4).padTop(1 * Monopoly.HEIGHT / 64);
        tblButtons.row();
        tblButtons.add(btnEndTurn).width(Monopoly.WIDTH / 4).padTop(1 * Monopoly.HEIGHT / 64);
        tblButtons.row();
        tblButtons.add(btnExit).width(Monopoly.WIDTH / 4).padTop(1 * Monopoly.HEIGHT / 16);
        stage.addActor(tblButtons);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(stage);

        vp.apply();

        hud.update(GameData.getPlayer());
        hud.render(game.spb);
        game.spb.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        stage.act(delta);
        stage.draw();
        board.render(game.spb);

        if (viewingASquare)
            squareScene.render(game.spb);
        else if (rollingDice) {
            Gdx.input.setInputProcessor(null);
            diceScene.render(game.spb);
        } else if (diceReady) {
            diceReady = false;
            moveLoop((int) diceScene.results().x, (int) diceScene.results().y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.setScreen(new MainMenu(game));
        }

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

        } else if (jailStatus == 1) {
            currentPlayer.move();
        } else if (jailStatus == 2) {
        }
        resetUI();
    }

    private void moveLoop(int i1, int i2) {
        Player currentPlayer;
        currentPlayer = GameData.getPlayer();
        currentPlayer.resetStatus();
        if (i1 == 0 || i2 == 0)
            currentPlayer.rollDice();
        else
            currentPlayer.rollDice(i1, i2);
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
        if (Board.getSquare(currentPlayer.getPosition()) instanceof Purchasable)
            if (!((Purchasable) Board.getSquare(currentPlayer.getPosition())).isOwned()) {
                viewingASquare = true;
                btnDice.setDisabled(true);
                btnDice.setTouchable(Touchable.disabled);
                btnEndTurn.setDisabled(true);
                btnEndTurn.setTouchable(Touchable.disabled);
                SquareScene.view(currentPlayer.getPosition());
            }
    }

    private void resetUI() {
        btnDice.setDisabled(false);
        btnDice.setTouchable(Touchable.enabled);
        btnEndTurn.setDisabled(true);
        btnEndTurn.setTouchable(Touchable.disabled);
        btnViewProp.setText(Board.getSquare(GameData.getPlayer().getPosition()).getTitle());
    }
}
