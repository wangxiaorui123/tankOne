package com.example.tank.entrance;

import com.example.tank.entity.Tank;
import com.example.tank.enums.Dir;
import com.example.tank.enums.Group;
import com.example.tank.frame.TankFrame;

/**
 * @author:wangxiaorui
 * @create 2020年5月2日19:39:08
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        //初始化游戏页面
        TankFrame tank = new TankFrame();

        //初始化敌方坦克
        for (int i = 0; i < 7; i++){
            tank.enemyTankList.add(new Tank(30 + 80*i, 100, Dir.DOWN, tank, Group.Bad));
        }

        while (true){
            Thread.sleep(50);
            tank.repaint();
        }
//        int a = 8;
//        a = (a++)+a;
//        System.out.println(a);
    }
}
