package simulation;

import gui.Window;

public class Output {

    private Window window;
    private int frameCount = 0;
    private long millis;
    private int currentFps = 0;

    public Output(Window w) {
        window = w;
        millis = System.currentTimeMillis();
    }

    public void printState(State state, double eW, double eH) {
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

        double x = 2 * eW;
        double y = window.getHeight() - 2 * eH;

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
