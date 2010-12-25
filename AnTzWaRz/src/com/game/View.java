package com.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class View implements Constants{
	
	private Bitmap background;
	private Point current_cords;
	private int which_view;

	public View(int which_view) {
		current_cords = new Point(0,0);
		this.which_view = which_view;
	}

	public void setBackground(Bitmap bitmap) {
		background = bitmap;
	}

	//fix the screen so what is displayed exists
	public void dontOverExtendBoundries(int screen_width, int screen_height) {
		if (current_cords.x < 0) current_cords.x = 0;
		if (current_cords.y < 0) current_cords.y = 0;
		if (current_cords.x > background.getWidth()-screen_width) current_cords.x = background.getWidth()-screen_width;
		if (current_cords.y > background.getHeight()-screen_height) current_cords.y = background.getHeight()-screen_height;
	}

	public int whichView() {
		return which_view;
	}

	public void draw(Canvas canvas) {
		if (background == null) return;
		canvas.drawBitmap(background,(float)-current_cords.x,(float)-current_cords.y,null);
		
	}

	public void updateCurrentCords(Point screen_move) {
		current_cords.x += screen_move.x;
		current_cords.y += screen_move.y;
	}

	public Point getCurrentCords() {
		return current_cords;
	}

}
