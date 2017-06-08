package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo1617t1g3.Monopoly;

import java.util.ArrayList;
import java.util.List;

public class NewGameScreen implements Screen{
    private Monopoly game;
    private Camera cam;
    private Viewport vp;
    private Stage stage;
    private Table table, tableInfo, tableButtons;
    private TextButton btnPlay, btnBack;
    public static List<TextField> playerNames;
    public static List<CheckBox> isAI;
    public static List<CheckBox> realDice;
    public static int playerCount;
    private TextField playerCountField;
    private ImageButton playerCountMore, playerCountLess;

    public NewGameScreen(Monopoly m) {
        game = m;
        cam = new OrthographicCamera();
        vp = new FitViewport(Monopoly.WIDTH, Monopoly.HEIGHT, cam);
        stage = new Stage(vp);
        Gdx.input.setInputProcessor(stage);
        table = new Table(Monopoly.skin);
        tableInfo = new Table(Monopoly.skin);
        tableButtons = new Table(Monopoly.skin);

        playerCount = 2;

        btnPlay = new TextButton("Play", Monopoly.btnStyle);
        btnPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)  {
                game.setScreen(new PlayScreen(game));
            }
        });
        btnBack = new TextButton("Go Back", Monopoly.btnStyle);
        btnBack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)  {
                game.setScreen(new MainMenu(game));
            }
        });

        playerNames = new ArrayList<TextField>();
        isAI = new ArrayList<CheckBox>();
        realDice = new ArrayList<CheckBox>();

        playerCountField = new TextField("2", Monopoly.tflStyle);
        playerCountField.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        playerCountField.addListener(new InputListener() {
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Input.Keys.ENTER && Integer.parseInt(playerCountField.getText()) > 1 && Integer.parseInt(playerCountField.getText()) <= 8) {
                    updatePlayers();
                }
                return false;
            }
        });

        playerCountMore = new ImageButton(Monopoly.ibtnStyleRight);
        playerCountLess = new ImageButton(Monopoly.ibtnStyleLeft);
        playerCountMore.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)  {
                if (Integer.parseInt(playerCountField.getText()) > 1 && Integer.parseInt(playerCountField.getText()) <= 7) {
                    playerCountField.setText(Integer.toString(Integer.parseInt(playerCountField.getText()) + 1));
                    updatePlayers();
                }
            }
        });
        playerCountLess.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)  {
                if (Integer.parseInt(playerCountField.getText()) > 2 && Integer.parseInt(playerCountField.getText()) <= 8) {
                    playerCountField.setText(Integer.toString(Integer.parseInt(playerCountField.getText()) - 1));
                    updatePlayers();
                }
            }
        });

        table.setBounds(0, 0, Monopoly.WIDTH, Monopoly.HEIGHT);
        tableInfo.add();
        tableInfo.add(new Label("Player number: ", Monopoly.lblStyle));
        tableInfo.add(playerCountLess).right();
        tableInfo.add(playerCountField).width(30);
        tableInfo.add(playerCountMore).left().row();

        tableInfo.add();
        tableInfo.add(new Label("Name", Monopoly.lblStyle)).colspan(2).padTop(30);
        tableInfo.add(new Label("AI", Monopoly.lblStyle)).padTop(30);
        tableInfo.add(new Label("RD", Monopoly.lblStyle)).padTop(30).row();
        for(int i = 0; i < 8; i++) {
            tableInfo.add(new Label("Player " + (i + 1) + ": ", Monopoly.lblStyle));
            playerNames.add(new TextField("Player " + (i + 1), Monopoly.tflStyle));
            tableInfo.add(playerNames.get(i)).width(2*Monopoly.HEIGHT/5).colspan(2);
            isAI.add(new CheckBox("", Monopoly.cbxStyle));
            realDice.add(new CheckBox("", Monopoly.cbxStyle));
            realDice.get(i).setChecked(false);
            tableInfo.add(isAI.get(i));
            tableInfo.add(realDice.get(i)).row();
            if (i > 1) {
                playerNames.get(i).setVisible(false);
                isAI.get(i).setVisible(false);
                realDice.get(i).setVisible(false);
                tableInfo.getCells().get(4*(i+2)+1).getActor().setVisible(false);
            }
        }
        tableButtons.add(btnPlay).padTop(30).width(300).padRight(30);
        tableButtons.add(btnBack).padTop(30).width(300).padLeft(30);

        table.add(tableInfo).row();
        table.add(tableButtons);

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    private void updatePlayers() {
        int oldPlayerCount = playerCount - 1;
        playerCount = Integer.parseInt(playerCountField.getText());

        if (playerCount - oldPlayerCount > 0) {
            for (int i = oldPlayerCount; i < playerCount; i++) {
                playerNames.get(i).setVisible(true);
                isAI.get(i).setVisible(true);
                realDice.get(i).setVisible(true);
                tableInfo.getCells().get(4*(i+2)+1).getActor().setVisible(true);
            }
        } else {
            for (int i = playerCount; i < 8; i++) {
                playerNames.get(i).setVisible(false);
                isAI.get(i).setVisible(false);
                realDice.get(i).setVisible(false);
                tableInfo.getCells().get(4*(i+2)+1).getActor().setVisible(false);
            }
        }
    }
}
