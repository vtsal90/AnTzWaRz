package com.game;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

public class Tunnel implements Constants {
	
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	
	private int max_depth;
	private int max_width;
	
	private int max_space;
	
	//the image that is all the tunnels
	//created by combining other images
	private Canvas tunnels_image;
	
	//small images that when overlayed eachother a bunch of
	//times creates the whole tunnels_image
	private Bitmap node_image;
	private Bitmap edge_image;
	
	//the image that designates a node that leads to topside
	private Bitmap top_image;
	
	//-----------------------------------
	//THIS ARE JUST TEMPORARY: NEED TO FIGURE OUT A WAY TO COMBINE ALL THE IMAGES
	//TO MAKE ONE TUNNEL IMAGE FOR EFFECIENTY REASONS
	//-----------------------------------
	private ArrayList<TempClass> tempImages = new ArrayList<TempClass>();

	public Tunnel(int tunnels_width, int tunnels_height) {
		max_width = tunnels_width;
		max_depth = tunnels_height;
	}

	public void loadLevel1Tunnel(Resources res, Bitmap background_image) {
		node_image = BitmapFactory.decodeResource(res, R.drawable.lvl_1_node);
		edge_image = BitmapFactory.decodeResource(res, R.drawable.lvl_1_edge);
		top_image = BitmapFactory.decodeResource(res, R.drawable.lvl_1_top);
	
		int center = (int)max_width/2;
		nodes.add(new Node(center, max_depth-60, true));
		nodes.add(new Node(center, max_depth-140, false));
		nodes.add(new Node(center+100, max_depth-140, false));
		nodes.add(new Node(center-80, max_depth-180, false));
		nodes.add(new Node(center+160, max_depth-170, false));
		nodes.add(new Node(center+10, max_depth-260, false));
		nodes.add(new Node(center+30, max_depth-340, false));
		
		edges.add(new Edge(nodes.get(0), nodes.get(1)));
		edges.add(new Edge(nodes.get(1), nodes.get(2)));
		edges.add(new Edge(nodes.get(1), nodes.get(3)));
		edges.add(new Edge(nodes.get(2), nodes.get(4)));
		edges.add(new Edge(nodes.get(2), nodes.get(5)));
		edges.add(new Edge(nodes.get(3), nodes.get(5)));
		edges.add(new Edge(nodes.get(5), nodes.get(6)));
	
		createCombinedImage(background_image);
	}

	//TODO: Clean this up
	//TODO: Something is a little off when lineing up
	//the images of the edges and nodes
	private void createCombinedImage(Bitmap background_image) {
		Node node_1;
		Node node_2;
		double angle;
		Point last_node;
		Bitmap rotated_edge;
		int size;
		for (int i = 0; i < edges.size(); i++) {
			node_1 = edges.get(i).getNode1();
			node_2 = edges.get(i).getNode2();
			angle = calculateAngle(node_1.getX(), node_1.getY(), node_2.getX(), node_2.getY());
			size = edge_image.getWidth();
			Bitmap image =  node_1.isTopNode() ? top_image : node_image;
			
			//background_image = combineTwoImages(background_image, image,
			//		node_1.getX()-image.getWidth()/2, node_1.getY()-image.getHeight()/2);
			tempImages.add(new TempClass(image,node_1.getX()-image.getWidth()/2, node_1.getY()-image.getHeight()/2));
			last_node = new Point(node_1.getX(), node_1.getY());
			
			image =  node_2.isTopNode() ? top_image : node_image;
			tempImages.add(new TempClass(image,node_2.getX()-image.getWidth()/2, node_2.getY()-image.getHeight()/2));
			//background_image = combineTwoImages(background_image, image,
			//		node_2.getX()-image.getWidth()/2, node_2.getY()-image.getHeight()/2);
			
			Matrix rotate = new Matrix();
			
			//why is this negative?
			rotate.postRotate((float)-(angle*180/Math.PI));
			rotate.postScale((float)1.05, (float)1.05);
			rotated_edge = Bitmap.createBitmap(edge_image, 0, 0, edge_image.getWidth(), edge_image.getHeight(), rotate, true);
			while (nodesNotConnected(last_node.x, last_node.y, node_2.getX(), node_2.getY(), angle)) {
				tempImages.add(new TempClass(rotated_edge, last_node.x+(Math.sin(angle)*size) - size*.55, last_node.y+(Math.cos(angle)*size) - size*.55));
	//			Log.i("Added", last_node.x + ", " + last_node.y);
				last_node.x += (Math.sin(angle)*size);
				last_node.y += (Math.cos(angle)*size);
			}
			//and one more time
			tempImages.add(new TempClass(rotated_edge, last_node.x+(Math.sin(angle)*size) - size*.55, last_node.y+(Math.cos(angle)*size) - size*.55));
	//		Log.i("Added", last_node.x + ", " + last_node.y);
		}
		
	}
	
	private boolean nodesNotConnected(double x1, double y1, double x2, double y2, double angle) {
		int length = edge_image.getWidth();
	//	Log.i("Coords", Math.abs((x1+(Math.sin(angle)*length))-x2) + ", " + Math.abs((y1+(Math.cos(angle)*length))-y2));
		return !(Math.abs((x1+(Math.sin(angle)*length))-x2) <= length/2 && 
			Math.abs((y1+(Math.cos(angle)*length))-y2) <= length/2);
	}

	public void draw (Canvas canvas, Point current_cords) {
		for (int i = 0; i < tempImages.size(); i++) {
			canvas.drawBitmap(tempImages.get(i).image, (float)(tempImages.get(i).location.x - current_cords.x),
					(float)(tempImages.get(i).location.y - current_cords.y), null); 
		}
	}
	
	//TODO: Make this method work
	/*private Bitmap combineTwoImages(Bitmap bmp1, Bitmap bmp2, double x, double y) {
		Canvas combo_image = new Canvas(bmp1);
		combo_image.drawBitmap(bmp2, (float)x, (float)y, null);
		return bmp2;
     //   Bitmap combined = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
     //   Canvas canvas = new Canvas(combined);
     //   canvas.drawBitmap(bmp1, new Matrix(), null);
     //   canvas.drawBitmap(bmp2, (float)x, (float)y, null);
     //  return combined; 
    } */
	
	private double calculateAngle(double x1, double y1, double x2, double y2) {
		double angle;
		if (y2-y1 == 0 && x2 - x1 == 0) {
			return 0;
		} else if (y2-y1 == 0) {
			angle = x2 > x1 ? Math.PI/2 : -Math.PI/2;
		} else if (x2-x1 == 0) {
			angle = y2 > y1 ? 0 : Math.PI;
		} else {
			angle = Math.atan((x2-x1)/(y2-y1));
		}
		
		if ((x1-x2 > 0 && y1-y2 > 0) || (x1-x2 < 0 && y1-y2 > 0)) angle += Math.PI;   
		else if (y1-x2 < 0 && y1-y2 < 0) angle += 2*Math.PI;

		if (angle < 0) angle += Math.PI*2;
		else if (angle > Math.PI*2) angle -= Math.PI*2;

		return angle;
	}
	
	

}
