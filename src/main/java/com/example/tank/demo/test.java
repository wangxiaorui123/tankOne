package com.example.tank.demo;

/**
 * @author:wangxiaorui
 * @create 2020年6月4日22:43:39
 */
public class test {

    public static void test(int[] arr, int index){
        if (index < arr.length){
            int newIndex = index + 1;
            printf(arr);
            System.out.println(arr[index]);
            System.out.println(index);
            test(arr, newIndex);
        }
    }



    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int index = 0;
        test(arr, index);
    }

    public static void printf(int[] arr){
        for (int i:arr){
            System.out.print(i + ",");
        }
        System.out.println();
    }
}
