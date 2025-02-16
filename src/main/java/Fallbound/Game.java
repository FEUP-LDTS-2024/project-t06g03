package Fallbound;

import Fallbound.GUI.GUI;
import Fallbound.GUI.LanternaGUI;
import Fallbound.State.GameState;
import Fallbound.State.State;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {

    private final GUI gui;
    private final State state;

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGUI(90, 30);
        this.state = State.getInstance();
    }

    public Game(State state, GUI gui) {
        this.state = state;
        this.gui = gui;
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        Game game = new Game();
        game.startGame();
    }

    public State getState() {
        return state;
    }

    public void setState(GameState gameState) {
        state.updateState(gameState);
    }

    void startGame() throws IOException, InterruptedException {
        int FPS = 60;
        int frameTime = 1000 / FPS;
        while (this.state.getCurrentState() != GameState.QUIT_GAME) {
            long startTime = System.currentTimeMillis();
            state.step(gui, this, startTime);
            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;
            sleep(sleepTime);
        }
        gui.close();
    }


    protected void sleep(long sleepTime) throws InterruptedException {
        if (sleepTime > 0) Thread.sleep(sleepTime);
    }

}
