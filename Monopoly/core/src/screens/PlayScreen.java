package screens;

import com.badlogic.gdx.Gdx;
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
import scenes.BoardScene;
import scenes.DiceScene;
import scenes.Hud;
import scenes.IndexedButton;
import scenes.SquareScene;


public class PlayScreen implements Screen {
    private static boolean viewingASquare;
    private static boolean rollingDice;
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
    private TextButton btnEndTurn;
    private TextButton btnDice;
    private TextButton btnViewProp;
    private List<Button> btnSq;

    public PlayScreen(Monopoly m) {
        game = m;
        cam = new OrthographicCamera();
        vp = new FitViewport(Monopoly.WIDTH, Monopoly.HEIGHT, cam);
        hud = new Hud(game.spb);
        board = new BoardScene();
        squareScene = new SquareScene();
        diceScene = new DiceScene();
        viewingASquare = false;
        rollingDice = false;

        stage = new Stage(vp);
        tblButtons = new Table(Monopoly.skin);
        tblSquares = new Table(Monopoly.skin);
        tblSquares.setBounds(0, 0, Monopoly.HEIGHT, Monopoly.HEIGHT);

        btnSq = new ArrayList<Button>();
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
                rollingDice = true;
                //moveLoop();
            }
        });

        btnViewProp = new TextButton("GO", Monopoly.btnStyle);
        btnViewProp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)  {
                viewingASquare = true;
                SquareScene.view(GameData.getPlayer().getPosition());
            }
        });

        tblButtons.setBounds(9 * Monopoly.WIDTH / 16, 0, 7 * Monopoly.WIDTH / 16, 25 * Monopoly.HEIGHT / 36);
        tblButtons.top();
        tblButtons.add(btnViewProp).width(2 * Monopoly.WIDTH / 5);
        tblButtons.row();
        tblButtons.add(btnDice).width(Monopoly.WIDTH / 5).padTop(1 * Monopoly.HEIGHT / 16);
        tblButtons.row();
        tblButtons.add(btnEndTurn).width(Monopoly.WIDTH / 5);
        stage.addActor(tblButtons);

        gameCycle();
    }

    public static void exitPropertyScene() {
        viewingASquare = false;
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

        board.render(game.spb);

        hud.update(GameData.getPlayer());
        hud.render(game.spb);
        game.spb.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        stage.act(delta);
        stage.draw();
        if (viewingASquare)
            squareScene.render(game.spb);
        else if (rollingDice)
            diceScene.render(game.spb);
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
