package ru.vladshi.javalearning.entity;

public interface CanBeAttacked {

    void takeDamage(int damage);

    boolean isDead();
}
