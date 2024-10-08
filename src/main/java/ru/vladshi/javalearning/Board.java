package ru.vladshi.javalearning;

import ru.vladshi.javalearning.entity.Entity;

import java.util.HashMap;

public class Board {

    public final int MAX_WIDTH = 10;
    public final int MAX_HEIGHT = 10;
    HashMap<Coordinates, Entity> entitiesMap = new HashMap<>();

}