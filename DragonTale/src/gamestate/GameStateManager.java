package gamestate;


import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager {
    private final ArrayList<GameState> gameStates = new ArrayList<GameState>();
    private int currentState = 0;
    public static final int MENUSTATE = 0;
    public static final int LEVEL1STATE = 1;
//    public static final int HELPSTATE = 2;

    public GameStateManager() {
        this.gameStates.add(new MenuState(this));
        this.gameStates.add(new Level1State(this));

    }

    public void setState(int state) {
        this.currentState = state;
        ((GameState)this.gameStates.get(this.currentState)).init();
    }

    public void update() {
        ((GameState)this.gameStates.get(this.currentState)).update();
    }

    public void draw(Graphics2D g) {
        ((GameState)this.gameStates.get(this.currentState)).draw(g);
    }

    public void keyPressed(int k) {
        ((GameState)this.gameStates.get(this.currentState)).keyPressed(k);
    }

    public void keyReleased(int k) {
        ((GameState)this.gameStates.get(this.currentState)).keyReleased(k);
    }
}

