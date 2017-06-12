package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo1617t1g3.Monopoly;

import scenes.RulesScene;

public class MainMenu implements Screen {
    public static boolean viewingRules;
    private Monopoly game;
    private Viewport vp;
    private OrthographicCamera cam;
    private Stage stage;
    private Table table;
    private TextButton btnPlay, btnRules, btnResume, btnExit;
    private Texture background;
    private RulesScene rulesScene;

    public MainMenu(Monopoly m) {
        game = m;
        cam = new OrthographicCamera();
        vp = new FitViewport(Monopoly.WIDTH, Monopoly.HEIGHT, cam);
        stage = new Stage(vp);
        Gdx.input.setInputProcessor(stage);
        table = new Table(Monopoly.skin);

        viewingRules = false;
        rulesScene = new RulesScene(game);

        btnPlay = new TextButton("New game", Monopoly.btnStyle);
        btnPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new NewGameScreen(game));
            }
        });

        btnRules = new TextButton("Rules", Monopoly.btnStyle);
        btnRules.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                viewingRules = true;
            }
        });

        btnResume = new TextButton("Continue game", Monopoly.btnStyle);
        btnResume.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (game.isHappening)
                    game.setScreen(new PlayScreen(game));
            }
        });

        btnExit = new TextButton("Exit", Monopoly.btnStyle);
        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        background = new Texture("menu_bg.jpg");

        table.setBounds(125, 0, Monopoly.WIDTH - 250, Monopoly.HEIGHT);
        table.row();
        table.add(btnPlay).width(300).left().expand();
        table.add(btnRules).width(300).right().expand();
        table.row();
        table.add(btnResume).width(300).left().expand();
        table.add(btnExit).width(300).right().expand();
        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(stage);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.spb.begin();
        game.spb.draw(background, 0, 0, Monopoly.WIDTH, Monopoly.HEIGHT);
        game.spb.end();
        stage.act(delta);
        stage.draw();

        if (viewingRules)
            rulesScene.render();
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
}
