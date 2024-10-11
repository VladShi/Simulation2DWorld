package ru.vladshi.javalearning;

import ru.vladshi.javalearning.entity.Grass;
import ru.vladshi.javalearning.entity.Herbivore;
import ru.vladshi.javalearning.entity.Rock;

import java.util.Queue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WorldMap worldMap = new WorldMap();
        Herbivore herbivore = new Herbivore();
        Grass grass1 = new Grass();
        Grass grass2 = new Grass();
        worldMap.putEntity(new Coordinates(2,1), herbivore);
        worldMap.putEntity(new Coordinates(5,1), new Rock());
        worldMap.putEntity(new Coordinates(6,4), new Rock());
        worldMap.putEntity(new Coordinates(2,7), grass1);
        worldMap.putEntity(new Coordinates(6,8), grass2);
        WorldMapConsoleRenderer renderer = new WorldMapConsoleRenderer();
        renderer.render(worldMap);
        System.out.println(herbivore.getPathToTarget());
        for (int i = 0; i < 13; i++) {
            herbivore.makeMove();
            if (i == 6)
                worldMap.entitiesMap.remove(grass1.coordinates);
            renderer.render(worldMap);
            System.out.println(herbivore.getPathToTarget());
            Thread.sleep(600);
        }
    }
}