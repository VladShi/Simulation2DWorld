package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.Coordinates;
import ru.vladshi.javalearning.WorldMap;

abstract public class Entity {

    public static WorldMap worldMap = WorldMap.getInstance();
    public Coordinates coordinates;

    public abstract String getSprite();
}