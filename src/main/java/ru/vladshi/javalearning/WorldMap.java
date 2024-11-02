package ru.vladshi.javalearning;

import ru.vladshi.javalearning.config.Settings;
import ru.vladshi.javalearning.entity.Entity;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class WorldMap {

    private static final WorldMap INSTANCE = new WorldMap();
    public final int MAX_WIDTH = Settings.WORLD_MAP_MAX_WIDTH;
    public final int MAX_HEIGHT = Settings.WORLD_MAP_MAX_HEIGHT;

    private final HashMap<Coordinates, Entity> entitiesMap = new HashMap<>();

    private WorldMap() {
    }

    public static WorldMap getInstance() {
        return INSTANCE;
    }

    public void putEntity(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entitiesMap.put(coordinates, entity);
    }

    public void putEntityToRandomEmptyCell(Entity entity) {
        boolean isMapFull = entitiesMap.size() >= MAX_WIDTH * MAX_HEIGHT;
        if (isMapFull) {
            throw new RuntimeException("Map has already been full");
        }
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
        if (coordinates == null)
            return Optional.empty();
        return Optional.ofNullable(entitiesMap.get(coordinates));
    }

    public void clearCell(Coordinates coordinates) {
        entitiesMap.remove(coordinates);
    }

    public boolean doesMapHaveObjectsOf(Class<? extends Entity> clazz) {
        for (Entity entity : entitiesMap.values())
            if (entity.getClass().equals(clazz)) {
                return true;
            }
        return false;
    }

    public HashMap<Coordinates, Entity> getEntitiesMap() {
        return entitiesMap;
    }
}