package com.xian.open.leetcode.middle;

import java.math.BigDecimal;

/**
 * @author xian
 * @description
 * @createTime 2021/10/12 15:35
 */
public class Solution01 {

    /**
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divide(int dividend, int divisor) {
        BigDecimal a = new BigDecimal(dividend);
        BigDecimal b = new BigDecimal(divisor);
        BigDecimal divide = a.divide(b,BigDecimal.ROUND_DOWN);
        double i = divide.doubleValue();
        if (i > Integer.MAX_VALUE || i < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) i;
    }

    public static void main(String[] args) {
        int divide = divide(1, 2);
        System.out.println(divide);
    }

}
