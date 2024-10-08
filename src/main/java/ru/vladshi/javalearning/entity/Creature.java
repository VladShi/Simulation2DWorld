package ru.vladshi.javalearning.entity;

abstract public class Creature extends Entity {
    int speed;
    int healthPoints;

    public abstract void makeMove();
}
