package ru.vladshi.javalearning;

import ru.vladshi.javalearning.config.Settings;
import ru.vladshi.javalearning.entity.*;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class WorldMap {

    public static final int MAX_WIDTH = Settings.WORLD_MAP_MAX_WIDTH;
    public static final int MAX_HEIGHT = Settings.WORLD_MAP_MAX_HEIGHT;

    HashMap<Coordinates, Entity> entitiesMap = new HashMap<>();

    public boolean isCellEmpty(Coordinates coordinates) {
        return !entitiesMap.containsKey(coordinates);
    }

    public void putEntity(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entitiesMap.put(coordinates, entity);
    }

    public void putEntityToRandomEmptyCell(Entity entity) {
        while (true) {
            Coordinates randomCoordinates =  new Coordinates(ThreadLocalRandom.current().nextInt(0, MAX_HEIGHT),
                                                             ThreadLocalRandom.current().nextInt(0, MAX_WIDTH));
            if (isCellEmpty(randomCoordinates)) {
                putEntity(randomCoordinates, entity);
                break;
            }
        }
    }

    public Entity getEntity(Coordinates coordinates) {
        return entitiesMap.get(coordinates);
    }
}