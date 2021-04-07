package simulation;

import gui.Window;
import board.*;

public class Simulation {
    State currState = State.PAUSED;
    public enum Mode {SEQUENTIAL, THREADED};
    // private int windowWidth, windowHeight, boardWidth, boardHeight;
    private Window window;
    private Board board;
    private Mode mode;
    private Output output;

    public Simulation(Mode mode) {
       this.mode = mode; 
    }

    public void setWindowParameters(int w, int h) {
        window = new Window("Conway's Game of Life", w, h);
    }

    public void setBoardParameters(int w, int h) {
        board = mode == Mode.SEQUENTIAL ? new SequentialBoard(w, h) : new ThreadedBoard(w, h);
    }

    public void setBoardParameters(int w, int h, int numThreads, int cutoff) {
        if(mode == Mode.SEQUENTIAL) throw new IllegalArgumentException("The simulation is in sequential mode.\nUnexpected parameters: numThreads, cutoff.");
        board = new ThreadedBoard(w, h, numThreads, cutoff);
    }
    public void start() {
        if (window == null || board == null)
            throw new IllegalStateException("No parameters for Board or Window supplied.\nCall setBoardParameters() and setWindowParametes() before this method.");
        
        VisualBoard b = new VisualBoard(board, window);
        window.open();
        output = new Output(window, b);
        b.init();
        while (window.isOpen()) {
            currState = Input.getNextState(b, currState);
            if (currState == State.RUNNING) {
                b.update();
                b.render();
                output.updateFrames();
                output.printState(currState);
            } else if (currState == State.DRAWING) {
                Input.getDrawingInput(b);
                b.render();
                output.printState(currState);
            } else if (currState == State.PAUSED) {
                Input.steppedExecution(b);
                b.render();
                output.printState(currState);
            }
            window.refreshAndClear();
        }
    }
}
