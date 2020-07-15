package com.example.tank.frame;

import com.example.tank.frame.listener.MyKeyListener;
import com.example.tank.util.Constant;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author:wangxiaorui
 * @create 2020年5月2日18:57:05
 */
public class TankFrame extends Frame{

    public static int x = Constant.TankInitX;
    public static int y = Constant.TankInitY;

    public TankFrame() {
        this.setSize(Constant.FrameSizeWidth, Constant.FrameSizeHeight);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("坦克大战1.0");

        this.addKeyListener(new MyKeyListener());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g){
        if (x < 0 || x > 800)
        g.fillRect(x, y, 30, 30);
    }
}
