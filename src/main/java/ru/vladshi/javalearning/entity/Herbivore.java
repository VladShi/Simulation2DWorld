package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.Coordinates;
import ru.vladshi.javalearning.config.Settings;

import java.util.Deque;
import java.util.Optional;

public class Herbivore extends Creature {

    private static final String sprite = " \uD83E\uDD8C ";
    private static final int speed = Settings.HERBIVORE_SPEED;
    private static final Class<? extends Entity> classOfTarget = Grass.class;
    public int healthPoints = Settings.HERBIVORE_HEALTH_POINTS;

    @Override
    public void makeMove() {
        for (int i = 0; i < 2; i++) {
            Deque<Coordinates> pathToTarget = this.getPathToTarget();
            if (pathToTarget == null) {
                return;
            }
            Optional<Entity> target = worldMap.getCellContents(pathToTarget.peekLast());
            boolean targetExists = target.isPresent() && target.get().getClass().equals(classOfTarget);
            Optional<Entity> nextCell = worldMap.getCellContents(pathToTarget.peekFirst());
            if (targetExists) {
                if (nextCell.isEmpty()) {
                    worldMap.entitiesMap.remove((this.coordinates));
                    worldMap.putEntity(pathToTarget.pollFirst(), this);
                    return;
                }
                if (nextCell.equals(target)) {
                    // TODO если находимся рядом с целью, то укусить и если уничтожили, занять клетку
                    //  видимо нужны будут методы swapCells(), removeEntity() в WorldMap
                    System.out.println("дошли до цели");
                    return;
                }
            }
            this.path = null;
        }
    }

    @Override
    public String getSprite() {
        return sprite;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public Class<? extends Entity> getClassOfTarget() {
        return classOfTarget;
    }
}
