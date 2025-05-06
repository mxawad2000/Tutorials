package com.example.myapplication.myutil;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public abstract class SwipeListener2 implements View.OnTouchListener{
    public static final int SWIPE_LEFT = 1;
    public static final int SWIPE_RIGHT = 2;
    public static final int SWIPE_UP = 3;
    public static final int SWIPE_DOWN = 4;

    private int swipeDirection = -1;
    private float velocityMinThreshold =180f;
    private float distanceMinThreshold =50f;
    public SwipeListener2(){
        super();
    }
    public int getSwipeType(){ return swipeDirection;}
    public void setVelocityThreshold(float th){this.velocityMinThreshold = th;}
    float downX,downY;
    float downTime;
    public boolean onTouch(View view, MotionEvent event){
        try {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    downX = event.getX();
                    downY = event.getY();
                    downTime = System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_UP:
                    float dx = event.getX() - downX; //delta x - displacement of x.
                    float dy = event.getY() - downY; //delta y - displacement of y
                    float dTime = System.currentTimeMillis() - downTime;
                    float velocityX = ( event.getX() - downX ) / dTime;
                    float velocityY = ( event.getY() - downY ) / dTime;
                    check(dx,dy,velocityX,velocityY);
                    break;
            }
            return true;
        }catch(Exception ex){ Log.i("MyApp",ex.getMessage());}
        return true;
    }
    private void check(float dx, float dy,float velocityX, float velocityY){
        if (Math.abs(dx) > Math.abs(dy)) { //horizontal
            if (Math.abs(dx) > this.distanceMinThreshold &&
                    Math.abs(velocityX) > this.velocityMinThreshold) {
                if (dx > 0) {//right
                    this.swipeDirection = SWIPE_RIGHT;
                    handleSwipe();
                } else { //left
                    this.swipeDirection = SWIPE_LEFT;
                    handleSwipe();
                }
            }
        }else{ //vertical
            if (Math.abs(dy) > this.distanceMinThreshold &&
                    Math.abs(velocityY) > this.velocityMinThreshold) {
                if (dy > 0) {//UP
                    this.swipeDirection = SWIPE_DOWN;
                    handleSwipe();
                } else { //down
                    this.swipeDirection = SWIPE_UP;
                    handleSwipe();
                }
            }
        }
    }

    public abstract void handleSwipe();
};

