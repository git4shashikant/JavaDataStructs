package com.practice;

import java.util.*;

/*
* find number of possible rectangles from an array of coordinates.
* Only applicable for orthogonal coordinates (parallel to x and y axis).
* */
public class NumberOfRectangles {

    public static void main(String[] args) {
        NumberOfRectangles nor = new NumberOfRectangles();
        System.out.println(nor.numberOfRectangles(new Point[]{
                new Point(2, 3),
                new Point(2, 4),
                new Point(2, 5),
                new Point(3, 3),
                new Point(3, 4),
                new Point(3, 5),
                new Point(4, 3),
                new Point(4, 4),
                new Point(4, 5)
        }));
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
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Point)) {
                return false;
            }

            Point objPoint = (Point) obj;
            if (objPoint.getX() != getX() || objPoint.getY() != getY()) {
                return false;
            }

            return true;
        }

    }

    public int numberOfRectangles(Point[] points) {
        Map<Integer, List<Point>> yPairs = new HashMap();
        for (int i = 0; i < points.length; i++) {
            int x = points[i].getX();
            int y = points[i].getY();
            for (int j = i + 1; j < points.length; j++) {
                if (x == points[j].getX()) {
                    if (yPairs.get(x) == null) {
                        yPairs.put(x, new ArrayList());
                    }
                    yPairs.get(x).add(new Point(y, points[j].getY()));
                }
            }
        }

        Object[] keys = yPairs.keySet().toArray();
        int numberOfRectangles = 0;
        for (int i = 0; i < keys.length; i++) {
            for (int j = i + 1; j < keys.length; j++) {
                List<Point> x1Pairs = yPairs.get(keys[i]);
                List<Point> x2Pairs = yPairs.get(keys[j]);
                numberOfRectangles += commonPairs(x1Pairs, x2Pairs);
            }
        }

        return numberOfRectangles;
    }

    public int commonPairs(List<Point> list1, List<Point> list2) {
        int count = 0;
        for (Point point1 : list1) {
            for (Point point2 : list2) {
                if (point1.equals(point2)) {
                    count++;
                }
            }
        }

        return count;
    }

}
