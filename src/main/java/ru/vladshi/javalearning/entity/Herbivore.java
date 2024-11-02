package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.config.Settings;

public class Herbivore extends Creature implements CanBeAttacked {

    private int healthPoints;

    public Herbivore() {
        super(
            Settings.HERBIVORE_SPEED,
            Grass.class
        );
        this.healthPoints = Settings.HERBIVORE_HEALTH_POINTS;
    }

    @Override
    public void makeMove() {
        if (this.isDead()) {
            return;
        }
        int stepsAvailable = this.speed;
        while (stepsAvailable > 0) {
            if (this.hasReachedTarget()) {
                this.eatTarget();
                stepsAvailable = 0;
            } else {
                this.moveOneStepToTarget();
            }
            stepsAvailable--;
        }
    }

    @Override
    public void takeDamage(int damage) {
        this.healthPoints -= damage;
    }

    @Override
    public boolean isDead() {
        return this.healthPoints <= 0;
    }
}
