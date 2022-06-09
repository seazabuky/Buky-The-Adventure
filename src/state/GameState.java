package state;

import java.awt.Graphics2D;

import util.MouseHandler;
import util.KeyHandler;

public abstract class GameState {
    protected GameStateManager gsm;

    public GameState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public abstract void update(double time);
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void render(Graphics2D g);
}
