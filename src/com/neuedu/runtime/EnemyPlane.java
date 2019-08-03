package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.print.PrinterAbortException;
import java.lang.reflect.Type;
import java.security.PrivateKey;
import java.util.Random;

public class EnemyPlane extends BaseSprite implements Moveable, Drawable {
    private Image image;
    private Image image1;
    private int speed= FrameConstant.GAME_SPEED;
    private Random random=new Random();
    private int type;
    static int hp;
    public int getType() {
        return type;
    }

    public EnemyPlane(){
        this(0,0,1);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public EnemyPlane(int x, int y, int type) {
        super(x, y);
        this.type=type;
        this.image = ImageMap.get("ep01");
        this.image1 = ImageMap.get("ep02");
        init();

    }
    void init(){
        if(type==2){
            hp=10;
        }
    }

    @Override
    public void draw(Graphics g) {
        if(type==1){
            g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        }else if (type==2){
            g.drawImage(image1,getX(),getY(),image.getWidth(null),image.getHeight(null),null);

        }
        move();
        fire();
    }
    public void fire(){
        GameFrame gameFrame=DataStore.get("gameframe");
        if(type==1) {
            if (random.nextInt(1000) > 992&&getY()>0) {
                gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null) / 2) - (ImageMap.get("epb01").getWidth(null) / 2),
                        getY() + image.getHeight(null), ImageMap.get("epb01")));
            }
        }
        else if (type==2){
            if (random.nextInt(1000) > 992&&getY()>0) {
                gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image1.getWidth(null) / 2) - (ImageMap.get("ebp02").getWidth(null) / 2),
                        getY() + image1.getHeight(null), ImageMap.get("ebp02")));
            }

        }
    }
    private boolean right=true;
    @Override
    public void move() {
        if(type==1){
            setY(getY()+speed);
        }
        else if(type==2){
                if (right) {
                    setX(getX()+speed);
                    setY(getY()+speed);
                }else {
                    setX(getX() - speed);
                }
        }
    borderTesting();
    }
    public void borderTesting() {
        if (type==1) {
            if (getY() > FrameConstant.FREAM_HEIGHT) {
                GameFrame gameFrame = DataStore.get("gameframe");
                gameFrame.enemyPlaneList.remove(this);
            }
        }
        else if(type==2){
            if (getX()+image1.getWidth(null)>=FrameConstant.FRAME_WIDTH){
                right=false;
            }else if(getX()<0){
                right=true;
            }
        }
    }

   @Override
   public Rectangle getRectange() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));    }
}
