package com.xian.open.leetcode.middle;

import java.util.*;

/**
 * @author xian
 * @description
 * @createTime 2021/10/13 11:16
 */
public class Solution03 {

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     * @param target
     * @return
     */
    public int jumpFloor(int target) {

        return 0;
    }


    class ListNode {
       int val;
       ListNode next = null;
    }



    /**
     *
     * @param head ListNode类 the head node
     * @return ListNode类
     */
    public ListNode sortInList (ListNode head) {
        // write code here
        if (head == null) return head;
        List<Integer> list = new ArrayList<>(100);
        ListNode now = head;
        while (now.next != null) {
            list.add(now.val);
            now = now.next;
        }
        list.sort((a,b) -> {return a-b;});
        ListNode index = head;
        int n = 0;
        while (now.next != null) {
            index.val = list.get(n++);
            index = index.next;
        }
        return index;
    }


    /**
     * 大家都知道斐波那契数列，现在要求输入一个正整数 n ，请你输出斐波那契数列的第 n 项。
     * 斐波那契数列是一个满足 fib(x)=\left\{ \begin{array}{rcl} 1 & {x=1,2}\\ fib(x-1)+fib(x-2) &{x>2}\\ \end{array} \right.fib(x)={
     * 1
     * fib(x−1)+fib(x−2)
     * ​
     *
     * x=1,2
     * x>2
     * ​
     *   的数列
     * 数据范围：1\leq n\leq 391≤n≤39
     * 要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n) ，本题也有时间复杂度 O(logn)O(logn) 的解法
     * @param n
     * @return
     */
    public static int Fibonacci(int n) {

        if (n == 1 || n == 2) return 1;
        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 1;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[n];
    }

    /**
     * 最大数
     * @param nums int整型一维数组
     * @return string字符串
     */
    public String solve (int[] nums) {
        // write code here
        ArrayList<String> list = new ArrayList<>();
        //将整型的数字转化为字符串
        for(int i = 0;i < nums.length;i ++){
            list.add(String.valueOf(nums[i]));
        }
        //排序
        Collections.sort(list,(a,b) -> {return (b + a).compareTo(a + b);});
        //这个地方需要注意如果第一个字符串已经是0了，那么直接输出0
        if(list.get(0).equals("0")) return "0";

        StringBuilder res = new StringBuilder(); //结果字符串
        for(int i = 0;i < list.size();i ++){//将排序好后的字符串一次相加就是最终的结果
            res.append(list.get(i));
        }
        return res.toString();
    }

    /**
     * 数组中出现次数超过一半的数字
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution(int [] array) {



        return 0;
    }
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
//        int fibonacci = Fibonacci(1);
//        System.out.println(fibonacci);
        String t = sc.nextLine();
        int integer = Integer.valueOf(t).intValue();
        String[] result = new String[integer];
        for (int i = 0; i < integer; i++) {
            String s = sc.nextLine();
            String[] strings = s.split("\\s+");
            double pow = (int) Math.pow(Double.valueOf(strings[0]), Double.valueOf(strings[1]));
            result[i] = String.valueOf((int) (pow % Double.valueOf(strings[2])));
        }
        for (String s : result) {
            System.out.println(s);
        }
    }


    /**
     * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
     *
     * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。 
     *
     * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combination-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    /**
     * @param candidates 候选数组
     * @param begin      搜索起点
     * @param len        冗余变量，是 candidates 里的属性，可以不传
     * @param target     每减去一个元素，目标值变小
     * @param path       从根结点到叶子结点的路径，是一个栈
     * @param res        结果集列表
     */
    private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < len; i++) {
            path.addLast(candidates[i]);

            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            dfs(candidates, i, len, target - candidates[i], path, res);

            // 状态重置
            path.removeLast();
        }
    }

    /**
     * 罗马转数字
     * @param s
     * @return
     */
    public int romanToInt(String s) {

        String temp = "I V X L C D M";

        return 0;
    }


}
