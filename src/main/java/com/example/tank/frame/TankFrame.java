package com.example.tank.frame;

import com.example.tank.entity.Bullet;
import com.example.tank.entity.Explode;
import com.example.tank.entity.Tank;
import com.example.tank.enums.Dir;
import com.example.tank.enums.Group;
import com.example.tank.util.Constant;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author:wangxiaorui
 * @create 2020年5月2日18:57:05
 */
public class TankFrame extends Frame{

    Tank myTank = new Tank(Constant.tankDefaultX, Constant.tankDefaultY, Constant.tankDefaultDir, this, Group.Good);

    //子弹集合
    public List<Bullet> bulletList = new ArrayList<Bullet>();

    //敌方坦克集合
    public List<Tank> enemyTankList = new ArrayList<Tank>();

    //爆炸集合
    public List<Explode> explodeList = new ArrayList<Explode>();
    public Random random = new Random();

    public TankFrame() {
        this.setSize(Constant.FrameSizeWidth, Constant.FrameSizeHeight);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("坦克大战1.0");

        this.addKeyListener(new MyKeyListener());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(Constant.WindowClosingStatus);
            }
        });
    }

    //解决闪烁问题开始================================================
    Image offScreenImage = null;

    /**
     * 双缓冲（游戏常用思路）
     * 闪烁的本质原因是因为画笔还没画完（计算时间），就迎来了下一次刷新
     * 先利用内存在内存中按照paint逻辑画出一张图，然后再用屏幕画笔把图片画到屏幕上
     * 重写update方法，刷新是先进入update方法在开始paint
     * @param g 画笔
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null){
            offScreenImage = this.createImage(Constant.FrameSizeWidth, Constant.FrameSizeHeight);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(Constant.offScreenImageXAndY, Constant.offScreenImageXAndY, Constant.FrameSizeWidth, Constant.FrameSizeHeight);
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(offScreenImage, Constant.offScreenImageXAndY, Constant.offScreenImageXAndY, null);
    }
    //解决闪烁问题结束================================================

    @Override
    public void paint(Graphics g){
        myTank.paint(g);
        /*//这种方式循环在删除的时候会引起错误，迭代器迭代过程中删除元素
        for (Bullet bullet : bulletList) {
            bullet.paint(g);
        }*/
        //绘制所有子弹
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }
        //绘制所有敌方坦克
        for (int i = 0; i < enemyTankList.size(); i++) {
            enemyTankList.get(i).paint(g);
        }
        //绘制死亡爆炸
        for (int i = 0; i < explodeList.size(); i++) {
            explodeList.get(i).paint(g);
        }
        //碰撞检测
        for (Bullet bullet : bulletList){
            for (Tank tank : enemyTankList){
                collisionDetection(bullet, tank);
            }
            collisionDetection(bullet, myTank);
        }

        if (!myTank.getLiving()){
            //System.out.println("游戏结束");
        }

    }

    private void collisionDetection(Bullet bullet, Tank tank) {
        //区分组，避免误伤友方
        if (bullet.getGroup() == tank.getGroup()) {
            return;
        }

        //得到子弹和坦克的区域
        Rectangle rectangle1 = bullet.getRectangle();
        Rectangle rectangle2 = tank.getRectangle();

        if (rectangle1.intersects(rectangle2)){
            bullet.die();
            tank.die();
        }
    }

    class MyKeyListener extends KeyAdapter{

        /**
         * 4个值表示当前键盘的方向按键情况
         */
        private Boolean bU = false;
        private Boolean bD = false;
        private Boolean bR = false;
        private Boolean bL = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            switch (keyCode){
                case KeyEvent.VK_LEFT :
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT :
                    bR = true;
                    break;
                case KeyEvent.VK_UP :
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN :
                    bD = true;
                    break;
                default:
                    break;
            }

            setMyTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();

            switch (keyCode){
                case KeyEvent.VK_LEFT :
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT :
                    bR = false;
                    break;
                case KeyEvent.VK_UP :
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN :
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL :
                    myTank.fire();
                    break;
                default:
                    break;
            }

            setMyTankDir();
        }

        public void setMyTankDir(){
            if (!bU && !bR && !bD && !bL) {
                myTank.setMoving(false);
                return;
            }

            myTank.setMoving(true);
            if (bL) { myTank.setDir(Dir.LEFT); }
            if (bR) { myTank.setDir(Dir.RIGHT); }
            if (bU) { myTank.setDir(Dir.UP); }
            if (bD) { myTank.setDir(Dir.DOWN); }
        }
    }
}
