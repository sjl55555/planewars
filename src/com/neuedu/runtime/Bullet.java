package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;
import com.neuedu.util.ImageUtil;
import javafx.geometry.HPos;


import java.awt.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.zip.DataFormatException;

public class Bullet extends BaseSprite implements Moveable, Drawable {
    private Image image;
    private int speed = FrameConstant.GAME_SPEED * 2;
    private int type;

    public int getType() {
        return type;
    }

    public Bullet() {
        this(0, 0, ImageMap.get("epb01"), 1);
    }

    public Bullet(int x, int y, Image image, int type) {
        super(x, y);
        this.type = type;
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        borderTesting();
    }

    @Override
    public void move() {
        setY(getY() - speed);
    }

    public void borderTesting() {
        if (getY() < 30 - image.getHeight(null)) {
            GameFrame gameFrame = DataStore.get("gameframe");
            gameFrame.bulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectange() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    public void collisionTesting(List<EnemyPlane> enemyPlanelist) {
        GameFrame gameFrame = DataStore.get("gameframe");
        for (EnemyPlane enemyPlane : enemyPlanelist) {
            if (enemyPlane.getRectange().intersects(this.getRectange())) {

                if (enemyPlane.getType() == 1) {
                    enemyPlanelist.remove(enemyPlane);
                    gameFrame.bulletList.remove(this);
                }
                if (type==2) {
                    if (enemyPlane.getType() == 2) {
                        enemyPlane.setHp(enemyPlane.getHp() - 1000);
                    }
                }
                if (type==1){
                    if (enemyPlane.getType() == 2) {
                        enemyPlane.setHp(enemyPlane.getHp() - 1);
                        gameFrame.bulletList.remove(this);
                    }
                }

                if (enemyPlane.getHp() <= 0) {

                   gameFrame.score=gameFrame.score+enemyPlane.getType()*10+enemyPlane.getType()*20;

                    enemyPlanelist.remove(enemyPlane);
                }
            }
        }
    }

    public void collisionTesting1(Boss boss) {
        GameFrame gameFrame = DataStore.get("gameframe");
        if (boss.getRectange().intersects(this.getRectange())) {

            if (gameFrame.boss.getH() > 0) {
                if (type == 2) {
                    gameFrame.boss.setH(gameFrame.boss.getH() - 1000);
                } else {
                    gameFrame.boss.setH(gameFrame.boss.getH() - 4);
                    gameFrame.bulletList.remove(this);
                }

//                if (gameFrame.boss.getH() <= 0) {
//                    gameFrame.gameOver = true;
//                }


            }
        }
    }
}







