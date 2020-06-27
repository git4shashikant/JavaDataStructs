package com.practice;

/*
* o o o o x
* x o x x x
* x o x o x
* x x x x x
* o o x x x
*
* Find the maximum square available in the above matrix surrounded by any char c.
* For example here for char x the answer is 3.
*
* Solution: create a new matrix showing number of x counted from left to right as width and number of x
*           counted from top to bottom as height in the cell. So, the resulting matrix would be:
* (0, 0) (0, 0) (0, 0) (0, 0) (1, 1)
* (1, 1) (0, 0) (1, 1) (2, 1) (3, 2)
* (1, 2) (0, 0) (1, 2) (0, 0) (1, 3)
* (1, 3) (2, 1) (3, 3) (4, 1) (5, 4)
* (0, 0) (0, 0) (1, 4) (2, 2) (3, 5)
*
* Now, iterate over the dimensions cells from bottom right to left
* 1. Calculate min of width and height which can form the square:
*    int side = Math.min(dimensions[i][j].getWidth(), dimensions[i][j].getHeight())
* 2. the steps required to get to the left and top node cell of the square will be
*    int steps = side -1
* 2. Check dimensions of the left and top cell by using above steps.
* 3. if (dimensions[i][j-steps].getHeight() >= side && dimensions[i-steps][j].getWidth() >= side) then
*    we found max possible square at this cell. else iterate for steps -1 un till you reach the same node.
*    if same node then its 1.
* 4. Do it for all the cells and store the maxSideLength on each iteration to get the final answer.
* */
public class MaxSquareSurroundedByX {

    public static void main(String[] args) {
        System.out.println(maxSquareSurroundedByX(new char[][] {
                new char[]{'o', 'o', 'o', 'o', 'x'},
                new char[]{'x', 'o', 'x', 'x', 'x'},
                new char[]{'x', 'o', 'x', 'o', 'x'},
                new char[]{'x', 'x', 'x', 'x', 'x'},
                new char[]{'o', 'o', 'x', 'x', 'x'}
        }, 'x'));
    }

    public static int maxSquareSurroundedByX(char[][] matrix, char c) {
        Dimension[][] dimensions = new Dimension[matrix.length][5];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0 && j == 0) {
                    if (matrix[i][j] == c) {
                        dimensions[i][j] = new Dimension(1, 1);
                    } else {
                        dimensions[i][j] = new Dimension(0, 0);
                    }
                } else if (i == 0) {
                    if (matrix[i][j] == c) {
                        dimensions[i][j] = new Dimension(dimensions[i][j-1].getWidth() + 1, 1);
                    } else {
                        dimensions[i][j] = new Dimension(0, 0);
                    }
                } else if (j == 0) {
                    if (matrix[i][j] == c) {
                        dimensions[i][j] = new Dimension(1, dimensions[i-1][j].getHeight() + 1);
                    } else {
                        dimensions[i][j] = new Dimension(0, 0);
                    }
                } else if (matrix[i][j] == c) {
                    Dimension dimension = new Dimension(1, 1);
                    if (matrix[i][j-1] == c) {
                        dimension.setWidth(dimension.getWidth() + dimensions[i][j-1].getWidth());
                    }

                    if (matrix[i-1][j] == c) {
                        dimension.setHeight(dimension.getHeight() + dimensions[i-1][j].getHeight());
                    }

                    dimensions[i][j] = dimension;
                } else {
                    dimensions[i][j] = new Dimension(0, 0);
                }
            }
        }

        int maxSideLength = 0;
        for (int i = matrix.length - 1; i >=0; i--) {
            for (int j = matrix[i].length - 1; j>=0; j--) {
                int side = Math.min(dimensions[i][j].getWidth(), dimensions[i][j].getHeight());
                int steps = side -1;
                while(steps >= 0) {
                    if (dimensions[i][j-steps].getHeight() >= side && dimensions[i-steps][j].getWidth() >= side) {
                        if (side > maxSideLength) {
                            maxSideLength = side;
                            break;
                        }
                    }

                    side--;
                    steps--;
                }
            }
        }

        return maxSideLength;
    }

    static class Dimension {
        private int width;
        private int height;

        public Dimension(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
