package com.example.tank.entity;

import com.example.tank.enums.Dir;
import com.example.tank.enums.Group;
import com.example.tank.frame.TankFrame;
import com.example.tank.util.ResourceManager;

import java.awt.*;

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
    private int TankWidth = ResourceManager.tankU.getWidth();
    /**
     * 坦克高度
     */
    private int TankHeight = ResourceManager.tankU.getHeight();
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
    private final int SPEED = 2;
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
                TankWidth = ResourceManager.tankR.getWidth();
                TankHeight = ResourceManager.tankR.getHeight();
                bulletWidth = ResourceManager.bulletR.getWidth();
                bulletHeight = ResourceManager.bulletR.getHeight();

                g.drawImage(ResourceManager.tankR, x, y, null);
                break;
            case LEFT:
                TankWidth = ResourceManager.tankL.getWidth();
                TankHeight = ResourceManager.tankL.getHeight();
                bulletWidth = ResourceManager.bulletL.getWidth();
                bulletHeight = ResourceManager.bulletL.getHeight();

                g.drawImage(ResourceManager.tankL, x, y, null);
                break;
            case DOWN:
                TankWidth = ResourceManager.tankD.getWidth();
                TankHeight = ResourceManager.tankD.getHeight();
                bulletWidth = ResourceManager.bulletD.getWidth();
                bulletHeight = ResourceManager.bulletD.getHeight();

                g.drawImage(ResourceManager.tankD, x, y, null);
                break;
            case UP:
                TankWidth = ResourceManager.tankU.getWidth();
                TankHeight = ResourceManager.tankU.getHeight();
                bulletWidth = ResourceManager.bulletU.getWidth();
                bulletHeight = ResourceManager.bulletU.getHeight();

                g.drawImage(ResourceManager.tankU, x, y, null);
                break;
        }
        moving();
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

        if (tankFrame.random.nextInt(100) > 90) {
            fire();
        }
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
    }

    public Group getGroup() {
        return group;
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
