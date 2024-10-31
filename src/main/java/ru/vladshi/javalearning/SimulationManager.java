package ru.vladshi.javalearning;

import java.util.Scanner;

public class SimulationManager {

    private static final String QUIT = "q";

    public static void runSimulation(){
        Actions.initWorldMap();
        Simulation simulation = new Simulation();
        simulation.setPaused(true);
        inputMonitor(simulation).start();
        simulation.start();
    }

    private static Thread inputMonitor(Simulation simulation) {
        Thread thread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while(simulation.isRunning()) {
                String userInput = scanner.nextLine();
                if (userInput.equals(QUIT)) {
                    scanner.close();
                    simulation.stop();
                } else {
                    simulation.flipPause();
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setDaemon(true);
        return thread;
    }
}
