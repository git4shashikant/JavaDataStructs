package com.datastructure.tree;

public class BinarySearchTree {

	private BSTNode rootNode;
	
	public void addNode(int value) {
		
		BSTNode newNode = new BSTNode();
		newNode.setValue(value);
		
		if (this.rootNode==null) {
			rootNode=newNode;
		} else {
			BSTNode currNode = this.rootNode;
			while (true) {
				if (value<currNode.getValue()) {
					if (currNode.getLeftNode()==null){
						currNode.setLeftNode(newNode);	
						break;
					} else {
						currNode = currNode.getLeftNode();
					}
				} else if (value>currNode.getValue()) {
					if (currNode.getRightNode()==null){
						currNode.setRightNode(newNode);	
						break;
					} else {
						currNode = currNode.getRightNode();
					}
				} else {
					System.out.println("This value already exists in the tree.");
					break;
				}
			}
		}
	}
	
	public BSTNode findNode(int value) {
		BSTNode currNode = this.rootNode;
		while (currNode!=null){
			if (value<currNode.getValue()) {
				currNode = currNode.getLeftNode();
			} else if (value>currNode.getValue()){
				currNode = currNode.getRightNode();
			} else {
				break;
			}
		}
		return currNode;
	}

	public int treeHeight() {
		return nodeHeight(this.rootNode);
	}

	public int nodeHeight(BSTNode node) {
		if (node == null) {
			return 0;
		}

		int leftNodeHeight = 0;
		if (node.getLeftNode() != null) {
			leftNodeHeight = nodeHeight(node.getLeftNode());
		}

		int rightNodeHeight = 0;
		if (node.getRightNode() != null) {
			rightNodeHeight = nodeHeight(node.getRightNode());
		}

		return Math.max(leftNodeHeight, rightNodeHeight) + 1;
	}

	public boolean isBalanced() {
		return height(rootNode) != -1;
	}

	static int height(BSTNode rootNode) {

		if (rootNode == null) {
			return 0;
		}

		BSTNode left = rootNode.getLeftNode();
		BSTNode right = rootNode.getRightNode();

		int leftHeight = height(left);
		int rightHeight = height(right);

		if (leftHeight == -1 || rightHeight == -1) {
			return -1;
		}

		if (Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		}

		return Math.max(leftHeight, rightHeight) + 1;
	}

	public void balanceTree() {
		balanceNode(rootNode, null);
	}

	public void balanceNode(BSTNode node, BSTNode parentNode) {
		BSTNode leftNode = node.getLeftNode();
		BSTNode rightNode = node.getRightNode();
		int leftHeight = nodeHeight(leftNode);
		int rightHeight = nodeHeight(rightNode);

		if (leftHeight > rightHeight) {
			BSTNode rightOfLeft = leftNode.getRightNode();
			leftNode.setRightNode(parentNode);
			addNode(rightOfLeft.getValue());

			if (parentNode == null) {
				rootNode = leftNode;
			} else {
				parentNode.setLeftNode(leftNode);
			}
		} else if (rightHeight > leftHeight) {
			addNode(node.getValue());

			if (parentNode == null) {
				rootNode = rightNode;
			} else {
				parentNode.setRightNode(rightNode);
			}
		} else {
			System.out.println("Tree is already sorted.");
		}
	}
	
	public static void main (String [] args) {
		
		BinarySearchTree bst = new BinarySearchTree();
		bst.addNode(23);
		bst.addNode(12);
		bst.addNode(18);
		bst.addNode(78);
		bst.addNode(26);
		bst.addNode(69);
		bst.addNode(45);
		
		System.out.println(bst.findNode(23));
		System.out.println(bst.findNode(15));
		System.out.println(bst.findNode(69));
		System.out.println(bst.findNode(22));
		System.out.println(bst.treeHeight());
		// bst.addNodeRec(22);

		System.out.println("tree is balanced: " + bst.isBalanced());
		bst.balanceTree();
		System.out.println("tree is balanced: " + bst.isBalanced());
	}

	static class BSTNode {

		private int value;
		private BSTNode leftNode;
		private BSTNode rightNode;

		BSTNode(){}
		BSTNode(int value){
			this.value=value;
		}

		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public BSTNode getLeftNode() {
			return leftNode;
		}
		public void setLeftNode(BSTNode leftNode) {
			this.leftNode = leftNode;
		}
		public BSTNode getRightNode() {
			return rightNode;
		}
		public void setRightNode(BSTNode rightNode) {
			this.rightNode = rightNode;
		}
	}
}