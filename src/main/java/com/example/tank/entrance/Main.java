package com.example.tank.entrance;

import com.example.tank.frame.TankFrame;

/**
 * @author:wangxiaorui
 * @create 2020年5月2日19:39:08
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tank = new TankFrame();

        while (true){
            Thread.sleep(50);
            tank.repaint();
        }
    }
}
