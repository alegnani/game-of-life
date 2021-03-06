package board;

import java.util.Random;
import simulation.BitMap;

public abstract class Board implements BitMap {
    protected boolean[][] frontBuffer, backBuffer;
    protected int width, height;
    protected int[] rules = {2, 3, 3};

    public Board(int width, int height) {
        frontBuffer = new boolean[width][height];
        backBuffer = new boolean[width][height];
        this.width = width;
        this.height = height;
    }

    public void setBoard(boolean[][] input) {
        // check input has at least size 1x1
        assert(input.length > 0);
        assert(input[0].length > 0);

        int w = input[0].length;
        int h = input.length;

        boolean[][] tempBoard = new boolean[w][h];
        for (int i = 0;  i < w; ++i) {
            for (int j = 0; j < h; ++j) {
                tempBoard[i][j] = input[j][i];
            }
        }
        setBoardMirrored(tempBoard);
    }

    public void setBoardMirrored(boolean[][] board) {
        this.frontBuffer = board.clone();
        this.width = this.frontBuffer.length;
        this.height = this.frontBuffer[0].length;

    }

    public boolean[][] getBoard() {
        boolean[][] tempBoard = new boolean[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                tempBoard[i][j] = this.frontBuffer[j][i];
            }
        }
        return tempBoard;
    }

    public boolean[][] getBoardMirrored() {
        return this.frontBuffer.clone();
    }

    public void setRandomBoard() {
        Random rnd = new Random();
        boolean[][] tempBoard = new boolean[width][height];
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                tempBoard[x][y] = rnd.nextBoolean();
            }
        }
        this.frontBuffer = tempBoard;
    }

    public abstract void update();

    public boolean[][] getBitMap() {
        return frontBuffer;
    }

    public void init() {
        setRandomBoard();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setEntity(int x, int y, boolean value) {
        frontBuffer[x][y] = value;
    }

    protected int getBorder(int x, int y) {
        // TODO: optimize if (x, y) is in the middle of the board
        int sum = 0;
        for (int yDelta = -1; yDelta <= 1; ++yDelta) {
            for (int xDelta = -1; xDelta <= 1; ++ xDelta) {
                int tempX = x + xDelta;
                int tempY = y + yDelta;
                //check if neighbour exists or is not the same vertex
                if (!(tempX < 0 || tempX >= width || tempY < 0 || tempY >= height
                    || tempX == x && tempY == y)) {
                    //add one if neighbour is alive
                    sum += frontBuffer[tempX][tempY] ? 1 : 0;
                }
            }
        }
        return sum;
    }

    protected void computeEntity(int x, int y) {
        int neighbours = getBorder(x, y);
            if (frontBuffer[x][y]) {
                if (neighbours < 2 || neighbours > 3) backBuffer[x][y] = false; // over- or underpopulation
                else backBuffer[x][y] = true; // perfect population
            } else {
                backBuffer[x][y] = neighbours == 3; // reproduction
            }
    }

    public void swapBuffers() {
        boolean[][] temp = frontBuffer;
        frontBuffer = backBuffer;
        backBuffer = temp;
    }

    public String toString() {
        String out = "";
        boolean[][] tempBoard = getBoard();
        for (boolean[] i : tempBoard) {
            for (boolean j : i) {
                out += " " + (j ? "@" : "-");
            }
            out += "\n";
        }
        return out;
    }
}
