package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.*;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {
    //创建背景对象
    private Background background = new Background();
    //创建飞机对象
    private Plane plane = new Plane();
    //创建敌方飞机集合
    private  EnemyPlane enemyPlane=new EnemyPlane();
    //创建boss集合
   public Boss boss = new Boss(300,-1000);
    //创建子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();
    //创建敌方飞机集合
    public final List<EnemyPlane>enemyPlaneList=new CopyOnWriteArrayList<>();
    //创建敌方子弹集合
    public final List<EnemyBullet>enemyBulletList=new CopyOnWriteArrayList<>();
    //创建技能集合
    public final List<Prop> propList=new CopyOnWriteArrayList<Prop>();
    //创建通关背景对象
    private Tgbg tgbg=new Tgbg();


    //游戏结束
    public boolean gameOver=false;

    public boolean isGameOver() {
        return gameOver;
    }

    public  static int score=0;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private Random random=new Random();
    @Override
    public void paint(Graphics g) {
        if (!gameOver){
        background.draw(g);
        plane.draw(g);
        for (Bullet bullet : bulletList) {
            bullet.draw(g);
        }
        for (EnemyBullet enemyBullet : enemyBulletList) {
            enemyBullet.draw(g);
        }
        for (EnemyPlane enemyPlane : enemyPlaneList) {
            enemyPlane.draw(g);
        }
        for (Bullet bullet : bulletList) {
            bullet.collisionTesting(enemyPlaneList);
            }
        
        boss.draw(g);
        g.setFont(new Font("楷体",5,40));
        g.setColor(new Color(0xFF5F4A));
        g.drawString("得分："+score,100,100);

//            g.setFont(new Font("楷体",5,40));
//            g.setColor(new Color(0xFF5F4A));
//            g.drawString("hp："+boss.getH(),100,150);

            g.setFont(new Font("楷体",5,40));
            g.setColor(new Color(0xFF5F4A));
            g.drawString("hp: "+plane.getHp(),100,250);
        for (EnemyBullet enemyBullet : enemyBulletList) {
            enemyBullet.collisionTesting(plane);
        }
        boss.collisionTesting(plane);
            for (Prop prop : propList) {
                prop.collisionTesting(plane);
            }
        for (Bullet bullet : bulletList) {
                bullet.collisionTesting1(boss);
            }
            for (Prop prop : propList) {
                prop.draw(g);
            }
            GameFrame gameFrame = DataStore.get("gameframe");
            if(gameOver) {
                g.setFont(new Font("楷体", 5, 100));
                g.setColor(new Color(0xFF5F4A));
                g.drawString("GAME OVER " , 200, 600);

            }
            if(gameFrame.boss.getH()<=0&&gameFrame.boss.getY()<1000&&gameFrame.boss.getY()>=30){
                tgbg.draw(g);
                g.setFont(new Font("圆体", 5, 80));
                g.setColor(new Color(0x3F72FF));
                g.drawString("恭喜你通关了 " , 200, 500);
                g.drawString("得分："+score,100,100);
            }

            if(gameFrame.boss.getH()>0&&gameFrame.boss.getY()>1000){
                g.setFont(new Font("楷体", 5, 40));
                g.setColor(new Color(0xFF5F4A));
                g.drawString("你失败了" , 300, 500);
                g.drawString("得分："+score,100,100);
            }





        
//        g.setColor(Color.red);
//        g.drawString(""+enemyBulletList.size(),100,100);
    }}

    /*
     *使用这个方法初始化窗口
     */
    public void init() {
        //设置好尺寸
        setSize(FrameConstant.FRAME_WIDTH, FrameConstant.FREAM_HEIGHT);
        //设置居中
        setLocationRelativeTo(null);
        enableInputMethods(false);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        //添加键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                plane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);
            }
        });
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GameFrame gameFrame= DataStore.get("gameframe");
                    if (random.nextInt(1000)>990){
                        gameFrame.propList.add(new Prop(random.nextInt(700)
                                ,random.nextInt(800),ImageMap.get("blood01")));
                    }
                }
            }

        }.start();
        //游戏初始时添加一些敌方飞机
        enemyPlaneList.add(new EnemyPlane(300,30,1));
        enemyPlaneList.add(new EnemyPlane(300,-500,1));
        enemyPlaneList.add(new EnemyPlane(600,30,1));
        enemyPlaneList.add(new EnemyPlane(450,30,1));
        enemyPlaneList.add(new EnemyPlane(150,-120,2));
        enemyPlaneList.add(new EnemyPlane(0,-80,2));
        enemyPlaneList.add(new EnemyPlane(30,-60,2));
        enemyPlaneList.add(new EnemyPlane(200,-100,2));
//        //添加一些个技能包
//        propList.add(new Prop(300,-30, ImageMap.get("blood01")));
//        propList.add(new Prop(500,-80, ImageMap.get("blood01")));
//        propList.add(new Prop(600,-110, ImageMap.get("blood01")));
//        propList.add(new Prop(100,60, ImageMap.get("blood01")));
        setVisible(true);

    }

    private Image offScreenImage = null;//创建缓冲区

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH,FrameConstant.FREAM_HEIGHT);
        }
        Graphics goff = offScreenImage.getGraphics();//创建离线图片的实例，在图片缓冲区绘图
        paint(goff);
        g.drawImage(offScreenImage, 0, 0, null);
    }
//    public void enemyPlaneListAdd(){
//        while (true){
//
//            enemyPlaneList.add(new EnemyPlane(random.nextInt(700),-random.nextInt(4000), ImageMap.get("ep01")));
//            if(enemyPlaneList.size()>10){
//                break;
//            }
//        }
//
//    }

}
