package com.game;

import android.graphics.Canvas;

class GameLoop extends Thread {
    private Panel panel;
    private boolean run = false;
    
    public GameLoop(Panel panel) {
        this.panel = panel;
    }
    
    public void setRunning(boolean run) {
        this.run = run;
    }

    public boolean isRunning() {
        return run;
    }
    
    //THEEE LOOPPPP
    @Override
    public void run() {
        Canvas c;
        while (run) {
            c = null;
            try {
                c = panel.getHolder().lockCanvas(null);
                synchronized (panel.getHolder()) {
                    panel.update(c);
                }
            } finally {
                // do this in a finally so that if an exception is thrown
                // during the above, we don't leave the Surface in an
                // inconsistent state
                if (c != null) {
                    panel.getHolder().unlockCanvasAndPost(c);
                }
            } 
        }
    }
}