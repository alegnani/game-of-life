package GameOfLife;

import gui.Window;
import java.util.Random;

public class Board {
    protected boolean[][] board;
    protected int width, height;

    public Board(int width, int height) {
        board = new boolean[width][height];
        this.width = width;
        this.height = height;
    }

    public void setBoardMirrored(boolean[][] board) {
        this.board = board.clone();
        this.width = this.board.length;
        this.height = this.board[0].length;

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

    public boolean[][] getBoardMirrored() {
        return this.board.clone();
    }

    public boolean[][] getBoard() {
        boolean[][] tempBoard = new boolean[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                tempBoard[i][j] = this.board[j][i];
            }
        }
        return tempBoard;
    }

    public void update() {
        boolean[][] buffer = new boolean[width][height];
        for(int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                int neighbours = getBorder(x, y);
                if (board[x][y]) {
                    if (neighbours < 2 || neighbours > 3) buffer[x][y] = false; // over- or underpopulation
                    else buffer[x][y] = true; // perfect population
                } else {
                    if (neighbours == 3) buffer[x][y] = true; // reproduction
                }
            }
        }
        board = buffer;
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

    private int getBorder(int x, int y) {
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
                    sum += board[tempX][tempY] ? 1 : 0;
                }
            }
        }
        return sum;
    }

    public void setRandomBoard(int w, int h) {
        Random rnd = new Random();
        boolean[][] tempBoard = new boolean[w][h];
        for (int x = 0; x < w; ++x) {
            for (int y = 0; y < h; ++y) {
                tempBoard[x][y] = rnd.nextBoolean();
            }
        }
        this.board = tempBoard;
    }

    public void setRandomBoard() {
        setRandomBoard(width, height);
    }

    public void setEntity(int x, int y, boolean value) {
        board[x][y] = value;
    }
}
