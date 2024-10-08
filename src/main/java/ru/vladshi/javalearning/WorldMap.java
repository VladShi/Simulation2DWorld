package ru.vladshi.javalearning;

import ru.vladshi.javalearning.entity.Entity;

import java.util.HashMap;

public class WorldMap {

    public static final int MAX_WIDTH = 10;
    public static final int MAX_HEIGHT = 10;

    HashMap<Coordinates, Entity> entitiesMap = new HashMap<>();

    public boolean isCellEmpty(Coordinates coordinates) {
        return !entitiesMap.containsKey(coordinates);
    }
}