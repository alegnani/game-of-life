package board;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    boolean[][] testBoard = {{false, true, false, false, false}, 
                             {false, false, true, false, false}, 
                             {true, true, true, false, false},
                             {false, false, false, false, false},
                             {false, false, false, false, false}};



    @Test
    public void testGetBorder() {
        Board b = new SequentialBoard(5, 5); // Board is defines as abstract
        b.setBoard(testBoard);
        System.out.println(b);
        assertEquals(0, b.getBorder(0, 0));
        assertEquals(2, b.getBorder(0, 1));
        assertEquals(1, b.getBorder(0, 2));
        assertEquals(1, b.getBorder(0, 3));
        assertEquals(0, b.getBorder(0, 4));
        assertEquals(3, b.getBorder(1, 0));
        assertEquals(4, b.getBorder(1, 1));
        assertEquals(1, b.getBorder(1, 2));
        assertEquals(1, b.getBorder(1, 3));
        assertEquals(0, b.getBorder(1, 4));
        assertEquals(1, b.getBorder(2, 0));
        assertEquals(2, b.getBorder(2, 1));
    }
    @Test
    public void testswapBuffers() {
        Board b = new SequentialBoard(2, 2);
        boolean[][] t1 = {{true, false}, {false, true}};
        b.setBoard(t1);
        b.backBuffer[0][0] = true;
        b.swapBuffers();
        System.out.println(b);
        b.backBuffer[1][0] = true;
        System.out.println(b);
        b.swapBuffers();
        System.out.println(b);
    }
}
