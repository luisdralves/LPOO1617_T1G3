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

public class PlayersScreen implements Screen{
    private Monopoly game;
    private Camera cam;
    private Viewport vp;
    private Stage stage;
    private Table table;
    private TextButton btnPlay, btnBack;
    public static List<TextField> playerNames;
    public static List<CheckBox> isAI;
    public static int playerCount;
    private TextField playerCountField;

    public PlayersScreen(Monopoly m) {
        game = m;
        cam = new OrthographicCamera();
        vp = new FitViewport(Monopoly.WIDTH, Monopoly.HEIGHT, cam);
        stage = new Stage(vp);
        Gdx.input.setInputProcessor(stage);
        table = new Table(Monopoly.skin);

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

        playerCountField = new TextField("2", Monopoly.tflStyle);
        playerCountField.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        playerCountField.addListener(new InputListener() {
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Input.Keys.ENTER) {
                    addPlayer();
                }
                return false;
            }
        });

        table.setBounds(0, 0, Monopoly.WIDTH, Monopoly.HEIGHT);
        table.add();
        table.add(new Label("Player number: ", Monopoly.lblStyle));
        table.add(playerCountField).width(30).row();

        table.add();
        table.add(new Label("Name", Monopoly.lblStyle)).padTop(30);
        table.add(new Label("AI", Monopoly.lblStyle)).padTop(30).row();
        for(int i = 0; i < 8; i++) {
            table.add(new Label("Player " + (i + 1) + ": ", Monopoly.lblStyle));
            playerNames.add(new TextField("Player " + (i + 1), Monopoly.tflStyle));
            table.add(playerNames.get(i)).width(Monopoly.HEIGHT/3);
            isAI.add(new CheckBox("", Monopoly.cbxStyle));
            table.add(isAI.get(i)).row();
            if (i > 1) {
                playerNames.get(i).setVisible(false);
                isAI.get(i).setVisible(false);
                table.getCells().get(3*(i+2)).getActor().setVisible(false);
            }
        }
        table.add(btnPlay).padTop(20);
        table.add(btnBack).padTop(20);

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

    private void addPlayer() {
        int oldPlayerCount = playerCount - 1;
        playerCount = Integer.parseInt(playerCountField.getText());

        if (playerCount - oldPlayerCount + 1 > 0) {
            for (int i = oldPlayerCount; i < playerCount; i++) {
                playerNames.get(i).setVisible(true);
                isAI.get(i).setVisible(true);
                table.getCells().get(3 * (i + 2)).getActor().setVisible(true);
            }
        } else {
            for (int i = playerCount; i < 8; i++) {
                playerNames.get(i).setVisible(false);
                isAI.get(i).setVisible(false);
                table.getCells().get(3 * (i + 2)).getActor().setVisible(false);
            }
        }
    }
}
