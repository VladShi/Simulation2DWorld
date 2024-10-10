package ru.vladshi.javalearning;

import ru.vladshi.javalearning.config.Settings;
import ru.vladshi.javalearning.entity.Entity;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Actions {

    public static void initWorldMap(WorldMap worldMap) {
        /*
        Да читается и выглядит ужасно =\ . Но изначальная альтернатива это 5 одинаковых циклов(по количеству
        сущностей), в которых создается нужное количество экземпляров. Что тоже выглядело не очень (DRY). К тому же
        при таком подходе расширение сущностей кажется более легким, достаточно просто добавить в хеш-таблицу
        в Settings. Ничего другого не смог придумать
         */
        for (Map.Entry<Class<? extends Entity>, Integer> entry : Settings.NUMBER_OF_ENTITIES.entrySet()) {
            Class<?> entityClass = entry.getKey();
            for (int i = 0; i < entry.getValue(); i++) {
                try {
                    worldMap.putEntityToRandomEmptyCell((Entity) entityClass.getDeclaredConstructor().newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
