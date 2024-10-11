package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.config.Settings;

public class Herbivore extends Creature {

    private static final String sprite = " \uD83E\uDD8C ";
    private static final int speed = Settings.HERBIVORE_SPEED;
    public int healthPoints = Settings.HERBIVORE_HEALTH_POINTS;

    @Override
    public void makeMove() {
    }

    @Override
    public String getSprite() {
        return sprite;
    }

    @Override
    public int getSpeed() {
        return speed;
    }
}
