package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.config.Settings;

public class Herbivore extends Creature {

    private final String sprite = " \uD83E\uDD8C ";
    public int healthPoints = Settings.HERBIVORE_HEALTH_POINTS;
    public int speed = Settings.HERBIVORE_SPEED;

    @Override
    public void makeMove() {
    }

    @Override
    public String getSprite() {
        return sprite;
    }
}
