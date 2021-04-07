package board;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ThreadedBoard extends Board {

    protected ForkJoinPool fjpool;
    protected int cutoff = 2048;

    public ThreadedBoard(int width, int height) {
        super(width, height);
        fjpool = new ForkJoinPool();
    }

    public ThreadedBoard(int width, int height, int numThreads, int cutoff) {
        super(width, height);
        fjpool = new ForkJoinPool(numThreads);
        this.cutoff = cutoff;
    }

    @Override
    public void update() {
        fjpool.invoke(new UpdateBoard(this, 0, width, 0, height, cutoff));
        swapBuffers();
    }  
}


class UpdateBoard extends RecursiveAction {
    private int x1, x2, y1, y2;
    private int cutoff;
    private ThreadedBoard board;
    private static final long serialVersionUID = 1L;

    public UpdateBoard(ThreadedBoard board, int x1, int x2, int y1, int y2, int cutoff) {
        this.board = board;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.cutoff = cutoff;
    }

    @Override
    public void compute() {
        int deltaX = x2 - x1;
        int deltaY = y2 - y1;
        if((deltaX) * (deltaY) <= cutoff) {
            for (int x = x1; x < x2; ++x) {
                for (int y = y1; y < y2; ++y) {
                    board.computeEntity(x, y);
                }
            }
        } else {
            UpdateBoard t1, t2;
            if (deltaX > deltaY) {
                int mid = (x1 + x2) / 2;
                t1 = new UpdateBoard(board, x1, mid, y1, y2, cutoff);
                t2 = new UpdateBoard(board, mid, x2, y1, y2, cutoff);
            } else {
                int mid = (y1 + y2) / 2;
                t1 = new UpdateBoard(board, x1, x2, y1, mid, cutoff);
                t2 = new UpdateBoard(board, x1, x2, mid, y2, cutoff);
            }
            t1.fork();
            t2.fork();
            t1.join();
            t2.join();
        }
    }
}
