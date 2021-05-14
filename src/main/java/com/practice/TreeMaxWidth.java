package com.company;

import java.util.*;
public class TreeMaxWidth {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello World");
        CustomTree tree = new CustomTree();
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(8);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(9);

        tree.add(node1);
        tree.add(node2);
        tree.add(node3);
        tree.add(node4);
        tree.add(node5);
        tree.add(node6);
        tree.add(node7);
        tree.printTree();
        System.out.println("Max Width: " + tree.maxWidth());
    }
}

class CustomTree {
    TreeNode root;
    public CustomTree() {}

    int maxWidth() {
        TreeNode left = root.left;
        TreeNode right = root.right;
        System.out.println("root left: " + left.value);
        System.out.println("root right: " + right.value);
        return rightWidth(right) - leftWidth(left);

    }

    int rightWidth(TreeNode node) {
        if(node == null) return 0;
        return Math.max(rightWidth(node.left) - 1, rightWidth(node.right) + 1);
    }

    int leftWidth(TreeNode node) {
        if (node == null) return 0;
        return Math.min(leftWidth(node.left) - 1, leftWidth(node.right) + 1);
    }

    public void add(TreeNode node) {
        if (root == null ) {
            root = node;
            return;
        }

        TreeNode currNode = root;
        while(currNode != null) {
            if(currNode.value > node.value) {
                if (currNode.left == null) {
                    currNode.left = node;
                    break;
                }

                currNode = currNode.left;
            } else {
                if (currNode.right == null) {
                    currNode.right = node;
                    break;
                }

                currNode = currNode.right;
            }
        }
    }

    public void printTree() {
        TreeNode curr = root;
        LinkedList<TreeNode> queue = new LinkedList();
        queue.add(curr);
        while (queue.size() > 0) {
            curr = queue.getFirst();
            queue.removeFirst();
            System.out.println("value: " + curr.value);
            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }
    }
}

class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {}

    TreeNode(int value) {
        this.value = value;
    }
}
