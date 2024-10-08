package ru.vladshi.javalearning;

public class Main {
    public static void main(String[] args) {
        WorldMap worldMap = new WorldMap();
        WorldMapConsoleRenderer renderer = new WorldMapConsoleRenderer();
        renderer.render(worldMap);
    }
}