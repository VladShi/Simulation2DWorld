package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.Coordinates;
import ru.vladshi.javalearning.util.PathFinder;

import java.util.Queue;

abstract public class Creature extends Entity {

    public Entity target = null;
    // TODO добавить поле classOfTarget каждому Creature, желательно как статическое и передать его внутри в findPath()

    public abstract void makeMove();

    public Queue<Coordinates> findPath(Class<? extends Entity> classOfTarget) {
        return PathFinder.findPath(this.worldMap, this.coordinates, classOfTarget);
    }

    abstract public int getSpeed();
}
