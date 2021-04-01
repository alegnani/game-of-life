
/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package GameOfLife;

import gui.Window;

public class App {

    enum State {PAUSED, DRAWING, RUNNING};
    static State currState = State.PAUSED;
    public static void main(String[] args) {
        int size = 250;
        Window window = new Window("Empty", 1000, 1000);
        window.open();
        VisualBoard b = new VisualBoard(size, size, window);
        b.setRandomBoard();
        while (window.isOpen()) {
            currState = Input.getNextState(b, currState);
            if (currState == State.RUNNING) {
                b.update();
                b.render();
                Output.printState(b, currState);
            } else if (currState == State.DRAWING) {
                Input.getDrawingInput(b);
                b.render();
                Output.printState(b, currState);
            } else if (currState == State.PAUSED) {
                Input.steppedExecution(b);
                b.render();
                Output.printState(b, currState);
            }
            window.refreshAndClear();
        }
    }

    
}
