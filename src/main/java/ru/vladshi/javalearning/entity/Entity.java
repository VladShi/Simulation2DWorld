package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.Coordinates;
import ru.vladshi.javalearning.WorldMap;

abstract public class Entity {
    public Coordinates coordinates;
    private String sprite;
    public WorldMap worldMap;

    public abstract String getSprite();
}