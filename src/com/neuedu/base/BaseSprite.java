package com.neuedu.base;

import com.sun.org.apache.regexp.internal.RE;

import java.awt.*;

public  abstract class BaseSprite {
    private int x;
    private int y;

    public BaseSprite() {
    }

    public BaseSprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public Rectangle getRectange(){
        return null;
    }

    public abstract void move();
}
