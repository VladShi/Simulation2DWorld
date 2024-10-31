package ru.vladshi.javalearning.renderer;

public enum Sprite {
    PREDATOR("\uD83E\uDD81"),
    HERBIVORE("\uD83E\uDD8C"),
    GRASS("\uD83E\uDD6C"),
    ROCK("\uD83D\uDDFB"),
    TREE("\uD83C\uDF34"),
    BACKGROUND("\033[48;5;143m"),
    GROUND("\uD83D\uDFEB"),
    END_OF_ROW("\u001B[0m");

    private final String emojiCode;

    Sprite(String signature) {
        this.emojiCode = signature;
    }

    @Override
    public String toString() {
        return emojiCode;
    }
}
