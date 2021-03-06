package com.game;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

//The buttons for navigating the menus!
public class MenuButton extends Button {

	private int which_menu_button;
	
	public MenuButton(Resources res, int which_menu_button, int screen_width, int screen_height) {
		this.which_menu_button = which_menu_button;
		this.screen_width = screen_width;
		this.screen_height = screen_height;
		location = new Point();
		loadButton(res);
		
	}
	
	//loads the correct button stats based on what button it is
	@Override
	protected void loadButton(Resources res) {
		if (which_menu_button == MB_SELECT_LEVEL) {
			image = BitmapFactory.decodeResource(res,R.drawable.mb_select_level);
			location.x = screen_width*3/4 - image.getWidth()/2;
			location.y = screen_height*5/6 - image.getHeight()/2;
		} else if (which_menu_button == MB_CONTINUE) {
			image = BitmapFactory.decodeResource(res,R.drawable.mb_continue);
			location.x = screen_width*1/2 - image.getWidth()/2;
			location.y = screen_height*5/6 - image.getHeight()/2;
		} else if (which_menu_button == MB_OPTIONS) {
			image = BitmapFactory.decodeResource(res,R.drawable.mb_options);
			location.x = screen_width*1/4 - image.getWidth()/2;
			location.y = screen_height*5/6 - image.getHeight()/2;
		} else if (which_menu_button == MB_BACK) {
			image = BitmapFactory.decodeResource(res,R.drawable.mb_back);
			location.x = screen_width*1/7 - image.getWidth()/2;
			location.y = screen_height*7/8 - image.getHeight()/2;
		} else if (which_menu_button == PAUSE_RESUME) {
			image = BitmapFactory.decodeResource(res,R.drawable.pause_resume);
			location.x = screen_width/2 - image.getWidth()/2;
			location.y = screen_height*1/3 - image.getHeight()/2;
		} else if (which_menu_button == PAUSE_SAVE_QUIT) {
			image = BitmapFactory.decodeResource(res,R.drawable.pause_save_quit);
			location.x = screen_width/2 - image.getWidth()/2;
			location.y = screen_height*2/3 - image.getHeight()/2;		
		} else if (which_menu_button == PAUSE_QUIT) {
			image = BitmapFactory.decodeResource(res,R.drawable.pause_quit);
			location.x = 10;
			location.y = screen_height - image.getHeight() - 10;;	
		}
	}

	public int whichMenuButton() {
		return which_menu_button;
	}

}
