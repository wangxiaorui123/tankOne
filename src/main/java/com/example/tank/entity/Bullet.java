package com.example.tank.entity;

import com.example.tank.enums.Dir;
import com.example.tank.enums.Group;
import com.example.tank.frame.TankFrame;
import com.example.tank.util.Constant;
import com.example.tank.util.ResourceManager;

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
     * 子弹x坐标
     */
    private int x;
    /**
     * 子弹y坐标
     */
    private int y;
    /**
     * 子弹宽度
     */
    private int bulletWidth = ResourceManager.bulletU.getWidth();
    /**
     * 子弹高度
     */
    private int bulletHeight = ResourceManager.bulletU.getHeight();
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
    private TankFrame tankFrame;
    /**
     * Rectangle属性
     */
    private Rectangle rectangle = new Rectangle();
    /**
     * 是否存活
     */
    private Boolean living = true;
    /**
     * 坦克归属（好，坏）
     */
    private Group group = Group.Bad;

    public Bullet (int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
    }

    /**
     * 子弹实体自己绘制自己的轨迹
     * @param g 画笔
     */
    public void paint(Graphics g) {
        if (!living) {
            tankFrame.bulletList.remove(this);
        }

        switch (dir) {
            case RIGHT:
                bulletWidth = ResourceManager.bulletR.getWidth();
                bulletHeight = ResourceManager.bulletR.getHeight();

                g.drawImage(ResourceManager.bulletR, x, y, null);
                break;
            case LEFT:
                bulletWidth = ResourceManager.bulletL.getWidth();
                bulletHeight = ResourceManager.bulletL.getHeight();

                g.drawImage(ResourceManager.bulletL, x, y, null);
                break;
            case DOWN:
                bulletWidth = ResourceManager.bulletD.getWidth();
                bulletHeight = ResourceManager.bulletD.getHeight();

                g.drawImage(ResourceManager.bulletD, x, y, null);
                break;
            case UP:
                bulletWidth = ResourceManager.bulletU.getWidth();
                bulletHeight = ResourceManager.bulletU.getHeight();

                g.drawImage(ResourceManager.bulletU, x, y, null);
                break;
        }
        moving();

        if (x < 0 || y < 0 || x > Constant.FrameSizeWidth || y > Constant.FrameSizeHeight) {
            tankFrame.bulletList.remove(this);
        }
    }

    /**
     * 死亡
     */
    public void die() {
        this.living = false;
    }

    /**
     * 移动子弹
     */
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

    public Group getGroup() {
        return group;
    }

    public Rectangle getRectangle() {
        rectangle.setBounds(x, y, bulletWidth, bulletHeight);
        return rectangle;
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
