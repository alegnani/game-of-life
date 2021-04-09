package GameOfLife;

import board.ThreadedBoard;
import langton.LangtonBoard;
import simulation.*;

public class App {

    public static void main(String[] args) {
        // BitMap b = new ThreadedBoard(100, 100);
        BitMap b = new LangtonBoard(100, 100); 
        Simulation s = new Simulation(b);
        s.setWindowParameters(900, 900);
        s.start();
    }

    
}
