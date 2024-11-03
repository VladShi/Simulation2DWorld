package ru.vladshi.javalearning;

import ru.vladshi.javalearning.config.Settings;
import ru.vladshi.javalearning.entity.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Actions {

    private static final Map<Class<? extends Entity>, Integer> compositionOfEntities = new HashMap<>();

    public static void initWorldMap() {

        WorldMap worldMap = WorldMap.getInstance();

        compositionOfEntities.put(Rock.class, Settings.NUMBER_OF_ROCKS);
        compositionOfEntities.put(Tree.class, Settings.NUMBER_OF_TREES);
        compositionOfEntities.put(Grass.class, Settings.NUMBER_OF_GRASSES);
        compositionOfEntities.put(Herbivore.class, Settings.NUMBER_OF_HERBIVORES);
        compositionOfEntities.put(Predator.class, Settings.NUMBER_OF_PREDATORS);

        randomlyFillMapWithEntities(worldMap, compositionOfEntities);
    }

    public static void turnActions() {

        WorldMap worldMap = WorldMap.getInstance();

        int numberOfHerbivore = 0;
        int numberOfGrass = 0;
        Entity randomTree = null;
        List<Creature> creatures = new ArrayList<>();
        for (Entity entity: worldMap.getEntitiesMap().values()) {
            if (entity instanceof Creature creature) {
                creatures.add(creature);
            }
            if (entity instanceof Herbivore) {
                numberOfHerbivore++;
            }
            if (entity instanceof Grass) {
                numberOfGrass++;
            }
            if (entity instanceof Tree && ThreadLocalRandom.current().nextBoolean()) {
                randomTree = entity;
            }
        }

        for (Creature creature: creatures) {
            creature.makeMove();
        }

        if (numberOfHerbivore < compositionOfEntities.get(Herbivore.class)
                    && ThreadLocalRandom.current().nextDouble() < 0.8) {
            putEntityToRandomFreeCoordinates(worldMap, new Herbivore());
        }
        if (numberOfGrass < compositionOfEntities.get(Grass.class)
                    && ThreadLocalRandom.current().nextDouble() < 0.8) {
            putEntityToRandomFreeCoordinates(worldMap, new Grass());
        }
        if (randomTree != null && ThreadLocalRandom.current().nextDouble() < 0.03) {
            WorldMap.getInstance().clearCell(randomTree.coordinates);
            putEntityToRandomFreeCoordinates(worldMap, new Tree());
        }
    }

    private static void randomlyFillMapWithEntities (WorldMap worldMap,
                                                     Map<Class<? extends Entity>, Integer> compositionOfEntities) {
        for (Map.Entry<Class<? extends Entity>, Integer> entry : compositionOfEntities.entrySet()) {
            Class<? extends Entity> entityClass = entry.getKey();
            int quantity = entry.getValue();
            for (int i = 0; i < quantity; i++) {
                try {
                    Entity entity = entityClass.getConstructor().newInstance();
                    putEntityToRandomFreeCoordinates(worldMap, entity);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static void putEntityToRandomFreeCoordinates(WorldMap map, Entity entity) {
        boolean isMapFull = map.getEntitiesMap().size() >= map.maxWidth * map.maxHeight;
        if (isMapFull) {
            throw new RuntimeException("Map has already been full");
        }
        while (true) {
            Coordinates randomCoordinates =  new Coordinates(
                    ThreadLocalRandom.current().nextInt(0, map.maxHeight),
                    ThreadLocalRandom.current().nextInt(0, map.maxWidth)
            );
            if (map.getCellContents(randomCoordinates).isEmpty()) {
                map.putEntity(randomCoordinates, entity);
                break;
            }
        }
    }
}
