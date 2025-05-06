package com.example.myapplication.myutil;

import android.view.MotionEvent;
import android.view.View;

public abstract class NumTapsListener implements View.OnTouchListener {
    protected int taps=0;
    protected long startTime,endTime;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://event of finger is touching
                startTime = System.currentTimeMillis();
                if(startTime - endTime > 300) { //beginning of the tapping
                    taps = 0;
                    new Thread(() -> {
                        try { Thread.sleep(400);} catch (Exception ignored) {
                        }
                        handleTaps();
                    }).start();
                }
                return true; //event was not consumed
            case MotionEvent.ACTION_UP: //event of finger is lifted
                endTime = System.currentTimeMillis();
                taps++;
        }
        return false;
    }
    public abstract void handleTaps();
}