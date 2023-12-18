package gamestate;

import java.awt.Graphics2D;

public abstract class GameState {
    protected GameStateManager gsm;

    public GameState() {
    }

    public abstract void init();

    public abstract void update();

    public abstract void draw(Graphics2D var1);

    public abstract void keyPressed(int var1);

    public abstract void keyReleased(int var1);
}
