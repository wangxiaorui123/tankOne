package com.example.tank.entity;

import com.example.tank.frame.TankFrame;
import com.example.tank.util.Audio;
import com.example.tank.util.ResourceManager;

import java.awt.*;

/**
 * @Project : tank
 * @Package Name : com.example.tank.entity
 * @Company home
 * @Author wangxiaorui
 * @Creation Date： 2020/9/9 21:29
 * @Description : 坦克死亡的爆炸效果
 */
public class Explode {
    /**
     * 爆炸宽度
     */
    public static int explodeWidth = ResourceManager.explodes[0].getWidth();

    /**
     * 爆炸高度
     */
    public static int explodeHeight = ResourceManager.explodes[0].getHeight();

    /**
     * 爆炸的时候的X坐标
     */
    private int x;

    /**
     * 爆炸的时候的Y坐标
     */
    private int y;

    /**
     * 窗口实例
     */
    private TankFrame tankFrame;

    /**
     * 爆炸阶段
     */
    private int step = 0;

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        //new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceManager.explodes[step++], x, y, null);

        if (step >= ResourceManager.explodes.length){
            tankFrame.explodeList.remove(this);
        }
    }
}
