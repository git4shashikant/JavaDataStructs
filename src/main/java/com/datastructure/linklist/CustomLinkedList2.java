package com.datastructure.linklist;

public class CustomLinkedList2 {
    private Node head;
    
    private volatile Integer size = new Integer(0);

    public Node getHead() {
           return head;
    }

    public void setHead(Node head) {
           this.head = head;
    }
    
    public void add(Object obj) {
           if(null == head) {
                  head = new Node(obj);
           } else {
                  Node nextNode = head.getNextNode();
                  Node nodeToAdd = head.getNextNode();
                  while(nextNode != null) {
                        nodeToAdd = nextNode;
                        nextNode = nextNode.getNextNode();
                  }
                  Node newNode = new Node(obj);
                  if(nodeToAdd != null) {
                        nodeToAdd.setNextNode(newNode);
                  } else {
                        head.setNextNode(newNode);
                  }
                  
           }

           incrementSize();
    }
    
    public void addAt(Object obj, int index) {
           int i = 0;
           Node nextNode = head.getNextNode();
           Node previousNode = head.getNextNode();
           while(nextNode != null && (i == index)) {
                  previousNode = nextNode;
                  nextNode = nextNode.getNextNode();
                  i++;
           }

           if(previousNode.equals(nextNode) && head.equals(nextNode)) {
                  Node newNode = new Node(obj);
                  Node tempNode = head;
                  head = newNode;
                  head.setNextNode(tempNode);
           } else {
                  Node newNode = new Node(obj);
                  previousNode.setNextNode(newNode);
                  newNode.setNextNode(nextNode);
           }

           incrementSize();
    }
    
    private void incrementSize() {
           synchronized (this.size) {
                  this.size++;
           }
    }
    
    private void decrementSize() {
           synchronized (this.size) {
                  this.size--;
           }
    }
    
    public void deleteNode(Node node) {
           Node nextNode = head;
           while(nextNode != null && !node.equals(nextNode)) {
                  nextNode = head.getNextNode();
           }
           if(nextNode.equals(head)) {
                  head = head.getNextNode();
           }
           decrementSize();
    }
    
    public void deleteAt(int index) {
           int ind = 0;
           Node nextNode = head;
           Node previous = head;
           while(nextNode != null && ind < index) {
                  ind++;
                  previous = nextNode;
                  nextNode = nextNode.getNextNode();
           }
           if(head.equals(nextNode)) {
                  head = head.getNextNode();
           } else {
                  previous.setNextNode(nextNode.getNextNode());
           }
           decrementSize();
    }
    
    public void print() {
           Node nextNode = head;
           while(nextNode != null) {
                  System.out.println(nextNode.getValue().toString());
                  nextNode = nextNode.getNextNode();
           }
    }
    
    public int size() {
           return size;
    }

    static class Node {
        private Object value;
        private Node nextNode;

        public Node(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }
}

