package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.Coordinates;
import ru.vladshi.javalearning.util.PathFinder;

import java.util.Optional;
import java.util.Queue;

abstract public class Creature extends Entity {
    int speed;
    int healthPoints;
    private Entity target;

    public abstract void makeMove();

    public Queue<Coordinates> findPath(Class<? extends Entity> classOfTarget) {
        return PathFinder.findPath(this.worldMap, this.coordinates, classOfTarget);
    }

    public Optional<Entity> getTarget() {
        return Optional.ofNullable(target);
    }
}
