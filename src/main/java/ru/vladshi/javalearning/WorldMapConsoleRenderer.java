package ru.vladshi.javalearning;

public class WorldMapConsoleRenderer {


    public static final String SPRITE_FOR_EMPTY_CELL = "  . "; // ☐ ◻ ◌ ❏ ❐ ✛ ▫

    public void render(WorldMap worldMap) {
        for (int row = 0; row < WorldMap.MAX_HEIGHT; row++) {
            for (int column = 0; column < WorldMap.MAX_WIDTH; column++) {
                Coordinates coordinates = new Coordinates(row, column);
                if (worldMap.isCellEmpty(coordinates)) {
                    System.out.print(SPRITE_FOR_EMPTY_CELL);
                } else {
                    System.out.print(worldMap.getEntity(coordinates).getSprite());
                }
            }
            System.out.println();
        }
    }
}
