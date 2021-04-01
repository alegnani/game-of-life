package GameOfLife;

import GameOfLife.Board;


import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {
    @Test
    public void checkConstructor() {

    }

    @Test
    public void checkGetBorder() {
        boolean[][] test1 = { { false, true, true }, { true, false, false }, { false, false, true } };
        /*
         * // 0 1 1 // 1 0 0 // 0 0 1
         */

        Board b = new Board(3, 3);
        b.setBoard(test1);

        //assertEquals(2, b.getBorder(0, 0));
        //assertEquals(2, b.getBorder(1, 0));
        //assertEquals(4, b.getBorder(1, 1));
        //assertEquals(0, b.getBorder(2, 2));

    }

    @Test
    public void checkUpdate() {
        boolean[][] test1 = {{false, true, true}, {true, false, false}, {false,false, true}};
        boolean[][] res1 = {{false, true, false}, {false, false, true}, {false, false, false}};
        Board b = new Board(3, 3);
        b.setBoard(test1);
        b.update();
        System.out.println("Expected:");
        System.out.println(Arrays.deepToString(res1));
        System.out.println("Got:");
        System.out.println(Arrays.deepToString(b.getBoard()));
        assertArrayEquals(res1, b.getBoard());


    }
}
