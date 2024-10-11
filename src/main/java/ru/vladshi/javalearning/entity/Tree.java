package ru.vladshi.javalearning.entity;

public class Tree extends Entity {

    private static final String sprite = " \uD83C\uDF34 "; // "🌴" "🌳" "🌲"

    @Override
    public String getSprite() {
        return sprite;
    }
}
