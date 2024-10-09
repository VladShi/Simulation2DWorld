package ru.vladshi.javalearning;

import ru.vladshi.javalearning.entity.Grass;
import ru.vladshi.javalearning.entity.Herbivore;

public class Main {
    public static void main(String[] args) {
        WorldMap worldMap = new WorldMap();
        Herbivore herbivore = new Herbivore();
        Grass grass = new Grass();
        worldMap.putEntity(new Coordinates(2,1), herbivore);
//        worldMap.putEntity(new Coordinates(4,3), new Rock());
//        worldMap.putEntity(new Coordinates(4,4), new Rock());
        worldMap.putEntity(new Coordinates(6,8), grass);
        int dist = herbivore.findPath(grass.coordinates);
        WorldMapConsoleRenderer renderer = new WorldMapConsoleRenderer();
        renderer.render(worldMap);
        System.out.println(dist);
    }
}