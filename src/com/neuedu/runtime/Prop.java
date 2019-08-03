package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.Map;
import java.util.Random;

public class Prop extends BaseSprite implements Drawable, Moveable {
    private Image image;
    private int speed = FrameConstant.GAME_SPEED;
    private Random random = new Random();

    public Prop() {
        this(0, 0, ImageMap.get("blood01"));
    }

    public Prop(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public int getX() {
        return super.getX();
    }


    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
    }

    @Override
    public void move() {
        setY(getY() + speed);

        borderTesting();
    }

    public void borderTesting() {
        if (getY() > FrameConstant.FREAM_HEIGHT) {
            GameFrame gameFrame = DataStore.get("gameframe");
            gameFrame.propList.remove(this);
        }
    }

    @Override
    public Rectangle getRectange() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    public void collisionTesting(Plane plane) {
        GameFrame gameFrame = DataStore.get("gameframe");
        if (plane.getRectange().intersects(this.getRectange())) {
            plane.setHp(plane.getHp() + 1);
            gameFrame.propList.remove(this);
        }
    }
}

