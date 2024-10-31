package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.config.Settings;

import java.util.Optional;

public class Herbivore extends Creature implements CanBeAttacked {

    private static final int speed = Settings.HERBIVORE_SPEED;
    private static final Class<? extends Entity> classOfTarget = Grass.class;
    private int healthPoints = Settings.HERBIVORE_HEALTH_POINTS;

    @Override
    public void makeMove() {
        if (this.healthPoints <= 0) {
            return;
        }
        Optional<Entity> nextCell = worldMap.getCellContents(this.path.peekFirst());
        if (nextCell.isPresent() && nextCell.get().getClass().equals(classOfTarget)) {
            worldMap.clearCell(this.coordinates);
            worldMap.putEntity(nextCell.get().coordinates, this);
            return;
        }
        goToTarget(this.getSpeed());
    }

    @Override
    public void takeDamage(int damage) {
        this.healthPoints -= damage;
    }

    @Override
    public boolean isOutOfHealthPoints() {
        return this.healthPoints <= 0;
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
