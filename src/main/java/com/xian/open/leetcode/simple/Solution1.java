package com.xian.open.leetcode.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xian
 * @description
 * @createTime 2021/10/12 11:04
 */
public class Solution1 {

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = {2,6,8,9,10};
        int k = 3;
        searchMinAbs(arr1,arr2,k);

    }

    public static void searchMinAbs(int[] arr1, int[] arr2,int k) {
        // 先排序
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int length1 = arr1.length;
        int length2 = arr2.length;
        // 设置两个数组的初始下标
        int i1 = 0, i2 = 0;
        // 创建存储满足条件的元素对的容器, 存放规则 “a,b” a为arr1中的元素，b为arr2中的元素
        List<String> results = new ArrayList<>(Math.max(length1,length2));
        while (i1 < length1 && i2 < length2) {
            if (Math.abs(arr1[i1] - arr2[i2]) < k) {
                results.add(arr1[i1]+","+arr2[i2]);
            }
            if (arr1[i1] > arr2[i2]) {
                i2++;
            } else {
                i1++;
            }
        }
        results.forEach(System.out::println);
    }


}
