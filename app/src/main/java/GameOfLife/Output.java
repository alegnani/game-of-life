package GameOfLife;

import gui.Window;

public class Output {
    public static void printState(VisualBoard board, App.State state) {
        String str;
        if (state == App.State.RUNNING) {
            str = "Running";
        } else if (state == App.State.DRAWING) {
            str = "Editing";
        } else {
            str = "Paused";
        }
        Window window = board.getWindow();
        window.setColor(255, 0, 255);
        board.getWindow().drawString(str, 2 * board.entityWidth, (board.height - 2) * board.entityHeight);
        window.setColor(0, 0, 0);
    }
}
