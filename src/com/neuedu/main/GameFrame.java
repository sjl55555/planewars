package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.Background;
import com.sun.org.apache.bcel.internal.generic.NEW;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {
    private Background background= new Background();
    @Override
    public void paint(Graphics g) {
    background.draw(g);
    }
    /*
    *使用这个方法初始化窗口
     */
    public void init(){
        //设置好尺寸
        setSize(FrameConstant.FRAME_WIDTH,FrameConstant.FREAM_HEIGHT);
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
        new Thread(){
            @Override
            public void run(){
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }.start();
        setVisible(true);
    }
    private Image offScreenImage=null;//创建缓冲区
    @Override
    public void update(Graphics g ){
        if (offScreenImage==null){
            offScreenImage=this.createImage(1600,702);
        }
        Graphics goff=offScreenImage.getGraphics();//创建离线图片的实例，在图片缓冲区绘图
        paint(goff);
        g.drawImage(offScreenImage,0,0,null);
    }
}