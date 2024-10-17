package ru.vladshi.javalearning;

public class Simulation {

    private int moveCounts = 0;
    private final WorldMapConsoleRenderer renderer = new WorldMapConsoleRenderer();
    private final WorldMap worldMap = WorldMap.getInstance();

    public void nextTurn() {
        moveCounts++;
        Actions.turnActions();
        renderer.render(worldMap);
    }

    public void startSimulation() {
        Actions.initWorldMap();
        while (moveCounts < 100) {
            System.out.println("===Simulation=== Turn number = " + moveCounts);
            nextTurn();
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
