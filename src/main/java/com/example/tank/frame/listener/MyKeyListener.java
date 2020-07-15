package com.example.tank.frame.listener;

import com.example.tank.frame.TankFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyListener extends KeyAdapter{
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode){
            case KeyEvent.VK_LEFT :
                TankFrame.x -= 10;break;
            case KeyEvent.VK_RIGHT :
                TankFrame.x += 10;break;
            case KeyEvent.VK_UP :
                TankFrame.y -= 10;break;
            case KeyEvent.VK_DOWN :
                TankFrame.y += 10;break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("222");
    }
}
