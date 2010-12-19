package com.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

public class Antzwarz extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN); 
        
        Display display = getWindowManager().getDefaultDisplay();         
        setContentView(new Panel(this,display.getWidth(),display.getHeight()));
    }
}