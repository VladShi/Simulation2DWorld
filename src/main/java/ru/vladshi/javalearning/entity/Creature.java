package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.Coordinates;
import ru.vladshi.javalearning.util.PathFinder;

import java.util.Deque;
import java.util.Optional;

abstract public class Creature extends Entity {

    protected Deque<Coordinates> path = null;
    // TODO зарефакторить что бы path было поле final и инициализировалось пустым Deque, мы бы его передавали в
    //  PathFinder, и там внутри сразу при обходе массива с номером ячейки из которой пришли клали бы сразу в Deque
    //  вместо создания LinkedList и реверса на нем! А при пропаже цели просто обнуляли бы Deque

    public abstract void makeMove();

    abstract public int getSpeed();

    abstract public Class<? extends Entity> getClassOfTarget();

    public void goToTarget(int stepsCounter) {
        Optional<Entity> target = this.getTarget();
        if (target.isEmpty())
            return;
        while(stepsCounter > 0) {
            Optional<Entity> nextCell = worldMap.getCellContents(this.path.peekFirst());
            if (nextCell.isEmpty()) {
                takeStepToTarget();
                stepsCounter--;
            } else if(this.path.size() == 1){
                break;
            } else {
                this.path = PathFinder.findPath(worldMap, this.coordinates, this.getClassOfTarget());
            }
        }
    }

    private Deque<Coordinates> getPathToTarget() {
        if (this.path == null) {
            this.path = PathFinder.findPath(worldMap, this.coordinates, this.getClassOfTarget());
        }
        return this.path;
    }

    private Optional<Entity> getTarget() {
        Deque<Coordinates> pathToTarget = this.getPathToTarget();
        if (pathToTarget == null) {
            return Optional.empty();
        }
        Optional<Entity> target = worldMap.getCellContents(pathToTarget.peekLast());
        if (target.isPresent() && target.get().getClass().equals(this.getClassOfTarget())) {
            return target;
        }
        this.path = null;
        return getTarget();
    }

    private void takeStepToTarget() {
            worldMap.clearCell((this.coordinates));
            worldMap.putEntity(this.path.pollFirst(), this);
    }
}
