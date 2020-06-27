package com.datastructure.tree;

import com.datastructure.Data;

import java.util.List;

public class TreeNode {

	private TreeNode parentNode;
	private Data data;
	private List<TreeNode> childNodeList;
	
	public TreeNode getParentNode() {
		return parentNode;
	}
	public void setParentNode(TreeNode parentNode) {
		this.parentNode = parentNode;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public List<TreeNode> getChildNodeList() {
		return childNodeList;
	}
	public void setChildNodeList(List<TreeNode> childNodeList) {
		this.childNodeList = childNodeList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((childNodeList == null) ? 0 : childNodeList.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((parentNode == null) ? 0 : parentNode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeNode other = (TreeNode) obj;
		if (childNodeList == null) {
			if (other.childNodeList != null)
				return false;
		} else if (!childNodeList.equals(other.childNodeList))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (parentNode == null) {
			if (other.parentNode != null)
				return false;
		} else if (!parentNode.equals(other.parentNode))
			return false;
		return true;
	}
	
}
