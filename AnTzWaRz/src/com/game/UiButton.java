package com.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class UiButton extends Button implements Constants {

	protected int which_ui_button;

	public UiButton(Resources res, int which_ui_button, int screen_width, int screen_height) {
		this.which_ui_button = which_ui_button;
		this.screen_width = screen_width;
		this.screen_height = screen_height;
		location = new Point();
		loadButton(res);

	}

	@Override
	protected void loadButton(Resources res) {
		if (which_ui_button == UI_BUTTON_PAUSE) {
			image = BitmapFactory.decodeResource(res, R.drawable.ui_button_pause);
			location.x = (screen_width - image.getWidth()) - 10;
			location.y = 10;
		} else if (which_ui_button == UI_BUTTON_CHANGE_VIEW) {
			image = BitmapFactory.decodeResource(res, R.drawable.ui_button_topside);
			location.x = (screen_width - image.getWidth()) - 10;
			location.y = (screen_height - image.getHeight()) - 10;

		} else if (which_ui_button == UI_BUTTON_NOTIFICATION) {
			
		}
	}
	
	public int whichButton() {
		return which_ui_button;
	}

}
