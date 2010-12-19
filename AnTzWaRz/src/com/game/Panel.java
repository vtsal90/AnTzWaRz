package com.game;


import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class Panel extends SurfaceView implements SurfaceHolder.Callback, Constants {
    
    private GameLoop thread;
    private Game game;

    private int screen_width;
    private int screen_height;   
    
    public Panel(Context context, int width, int height) {
        super(context);
        screen_width = width;
        screen_height = height;
        
        thread = new GameLoop(this);

        setFocusable(true);
        thread.setRunning(true);
        thread.start();
    }
    
    // Process the MotionEvent, AKA screen touches.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        synchronized (getHolder()) {
        	//Screen pressed
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
            	game.screenPressed(event);
            } 
            //Drag
            else if (event.getAction() == MotionEvent.ACTION_MOVE) {

            }
            //Screen released
            else if (event.getAction() == MotionEvent.ACTION_UP) {

            }
            return true;
        }
    }

    // Draw stuff on the screen
	@Override
    public void onDraw(Canvas canvas) { 	
    	if (canvas == null) 
    		return;

    	game.draw(canvas);
    }
	

	//Called by GameThread
	public void update(Canvas c) {
		if (game != null) {
			//update all logic
			game.update();
			//draw everything
			onDraw(c);
		} else {
			game = new Game(getResources(), screen_width, screen_height);
		}
	}

    /**
     * Called on creation of the SurfaceView.
     * Which could be on first start or relaunch.
     */
    public void surfaceCreated(SurfaceHolder holder) {
        if (!thread.isAlive()) {
            thread = new GameLoop(this);
        }
        thread.setRunning(true);
        thread.start();
    }

    /**
     * Called if the SurfaceView should be destroyed.
     * We try to finish the game loop thread here.
     */
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // we will try it again and again...
            }
        }
        Log.i("thread", "Thread terminated...");
    }

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

}