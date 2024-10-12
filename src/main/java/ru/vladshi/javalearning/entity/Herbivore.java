package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.config.Settings;

public class Herbivore extends Creature {

    private static final String sprite = " \uD83E\uDD8C ";
    private static final int speed = Settings.HERBIVORE_SPEED;
    private static final Class<? extends Entity> classOfTarget = Grass.class;
    public int healthPoints = Settings.HERBIVORE_HEALTH_POINTS;

    @Override
    public void makeMove() {
        goToTarget(this.getSpeed());
//        if (nextCell.equals(target)) {   // if (this.path.size() == 1)
//            // TODO если находимся рядом с целью, то укусить и если уничтожили, занять клетку
//            //  видимо нужны будут методы swapCells(), removeEntity() в WorldMap
//            System.out.println("дошли до цели");
//            return;
//        }
    }

    @Override
    public String getSprite() {
        return sprite;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public Class<? extends Entity> getClassOfTarget() {
        return classOfTarget;
    }
}
