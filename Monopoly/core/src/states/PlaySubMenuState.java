package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoo1617t1g3.Monopoly;

public class PlaySubMenuState extends State {
    protected Texture bg;
    public PlaySubMenuState(GameStateManager gsm) {
        super(gsm);
        bg = new Texture("playSubMenu_bg.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spb) {
        spb.begin();
        spb.draw(bg, 0, 0, Monopoly.WIDTH, Monopoly.HEIGHT);
        spb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
    }
}
