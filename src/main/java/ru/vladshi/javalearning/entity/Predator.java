package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.config.Settings;

public class Predator extends Creature{

    private static final String sprite = " \uD83E\uDD81 "; // "🦁" "🐯" "🐆" "🐅"
    private static final int speed = Settings.PREDATOR_SPEED;
    public int attackPower = Settings.PREDATOR_ATTACK_POWER;

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
