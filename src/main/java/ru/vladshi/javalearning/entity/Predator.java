package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.Coordinates;
import ru.vladshi.javalearning.config.Settings;

public class Predator extends Creature {

    private final int attackPower;

    public Predator(){
        super(
            Settings.PREDATOR_SPEED,
            Herbivore.class
        );
        this.attackPower = Settings.PREDATOR_ATTACK_POWER;
    }

    @Override
    public void makeMove() {
        int stepsAvailable = this.speed;
        while (stepsAvailable >= 0) {
            if (this.hasReachedTarget()) {
                this.attackTarget();
                if (isTargetDead()) {
                    this.eatTarget();
                }
                if (stepsAvailable == 0) {
                    break;
                }
                stepsAvailable = 0;
            } else if (stepsAvailable > 0) {
                this.moveOneStepToTarget();
            }
            stepsAvailable--;
        }
    }

    private void attackTarget() {
        CanBeAttacked target = getTarget();
        target.takeDamage(attackPower);
    }

    private boolean isTargetDead() {
        CanBeAttacked target = getTarget();
            return target.isDead();
    }

    private CanBeAttacked getTarget() {
        Coordinates targetCoordinates = this.getCurrentTargetCoordinates()
                .orElseThrow(() -> new IllegalArgumentException("Target coordinate is null"));
        Entity currentTarget = worldMap.getCellContents(targetCoordinates)
                .orElseThrow(() -> new IllegalArgumentException("Target coordinate is not present on the map"));
        if (currentTarget instanceof CanBeAttacked target) {
            return target;
        }
        throw new IllegalArgumentException("This entity cannot be attacked");
    }
}
