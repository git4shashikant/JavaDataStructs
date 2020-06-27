package com.datastructure.tree;

import java.util.List;
import java.util.ArrayList;

import com.datastructure.Data;

public class Tree {
	
	private TreeNode rootNode;

	public TreeNode getRootNode() {
		return rootNode;
	}

	public void addAsRootNode(TreeNode node) {
		if (rootNode != null) {
			TreeNode parentNode = node.getParentNode();
			if (parentNode!=null) {
				if (node.getChildNodeList().size()>0)
					parentNode.setChildNodeList(node.getChildNodeList());
				else 
					parentNode.setChildNodeList(null);
			} else { 
				List<TreeNode> rootNodeChildList = new ArrayList<TreeNode>();
				rootNodeChildList.add(this.rootNode);
				node.setChildNodeList(rootNodeChildList);
			}
		}	
		this.rootNode = node;
	}
	
	public void addToNode(TreeNode parentNode, TreeNode childNode) {
		
		// detach node from the parent node if attached to
		TreeNode childParentNode = childNode.getParentNode();
		if (childParentNode!=null) {
			if (childNode.getChildNodeList().size()>0)
				childParentNode.setChildNodeList(childNode.getChildNodeList());
			else 
				childParentNode.setChildNodeList(null);
		}
				
		// traverse through the root node 
		TreeNode matchedNode = getNode(parentNode);
		
		List<TreeNode> list = matchedNode.getChildNodeList() == null ? new ArrayList<TreeNode>() : matchedNode.getChildNodeList();
		list.add(childNode);
		matchedNode.setChildNodeList(list);

		// we can also use this code instead of above one, but here traversal would be on all nodes 
		// addToCurrNode(this.rootNode, parentNode, childNode);
		
		
	}
	
	public TreeNode getNode(TreeNode node) {
		return getNodeFromCurrNode(this.rootNode, node);
	}
	
	private TreeNode getNodeFromCurrNode(TreeNode currNode, TreeNode node){
		
		TreeNode requiredNode = null;
		
		if (currNode.equals(node)){
			requiredNode = currNode;
		} else {
			if (currNode.getChildNodeList()!=null){
				for (TreeNode currChildNode : currNode.getChildNodeList()) {
					requiredNode = getNodeFromCurrNode(currChildNode, node);
					if (requiredNode!=null){
						break;
					}
				}
			}
		}
		return requiredNode;
	}
	
	// deprecated to avoid all node traversal 
	private void addToCurrNode(TreeNode currNode, TreeNode parentNode, TreeNode childNode){
		
		if (currNode.equals(parentNode)){
			List<TreeNode> list = currNode.getChildNodeList() == null ? new ArrayList<TreeNode>() : currNode.getChildNodeList();
			list.add(childNode);
			currNode.setChildNodeList(list);
		} else {
			if (currNode.getChildNodeList()!=null){
				for (TreeNode currChildNode : currNode.getChildNodeList()) {
					addToCurrNode(currChildNode, parentNode, childNode);
				}
			}
		}
		childNode.setParentNode(parentNode);
	}
	
	public static void main (String [] args) {
		
		Tree tree = new Tree();
		
		Data rootData = new Data(0, 0.0f, "root");
		Data testData1 = new Data(1, 1.1f, "node-1");
		Data testData2 = new Data(2, 1.2f, "node-2");
		Data testData3 = new Data(3, 1.3f, "node-3");
		
		TreeNode rootNode = new TreeNode();
		rootNode.setData(rootData);
		
		tree.addAsRootNode(rootNode);
		
		TreeNode resultRootNode = tree.getRootNode();
		System.out.println(resultRootNode);
		
		TreeNode testNode1 = new TreeNode();
		testNode1.setData(testData1);
		
		tree.addToNode(resultRootNode, testNode1);
		
		TreeNode testNode2 = new TreeNode();
		testNode2.setData(testData2);
		
		tree.addToNode(resultRootNode, testNode2);
		
		TreeNode testNode3 = new TreeNode();
		testNode3.setData(testData3);
		
		tree.addToNode(testNode1, testNode3);
		
		System.out.println("");
	}
	
}
