package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.Coordinates;

abstract public class Entity {
    public Coordinates coordinates;
    private String sprite;

    public abstract String getSprite();
}