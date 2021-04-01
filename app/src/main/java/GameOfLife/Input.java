package GameOfLife;

import javax.lang.model.util.ElementScanner6;

import gui.Window;

public class Input {

    enum Mode {PAINTING, ERASING, NAN};

    public static void getDrawingInput(VisualBoard board) {
        Window window = board.getWindow();
        Mode mode;
        if(window.isLeftMouseButtonPressed())
            mode = Mode.PAINTING;
        else if (window.isRightMouseButtonPressed())
            mode = Mode.ERASING;
        else 
            mode = Mode.NAN;
        if (mode != Mode.NAN) {
            int x = (int) (window.getMouseX() / board.entityWidth);
            int y = (int) (window.getMouseY() / board.entityHeight);
            board.setEntity(x, y, mode == Mode.PAINTING ? true : false);
        }
    }

    public static App.State getNextState(VisualBoard board, App.State prevState) {
        Window window = board.getWindow();
        App.State ret;
        if (prevState == App.State.PAUSED && window.wasKeyTyped("P") 
            || prevState == App.State.DRAWING && window.wasKeyTyped("E"))
            ret = App.State.RUNNING;
        else if (window.wasKeyTyped("P"))
            ret = App.State.PAUSED;
        else if (window.wasKeyTyped("E"))
            ret = App.State.DRAWING;
        else
            ret = prevState;

        return ret;
    }

    public static void steppedExecution(VisualBoard b) {
        Window window = b.getWindow();
        if (window.wasKeyTyped("right"))
            b.update();
    }
}
