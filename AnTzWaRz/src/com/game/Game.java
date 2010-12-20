package com.game;

import com.game.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

//The big head-honcho class.  Pretty much in charge of everything. 
//And pretty much everything happens in this class
public class Game implements Constants {
	
	//General stuff that is needed
	private Resources res;
	private int game_state;
	private int screen_width;
	private int screen_height;
	
	//The background
	//changes from the menu background to the
	//game backgrounds when a game starts
	private Bitmap background;
	
	//The menu buttons
	private MenuButton select_level = null;
	private MenuButton game_continue = null; 
	private MenuButton options = null;
	private MenuButton back = null;
	
	//The select level buttons
	private LevelButton[] lvl_buttons = new LevelButton[LEVEL_COUNT];
	
	//----------------------------------
	//Everything need for the actual game play
	//----------------------------------
	private Level level;
	private int which_view;
	
	//where the player is right now.  Determines what is drawn on screen
	private Point current_cords;
	//for moving the screen around
	private Point last_pressed;
	private Point screen_move;
	
	
	//keeps track of the ant colony, tunnels, resources ect.
	//the actual player
	private Player player;

	//Game is created on app startup!
	public Game(Resources res, int screen_width, int screen_height) {
		this.res = res;
		this.screen_width = screen_width;
		this.screen_height = screen_height;
		
		current_cords = new Point(0,0);
		last_pressed = new Point(0,0);
		screen_move = new Point(0,0);
		
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
	

	public void screenDragged(MotionEvent event) {
		if (gameInGame()) {
			//for moving around views
			screenDraggedGame(event);
		} else if (gameInMenu()) {
			//for viewed more levels to select
			screenDraggedMenu(event);
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
			moveWorld();
			dontOverExtendBoundries();


		} else if (game_state == GS_GAME_PAUSED) {
			
		}
	}

	private void moveWorld() {
		current_cords.x += screen_move.x;
		current_cords.y += screen_move.y;
		screen_move.x *= VIEW_MOVE_DEACCELERATION_RATE;
		screen_move.y *= VIEW_MOVE_DEACCELERATION_RATE;
		if (Math.abs(screen_move.x) < VIEW_MOVE_STOPPING_POINT) screen_move.x = 0;
		if (Math.abs(screen_move.y) < VIEW_MOVE_STOPPING_POINT) screen_move.y = 0;
	}

	private void dontOverExtendBoundries() {
		if (current_cords.x < 0) current_cords.x = 0;
		if (current_cords.y < 0) current_cords.y = 0;
		if (current_cords.x > background.getWidth()-screen_width) current_cords.x = background.getWidth()-screen_width;
		if (current_cords.y > background.getHeight()-screen_height) current_cords.y = background.getHeight()-screen_height;
	}

	private void drawGame(Canvas canvas) {
		canvas.drawBitmap(background,(float)-current_cords.x,(float)-current_cords.y,null);
		player.tunnel.draw(canvas, current_cords);
		//player.ant_colony.draw(canvas, current_cords);
		
	}
	
	//Screen was pressed while in game
	private void screenPressedGame(MotionEvent event) {
		if (game_state == GS_GAME_PLAYING) {
			last_pressed.x = event.getX();
			last_pressed.y = event.getY();
		}
		
	}


	private void screenDraggedGame(MotionEvent event) {
		if (game_state == GS_GAME_PLAYING) {
			screen_move.x = -(event.getX()-last_pressed.x);
			screen_move.y = -(event.getY()-last_pressed.y);
			last_pressed.x = event.getX();
			last_pressed.y = event.getY();
		}
	}


	private void loadAndStartLevel(int which_level) {
		//TODO: Make a loading screen and once it is done loading remove the loading screen
		game_state = GS_GAME_PLAYING;
		level = new Level(res, which_level);
		which_view = VIEW_UNDERGROUND;
		background = level.whichImage(which_view);
		player = new Player();
		level.setPlayer(player);
		//TODO: Loading screen now goes away
	}
	
	private void changeView(int new_view) {
		which_view = new_view;
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
	

	private void screenDraggedMenu(MotionEvent event) {
		if (game_state == GS_MENU_SELECT_LEVEL) {
			//TODO: scroll screen to view more levels
		}
	}

	
}