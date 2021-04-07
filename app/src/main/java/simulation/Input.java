package simulation;

import gui.Window;

public class Input {

    enum Mode {PAINTING, ERASING, NAN};
    private Window window;
    private BitMap board;

    public Input(Window window, BitMap board) {
        this.window = window;
        this.board = board;
    }

    public void getDrawingInput(double eW, double eH) { 
        Mode mode;
        if(window.isLeftMouseButtonPressed())
            mode = Mode.PAINTING;
        else if (window.isRightMouseButtonPressed())
            mode = Mode.ERASING;
        else 
            mode = Mode.NAN;
        if (mode != Mode.NAN) {
            int x = (int) (window.getMouseX() / eW);
            int y = (int) (window.getMouseY() / eH);
            board.setEntity(x, y, mode == Mode.PAINTING ? true : false);
        }
    }

    public State getNextState(State currState) {
        State ret;
        if (currState == State.PAUSED && window.wasKeyTyped("P") 
            || currState == State.DRAWING && window.wasKeyTyped("E"))
            ret = State.RUNNING;
        else if (window.wasKeyTyped("P"))
            ret = State.PAUSED;
        else if (window.wasKeyTyped("E"))
            ret = State.DRAWING;
        else
            ret = currState;

        return ret;
    }

    public void steppedExecution() {
        if (window.wasKeyTyped("right"))
            board.update();
    }
}
