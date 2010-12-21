package com.game;

import android.graphics.Bitmap;

//
public abstract class Resource {

	//where is this resource located
	protected int x;
	protected int y;
	
	//how many particles are there
	protected int quantity;
	
	//how much is each particle worth
	protected int worth;
	
	protected Point[] particles;

	protected Bitmap image;
	
	protected void initializeResource(int x, int y, int quantity, int worth) {
		this.x = x;
		this.y = y;
		this.quantity = quantity;
		this.worth = worth;
	
		setParticleLocations();
	}

	//particles of the object appear randomly in a circle around the actual location
	//of the resource
	private void setParticleLocations() {
		double radius = quantity/3.14;
		radius = radius*image.getWidth();
		
		for (int i = 0; i < quantity; i++) {
			particles[i].x = x + ((Math.random()*2) - 1) * radius;
			particles[i].y = y + ((Math.random()*2) - 1) * radius;
		}
		
	}


	public void setWorth(int worth) {
		this.worth = worth;
	}
	
	public int getWorth() {
		return this.worth;
	}

	
}
