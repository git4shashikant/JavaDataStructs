package com.datastructure.tree;

public class RedBlackTree {

    private RBTNode rootNode;

    public RedBlackTree(int value) {
        RBTNode node = new RBTNode(value);
        node.setColor(Color.BLACK);
        this.rootNode = node;
    }

    public void addNode() {

    }

    public RBTNode get(int value) {
        RBTNode currentNode = rootNode;
        boolean found = false;
        while(true) {
            int currentValue = currentNode.getValue();
            if (currentValue == value) {
                found = true;
                break;
            } else if (currentValue < value) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        return found ? currentNode : null;
    }

    public void delete(int value) throws Exception {
        RBTNode foundNode = get(value);
        if (foundNode != null) {
            if (isLeafNode(foundNode)) {

            }
        } else {
            throw new Exception("No such node found in the RB Tree.");
        }
    }

    private boolean isLeafNode(RBTNode node) {
        return node.getLeft() == null && node.getRight() == null;
    }

    public void balanceTree() {

    }

    enum Color {RED, BLACK};

    static class RBTNode {
        private int value;
        private RBTNode left;
        private RBTNode right;
        private Color color;

        public RBTNode(int value) {
            this.value = value;
            this.color = Color.RED;
        }

        public int getValue() {
            return value;
        }

        public RBTNode getLeft() {
            return left;
        }

        public void setLeft(RBTNode left) {
            this.left = left;
        }

        public RBTNode getRight() {
            return right;
        }

        public void setRight(RBTNode right) {
            this.right = right;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }

    public static void main(String[] args) {

    }
}
