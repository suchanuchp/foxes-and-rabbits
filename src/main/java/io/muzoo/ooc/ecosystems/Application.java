package io.muzoo.ooc.ecosystems;

import java.util.concurrent.TimeUnit;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        Simulator sim = new Simulator(100, 180);
        sim.simulate(4000);
        System.exit(0);


    }

}
