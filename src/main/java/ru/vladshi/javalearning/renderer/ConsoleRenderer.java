package ru.vladshi.javalearning.renderer;

import ru.vladshi.javalearning.Coordinates;
import ru.vladshi.javalearning.WorldMap;
import ru.vladshi.javalearning.entity.Entity;

import java.util.Optional;

public class ConsoleRenderer {

    public void render(WorldMap worldMap) {
        for (int row = 0; row < worldMap.MAX_HEIGHT; row++) {
            for (int column = 0; column < worldMap.MAX_WIDTH; column++) {
                Coordinates coordinates = new Coordinates(row, column);
                renderCell(worldMap, coordinates);
            }
            System.out.println(Sprite.END_OF_ROW);
        }
    }

    private void renderCell(WorldMap map, Coordinates coordinates) {
        Optional<Entity> cell = map.getCellContents(coordinates);
        System.out.print(Sprite.BACKGROUND + " ");
        System.out.print(cell.isPresent() ? getSpriteForEntity(cell.get()) : Sprite.GROUND);
    }

    private String getSpriteForEntity(Entity entity) {
        return switch (entity.getClass().getSimpleName()) {
            case "Predator" -> Sprite.PREDATOR.toString();
            case "Herbivore" -> Sprite.HERBIVORE.toString();
            case "Grass" -> Sprite.GRASS.toString();
            case "Rock" -> Sprite.ROCK.toString();
            case "Tree" -> Sprite.TREE.toString();
            default -> throw new IllegalStateException("Unexpected value: " + entity.getClass().getSimpleName());
        };
    }
}
