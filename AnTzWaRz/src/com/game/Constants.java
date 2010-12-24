package com.game;

//All constants go here
public interface Constants {
	
	//All the game states
	public final int GS_MENU_HOME = 1;
	public final int GS_MENU_SELECT_LEVEL = 2;
	public final int GS_MENU_OPTIONS = 3;
	
	public final int GS_GAME_PLAYING = 4;
	public final int GS_GAME_PAUSED = 5;
	
	//Menu buttons
	public final int MB_SELECT_LEVEL = 1;
	public final int MB_CONTINUE = 2;
	public final int MB_OPTIONS = 3;
	public final int MB_BACK = 4;
	public final int PAUSE_RESUME = 5;
	public final int PAUSE_SAVE_QUIT = 6;
	public final int PAUSE_QUIT = 7;

	
	//UI Buttons
	public final int UI_BUTTON_PAUSE = 1;
	public final int UI_BUTTON_CHANGE_VIEW = 2;
	public final int UI_BUTTON_NOTIFICATION = 3;
	
	//Level count
	public final int LEVEL_COUNT = 5;
	
	//Levels
	public final int LEVEL_1 = 1;
	public final int LEVEL_2 = 2;
	public final int LEVEL_3 = 3;
	public final int LEVEL_4 = 4;
	public final int LEVEL_5 = 5;
	
	//Views
	public final int VIEW_UNDERGROUND = 1;
	public final int VIEW_TOPSIDE = 2;
	
	//Move deacceleration rate
	public final double VIEW_MOVE_DEACCELERATION_RATE = 0.9;
	//stop moving when
	public final double VIEW_MOVE_STOPPING_POINT = 0.1;
	
	//Tunnels: image size for edges/nodes
	public final int TUNNEL_BLOCK_IMAGE_SIZE = 20;
	
	//Ants!
	public final int ANT_QUEEN = 1;
	public final int ANT_LARVA = 2;
	public final int ANT_WORKER = 3;
	public final int ANT_WARRIOR = 4;
	public final int ANT_SCIENTIST = 5;
}
