package com.datastructure.tree;

public class Trie {

	private TrieNode rootNode;
	
	public Trie(){
		rootNode = new TrieNode('\0', false);
	}
	
	public void insertWord(String word) {
		
		char [] charArr = word.toCharArray();
		
		TrieNode [] rootLinks = rootNode.getTrieNode();
		int treeDepth = 0;
		for (char character : charArr) {
			for (int i =0; i<rootLinks.length; i++) {
				if (rootLinks[i]!=null){
					if (rootLinks[i].getCharacter()==character) {
						if (treeDepth==charArr.length-1){
							rootLinks[i].setFullWord(true);
						}
						rootLinks = rootLinks[i].getTrieNode();
						break;
					}
				} else {
					TrieNode trieNode = (treeDepth==(charArr.length-1)) ? new TrieNode(character, true) 
										: new TrieNode(character, false);
					rootLinks[i] = trieNode;
					rootLinks = trieNode.getTrieNode();
					break;
				}
			}
			treeDepth++;
		}
	}
	
	public boolean contains(String word) {

		int treeDepth = 0;
		char [] charArr = word.toCharArray();
		TrieNode [] rootLinks = rootNode.getTrieNode();
		boolean isFullWord = false;
		
		for (char character : charArr) {
			boolean charFound = false;
			for (int i =0; i<rootLinks.length; i++) {
				if (rootLinks[i]!=null){
					if (rootLinks[i].getCharacter()==character) {
						isFullWord = rootLinks[i].isFullWord();
						rootLinks = rootLinks[i].getTrieNode();
						charFound = true;
						break;
					}
				} else {
					break;
				}
			}
			
			if (charFound==false) {
				return false;
			} else {
				if (treeDepth==charArr.length-1 && isFullWord){
					return true;
				}
			}
			treeDepth++;
		}
		return false;
	}
	
	public static void main (String [] args) {
		
		Trie trie = new Trie();
		trie.insertWord("Ram");
		trie.insertWord("Ramesh");
		trie.insertWord("Suresh");
		trie.insertWord("Rajan");
		trie.insertWord("Shashi");
		
		System.out.println("contains ram : "+ trie.contains("Ram"));
		System.out.println("contains rame : "+ trie.contains("Rame"));
		trie.insertWord("Rame");
		System.out.println("contains rame : "+ trie.contains("Rame"));
		
		
	}

	static class TrieNode {

		private char character;
		private TrieNode[] nodeList;
		private boolean isFullWord;

		public TrieNode(char character, boolean isFullWord){
			this.character = character;
			this.nodeList = new TrieNode[26];
			this.isFullWord = isFullWord;
		}

		public char getCharacter() {
			return character;
		}

		public void setCharacter(char character) {
			this.character = character;
		}

		public TrieNode[] getTrieNode() {
			return nodeList;
		}

		public void setTrieNode(TrieNode[] trieNode) {
			this.nodeList = trieNode;
		}

		public boolean isFullWord() {
			return isFullWord;
		}

		public void setFullWord(boolean isFullWord) {
			this.isFullWord = isFullWord;
		}

	}
}