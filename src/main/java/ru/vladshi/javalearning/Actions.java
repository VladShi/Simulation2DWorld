package ru.vladshi.javalearning;

import ru.vladshi.javalearning.config.Settings;
import ru.vladshi.javalearning.entity.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Actions {

    public static void initWorldMap() {
        /*
        Да читается и выглядит ужасно =\ . Но изначальная альтернатива это 5 одинаковых циклов(по количеству
        сущностей), в которых создается нужное количество экземпляров. Что тоже выглядело не очень (DRY). К тому же
        при таком подходе расширение сущностей кажется более легким, достаточно просто добавить в хеш-таблицу
        в Settings. Ничего другого не смог придумать
         */
        for (Map.Entry<Class<? extends Entity>, Integer> entry : Settings.NUMBER_OF_ENTITIES.entrySet()) {
            Class<? extends Entity> entityClass = entry.getKey();
            for (int i = 0; i < entry.getValue(); i++) {
                try {
                    WorldMap.getInstance().putEntityToRandomEmptyCell(entityClass.getConstructor().newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void turnActions() {
        int numberOfHerbivore = 0;
        int numberOfGrass = 0;
        Entity tree = null;
        List<Creature> creatures = new ArrayList<>();
        for (Entity entity: WorldMap.getInstance().getEntitiesMap().values()) {
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
                tree = entity;
            }
        }
        for (Creature creature: creatures) {
            creature.makeMove();
        }
        if (numberOfHerbivore < Settings.NUMBER_OF_ENTITIES.get(Herbivore.class)
                    && ThreadLocalRandom.current().nextDouble() < 0.9) {
            WorldMap.getInstance().putEntityToRandomEmptyCell(new Herbivore());
        }
        if (numberOfGrass < Settings.NUMBER_OF_ENTITIES.get(Grass.class)
                    && ThreadLocalRandom.current().nextDouble() < 0.9) {
            WorldMap.getInstance().putEntityToRandomEmptyCell(new Grass());
        }
        if (tree != null && ThreadLocalRandom.current().nextDouble() < 0.05) {
            WorldMap.getInstance().clearCell(tree.coordinates);
            WorldMap.getInstance().putEntityToRandomEmptyCell(new Tree());
        }
    }
}
