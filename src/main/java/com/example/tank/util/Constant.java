package com.example.tank.util;

import com.example.tank.enums.Dir;

/**
 * @Project : tank
 * @Package Name : com.example.tank.util
 * @Company home
 * @Author wangxiaorui
 * @Creation Date： 2020/7/5 14:29
 * @Description : 定义常量
 */
public class Constant {

     //窗口配置================================================================================================================================
    /**
     * 窗口宽度
     */
    public static final int FrameSizeWidth = 800;
    /**
     * 窗口高度
     */
    public static final int FrameSizeHeight = 600;


    //坦克默认配置=============================================================================================================================
    /**
     * 默认X坐标
     */
    public static final int tankDefaultX = 100;
    /**
     * 默认y坐标
     */
    public static final int tankDefaultY = 200;
    /**
     * 默认方向
     */
    public static final Dir tankDefaultDir = Dir.UP;


    //子弹默认配置=============================================================================================================================
    /**
     * 默认X坐标
     */
    public static final int bulletDefaultX = 100;
    /**
     * 默认y坐标
     */
    public static final int bulletDefaultY = 200;
    /**
     * 默认方向
     */
    public static final Dir bulletDefaultDir = Dir.UP;


    //其他配置================================================================================================================================
    /**
     * 窗口关闭状态
     */
    public static final int WindowClosingStatus = 0;
    /**
     * 缓冲画笔绘画坐标值
     */
    public static final int offScreenImageXAndY = 0;
}
