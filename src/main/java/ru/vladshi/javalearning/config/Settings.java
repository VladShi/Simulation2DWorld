package ru.vladshi.javalearning.config;

import ru.vladshi.javalearning.entity.*;

import java.util.Map;
import java.util.WeakHashMap;

public class Settings {
    public static final int WORLD_MAP_MAX_WIDTH = 10;
    public static final int WORLD_MAP_MAX_HEIGHT = 10;

    public static final int HERBIVORE_HEALTH_POINTS = 50;
    public static final int HERBIVORE_SPEED = 1;
    public static final int PREDATOR_SPEED = 1;
    public static final int PREDATOR_ATTACK_POWER = 20;

    public static final Map<Class<? extends Entity>, Integer> NUMBER_OF_ENTITIES;

    static {
        NUMBER_OF_ENTITIES = new WeakHashMap<Class<? extends Entity>, Integer>();
        NUMBER_OF_ENTITIES.put(Rock.class, 3);
        NUMBER_OF_ENTITIES.put(Tree.class, 2);
        NUMBER_OF_ENTITIES.put(Grass.class, 2);
        NUMBER_OF_ENTITIES.put(Herbivore.class, 2);
        NUMBER_OF_ENTITIES.put(Predator.class, 1);
    }
}
