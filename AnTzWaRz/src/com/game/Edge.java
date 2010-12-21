package com.game;

public class Edge {
	
	private Node node_1;
	private Node node_2;
	
	public Edge(Node node_1, Node node_2) {
		this.node_1 = node_1;
		this.node_2 = node_2;
	}
	
	public Node getNode1() {
		return node_1;
	}
	
	public Node getNode2() {
		return node_2;
	}

}
