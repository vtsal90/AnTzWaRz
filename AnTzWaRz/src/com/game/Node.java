package com.game;

public class Node {
	
	public Point location;
	
	//is this node an exit to the topside
	private boolean top_node;
	
	public Node(double y, double x, boolean top_node) {
		location = new Point(x,y);
		this.top_node = top_node;
	}
	
	public double getX() {
		return location.x;
	}
	
	public double getY() {
		return location.y;
	}
	
	public boolean isTopNode() {
		return top_node;
	}

}
