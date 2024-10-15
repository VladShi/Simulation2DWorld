package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.config.Settings;

import java.util.Optional;

public class Predator extends Creature {

    private static final String sprite = " \uD83E\uDD81 "; // "🦁" "🐯" "🐆" "🐅"
    private static final int speed = Settings.PREDATOR_SPEED;
    private static final Class<? extends Entity> classOfTarget = Herbivore.class;
    private static final int attackPower = Settings.PREDATOR_ATTACK_POWER;

    @Override
    public void makeMove() {
        int stepsLeft = this.getSpeed();
        while (stepsLeft >= 0) {
            Optional<Entity> nextCell = worldMap.getCellContents(this.path.peekFirst());
            if (nextCell.isPresent()
                    && nextCell.get().getClass().equals(classOfTarget)
                        && nextCell.get() instanceof CanBeAttacked target) {
                target.takeDamage(attackPower);
                if (target.isOutOfHealthPoints() && stepsLeft >= 1) {
                    worldMap.clearCell(this.coordinates);
                    worldMap.putEntity(nextCell.get().coordinates, this);
                    return;
                }
            }
            if (stepsLeft == 0) {
                return;
            }
            stepsLeft = goToTarget(stepsLeft);
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
