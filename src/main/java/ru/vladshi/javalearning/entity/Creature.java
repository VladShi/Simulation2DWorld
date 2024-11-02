package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.Coordinates;
import ru.vladshi.javalearning.util.PathFinder;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;

abstract public class Creature extends Entity {

    protected Deque<Coordinates> rememberedPath = new LinkedList<>();
    protected final int speed;
    protected final Class<? extends Entity> classOfTarget;

    protected Creature(int speed, Class<? extends Entity> classOfTarget) {
        this.speed = speed;
        this.classOfTarget = classOfTarget;
    }

    abstract public void makeMove();

    protected void moveOneStepToTarget() {
        this.checkAndUpdateCurrentTarget();
        if (!this.hasValidCurrentTarget()) {
            return;
        }
        if (this.getNextCell().isEmpty()) {
            this.moveOneStepByPath();
            return;
        }
        if (this.hasReachedTarget()) {
            return;
        }
        this.updatePath();
        if (this.hasNoTarget()) {
            return;
        }
        this.moveOneStepByPath();
    }

    protected Optional<Coordinates> getCurrentTargetCoordinates() {
        return Optional.ofNullable(this.rememberedPath.peekLast());
    }

    protected boolean hasReachedTarget() {
        if (this.rememberedPath.size() == 1) {
            return this.hasValidCurrentTarget();
        }
        return false;
    }

    protected void eatTarget() {
        worldMap.clearCell(this.rememberedPath.pollLast());
    }

    private boolean hasValidCurrentTarget() {
        Optional<Coordinates> targetCoordinates = this.getCurrentTargetCoordinates();
        if (targetCoordinates.isEmpty()) {
            return false;
        }
        Optional<Entity> target = worldMap.getCellContents(targetCoordinates.get());
        return target.isPresent() && target.get().getClass().equals(this.classOfTarget);
    }

    private void checkAndUpdateCurrentTarget() {
        if (!this.hasValidCurrentTarget())
            this.updatePath();
    }

    private void moveOneStepByPath() {
        worldMap.clearCell(this.coordinates);
        worldMap.putEntity(this.rememberedPath.pollFirst(), this);
    }

    private Optional<Coordinates> getNextCellCoordinates() {
        return Optional.ofNullable(this.rememberedPath.peekFirst());
    }

    private Optional<Entity> getNextCell() {
        if (this.getNextCellCoordinates().isEmpty()) {
            return Optional.empty();
        }
        return worldMap.getCellContents(this.getNextCellCoordinates().get());
    }

    private boolean hasNoTarget() {
        return getCurrentTargetCoordinates().isEmpty();
    }

    private void updatePath() {
        PathFinder.findPath(this.rememberedPath, this.coordinates, this.classOfTarget);
    }
}
