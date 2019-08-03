package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;

import java.awt.*;

public class EnemyBullet extends BaseSprite implements Moveable, Drawable {
   private  Image image;
   public int speed= FrameConstant.GAME_SPEED*3;
   public EnemyBullet(){

   }

    public EnemyBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();
        borderTesting();
    }

    @Override
    public void move() {
    setY(getY()+speed);
    }
    public void borderTesting(){
        if (getY()>FrameConstant.FREAM_HEIGHT) {
            GameFrame gameFrame= DataStore.get("gameframe");
            gameFrame.enemyBulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectange() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }

    public void collisionTesting(Plane plane){
       GameFrame gameFrame=DataStore.get("gameframe");
        if(plane.getRectange().intersects(this.getRectange())){
            plane.setHp(plane.getHp()-1);
            gameFrame.enemyBulletList.remove(this);
            if(plane.getHp()<=0){
            gameFrame.gameOver=true;}
        }

   }
}
