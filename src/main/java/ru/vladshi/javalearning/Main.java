package ru.vladshi.javalearning;

import ru.vladshi.javalearning.entity.Grass;
import ru.vladshi.javalearning.entity.Herbivore;
import ru.vladshi.javalearning.entity.Rock;

import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        WorldMap worldMap = new WorldMap();
        Herbivore herbivore = new Herbivore();
        Grass grass = new Grass();
        worldMap.putEntity(new Coordinates(2,1), herbivore);
        worldMap.putEntity(new Coordinates(5,1), new Rock());
        worldMap.putEntity(new Coordinates(6,4), new Rock());
        worldMap.putEntity(new Coordinates(6,8), grass);
        Queue<Coordinates> pathTest = herbivore.findPath(Grass.class);
        WorldMapConsoleRenderer renderer = new WorldMapConsoleRenderer();
        renderer.render(worldMap);
        System.out.println(pathTest);
    }
}