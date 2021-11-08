package com.xian.open.leetcode.middle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author xian
 * @description
 * @createTime 2021/10/22 8:26
 */
public class Solution05 {

    public static void main(String[] args) {
        // 面向过程解法,c语言可以参考这部分代码，是一样的
        int n = 10;
        int[] num = new int[n];
        // 前三年都没有生
        num[0] = 1;
        num[1] = 1;
        num[2] = 1;
        for (int i = 3; i < num.length; i++) {
            // 当年的数量等于前第三年的数量加上去年的数量
            num[i] = num[i-1] + num[i-3];
        }
        System.out.println(num[n-1]);

        /**
         *
         */

        // 面向对象解法
        // 抽象的牛栏
        List<Cow> cows = new ArrayList<>();
        // 第一头牛添加到牛栏
        Cow cow = new Cow();
        cow.age = 1;
        cows.add(cow);
        // 从第二年开始
        for (int i = 2; i <= n; i++) {
            for (Cow cow1 : new HashSet<>(cows)) {
                // 每年为每头牛都加一岁
                cow1.addAge();
                // 判断年龄是否到了4
                if (cow1.age >= 4) {
                    // 生出一个一岁的小牛，放到牛栏里
                    Cow cow2 = new Cow();
                    cow2.age = 1;
                    cows.add(cow2);
                }
            }
        }
        System.out.println(cows.size());
    }

    static class Cow {
        int age;
        public void addAge() {
            age++;
        }
    }
}
