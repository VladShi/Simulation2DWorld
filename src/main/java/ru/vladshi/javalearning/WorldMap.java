package ru.vladshi.javalearning;

import ru.vladshi.javalearning.config.Settings;
import ru.vladshi.javalearning.entity.Entity;

import java.util.HashMap;
import java.util.Optional;

public class WorldMap {

    private static final WorldMap INSTANCE = new WorldMap();
    public final int maxWidth = Settings.WORLD_MAP_MAX_WIDTH;
    public final int maxHeight = Settings.WORLD_MAP_MAX_HEIGHT;

    private final HashMap<Coordinates, Entity> entitiesMap = new HashMap<>();

    private WorldMap() {
    }

    public static WorldMap getInstance() {
        return INSTANCE;
    }

    public void putEntity(Coordinates coordinates, Entity entity) {
        validateCoordinates(coordinates);
        entity.coordinates = coordinates;
        entitiesMap.put(coordinates, entity);
    }

    public Optional<Entity> getCellContents(Coordinates coordinates) {
        if (coordinates == null)
            return Optional.empty();
        validateCoordinates(coordinates);
        return Optional.ofNullable(entitiesMap.get(coordinates));
    }

    public void clearCell(Coordinates coordinates) {
        validateCoordinates(coordinates);
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

    private void validateCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Coordinates cannot be null");
        }
        if (coordinates.row() > maxHeight || coordinates.column() > maxWidth) {
            throw new IllegalArgumentException("Coordinates are out of bounds");
        }
    }
}