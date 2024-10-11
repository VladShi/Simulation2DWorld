package ru.vladshi.javalearning.entity;

public class Rock extends Entity {

    private static final String sprite = " \uD83D\uDDFB "; // "🗻" "⛰️"

    @Override
    public String getSprite() {
        return sprite;
    }
}
