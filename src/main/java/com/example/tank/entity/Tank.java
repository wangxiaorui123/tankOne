package com.example.tank.entity;

import com.example.tank.enums.Dir;
import com.example.tank.enums.Group;
import com.example.tank.frame.TankFrame;
import com.example.tank.util.Constant;
import com.example.tank.util.ResourceManager;

import java.awt.*;
import java.util.Random;

/**
 * @Project : tank
 * @Package Name : com.example.tank.entity
 * @Company home
 * @Author wangxiaorui
 * @Creation Date： 2020/7/17 20:04
 * @Description : 坦克实体类
 */
public class Tank {
    /**
     * 坦克宽度
     */
    public int TankWidth = ResourceManager.badTankL.getWidth();
    /**
     * 坦克高度
     */
    public int TankHeight = ResourceManager.badTankL.getHeight();
    /**
     * 子弹宽度(用于计算子弹发射位置)
     */
    private int bulletWidth = ResourceManager.bulletU.getWidth();
    /**
     * 子弹高度(用于计算子弹发射位置)
     */
    private int bulletHeight = ResourceManager.bulletU.getHeight();
    /**
     * 坦克x坐标
     */
    private int x;
    /**
     * 坦克y坐标
     */
    private int y;
    /**
     * 坦克速度（每次坦克移动数值）
     */
    private final int SPEED = 3;
    /**
     * 坦克方向
     */
    private Dir dir;
    /**
     * 坦克状态（是否移动）
     */
    private Boolean moving = true;
    /**
     * 窗口实例
     */
    private TankFrame tankFrame;
    /**
     * 坦克归属（好，坏）
     */
    private Group group = Group.Bad;
    /**
     * Rectangle属性
     */
    private Rectangle rectangle = new Rectangle();
    /**
     * 是否存活
     */
    private Boolean living = true;
    /**
     * 敌方坦克上次换方向的时间
     */
    private long changeDirTime = 0L;

    public Tank (int x, int y, Dir dir, TankFrame tankFrame, Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
    }

    /**
     * 坦克实体自己绘制自己的轨迹
     * @param g 画笔
     */
    public void paint(Graphics g) {
        if (!living) {
            tankFrame.enemyTankList.remove(this);
        }

        switch (dir) {
            case RIGHT:
                bulletWidth = ResourceManager.bulletR.getWidth();
                bulletHeight = ResourceManager.bulletR.getHeight();
                if (this.group == Group.Bad){
                    TankWidth = ResourceManager.badTankR.getWidth();
                    TankHeight = ResourceManager.badTankR.getHeight();

                    g.drawImage(ResourceManager.badTankR, x, y, null);
                } else {
                    TankWidth = ResourceManager.goodTankR.getWidth();
                    TankHeight = ResourceManager.goodTankR.getHeight();

                    g.drawImage(ResourceManager.goodTankR, x, y, null);
                }
                break;
            case LEFT:
                bulletWidth = ResourceManager.bulletL.getWidth();
                bulletHeight = ResourceManager.bulletL.getHeight();
                if (this.group == Group.Bad){
                    TankWidth = ResourceManager.badTankL.getWidth();
                    TankHeight = ResourceManager.badTankL.getHeight();

                    g.drawImage(ResourceManager.badTankL, x, y, null);
                } else {
                    TankWidth = ResourceManager.goodTankL.getWidth();
                    TankHeight = ResourceManager.goodTankL.getHeight();

                    g.drawImage(ResourceManager.goodTankL, x, y, null);
                }
                break;
            case DOWN:
                bulletWidth = ResourceManager.bulletD.getWidth();
                bulletHeight = ResourceManager.bulletD.getHeight();
                if (this.group == Group.Bad){
                    TankWidth = ResourceManager.badTankD.getWidth();
                    TankHeight = ResourceManager.badTankD.getHeight();

                    g.drawImage(ResourceManager.badTankD, x, y, null);
                } else {
                    TankWidth = ResourceManager.goodTankD.getWidth();
                    TankHeight = ResourceManager.goodTankD.getHeight();

                    g.drawImage(ResourceManager.goodTankD, x, y, null);
                }
                break;
            case UP:
                bulletWidth = ResourceManager.bulletU.getWidth();
                bulletHeight = ResourceManager.bulletU.getHeight();
                if (this.group == Group.Bad){
                    TankWidth = ResourceManager.badTankU.getWidth();
                    TankHeight = ResourceManager.badTankU.getHeight();

                    g.drawImage(ResourceManager.badTankU, x, y, null);
                } else {
                    TankWidth = ResourceManager.goodTankU.getWidth();
                    TankHeight = ResourceManager.goodTankU.getHeight();

                    g.drawImage(ResourceManager.goodTankU, x, y, null);
                }
                break;
        }
        moving();

        //不允许超出边界,判定是否超出边界
        isCrossBorder();
    }

    /**
     * 判定是否超出边界
     */
    private void isCrossBorder() {
        if (x < 0) {
            x = 0;
        }
        if (y < 0 + this.TankHeight/2) {
            y = 0 + this.TankHeight/2;
        }
        if (x > Constant.FrameSizeWidth - this.TankWidth) {
            x = Constant.FrameSizeWidth - this.TankWidth;
        }
        if (y > Constant.FrameSizeHeight - this.TankHeight) {
            y = Constant.FrameSizeHeight - this.TankHeight;
        }
    }

    /**
     * 移动坦克
     */
    private void moving(){

        if (!moving) { return; }

        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
        }

        if (Group.Bad == this.group && tankFrame.random.nextInt(100) > 95) {
            fire();
        }

        if (Group.Bad == this.group && (System.currentTimeMillis()/1000 - changeDirTime) > 2){
            changeDirTime();
        }
    }

    /**
     * 改变方向
     */
    private void changeDirTime() {
        this.dir = Dir.values()[tankFrame.random.nextInt(4)];
        changeDirTime = System.currentTimeMillis()/1000;
    }

    /**
     * 发射子弹
     */
    public void fire() {
        switch (dir) {
            case UP:
                tankFrame.bulletList.add(new Bullet(x + (TankWidth / 2) - (bulletWidth / 3), y, dir, tankFrame, group));
                break;
            case DOWN:
                tankFrame.bulletList.add(new Bullet(x + (TankWidth / 2) - (bulletWidth / 2) - 1, y + TankHeight - bulletHeight, dir, tankFrame, group));
                break;
            case LEFT:
                tankFrame.bulletList.add(new Bullet(x, y + (TankHeight / 2) - (bulletHeight / 2), dir, tankFrame, group));
                break;
            case RIGHT:
                tankFrame.bulletList.add(new Bullet(x + TankWidth - bulletWidth, y + (TankHeight / 2) - (bulletHeight / 2), dir, tankFrame, group));
                break;
        }
    }

    /**
     * 死亡
     */
    public void die() {
        living = false;

        int eX = this.x + this.TankWidth/2 - Explode.explodeWidth/2;
        int eY = this.y + this.TankHeight/2 - Explode.explodeHeight/2;
        tankFrame.explodeList.add(new Explode(eX, eY, tankFrame));
    }

    public Group getGroup() {
        return group;
    }

    public Boolean getLiving() {
        return living;
    }

    public Rectangle getRectangle() {
        rectangle.setBounds(x, y, TankWidth, TankHeight);
        return rectangle;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setMoving(Boolean moving) {
        this.moving = moving;
    }

    @Override
    public String toString() {
        return "Tank{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                '}';
    }
}
