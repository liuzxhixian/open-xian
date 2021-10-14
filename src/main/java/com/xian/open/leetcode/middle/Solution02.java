package com.xian.open.leetcode.middle;

import java.util.*;

/**
 * @author xian
 * @description
 * @createTime 2021/10/12 18:02
 */
public class Solution02 {


    /**
     * 无重复字符的最长子串
     * abcabcbc    -->    abc
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){
                // 进入这里，表示第二次遇到该字符，则需要移动最左边的坐标变量
                left = Math.max(left,map.get(s.charAt(i)) + 1);
            }
            // 放入map中， key 为符号，value为下标
            map.put(s.charAt(i),i);
            max = Math.max(max,i-left+1);
        }
        return max;

    }

    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] arr = new int[nums1.length + nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            arr[i] = nums1[i];
        }
        for (int i = 0; i < nums2.length; i++) {
            arr[nums1.length + i] = nums2[i];
        }
        Arrays.sort(arr);
        int length = nums1.length + nums2.length;
        double avg = 0.0;
        if (length % 2 == 0) {
            avg = (arr[length / 2 - 1] + arr[length / 2]) / 2.0;
        } else {
            avg = arr[length / 2];
        }
        return avg;
    }

    /**
     *
     * 外观数列
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n == 1) return "1";
        else {
            String lastStr = countAndSay(n - 1); // 1 2 1 1
            StringBuilder ans = new StringBuilder();
            int i = 0, j = 1, len = lastStr.length();
            while (j < len) {
                if (lastStr.charAt(i) != lastStr.charAt(j)) {
                    ans.append(j - i).append(lastStr.charAt(i));
                    i = j;
                }
                j++;
            }
            ans.append(j - i).append(lastStr.charAt(i));
            return ans.toString();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();

    }

    /**
     *
     写一个程序，输出从 1 到 n 数字的字符串表示。

     1. 如果 n 是3的倍数，输出“Fizz”；

     2. 如果 n 是5的倍数，输出“Buzz”；

     3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
     * @param n
     * @return
     */
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>(n);

        for (int i = 1; i <= n; i++) {
            int x = i % 3;
            int y = i % 5;
            if (x == 0 && y == 0) {
                list.add("FizzBuzz");
                continue;
            }
            if (x == 0) {
                list.add("Fizz");
                continue;
            }
            if (y == 0) {
                list.add("Buzz");
                continue;
            }
            list.add(String.valueOf(i));
        }
        return list;
    }


}
