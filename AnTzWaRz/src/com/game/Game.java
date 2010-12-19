package com.game;

import com.game.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

//The big head-honcho class.  Pretty much in charge of everything. 
public class Game implements Constants {
	
	
	private Resources res;
	private int game_state;
	private int screen_width;
	private int screen_height;
	
	//The background
	//changes from the menu background to the game background
	//when a game starts
	private Bitmap background;
	
	//The menu buttons
	private MenuButton select_level = null;
	private MenuButton game_continue = null; 
	private MenuButton options = null;
	private MenuButton back = null;
	
	//The select level buttons
	private LevelButton[] lvl_buttons = new LevelButton[LEVEL_COUNT];

	//Game is created on app startup!
	public Game(Resources res, int screen_width, int screen_height) {
		this.res = res;
		this.screen_width = screen_width;
		this.screen_height = screen_height;
		
		//The game is at the default menu when app starts
		changeToMenuHome();
	}

	//All update numbers logic
	public void update() {
		//all logic while in a game
		if (gameInGame()) {
			updateGame();
		}
		//all logic while in the menus
		else if (gameInMenu()) {
			updateMenu();
		}
		
	}
	
	//Draw everything on the screen
	public void draw(Canvas canvas) {
		if (gameInGame()) {
			drawGame(canvas);
		} else if (gameInMenu()) {
			drawMenu(canvas);
		}
	}

	//The screen was pressed
	public void screenPressed(MotionEvent event) {
		if (gameInGame()) {
			screenPressedGame(event);
		} else if (gameInMenu()) {
			screenPressedMenu(event);
		}
	}


	//-----------------------------------------
	//Private methods below this point
	//-----------------------------------------
	
	//Currently in a game?
	private boolean gameInGame() {		
		return game_state == GS_GAME_PLAYING ||
			game_state == GS_GAME_PAUSED;
	}
	
	private void updateGame() {
		if (game_state == GS_GAME_PLAYING) {
			
		} else if (game_state == GS_GAME_PAUSED) {
			
		}
	}

	private void drawGame(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}
	
	//Screen was pressed while in game
	private void screenPressedGame(MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

	private void loadAndStartLevel(int i) {
		// TODO Auto-generated method stub
		
	}
	
	//Change the game to the main menu
	private void changeToMenuHome() {
		//default background
		game_state = GS_MENU_HOME;
		background = BitmapFactory.decodeResource(res,R.drawable.background_default);
		
		if (select_level == null)
			select_level = new MenuButton(res, MB_SELECT_LEVEL, screen_width, screen_height);
		
		if (game_continue == null)
			game_continue = new MenuButton(res, MB_CONTINUE, screen_width, screen_height);
		
		if (options == null)
			options = new MenuButton(res, MB_OPTIONS, screen_width, screen_height);
		
		if (back == null)
			back = new MenuButton(res, MB_BACK, screen_width, screen_height);
		
	}
	
	//By default only the first level is unlocked
	private void changeToMenuSelectLevel() {
		game_state = GS_MENU_SELECT_LEVEL;
		
		for (int i = 0; i < lvl_buttons.length; i++) {
			if (lvl_buttons[i] == null) {
				lvl_buttons[i] = new LevelButton(res, i+1, i!=0, screen_width, screen_height);
			}
		}
	}
	
	private void changeToMenuOptions() {
		game_state = GS_MENU_OPTIONS;
	}

	private void loadSavedGame() {
		//TODO: Load saved game
	}

	
	//Is the game currently at a menu?
	private boolean gameInMenu() {		
		return game_state == GS_MENU_HOME ||
			game_state == GS_MENU_SELECT_LEVEL ||
			game_state == GS_MENU_OPTIONS;
	}
	
	private void updateMenu() {
		if (game_state == GS_MENU_HOME) {
			
		} else if (game_state == GS_MENU_SELECT_LEVEL) {
			
		} else if (game_state == GS_MENU_OPTIONS) {
			
		}
	}
	
	//Draw everything assioated with the menu
	private void drawMenu(Canvas canvas) {
		canvas.drawBitmap(background,0,0,null);
		if (game_state == GS_MENU_HOME) {
			select_level.draw(canvas);
			game_continue.draw(canvas);
			options.draw(canvas);
		} else if (game_state == GS_MENU_SELECT_LEVEL) {
			back.draw(canvas);
			for (int i = 0; i < lvl_buttons.length; i++) {
				lvl_buttons[i].draw(canvas);
			}
		} else if (game_state == GS_MENU_OPTIONS) {
			back.draw(canvas);
		}
	}

	//The screen was pressed while in a menu
	private void screenPressedMenu(MotionEvent event) {
		float event_x = event.getX();
		float event_y = event.getY();
		if (game_state == GS_MENU_HOME) {
			if (select_level.wasPressed(event_x, event_y)) {
				changeToMenuSelectLevel();
			} else if (game_continue.wasPressed(event_x, event_y)) {
				loadSavedGame();
			} else if  (options.wasPressed(event_x, event_y)) {
				changeToMenuOptions();
			}
		} else if (game_state == GS_MENU_SELECT_LEVEL) {
			for (int i = 0; i < lvl_buttons.length; i++) {
				if (lvl_buttons[i].wasPressed(event_x, event_y) && !lvl_buttons[i].isLocked()) {
					//TODO: If the user has saved a game make a popup confirmation
					//window that says that this will override their current saved game
					loadAndStartLevel(i+1);
				}
			}
			if (back.wasPressed(event_x, event_y)) {
				changeToMenuHome();
			}
		} else if (game_state == GS_MENU_OPTIONS) {
			if (back.wasPressed(event_x, event_y)) {
				changeToMenuHome();
			}
		}
		
	}


	
}