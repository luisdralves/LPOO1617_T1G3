package states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by up201405308 on 25/05/2017.
 */

public abstract class State {
    protected Vector2 cur;
    protected OrthographicCamera cam;
    protected GameStateManager gsm;

    protected State(GameStateManager g) {
        gsm = g;
        cur = new Vector2();
        cam = new OrthographicCamera();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch spb);
    public abstract void dispose();
}
