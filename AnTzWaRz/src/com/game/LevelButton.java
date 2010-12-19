package com.game;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class LevelButton extends Button {
	
	private int which_level_button;
	private boolean level_locked;

	public LevelButton(Resources res, int which_level_button, boolean level_locked,
			int screen_width, int screen_height) {
		
		this.which_level_button = which_level_button;
		this.level_locked = level_locked;
		this.screen_width = screen_width;
		this.screen_height = screen_height;

		loadButton(res);
	}
	
	public void unlockLevel(Resources res) {
		this.level_locked = false;
		
		loadButton(res);
	}
	
	public boolean isLocked() {
		return level_locked == true;
	}

	@Override
	protected void loadButton(Resources res) {
		if (which_level_button == LVL_BUTTON_1) {
			image = BitmapFactory.decodeResource(res,R.drawable.lvl_button_1);
			x = screen_width*5/8 - image.getWidth()/2;
			y = screen_height*1/10 - image.getHeight()/2;
		} else if (which_level_button == LVL_BUTTON_2) {
			image = BitmapFactory.decodeResource(res,R.drawable.lvl_button_2);
			x = screen_width*5/8 - image.getWidth()/2;
			y = screen_height*3/10 - image.getHeight()/2;
		} else if (which_level_button == LVL_BUTTON_3) {
			image = BitmapFactory.decodeResource(res,R.drawable.lvl_button_3);
			x = screen_width*5/8 - image.getWidth()/2;
			y = screen_height*5/10 - image.getHeight()/2;
		} else if (which_level_button == LVL_BUTTON_4) {
			image = BitmapFactory.decodeResource(res,R.drawable.lvl_button_4);
			x = screen_width*5/8 - image.getWidth()/2;
			y = screen_height*7/10 - image.getHeight()/2;
		} else if (which_level_button == LVL_BUTTON_5) {
			image = BitmapFactory.decodeResource(res,R.drawable.lvl_button_5);
			x = screen_width*5/8 - image.getWidth()/2;
			y = screen_height*9/10 - image.getHeight()/2;
		}
		
		if (level_locked) {
			image = BitmapFactory.decodeResource(res,R.drawable.lvl_button_locked);
		}
	}

}
