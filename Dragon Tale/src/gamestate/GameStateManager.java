package gamestate;

<<<<<<< HEAD
import java.util.ArrayList;

public class GameStateManager {
	
	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	public static final int GAMEOVERSTATE = 2;


	public GameStateManager() {
		
		gameStates = new ArrayList<GameState>();
		
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new Level1State(this));

	}
	
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update() {
		gameStates.get(currentState).update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}
	
}









=======

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager {
    private final ArrayList<GameState> gameStates = new ArrayList<GameState>();
    private int currentState = 0;
    public static final int MENUSTATE = 0;
    public static final int LEVEL1STATE = 1;
//    public static final int HELPSTATE = 2;

    public GameStateManager() {
        gameStates.add(new MenuState(this));
        gameStates.add(new Level1State(this));

    }

    public void setState(int state) {
        currentState = state;
        ((GameState)gameStates.get(currentState)).init();
    }

    public void update() {
        ((GameState)gameStates.get(currentState)).update();
    }

    public void draw(Graphics2D g) {
        ((GameState)gameStates.get(currentState)).draw(g);
    }

    public void keyPressed(int k) {
        ((GameState)gameStates.get(currentState)).keyPressed(k);
    }

    public void keyReleased(int k) {
        ((GameState)gameStates.get(currentState)).keyReleased(k);
    }
}

>>>>>>> NguyenPhuc
