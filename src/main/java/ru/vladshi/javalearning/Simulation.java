package ru.vladshi.javalearning;

import java.util.Scanner;

public class Simulation {

    private int moveCounts = 0;
    private final WorldMapConsoleRenderer renderer = new WorldMapConsoleRenderer();
    private final WorldMap worldMap = WorldMap.getInstance();
    private volatile boolean paused = false;

    public void nextTurn() {
        moveCounts++;
        renderer.render(worldMap);
        Actions.turnActions();
    }

    public void startSimulation() throws InterruptedException {
        Actions.initWorldMap();

        Thread thread = threadToPauseSimulation();
        thread.start();

        while (true) {
            System.out.println("===Simulation=== Turn number = " + moveCounts);
            nextTurn();
            Thread.sleep(600);
            while (paused) {
                Thread.sleep(1);
            }
        }
    }

    public void pauseSimulation() {
        if (!paused) paused = true;
        else paused = false;
    }

    private Thread threadToPauseSimulation() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                if (input.equals("q")) {
                    scanner.close();
                    System.exit(0);
                } else {
                    pauseSimulation();
                }
                Thread.yield();
            }
        });
        thread.setDaemon(true);
        return thread;
    }
}
