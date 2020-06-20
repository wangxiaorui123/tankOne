package com.example.tank.demo;

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

    int x = 100;
    int y = 100;

    public TankFrame() {
        this.setSize(800, 600);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("坦克大战1.0");

        this.addKeyListener(new MykeyListener());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g){
        System.out.println("Paint...");
        g.fillRect(x, y, 50, 50);
        x += 100;
    }

    class MykeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("111");
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("222");
        }
    }

}
