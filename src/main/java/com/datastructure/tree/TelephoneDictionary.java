package com.datastructure.tree;

public class TelephoneDictionary {

	private RootNode root;
	
	public TelephoneDictionary(){
		root = new RootNode();
	}
	
	public void addNumber(String telNum, String code) {
		
		int offset = 97;
		
		DictionaryNode telRef = null;
		DictionaryNode codeRef = null;
		DictionaryNode dummyRef = null;
		
		String s = telNum;
		DictionaryNode[] currNodeList = this.root.getNodes();
		for (int index=0; index<s.length(); index++) {
			int num = Integer.parseInt(s.substring(index, index+1));
			if (currNodeList[num]==null){
				currNodeList[num] = new DictionaryNode();
			}
			currNodeList = currNodeList[num].getNodes();
			
			if (index==9){
				telRef = currNodeList[num];
			}
		}
		
		char [] sCode = code.toCharArray();
		DictionaryNode[] currCharNodeList = this.root.getCharNodes();
		for (int index=0; index<sCode.length; index++) {
			if (currCharNodeList[sCode[index]-offset]==null){
				currCharNodeList[sCode[index]-offset] = new DictionaryNode();
			}
			
			currCharNodeList = currCharNodeList[sCode[index]-offset].getNodes();
			
			if (index==sCode.length-1){
				codeRef = currCharNodeList[sCode[index]-offset];
			}
		}
		
		dummyRef = codeRef;
		codeRef = telRef;
		telRef = dummyRef;
		
	}
	
	public static void main (String ... strings){
		
		TelephoneDictionary dic = new TelephoneDictionary();
		dic.addNumber("9988776655", "brtynm");
		System.out.println("");
	}

	static class RootNode extends DictionaryNode {

		DictionaryNode[] charNodes;

		RootNode() {
			super();
			charNodes = new DictionaryNode[26];
		}

		public DictionaryNode[] getCharNodes() {
			return charNodes;
		}

		public void setCharNodes(DictionaryNode[] charNodes) {
			this.charNodes = charNodes;
		}

	}

	static class DictionaryNode {

		DictionaryNode parent;

		DictionaryNode[] nodes;

		DictionaryNode(){
			nodes = new DictionaryNode[26];
		}

		public DictionaryNode getParent() {
			return parent;
		}

		public void setParent(DictionaryNode parent) {
			this.parent = parent;
		}

		public DictionaryNode[] getNodes() {
			return nodes;
		}

		public void setNodes(DictionaryNode[] nodes) {
			this.nodes = nodes;
		}

	}
	
}