package simulation;

import gui.Window;
import board.VisualBoard;

public class Output {

    private State state;
    private Window window;
    private VisualBoard board;
    private int frameCount = 0;
    private long millis;
    private int currentFps = 0;

    public Output(Window w, VisualBoard b) {
        window = w;
        board = b;
        millis = System.currentTimeMillis();
    }

    public void printState(State state) {
        String str;
        if (state == State.RUNNING) {
            str = "Running @ " + currentFps + " FPS";
        } else if (state == State.DRAWING) {
            str = "Editing";
        } else {
            str = "Paused";
        }
        window.setFontSize((int) window.getHeight() / 33);
        window.setColor(255, 0, 255);

        double x = 2 * board.entityWidth;
        double y = window.getHeight() - 2 * board.entityHeight;

        window.setFontSize(20);
        window.drawString(str, x, y);
        window.setColor(0, 0, 0);
    }

    public void updateFrames() {
        long temp = System.currentTimeMillis();
        if (temp - millis >= 1000) {
            currentFps = frameCount;
            frameCount = 0;
            millis = temp; 
        } else {
            ++frameCount;
        }
        window.setColor(255, 0 ,255);
    }
   }
