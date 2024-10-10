package ru.vladshi.javalearning;

import ru.vladshi.javalearning.entity.Entity;

import java.util.Optional;

public class WorldMapConsoleRenderer {

    public static final String SPRITE_FOR_EMPTY_CELL = "  . "; // ☐ ◻ ◌ ❏ ❐ ✛ ▫

    public void render(WorldMap worldMap) {
        for (int row = 0; row < WorldMap.MAX_HEIGHT; row++) {
            for (int column = 0; column < WorldMap.MAX_WIDTH; column++) {
                Optional<Entity> cell = worldMap.getCellContents(new Coordinates(row, column));
                System.out.print(cell.isEmpty() ? SPRITE_FOR_EMPTY_CELL : cell.get().getSprite());
            }
            System.out.println();
        }
    }
}
