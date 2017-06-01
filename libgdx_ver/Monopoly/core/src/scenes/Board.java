package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.lpoo1617t1g3.Monopoly;

import logic.GameData;
import logic.Player;

public class Board {
    private Texture board;
    private Vector2 cur1;
    private Vector2 cur2;
    private Vector2 pos;
    private boolean released;

    public Board() {
        board = new Texture("board.jpg");
        cur1 = new Vector2(0, 0);
        cur2 = new Vector2(0, 0);
        pos = new Vector2(0, 0);
        released = true;
    }

    public void handleInput() {
        if (Gdx.input.isTouched()) {
            if(released) {
                cur1.set(Gdx.input.getX(), -Gdx.input.getY());
                released = false;
            }
            cur2.set(Gdx.input.getX(), -Gdx.input.getY());
            pos.add(new Vector2((cur2.x - cur1.x), (cur2.y - cur1.y)));
            checkLimits();
        } else {
            released = true;
        }
    }

    public void checkLimits() {
        if(pos.x < Monopoly.WIDTH - board.getWidth())
            pos.x = Monopoly.WIDTH - board.getWidth();
        else if(pos.x > 0)
            pos.x = 0;
        if(pos.y < Monopoly.HEIGHT - board.getHeight())
            pos.y = Monopoly.HEIGHT - board.getHeight();
        else if(pos.y > 0)
            pos.y = 0;
    }

    public void render(SpriteBatch spb) {
        spb.begin();
        spb.draw(board, pos.x, pos.y);
        for(Player p : GameData.getPlayers()) {
            spb.draw(p.getToken(), posToCoords(p.getPosition())[0], posToCoords(p.getPosition())[1]);
        }
        spb.end();
    }

    private int[] posToCoords(int pos) {
        if(pos < 10)
            return new int[]{(10 - pos) * 50, 0};
        else if(pos < 20)
            return new int[]{0, (pos - 10) * 50};
        else if(pos < 30)
            return new int[]{(pos-20) * 50, 500};
        else if(pos < 40)
            return new int[]{500, (40 - pos) * 50};
        return new int[]{0, 0};
    }
}
