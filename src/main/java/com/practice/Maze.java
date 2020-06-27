package com.practice;

import java.util.*;

/*
* X Y X X Y X X X E
* Y Y X X Y X X X Y
* Y X X X Y X X Y Y
* Y X X X Y X X Y X
* Y Y Y Y Y Y Y Y X
* S Y X X X X X X X
* 
* Above is a matrix defining the possible ways to move if the cell contains Y.
* Find the path possible to reach from S(start) to E(end).
* */
public class Maze {

    public static void main(String[] args) {

    }

    public static Point[] path(char[][] maze, Point start, Point end, List<Point> path) {
        int rows = maze.length;
        int columns = maze[0].length; //assuming all rows have same number of columns

        path.add(start);
        Point currentPoint = start;
        while(currentPoint != end) {
            int x = currentPoint.getX();
            int y = currentPoint.getY();

            if(canMoveUp(x)) {
                currentPoint = new Point(x-1, y);
                path(maze, currentPoint, end, path);
            }
        }

        return null;
    }

    private static boolean canMoveUp(int x) {
        return x != 0;
    }

    private static boolean canMoveDown(int x, int rows) {
        return x != rows - 1;
    }

    private static boolean canMoveLeft(int y) {
        return y != 0;
    }

    private static boolean canMoveRight(int y, int columns) {
        return y != columns -1;
    }

    static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return getX() == point.getX() &&
                    getY() == point.getY();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getX(), getY());
        }
    }
}
