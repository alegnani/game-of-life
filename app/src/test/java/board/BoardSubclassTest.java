package board;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardSubclassTest {
    @Test
    public void compareSpeed() {
        SequentialBoard seq = new SequentialBoard(100, 100);
        ThreadedBoard thr = new ThreadedBoard(100, 100, 16, 2048);

        seq.setRandomBoard();
        thr.setBoard(seq.getBoard());
        assertArrayEquals(seq.getBoard(), thr.getBoard());

        System.out.println("Testing sequential time");

        long s = execTime(seq, 14, 10);

        System.out.println("Testing threaded time");

        long t = execTime(thr, 14, 10);
        
        assertArrayEquals(thr.getBoard(), seq.getBoard());
        System.out.println(s);
        System.out.println(t);
    }

    // @Test
    public void testThreadedSpeed() {
        ThreadedBoard b = new ThreadedBoard(100, 100);
        b.setRandomBoard();
        boolean[][] test = b.getBoard();

        for (int i = 1; i < 1 << 10; i = i << 1) {
            for (int j = 32; j < 1 << 12; j = j << 1) {
                b = new ThreadedBoard(100, 100, i, j);
                b.setBoard(test);
                System.out.print(i + " Threads, " + j + " Cutoff:\t");
                System.out.println(execTime(b, 12, 10));
            }
        }
    }

    private long execTime(Board board, int exp, int runs) {
        long t1 = System.currentTimeMillis();
        for (int iter = 0; iter < runs; ++iter) {
            for(int i = 0; i < 1 << exp; ++i) {
                board.update();
            }
        }
        long t2 = System.currentTimeMillis();
        return (t2 - t1) / runs;
    }
}
