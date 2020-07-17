package com.example.tank.entity;

import com.example.tank.enums.Dir;
import com.example.tank.frame.TankFrame;

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
    private final int TankWidth = 30;
    /**
     * 坦克高度
     */
    private final int TankHeight = 30;
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
    private final int SPEED = 5;
    /**
     * 坦克方向
     */
    private Dir dir;
    /**
     * 坦克状态（是否移动）
     */
    private Boolean moving = false;
    /**
     * 窗口实例
     */
    private TankFrame tankFrame = null;

    public Tank (int x, int y, Dir dir, TankFrame tankFrame){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    /**
     * 坦克实体自己绘制自己的轨迹
     * @param g 画笔
     */
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.fillRect(x, y, TankWidth, TankHeight);
        g.setColor(color);
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
    }

    /**
     * 发射子弹
     */
    public void fire() {
        tankFrame.bulletList.add(new Bullet(this.x, this.y, this.dir));
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Boolean getMoving() {
        return moving;
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
