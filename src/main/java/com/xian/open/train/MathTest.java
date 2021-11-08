package com.xian.open.train;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.SortedMap;

/**
 * @author xian
 * @description
 * @createTime 2021/10/20 10:33
 */
public class MathTest {

    public static void main(String[] args) {
       
//        double res = log(2, 32);
//        System.out.println(res);
//        res = log(2, 10);
//        System.out.println(res);
        double h = 8848.86;
        BigDecimal height = new BigDecimal(h);
        BigDecimal divide = height.divide(new BigDecimal(0.0002), RoundingMode.UP);
        double log = log(2, divide.doubleValue());
        BigDecimal bigDecimal = new BigDecimal(log);
        System.out.println(bigDecimal.setScale(0,RoundingMode.UP).toString());
//        double height = 0.0002 ; //初始厚度，单位转换为米
//
//        int count = 0;
//        while (height < 8848.86){
//            height = height  *  2;//折一下
//            count++; //计数
//        }
//        System.out.println("对折"+count+"次可达珠穆朗玛峰的高度");

    }

    public static double log(int basement, double n){
        return Math.log(n) / Math.log(basement);
    }
}
