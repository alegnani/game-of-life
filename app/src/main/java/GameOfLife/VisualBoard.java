package GameOfLife;

import gui.Window;

public class VisualBoard extends Board{
    
    private Window window;
    private double windowWidth, windowHeight;
    public double entityWidth, entityHeight;

    public VisualBoard(int width, int height, Window window) {
        super(width, height);
        this.window = window;
        window.setColor(0, 0, 0);
        computeScaling();
    }

    public Window getWindow() {
        return window;
    }

    private void computeScaling() {
        windowHeight = window.getHeight();
        windowWidth = window.getWidth();
        entityHeight = windowHeight / height;
        entityWidth = windowWidth / width;
        window.setFontSize((int) (windowWidth / 33));
    }

    public void render() {
        if (windowHeight != window.getHeight() || windowWidth != window.getWidth()) {
            computeScaling();
        }
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                if (board[x][y])
                    window.fillRect(x * entityWidth, y * entityHeight, entityWidth, entityHeight);
            }
        }
    }
}
