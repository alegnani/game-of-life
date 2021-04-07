package GameOfLife;

import simulation.*;

public class App {

    public static void main(String[] args) {
        Simulation s = new Simulation(Simulation.Mode.THREADED);
        s.setWindowParameters(1440, 1300);
        s.setBoardParameters(1440, 1440, 128, 10000);
        s.start();
    }

    
}
