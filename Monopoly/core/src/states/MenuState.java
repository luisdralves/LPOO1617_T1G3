package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoo1617t1g3.Monopoly;

public class MenuState extends State{
    protected Texture bg;
    protected Texture btn_play;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        bg = new Texture("menu_bg.png");
        btn_play = new Texture("btn_play.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlaySubMenuState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spb) {
        spb.begin();
        spb.draw(bg, 0, 0, Monopoly.WIDTH, Monopoly.HEIGHT);
        spb.draw(btn_play, Monopoly.WIDTH/4 - btn_play.getWidth()/2, Monopoly.HEIGHT*3/4 - btn_play.getHeight()/2, btn_play.getWidth(), btn_play.getHeight());
        spb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        btn_play.dispose();
    }
}
