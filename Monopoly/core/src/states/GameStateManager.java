package states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }

    public void push(State st) {
        states.push(st);
    }

    public State pop() {
        return states.pop();
    }

    public void set(State st) {
        states.pop();
        states.push(st);
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void render(SpriteBatch spb) {
        states.peek().render(spb);
    }
}
