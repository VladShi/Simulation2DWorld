package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.Coordinates;
import ru.vladshi.javalearning.util.PathFinder;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;

abstract public class Creature extends Entity {

    protected final Deque<Coordinates> path = new LinkedList<>();

    public abstract void makeMove();

    abstract public int getSpeed();

    abstract public Class<? extends Entity> getClassOfTarget();

    public int goToTarget(int stepsCounter) {
        Optional<Entity> target = this.getTarget();
        if (target.isEmpty())
            return 0;
        while(stepsCounter > 0) {
            Optional<Entity> nextCell = worldMap.getCellContents(this.path.peekFirst());
            if (nextCell.isEmpty()) {
                this.takeStepToTarget();
                stepsCounter--;
            } else if(this.path.size() == 1){
                break;
            } else {
                this.path.clear();
                PathFinder.findPath(this.path ,this.coordinates, this.getClassOfTarget());
            }
        }
        return stepsCounter;
    }

    private Deque<Coordinates> getPathToTarget() {
        if (this.path.isEmpty()) {
            PathFinder.findPath(this.path, this.coordinates, this.getClassOfTarget());
        }
        return this.path;
    }

    private Optional<Entity> getTarget() {
        Deque<Coordinates> pathToTarget = this.getPathToTarget();
        if (pathToTarget.isEmpty()) {
            return Optional.empty();
        }
        Optional<Entity> target = worldMap.getCellContents(pathToTarget.peekLast());
        if (target.isPresent() && target.get().getClass().equals(this.getClassOfTarget())) {
            return target;
        }
        this.path.clear();
        return this.getTarget();
    }

    private void takeStepToTarget() {
            worldMap.clearCell((this.coordinates));
            worldMap.putEntity(this.path.pollFirst(), this);
    }
}
