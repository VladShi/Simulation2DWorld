package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.config.Settings;

public class Predator extends Creature{

    private final String sprite = " \uD83E\uDD81 "; // "🦁" "🐯" "🐆" "🐅"
    public int attackPower = Settings.PREDATOR_ATTACK_POWER;
    public int speed = Settings.PREDATOR_SPEED;

    @Override
    public void makeMove() {
    }

    @Override
    public String getSprite() {
        return sprite;
    }
}
