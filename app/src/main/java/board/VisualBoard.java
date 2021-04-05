package board;

import gui.Window;

public class VisualBoard {
    protected Window window;
    protected double windowWidth, windowHeight;
    public double entityWidth, entityHeight;
    protected Board board;

    public VisualBoard(Board board, Window window) {
        this.board = board;
        this.windowWidth = window.getWidth();
        this.windowHeight = window.getHeight();
        this.window = window;
        window.setColor(0, 0, 0);
        computeScaling();
    }

    public Window getWindow() {
        return window;
    }

    public void update() {
        board.update();
    }

    public void init() {
        board.setRandomBoard();
    }

    public Board callFunc() { // TODO remove after debugging 
        return board;
    }

    protected void computeScaling() {
        windowHeight = window.getHeight();
        windowWidth = window.getWidth();
        entityHeight = windowHeight / board.height;
        entityWidth = windowWidth / board.width;
        window.setFontSize((int) (windowWidth / 33));
    }

    public void render() {
        if (windowHeight != window.getHeight() || windowWidth != window.getWidth()) {
            computeScaling();
        }
        for (int x = 0; x < board.width; ++x) {
            for (int y = 0; y < board.height; ++y) {
                if (board.frontBuffer[x][y])
                    window.fillRect(x * entityWidth, y * entityHeight, entityWidth, entityHeight);
            }
        }
    }

    public void setEntity(int x, int y, boolean value) {
        board.setEntity(x, y, value);
    }
}
