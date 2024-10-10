package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.Coordinates;
import ru.vladshi.javalearning.config.Settings;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

abstract public class Creature extends Entity {
    int speed;
    int healthPoints;
    private Entity target;

    public abstract void makeMove();

    public Queue<Coordinates> findPath(Class<? extends Entity> classOfTarget) {
        // проверяем есть ли на карте мира Entity до которого мы ищем путь
        boolean mapHasTargetClassEntity = false;
        for (Entity entity : this.worldMap.entitiesMap.values())
            if (entity.getClass().equals(classOfTarget)) {
                mapHasTargetClassEntity = true;
                break;
            }
        if (!mapHasTargetClassEntity)
            return new LinkedList<Coordinates>();

        // визуализация различных алгоритмов https://qiao.github.io/PathFinding.js/visual/
        // тут использовал поиск в ширину Breadth-First-Search, самый простой в реализации, но не самый эффективный
        Coordinates start = this.coordinates;
        int maxDistance = 1000;
        int rows = Settings.WORLD_MAP_MAX_HEIGHT;
        int cols = Settings.WORLD_MAP_MAX_WIDTH;
        // Двумерный массив размером как наше поле мира где будем хранить значение дистанции до начальной ячейки:
//        (Пока что в дистанциях кажется нет необходимости, поэтому закомментим)
//        int[][] distances = new int[rows][cols];
//        for (int row = 0; row < rows; row++) {
//            for (int col = 0; col < cols; col++) {
//                distances[row][col] = maxDistance; // заполняем большим числом, потому что мы не знаем пока расстояний
//            }
//        }
        // Двумерный массив в ячейках которого мы храним информацию из какой ячейки мы в эту ячейку пришли при обходе
        Coordinates[][] path = new Coordinates[rows][cols];
        // Двумерный массив для хранения уже проверенных ячеек(координат):
        boolean[][] visited = new boolean[rows][cols];

        // Какую дельту необходимо прибавить к координате, что бы проверить соседние ячейки (верх, низ, лево, право)
        int[] deltaRow = {-1, 1, 0, 0}; // deltaRow
        int[] deltaCol = {0, 0, -1, 1}; // deltaColumn

        // Очередь ячеек(координат) для обхода
        Queue<Coordinates> queue = new LinkedList<>();

        // Расстояние до начальной ячейки равно ноль, добавляем стартовую ячейку в уже проверенные и кладем в очередь
//        distances[start.row()][start.column()] = 0; (пока в дистанциях нет необходимости)
        visited[start.row()][start.column()] = true;
        queue.add(start);

        Coordinates target = null;
        // Алгоритм будет искать цель пока не пройдется по всем ячейкам и очередь опустеет или пока не прервём брэйком
        outerLoop:
        while (!queue.isEmpty()) {
            // вынимаем из очереди ячейку:
            Coordinates currentCoordinates = queue.poll();
            int curRow = currentCoordinates.row();
            int curColumn = currentCoordinates.column();
            for (int i = 0; i < 4; i++) {  // Проверяем четырех соседей (верх, низ, лево, право)
                int nRow = curRow + deltaRow[i];
                int nCol = curColumn + deltaCol[i]; // neighbourColumnCoordinate
                // Проверяем что координаты соседей не выходят за пределы поля и что мы их ещё не проверяли:
                if ((nRow >= 0 && nRow < rows) && (nCol >= 0 && nCol < cols) && !visited[nRow][nCol]) {
                    visited[nRow][nCol] = true;
                    Coordinates neighbourCellCoordinates = new Coordinates(nRow, nCol);
                    path[nRow][nCol] = currentCoordinates;
//                    distances[nRow][nCol] = distances[curRow][curColumn] + 1; (пока в дистанциях нет необходимости)
                    Optional<Entity> cell = this.worldMap.getCellContents(neighbourCellCoordinates);
                    if (cell.isEmpty()) {
                        queue.add(neighbourCellCoordinates); // добавляем в очередь на проверку
                    } else if (cell.get().getClass().equals(classOfTarget)) {
                        target = neighbourCellCoordinates;
                        break outerLoop;
                    }
                }
            }
        }
        // Вычленение пути, начинаем с последней точки и идем до стартовой
        Coordinates cursor = target;
        LinkedList<Coordinates> pathList = new LinkedList<>();
        while (!cursor.equals(start)) {
            pathList.add(cursor);
            cursor = path[cursor.row()][cursor.column()];
        }
        pathList = pathList.reversed();
        pathList.removeLast();
        return pathList;
//        int distanceToTarget = distances[target.row()][target.column()];
//        return distanceToTarget; (пока в дистанциях нет необходимости)
    }

    public Optional<Entity> getTarget() {
        return Optional.ofNullable(target);
    }
}
