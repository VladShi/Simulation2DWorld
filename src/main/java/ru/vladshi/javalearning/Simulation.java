package ru.vladshi.javalearning;

import ru.vladshi.javalearning.renderer.ConsoleRenderer;

public class Simulation {

    private int turnNumber = 0;
    private final ConsoleRenderer renderer = new ConsoleRenderer();
    private final WorldMap worldMap = WorldMap.getInstance();
    private volatile boolean paused = false;
    private volatile boolean running = false;

    public void start() {
        running = true;
        while (running) {
            if (!paused) {
                makeTurn();
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void flipPause() {
        if (paused) {
            paused = false;
        } else {
            paused = true;
        }
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    private void makeTurn() {
        renderer.render(worldMap, turnNumber);
        Actions.turnActions();
        turnNumber++;
    }
}
