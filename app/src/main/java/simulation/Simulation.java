package simulation;

import gui.Window;
import board.*;

public class Simulation {
    State currState = State.PAUSED;
    private Window window;
    BitMap board;
    private Output output;
    private Input input;

    private double entityWidth, entityHeight;

    public Simulation(BitMap board) {
        this.board = board;
    }

    public void setWindowParameters(int w, int h) {
        window = new Window("Conway's Game of Life", w, h);
    }

    public void computeScaling() {
        entityHeight = window.getHeight() / board.getHeight();
        entityWidth = window.getWidth() / board.getWidth();
        window.setFontSize((int) window.getWidth() / 33);
    }

    private void render() {
        // TODO check change in window size
        boolean[][] tempBitMap = board.getBitMap();
        for (int x = 0; x < board.getWidth(); ++x) {
            for (int y = 0; y < board.getHeight(); ++y) {
                if(tempBitMap[x][y]) {
                    window.fillRect(x * entityWidth, y * entityHeight,
                    entityWidth, entityHeight);
                }
            }
        }
    }

    public void start() {
        if (window == null)
            throw new IllegalStateException("No parameters for Window supplied.\nCall setWindowParametes() before this method.");
        
        window.open();
        computeScaling();
        input = new Input(window, board);
        output = new Output(window);
        board.init();
        while (window.isOpen()) {
            currState = input.getNextState(currState);
            if (currState == State.RUNNING) {
                board.update();
                render();
                output.updateFrames();
                output.printState(currState, entityWidth, entityHeight);
            } else if (currState == State.DRAWING) {
                input.getDrawingInput(entityWidth, entityHeight);
                render();
                output.printState(currState, entityWidth, entityHeight);
            } else if (currState == State.PAUSED) {
                input.steppedExecution();
                render();
                output.printState(currState, entityWidth, entityHeight);
            }
            window.refreshAndClear();
        }
    }
}
