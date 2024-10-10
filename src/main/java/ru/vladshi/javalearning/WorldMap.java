package ru.vladshi.javalearning;

import ru.vladshi.javalearning.config.Settings;
import ru.vladshi.javalearning.entity.*;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class WorldMap {

    public static final int MAX_WIDTH = Settings.WORLD_MAP_MAX_WIDTH;
    public static final int MAX_HEIGHT = Settings.WORLD_MAP_MAX_HEIGHT;

    public HashMap<Coordinates, Entity> entitiesMap = new HashMap<>();

    public void putEntity(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entity.worldMap = this;
        entitiesMap.put(coordinates, entity);
    }

    public void putEntityToRandomEmptyCell(Entity entity) {
        while (true) {
            Coordinates randomCoordinates =  new Coordinates(ThreadLocalRandom.current().nextInt(0, MAX_HEIGHT),
                                                             ThreadLocalRandom.current().nextInt(0, MAX_WIDTH));
            if (getCellContents(randomCoordinates).isEmpty()) {
                putEntity(randomCoordinates, entity);
                break;
            }
        }
    }

    public Optional<Entity> getCellContents(Coordinates coordinates) {
        return Optional.ofNullable(entitiesMap.get(coordinates));
    }
}