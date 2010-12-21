package com.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class Ui implements Constants {
	
	private Bitmap top_background;
	private Point top_bg_location;
	
	private UiButton pause_button;
	private UiChangeViewButton change_view_button;
	private UiButton notificiation_button;

	public Ui(Resources res, int screen_width, int screen_height, Player player) {
		top_background = BitmapFactory.decodeResource(res, R.drawable.ui_top_background);
		top_bg_location = new Point(screen_width-top_background.getWidth(), screen_height/2-top_background.getHeight()/2);
		pause_button = new UiButton(res, UI_BUTTON_PAUSE, screen_width, screen_height);
		change_view_button = new UiChangeViewButton(res, UI_BUTTON_CHANGE_VIEW, screen_width, screen_height);
	}


	public UiButton wasPressed(MotionEvent event) {
		if (pause_button.wasPressed(event.getX(), event.getY())) {
			return pause_button;
		} else if (change_view_button.wasPressed(event.getX(), event.getY())) {
			return change_view_button;
		}
		//TODO: Add notificiation button	
		return null;
	}


	public void changeChangeViewButton(int new_view) {
		change_view_button.changeImage(new_view);	
	}


	public void draw(Canvas canvas) {
		canvas.drawBitmap(top_background, (float)top_bg_location.x, (float)top_bg_location.y, null);
		pause_button.draw(canvas);
		change_view_button.draw(canvas);
		//pause_button.draw(canvas);
	}

}
