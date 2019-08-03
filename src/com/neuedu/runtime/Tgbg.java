package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class Tgbg extends BaseSprite implements Drawable ,Moveable{
    private Image image;

    public Tgbg() {
        this(0,0,ImageMap.get("bg02"));
    }

    public Tgbg(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public void move() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(), image.getWidth(null), image.getHeight(null),null);

    }
}
