package com.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

//For each level this class contains the locations of all food,
//minerals, enemy ant colonies, initial location for the, size of the world,
//the initial tunnel size and location, size of the underground
//
public class Level implements Constants {
	
	private int which_level;
	
	private Bitmap underground;
	private Bitmap topside;
	
	private int world_width;
	private int world_height;
	
	private int tunnels_width;
	private int tunnels_height;
	
	private Mineral[] food;
	private Mineral[] minerals;
	
	
	//what the player starts with
	private Tunnel initial_tunnel;
	private AntColony initial_colony;
	private int initial_food;
	private int initial_minerals;
	//initial space is generated by the size of the tunnel
	
	//enemy colony stuff
//	private int[][] enemy_tunnel_locations;
//	private EnemyColony[] enemy_ant_colonies;

	public Level(Resources res, int which_level) {
		this.which_level = which_level;
		loadImages(res);
		loadResources();
		loadPlayer(res);
	}
	
	public Bitmap whichImage(int which_view) {
		if (which_view == VIEW_UNDERGROUND)
			return underground;
		else if (which_view == VIEW_TOPSIDE)
			return topside;
		return null;
	}


	private void loadImages(Resources res) {
		if (which_level == LEVEL_1) {
			underground = BitmapFactory.decodeResource(res,R.drawable.lvl_1_underground);
			topside = BitmapFactory.decodeResource(res,R.drawable.lvl_1_topside);
		}
		
		//no this is not reversed, this is correct
		//due to how we rotate the images
		tunnels_width = underground.getHeight();
		tunnels_height = underground.getWidth();
		world_width = topside.getWidth();
		world_height = topside.getHeight();
	}
	
	private void loadResources() {
		initial_food = 100;
		initial_minerals = 0;
	}

	private void loadPlayer(Resources res) {
		initial_tunnel = new Tunnel(tunnels_width, tunnels_height);
		loadTunnels(res);
		initial_colony = new AntColony();

		
	}

	private void loadTunnels(Resources res) {
		if (which_level == LEVEL_1) {
			initial_tunnel.loadLevel1Tunnel(res, underground);
		}
	}

	public void setPlayer(Player player) {
		player.tunnel = initial_tunnel;
		player.colony = initial_colony;
		player.food = initial_food;
		player.minerals = initial_minerals;
	}

}
