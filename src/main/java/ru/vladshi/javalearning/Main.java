package ru.vladshi.javalearning;

import ru.vladshi.javalearning.entity.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WorldMap worldMap = WorldMap.getInstance();
        Herbivore herbivore = new Herbivore();
        Predator predator = new Predator();
        Grass grass1 = new Grass();
        Grass grass2 = new Grass();
        worldMap.putEntity(new Coordinates(2,1), herbivore);
        worldMap.putEntity(new Coordinates(5,1), new Rock());
        worldMap.putEntity(new Coordinates(6,4), new Rock());
        worldMap.putEntity(new Coordinates(2,7), grass1);
        worldMap.putEntity(new Coordinates(8,1), grass2);
//        worldMap.putEntity(new Coordinates(1,9), predator);
//        worldMap.putEntity(new Coordinates(4,3), predator);
//        worldMap.putEntity(new Coordinates(4,4), predator);
        worldMap.putEntityToRandomEmptyCell(predator);
        WorldMapConsoleRenderer renderer = new WorldMapConsoleRenderer();
        renderer.render(worldMap);
        for (int i = 0; i < 18; i++) {
            System.out.println("=====================  i = " + i);
            List<Creature> creatures = new ArrayList<>();
            for (Entity entity: worldMap.getEntitiesMap().values()) {
                if (entity instanceof Creature creature) {
                    creatures.add(creature);
                }
            }
            for (Creature creature: creatures) {
                creature.makeMove();
            }
            renderer.render(worldMap);
            Thread.sleep(500);
        }
    }
}