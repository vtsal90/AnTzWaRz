package com.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Button implements Constants {

	protected int screen_width;
	protected int screen_height;
	
	//The image of the button
	protected Bitmap image;
	
	//where the button is located
	protected Point location;


	public void draw(Canvas canvas) {
		canvas.drawBitmap(image,(float)location.x,(float)location.y,null);
	}
	
	//was the button pressed
	public boolean wasPressed(float x, float y) {
		if (x >= this.location.x && x <= this.location.x + image.getWidth()) {
			return (y >= this.location.y && y <= this.location.y + image.getHeight());
		}
		return false;
	}
	
	//loads the correct button stats based on what button it is
	protected abstract void loadButton(Resources res);

}