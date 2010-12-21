package com.game;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class Food extends Resource {

	public Food(Resources res, int x, int y, int quantity, int worth) {
		initializeResource(x,y,quantity,worth);
		this.image = BitmapFactory.decodeResource(res,R.drawable.food);
	}

}
