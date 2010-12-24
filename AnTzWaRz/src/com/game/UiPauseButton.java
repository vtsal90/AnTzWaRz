package com.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class UiPauseButton extends UiButton implements Constants {
	
	private Bitmap pause_background;
	private MenuButton resume_button;
	private MenuButton save_quit_button;
	private MenuButton quit_button;
	
	private boolean game_paused = false;


	public UiPauseButton(Resources res, int which_ui_button, int screen_width, int screen_height) {
		super(res, which_ui_button, screen_width, screen_height);
		pause_background = BitmapFactory.decodeResource(res,R.drawable.pause_background);
		resume_button = new MenuButton(res, PAUSE_RESUME, screen_width, screen_height);
		save_quit_button = new MenuButton(res, PAUSE_SAVE_QUIT, screen_width, screen_height);
		quit_button = new MenuButton(res, PAUSE_QUIT, screen_width, screen_height);
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(image,(float)location.x,(float)location.y,null);
		
		if (game_paused) {
			canvas.drawBitmap(pause_background, 0, 0, null);
			resume_button.draw(canvas);
			save_quit_button.draw(canvas);
			quit_button.draw(canvas);
		}
	}

	public MenuButton wasMenuButtonPressed(MotionEvent event) {
		if (!game_paused)
			return null;
		
		if (resume_button.wasPressed(event.getX(), event.getY())) {
			return resume_button;
		} else if (save_quit_button.wasPressed(event.getX(), event.getY())) {
			return save_quit_button;
		} else if (quit_button.wasPressed(event.getX(), event.getY())) {
			return quit_button;
		}
		
		return null;
	}

	public void setIfGamePaused(boolean game_paused) {
		this.game_paused = game_paused;
	}


}
