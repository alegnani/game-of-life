package GameOfLife;

import gui.Window;

public class Output {
    public static void printState(board.VisualBoard board, State state) {
        String str;
        if (state == State.RUNNING) {
            str = "Running";
        } else if (state == State.DRAWING) {
            str = "Editing";
        } else {
            str = "Paused";
        }
        Window window = board.getWindow();
        window.setColor(255, 0, 255);
        board.getWindow().drawString(str, 2 * board.entityWidth, (board.getWindow().getHeight() - 2) * board.entityHeight);
        window.setColor(0, 0, 0);
    }
}
