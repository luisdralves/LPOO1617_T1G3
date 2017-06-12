package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.lpoo1617t1g3.Monopoly;

import screens.MainMenu;

/**
 * Created by Miguel on 12/06/2017.
 */

public class RulesScene {
    private Monopoly game;
    private TextButton btnBack;
    private Texture bg;
    private Stage stage;
    private Table table;

    public RulesScene(Monopoly m) {
        stage = new Stage();
        table = new Table();
        bg = new Texture("rules_bg.jpg");
        game = m;
        btnBack = new TextButton("Go Back", Monopoly.btnStyle);
        btnBack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MainMenu.viewingRules = false;
            }
        });
        table.setBounds(0, 0, Monopoly.WIDTH, Monopoly.HEIGHT);
        table.bottom();
        table.add(btnBack);
        stage.addActor(table);
    }

    public void render() {
        Gdx.input.setInputProcessor(stage);
        game.spb.begin();
        game.spb.draw(bg, 0, 0, Monopoly.WIDTH, Monopoly.HEIGHT);
        game.spb.end();
        stage.act();
        stage.draw();
    }


}
