package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;
import sun.font.FontRunIterator;
import sun.text.normalizer.CharTrie;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Boss extends BaseSprite implements Drawable ,Moveable{
    private List<Image> imageList =new ArrayList<>();
    private int speed= FrameConstant.GAME_SPEED*2;
    private int h;

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Boss(int x, int y) {
        super(x, y);
        for (int i =1; i <10 ; i++) {
            imageList.add(ImageMap.get("boss"+i));
        }
        h=200;
    }

    int index=0;
    private boolean right=true;
    @Override
    public void draw(Graphics g) {

        g.drawImage(imageList.get(index++/5),getX(),getY(),imageList.get(0).getWidth(null),imageList.get(0).getHeight(null),
                null);
        if(index>=45){
            index=0;

        }
        move();
    }
    @Override
    public void move() {
        if (right) {
            setX(getX()+speed);
            setY(getY()+speed);
        }else {
            setX(getX() - speed);
        }
        borderTesting();
    }
    public void borderTesting(){
        if (getX()+imageList.get(0).getWidth(null)>=FrameConstant.FRAME_WIDTH){
            right=false;
        }else if(getX()<0){
            right=true;
        }
    }
    @Override
    public Rectangle getRectange() {
        return new Rectangle(getX(),getY(),imageList.get(0).getWidth(null),imageList.get(0).getHeight(null));
    }

    public void collisionTesting(Plane plane){
        GameFrame gameFrame= DataStore.get("gameframe");
        if(plane.getRectange().intersects(this.getRectange())){
            if(plane.getHp()>0){
            plane.setHp(plane.getHp()-2);}
            if(plane.getHp()<=0){
            gameFrame.gameOver=true;
            }
            gameFrame.bulletList.remove(this);
        }

    }
}


