package simulation;

import gui.Window;

public class Input {

    enum Mode {PAINTING, ERASING, NAN};

    public static void getDrawingInput(board.VisualBoard b) {
        Window window = b.getWindow();
        Mode mode;
        if(window.isLeftMouseButtonPressed())
            mode = Mode.PAINTING;
        else if (window.isRightMouseButtonPressed())
            mode = Mode.ERASING;
        else 
            mode = Mode.NAN;
        if (mode != Mode.NAN) {
            int x = (int) (window.getMouseX() / b.entityWidth);
            int y = (int) (window.getMouseY() / b.entityHeight);
            b.setEntity(x, y, mode == Mode.PAINTING ? true : false);
        }
    }

    public static State getNextState(board.VisualBoard b, State currState) {
        Window window = b.getWindow();
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

    public static void steppedExecution(board.VisualBoard b) {
        Window window = b.getWindow();
        if (window.wasKeyTyped("right"))
            b.update();
    }
}
