package langton;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestAnt {
    @Test
    public void testRotation() {
        Ant a = new Ant(0, 0, Direction.NORTH);
        a.rotate(true);
        assertTrue(Direction.EAST == a.getDirection());
        a.rotate(true);
        assertTrue(Direction.SOUTH == a.getDirection());
        a.rotate(false);
        assertTrue(Direction.EAST == a.getDirection());
    } 
}