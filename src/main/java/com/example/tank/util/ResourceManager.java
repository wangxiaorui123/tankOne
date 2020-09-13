package com.example.tank.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @Project : tank
 * @Package Name : com.example.tank.util
 * @Company home
 * @Author wangxiaorui
 * @Creation Date： 2020/7/18 15:22
 * @Description : 静态资源加载
 */
public class ResourceManager {
    //坦克的图片
    public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
    public static BufferedImage badTankL, badTankU, badTankR, badTankD;
    //子弹的图片
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;
    //爆炸效果
    public static BufferedImage[] explodes = new BufferedImage[16];

    //加载图片
    static {
        try {
            goodTankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);

            badTankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);

            bulletL = ImageIO.read(Objects.requireNonNull(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletL.gif")));
            bulletU = ImageIO.read(Objects.requireNonNull(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.gif")));
            bulletR = ImageIO.read(Objects.requireNonNull(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletR.gif")));
            bulletD = ImageIO.read(Objects.requireNonNull(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletD.gif")));

            for (int i = 0; i < 16; i++){
                explodes[i] = ImageIO.read(Objects.requireNonNull(ResourceManager.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
