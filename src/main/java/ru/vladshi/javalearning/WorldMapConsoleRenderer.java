package ru.vladshi.javalearning;

public class WorldMapConsoleRenderer {


    public static final String SPRITE_FOR_EMPTY_CELL = " * ";

    public void render(WorldMap worldMap) {
        for (int row = 0; row < WorldMap.MAX_HEIGHT; row++) {
            StringBuilder line = new StringBuilder();
            for (int column = 0; column < WorldMap.MAX_WIDTH; column++) {
                if (worldMap.isCellEmpty(new Coordinates(row, column))) {
                    line.append(SPRITE_FOR_EMPTY_CELL);
                }
                else line.append("   ");
            }
            System.out.println(line);
        }
    }
}
