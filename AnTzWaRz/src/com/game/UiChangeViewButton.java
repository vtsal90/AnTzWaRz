package com.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class UiChangeViewButton extends UiButton {
	
	private Bitmap image_topside;
	private Bitmap image_underground;

	public UiChangeViewButton(Resources res, int which_ui_button, int screen_width, int screen_height) {
		super(res, which_ui_button, screen_width, screen_height);
		image_topside = BitmapFactory.decodeResource(res, R.drawable.ui_button_topside);
		image_underground = BitmapFactory.decodeResource(res, R.drawable.ui_button_underground);
	}

	public void changeImage(int new_view) {
		image = new_view == VIEW_UNDERGROUND ? image_topside : image_underground;
	}

}
