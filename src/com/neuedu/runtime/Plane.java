package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileReader;

public class Plane extends BaseSprite implements Moveable, Drawable {
    private Image image;
    private boolean up, right, down, left;
    private boolean fire;
    private int speed = FrameConstant.GAME_SPEED * 3;
    private int hp=20;
    private int type;
    public int count=3;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Plane() {
        this((FrameConstant.FRAME_WIDTH - ImageMap.get("my01").getWidth(null)) / 2
                , FrameConstant.FREAM_HEIGHT - ImageMap.get("my01").getHeight(null)
                , ImageMap.get("my01"));
    }

    public Plane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
        fire();
        if (fire) {
            index++;
            if (index >= 10) {
                index = 0;
            }
        }
    }

    private int index = 0;

    /**
     * 开火方法
     * 判断开关是否打开
     * 创建一个子弹对象放入到gameFrame里的子弹集合中
     */
    public void fire() {
        if (fire && index == 0) {
            GameFrame gameFrame = DataStore.get("gameframe");
            if(type==1){
            gameFrame.bulletList.add(new Bullet(
                    getX() + (image.getWidth(null) / 2) - (ImageMap.get("mb01").getWidth(null) / 2),
                    getY() - ImageMap.get("mb01").getHeight(null),
                    ImageMap.get("mb01"),1
            ));
            }
            if (type==2) {
                if (count > 0) {
                    gameFrame.bulletList.add(new Bullet(
                            getX() + (image.getWidth(null) / 2) - (ImageMap.get("mb02").getWidth(null) / 2),
                            getY() - ImageMap.get("mb02").getHeight(null),
                            ImageMap.get("mb02"), 2
                    ));

                }
            }
        }
    }

    @Override
    public void move() {
        if (up) {
            setY(getY() - speed);
        }
        if (right) {
            setX(getX() + speed);
        }
        if (down) {
            setY(getY() + speed);
        }
        if (left) {
            setX(getX() - speed);
        }
        borderTesting();
    }

    public void borderTesting() {
        if (getX() < 0) {
            setX(0);
        }
        if (getX() > FrameConstant.FRAME_WIDTH - image.getWidth(null)) {
            setX(FrameConstant.FRAME_WIDTH - image.getWidth(null));
        }
        if (getY() < 30) {
            setY(30);
        }
        if (getY() > FrameConstant.FREAM_HEIGHT - image.getHeight(null)) {
            setY(FrameConstant.FREAM_HEIGHT - image.getHeight(null));
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {

            fire = true;
            type=1;
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {
            if(count>0){
                count--;
            }
            fire = true;
            type=2;

        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            GameFrame gameFrame = DataStore.get("gameframe");
            gameFrame.gameOver= !gameFrame.gameOver;
        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {

            fire = false;
            type=1;
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {

            fire = false;
            type=2;
        }
    }

    @Override
    public Rectangle getRectange() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
}
