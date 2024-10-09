package ru.vladshi.javalearning.entity;

import ru.vladshi.javalearning.Coordinates;
import ru.vladshi.javalearning.config.Settings;

import java.util.LinkedList;
import java.util.Queue;

abstract public class Creature extends Entity {
    int speed;
    int healthPoints;

    public abstract void makeMove();

    public int findPath(Coordinates target) {
        // визуализация различных алгоритмов https://qiao.github.io/PathFinding.js/visual/
        // тут использовал поиск в ширину Breadth-First-Search
        Coordinates start = this.coordinates;
        int maxDistance = 1000;
        int rows = Settings.WORLD_MAP_MAX_HEIGHT;
        int cols = Settings.WORLD_MAP_MAX_WIDTH;
        // Двумерный массив размером как наше поле мира где будем хранить значение дистанции до начальной ячейки
        int[][] distances = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                distances[row][col] = maxDistance; // заполняем большим числом, потому что мы не знаем пока расстояний
            }
        }
        // Двумерный массив для хранения уже проверенных ячеек(координат):
        boolean[][] visited = new boolean[rows][cols];

        // Какую дельту необходимо прибавить к координате, что бы проверить соседние ячейки (верх, низ, лево, право)
        int[] deltaRow = {-1, 1, 0, 0}; // deltaRow
        int[] deltaCol = {0, 0, -1, 1}; // deltaColumn

        // Очередь ячеек(координат) для обхода
        Queue<Coordinates> queue = new LinkedList<>();

        // Расстояние до начальной ячейки равно ноль, добавляем стартовую ячейку в уже проверенные и кладем в очередь
        distances[start.row()][start.column()] = 0;
        visited[start.row()][start.column()] = true;
        queue.add(start);

        // Алгоритм будет искать цель пока не пройдется по всем ячейкам и очередь опустеет или пока не прервём брэйком
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
                    distances[nRow][nCol] = distances[curRow][curColumn] + 1;
                    visited[nRow][nCol] = true;
                    queue.add(new Coordinates(nRow, nCol)); // добавляем в очередь на проверку
                }
            }
        }
        int distanceToTarget = distances[target.row()][target.column()];
        return distanceToTarget;
    }
}
