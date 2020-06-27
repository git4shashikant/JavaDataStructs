package com.datastructure.tree;

public class AVLTree {

	private AVLNode rootNode;
	private int treeDepth;
	
	public void addNode(AVLNode node){
		
		AVLNode currNode = this.rootNode;
		
		
	}

	static class AVLNode {

		private int value;
		private int height;
		private AVLNode leftNode, rightNode;

		AVLNode(int value) {
			this.value=value;
		}

		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
		public AVLNode getLeftNode() {
			return leftNode;
		}
		public void setLeftNode(AVLNode leftNode) {
			this.leftNode = leftNode;
		}
		public AVLNode getRightNode() {
			return rightNode;
		}
		public void setRightNode(AVLNode rightNode) {
			this.rightNode = rightNode;
		}

	}
}

