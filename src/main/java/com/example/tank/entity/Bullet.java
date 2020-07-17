package com.example.tank.entity;

import com.example.tank.enums.Dir;
import com.example.tank.frame.TankFrame;
import com.example.tank.util.Constant;

import java.awt.*;

/**
 * @Project : tank
 * @Package Name : com.example.tank.entity
 * @Company home
 * @Author wangxiaorui
 * @Creation Date： 2020/7/17 21:15
 * @Description : 子弹实体类
 */
public class Bullet {
    /**
     * 子弹宽度
     */
    private final int bulletWidth = 5;
    /**
     * 子弹高度
     */
    private final int bulletHeight = 5;
    /**
     * 子弹x坐标
     */
    private int x;
    /**
     * 子弹y坐标
     */
    private int y;
    /**
     * 子弹速度（每次子弹移动数值）
     */
    private final int SPEED = 5;
    /**
     * 坦克方向
     */
    private Dir dir;
    /**
     * 窗口实例
     */
    private TankFrame tankFrame = null;

    public Bullet (int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    /**
     * 子弹实体自己绘制自己的轨迹
     * @param g 画笔
     */
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, bulletWidth, bulletHeight);
        g.setColor(color);
        moving();

        if (this.x < 0 || this.y < 0 || this.x > Constant.FrameSizeWidth || this.y > Constant.FrameSizeHeight) {
            tankFrame.bulletList.remove(this);
        }
    }

    private void moving(){
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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return "Bullet{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                '}';
    }
}
