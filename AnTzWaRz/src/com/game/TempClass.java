package com.game;

import android.graphics.Bitmap;


//TEMPORARY CLASS FOR DRAWING THE TUNNEL
//THIS CLAS WILL NOT EXIST WHEN ALL THE IMAGES
//ARE COMBINED INTO 1 FOR DRAWING THE TUNNEL
public class TempClass {
	
	public Bitmap image;
	public Point location;
	
	public TempClass(Bitmap image, double x, double y) {
		this.image = image;
		this.location = new Point(x,y);
	}

}
