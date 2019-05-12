package io.muzoo.ooc.ecosystems;

public class Application {
    public static void main(String[] args) {
        Simulator sim = new Simulator(100, 180);
        sim.simulate(4000);

        //sim.simulateOneStep();
        System.exit(0);
        //Fox fox = new Fox(true);

    }

}
