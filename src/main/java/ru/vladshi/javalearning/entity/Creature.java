package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.Coordinates;
import ru.vladshi.javalearning.util.PathFinder;

import java.util.Deque;

abstract public class Creature extends Entity {

    protected Deque<Coordinates> path = null;

    public abstract void makeMove();

    public Deque<Coordinates> getPathToTarget() {
        if (this.path == null) {
            this.path = PathFinder.findPath(this.worldMap, this.coordinates, this.getClassOfTarget());
        }
        return this.path;
    }

    abstract public int getSpeed();

    abstract public Class<? extends Entity> getClassOfTarget();
}
